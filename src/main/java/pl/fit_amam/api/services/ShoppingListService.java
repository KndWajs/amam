package pl.fit_amam.api.services;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import pl.fit_amam.api.dto.*;
import pl.fit_amam.api.exceptions.EmptyRequiredFieldException;
import pl.fit_amam.api.exceptions.ObjectIdDoesNotExistsException;
import pl.fit_amam.api.exceptions.ObjectIsNullException;
import pl.fit_amam.api.mappers.ShoppingListMapper;
import pl.fit_amam.api.persistence.entities.ShoppingList;
import pl.fit_amam.api.persistence.repositories.ShoppingListDao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class ShoppingListService {
    private ShoppingListDao shoppingListDao;
    private ShoppingListMapper shoppingListMapper;
    private ShoppingElementService shoppingElementService;

    @Autowired
    public ShoppingListService(ShoppingListMapper shoppingListMapper, ShoppingElementService shoppingElementService,
                               ShoppingListDao shoppingListDao) {
        this.shoppingListMapper = shoppingListMapper;
        this.shoppingElementService = shoppingElementService;
        this.shoppingListDao = shoppingListDao;
    }

    public ShoppingListDTO create(ShoppingListDTO shoppingListDTO) throws ObjectIsNullException {
        validateShoppingListObject(shoppingListDTO);
        List<ShoppingElementDTO> shoppingElements = shoppingListDTO.getShoppingElements();

        shoppingListDTO.setShoppingElements(new ArrayList<>());

        ShoppingList shoppingList = shoppingListMapper.toEntity(shoppingListDTO);
        shoppingList.setCreationDate(new Timestamp(System.currentTimeMillis()));
        shoppingList.setUserName(UserService.getUserName());

        ShoppingListDTO savedShoppingList = shoppingListMapper.toDTO(shoppingListDao.getRepository().save(shoppingList));

        if (!(shoppingElements == null || shoppingElements.isEmpty())) {
            for (ShoppingElementDTO shoppingElementDTO : shoppingElements) {
                savedShoppingList.getShoppingElements().add(shoppingElementDTO);
            }
        }

        return update(savedShoppingList);
    }

    public ShoppingListDTO create(MenuDTO menuDTO) throws ObjectIsNullException {
        //TODO refactor this method
        ShoppingListDTO shoppingList = ShoppingListDTO.builder()
                .name(menuDTO.getName())
                .archival(false)
                .numberOfPeople(menuDTO.getNumberOfPeople())
                .shoppingElements(new ArrayList<>())
                .build();

        for (MenuMealDTO menuMeal : menuDTO.getMeals()) {
            for (MealIngredientDTO mealIngredient : menuMeal.getMeal().getIngredients()) {
                int shoppingElementIndex = shoppingList.getShoppingElements().stream()
                        .map(s -> s.getIngredient())
                        .collect(Collectors.toList()).indexOf(mealIngredient.getIngredient());

                if (shoppingElementIndex < 0) {
                    shoppingList.getShoppingElements().add(
                            ShoppingElementDTO.builder()
                                    .amount(mealIngredient.getAmount() * menuDTO.getNumberOfPeople())
                                    .alreadyBought(false)
                                    .ingredient(mealIngredient.getIngredient())
                                    .build());
                } else {
                    shoppingList.getShoppingElements().get(shoppingElementIndex).setAmount(
                            shoppingList.getShoppingElements().get(shoppingElementIndex).getAmount()
                                    + mealIngredient.getAmount() * menuDTO.getNumberOfPeople());
                }
            }
        }

        return create(shoppingList);
    }

    @Transactional(readOnly = true)
    public List<ShoppingListDTO> getAll(boolean archival) {
        List<ShoppingListDTO> shoppingListDTOS = new ArrayList<>();
        for (ShoppingList shoppingList : shoppingListDao.getShoppingListsByArchivalStatus(archival)) {
            shoppingListDTOS.add(shoppingListMapper.toDTO(shoppingList));
        }
        return shoppingListDTOS;
    }

    @Transactional(readOnly = true)
    public ShoppingListDTO get(Long id) {
        validateShoppingListId(id);
        return shoppingListMapper.toDTO(shoppingListDao.getRepository().findById(id).get());
    }

    public ShoppingListDTO update(ShoppingListDTO shoppingListDTO) {
        validateShoppingListObject(shoppingListDTO);
        validateShoppingListId(shoppingListDTO.getId());

        ShoppingList shoppingList = shoppingListMapper.toEntity(shoppingListDTO);
        shoppingList.setUpdateDate(new Timestamp(System.currentTimeMillis()));

        return shoppingListMapper.toDTO(shoppingListDao.getRepository().save(shoppingList));
    }

    public void delete(Long id) {
        validateShoppingListId(id);
        shoppingListDao.getRepository().deleteById(id);
    }

    public InputStream getPdfList(Long id) throws DocumentException {
        ShoppingListDTO shoppingList = this.get(id);

        Font font = FontFactory.getFont(FontFactory.COURIER, 12, BaseColor.BLACK);
        Chunk chunk = new Chunk("Name: " + shoppingList.getName(), font);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, byteArrayOutputStream);

        document.open();
        PdfPTable table = new PdfPTable(3);
        addTableHeader(table);
        addRows(table, shoppingList);
        document.add(chunk);
        document.add(table);
        document.close();

        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("Name", "Amount", "Unit")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(1);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private void addRows(PdfPTable table, ShoppingListDTO shoppingList) {

        for (ShoppingElementDTO element : shoppingList.getShoppingElements()) {
            table.addCell(element.getIngredient().getName());

            PdfPCell amountCell = new PdfPCell(new Phrase(element.getAmount().toString()));
            amountCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(amountCell);

            table.addCell(element.getIngredient().getIngredientUnit().getDescription());
        }
    }

    private void validateShoppingListId(Long shoppingListId) {
        if (shoppingListId == null || !shoppingListDao.getRepository().existsById(shoppingListId)) {
            throw new ObjectIdDoesNotExistsException(shoppingListId);
        }
    }

    private void validateShoppingListObject(ShoppingListDTO shoppingList) throws ObjectIsNullException {
        if (shoppingList == null) {
            throw new ObjectIsNullException(ShoppingListDTO.class.getName());
        }
        if (StringUtils.isEmpty(shoppingList.getName())) {
            throw new EmptyRequiredFieldException("Name");
        }
    }

}
