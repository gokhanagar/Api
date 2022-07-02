package stepDefinitions;


import io.cucumber.java.en.And;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static java.lang.Boolean.parseBoolean;
import static org.junit.Assert.assertEquals;

public class jsonPlaceHolderStepDefinitions {
    Response response;
    JsonPath json;

    @And("title is {string}")
    public void titleIs(String title) {

        String actualTitle = response.jsonPath().getString("title");
        assertEquals(title,actualTitle);
    }

    @And("completed is false")
    public void completedIsFalse() {

        assertEquals(false,response.jsonPath().getBoolean("completed"));
    }


/*
    @And("userId is {int}")
    public void useridIs(int userId) {

        assertEquals(userId,json.getInt("userId"));
    }
    */
}
