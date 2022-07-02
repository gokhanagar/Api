package stepDefinitions;

import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class GorestStepDefinitions {
    Response response;

    @Then("user validates gorest user exist and sees information")
    public void userValidatesGorestUserExistAndSeesInformation() {

        response = RestAssured.given().accept("application/json").
                when().get();
        response.prettyPrint();


        JsonPath json = response.jsonPath();


        System.out.println(json.getString("data.id"));


       // Map<String, Object> deserializedResponse = response.as(new TypeRef<Map<String, Object>>() {});


    }



















}
