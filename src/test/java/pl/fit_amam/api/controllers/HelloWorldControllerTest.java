package pl.fit_amam.api.controllers;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import pl.fit_amam.api.Globals;
import pl.fit_amam.api.base.AbstractIntegrationTestBase;

import static org.junit.Assert.assertEquals;

@Transactional
public class HelloWorldControllerTest extends AbstractIntegrationTestBase {

    @Value("${version}")
    private String version;

    private static final String INPUT_NAME = "AWS CodeStar";

    @Autowired
    private HelloWorldController controller;


    /**
     * Initializing variables before we run the tests.
     * Use @BeforeAll for initializing static variables at the start of the test class execution.
     * Use @BeforeEach for initializing variables before each test is run.
     */
//    @BeforeAll
//    static void setup() {
//        // Use as needed.
//    }

    /**
     * De-initializing variables after we run the tests.
     * Use @AfterAll for de-initializing static variables at the end of the test class execution.
     * Use @AfterEach for de-initializing variables at the end of each test.
     */
//    @AfterAll
//    static void tearDown() {
//        // Use as needed.
//    }

//    @Test
//    public void testGetAllIngredients() throws JSONException {
//        ArrayList response = (ArrayList) controller.getAllIngredients();
//        System.out.println(response);
//        assertNotNull(response);
//    }
//
//    @Test
//    public void testGetAllMeals() throws JSONException {
//        ArrayList response = (ArrayList) controller.getAllMeals();
//        System.out.println(response);
//        assertNotNull(response);
//    }
//
//    @Test
//    public void testGetMenu() throws JSONException {
//        ArrayList response = (ArrayList) controller.getMenu();
//        assertNotNull(response);
//    }
//
//    @Test
//    public void testGetMealsIngredients() throws JSONException {
//        ArrayList response = (ArrayList) controller.getMealsIngredients();
//        assertNotNull(response);
//    }

    @Test
    public void testGetRequest() throws JSONException {
        ResponseEntity responseEntity = controller.helloWorldGet();

        // Verify the response obtained matches the values we expect.
        JSONObject jsonObjectFromResponse = new JSONObject(responseEntity.getBody().toString());
        assertEquals("[API version: " + version + ", Rest version: " + Globals.API_VERSION + "]", jsonObjectFromResponse.get("Output"));
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }


    @Test
    public void testPostRequest() throws JSONException {
//        ResponseEntity responseEntity = controller.helloWorldPost(INPUT_NAME);
//
//        // Verify the response obtained matches the values we expect.
//        JSONObject jsonObjectFromResponse = new JSONObject(responseEntity.getBody().toString());
//        assertEquals(EXPECTED_RESPONSE_VALUE, jsonObjectFromResponse.get("Output"));
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}