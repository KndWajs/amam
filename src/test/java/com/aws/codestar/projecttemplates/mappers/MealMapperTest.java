package com.aws.codestar.projecttemplates.mappers;

import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MealMapperTest {
//    private BuildingMapper buildingMapper;
//
//    @Mock
//    ContactDetailsMapper contactDetailsMapper;
//
//    @Mock
//    ApartmentCrudDAO apartmentCrudDAO;
//
//    @Before
//    public void setUp() {
//        buildingMapper = new BuildingMapper(contactDetailsMapper, apartmentCrudDAO);
//        when(contactDetailsMapper.toDTO(any())).thenReturn(ContactDetailsMapperTest.buildContactDetailsDTO());
//        when(contactDetailsMapper.toEntity(any())).thenReturn(ContactDetailsMapperTest.buildContactDetails());
//    }
//
//    @Test
//    public void shouldReturnBuildingDTOWhenMapBuildingEntity() {
//        // given
//        BuildingEntity buildingEntity = buildBuildingEntity();
//        BuildingDTO buildingDTO = buildBuildingDTO();
//
//        // when
//        BuildingDTO returnedBuildingDTO = buildingMapper.toDTO(buildingEntity);
//
//        // then
//        assertEquals(buildingDTO, returnedBuildingDTO);
//    }
//
//    @Test
//    public void shouldReturnBuildingEntityWhenMapBuildingDTO() {
//        // given
//        BuildingEntity buildingEntity = buildBuildingEntity();
//        BuildingDTO buildingDTO = buildBuildingDTO();
//
//        // when
//        BuildingEntity returnedBuildingEntity = buildingMapper.toEntity(buildingDTO);
//
//        // then
//        assertEquals(buildingEntity, returnedBuildingEntity);
//    }
//
//    @Test
//    public void shouldReturBuildingEntityWithNullsWhenMapBuildingDTOWithNulls() {
//        // given
//        BuildingEntity buildingEntity = buildBuildingEntityWithNulls();
//        BuildingDTO buildingDTO = buildBuildingDTOWithNulls();
//
//        // when
//        BuildingEntity returnedBuildingEntity = buildingMapper.toEntity(buildingDTO);
//
//        // then
//        assertEquals(buildingEntity, returnedBuildingEntity);
//    }
//
//
//    public  static BuildingEntity buildBuildingEntity() {
//        return BuildingEntityBuilder.builder()
//                .description("very nice building")
//                .contactDetails(ContactDetailsMapperTest.buildContactDetails())
//                .floorsNumber(4)
//                .elevator(true)
//                .apartmentsNumber(200)
//                .apartments(new ArrayList<>())
//                .build();
//    }
//
//    public  static BuildingDTO buildBuildingDTO() {
//        return BuildingDTOBuilder.builder()
//                .description("very nice building")
//                .contactDetails(ContactDetailsMapperTest.buildContactDetailsDTO())
//                .floorsNumber(4)
//                .elevator(true)
//                .apartmentsNumber(200)
//                .apartmentsId(new ArrayList<>())
//                .build();
//    }
//
//    public  static BuildingEntity buildBuildingEntityWithNulls() {
//        return BuildingEntityBuilder.builder()
//                .description(null)
//                .contactDetails(ContactDetailsMapperTest.buildContactDetailsWithNulls())
//                .floorsNumber(null)
//                .elevator(null)
//                .apartmentsNumber(null)
//                .apartments(new ArrayList<>())
//                .build();
//    }
//
//    public  static BuildingDTO buildBuildingDTOWithNulls() {
//        return BuildingDTOBuilder.builder()
//                .description(null)
//                .contactDetails(null)
//                .floorsNumber(null)
//                .elevator(null)
//                .apartmentsNumber(null)
//                .apartmentsId(null)
//                .build();
//    }
}