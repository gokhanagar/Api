package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Map;

public class PetStoreStepDef {

    Response response;
    @Given("user send GET request to {string}")
    public void userSendGETRequestTo(String endPoint) {
        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = endPoint;

        response = RestAssured.given()
                .accept("application/json")
                .when().get();

    }

    @Then("user validate status code is {int}")
    public void userValidateStatusCodeIs(int statusCode) {

        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,actualStatusCode);
    }

    @Then("user validates pet exist and sees information")
    public void userValidatesPetExistAndSeesInformation() {

        Map<String, Object> deserializedResponse = response.as(new TypeRef<Map<String, Object>>(){});
        ArrayList<Map<String, Object>> tagValues2 = (ArrayList<Map<String, Object>>) deserializedResponse.get("tags");

        ArrayList<Map<String, Object>> tagValues = response.jsonPath().get("tags");
        Map<String, Object> categoryValues = response.jsonPath().get("category");

        System.out.println(deserializedResponse);
        System.out.println(deserializedResponse.get("id"));
        System.out.println(deserializedResponse.get("name"));
        System.out.println(deserializedResponse.get("category"));
        System.out.println(deserializedResponse.get("status"));
        System.out.println(deserializedResponse.get("tags"));
        System.out.println(deserializedResponse.get("photoUrls"));

        System.out.println(tagValues.get(0).get("name"));
        System.out.println(tagValues.get(0).get("id"));
        System.out.println(categoryValues.get("id"));
        System.out.println(categoryValues.get("name"));






    }
}
