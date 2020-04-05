package com.aws.codestar.projecttemplates.controllers;

import com.aws.codestar.projecttemplates.Globals;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class HelloWorldControllerTest {

    private static final String EXPECTED_RESPONSE_VALUE = "This API version: " + Globals.API_VERSION;
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
    @DisplayName("Basic test for GET request")
    public void testGetRequest() throws JSONException {
        ResponseEntity responseEntity = controller.helloWorldGet();

        // Verify the response obtained matches the values we expect.
        JSONObject jsonObjectFromResponse = new JSONObject(responseEntity.getBody().toString());
        assertEquals(EXPECTED_RESPONSE_VALUE, jsonObjectFromResponse.get("Output"));
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }


    @Test
    @DisplayName("Basic test for POST request")
    public void testPostRequest() throws JSONException {
//        ResponseEntity responseEntity = controller.helloWorldPost(INPUT_NAME);
//
//        // Verify the response obtained matches the values we expect.
//        JSONObject jsonObjectFromResponse = new JSONObject(responseEntity.getBody().toString());
//        assertEquals(EXPECTED_RESPONSE_VALUE, jsonObjectFromResponse.get("Output"));
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}