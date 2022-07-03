package stepDefinitions;


import io.cucumber.java.en.And;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.junit.Assert.assertEquals;

public class jsonPlaceHolderStepDefinitions {
    Response response;
    JsonPath json;

    @And("title is {string}")
    public void titleIs(String title) {

        response = RestAssured.given().accept("application/json").when().get();
        json = response.jsonPath();
        assertEquals(title,json.getString("title"));
    }

    @And("completed is false")
    public void completedIsFalse() {

        assertEquals(false,json.getBoolean("completed"));
    }



    @And("userId is {int}")
    public void useridIs(int userId) {

        assertEquals(userId,json.getInt("userId"));
    }


}
