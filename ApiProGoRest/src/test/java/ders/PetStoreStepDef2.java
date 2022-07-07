package ders;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pojos.PetStorePojo;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class PetStoreStepDef2 {

    Response response;
    @Given("user given api {string}")
    public void userGivenApi(String url) {

        RestAssured.baseURI = url;
        System.out.println("user given api url " + url);
    }

    @When("user sends a Get request to {string}")
    public void userSendsAGetRequestTo(String endPoint) {

        RestAssured.basePath = endPoint;
        response = RestAssured.given().accept("application/json").when().get();
        response.prettyPrint();
    }

    @Then("user validates status code should be {int}")
    public void userValidatesStatusCodeShouldBe(int statusCode) {

        int actualStatusCode = response.getStatusCode();
        assertEquals(statusCode, actualStatusCode);

    }

    @And("Content type should be JSON")
    public void contentTypeShouldBeJSON() {

        String actualContentType = response.contentType();
        assertEquals("application/json",actualContentType);


    }

    @Then("user validates pet exists and sees information")
    public void userValidatesPetExistsAndSeesInformation() {

    //Do assertions
        //1.way Type ref method

        Map<String, Object> deserializedResponse = response.as(new TypeRef<Map<String, Object>>() {});
        Map<String, Object> categoryValues = (Map<String, Object>) deserializedResponse.get("category");
        ArrayList<Map<String, Object>> tagValues = (ArrayList<Map<String, Object>>) deserializedResponse.get("tags");
        ArrayList<String> photoValues = (ArrayList<String>) deserializedResponse.get("photoUrls");


        assertEquals(13, deserializedResponse.get("id"));
        assertEquals(2, categoryValues.get("id"));
        assertEquals("bird", categoryValues.get("name"));

        assertEquals("ross", deserializedResponse.get("name"));
        assertEquals("https://www.birdlife.org/birds/eagle/", photoValues.get(0));

        assertEquals(1, tagValues.get(0).get("id"));
        assertEquals("eagle", tagValues.get(0).get("name"));

        assertEquals("available", deserializedResponse.get("status"));

        //2. way JsonPath format
        JsonPath json = response.jsonPath();

        assertEquals(13, json.getInt("id"));
        assertEquals(2, json.getInt("category.id"));
        assertEquals("bird", json.getString("category.name"));

        assertEquals("ross", json.getString("name"));
        assertEquals("https://www.birdlife.org/birds/eagle/", json.getString("photoUrls[0]"));

        assertEquals(1, json.getInt("tags[0].id"));
        assertEquals("eagle", json.getString("tags[0].name"));

        assertEquals("available", json.getString("status"));

        //3. way pojo class

        PetStorePojo  petStorePojo = response.as(PetStorePojo.class);


        assertEquals(13, petStorePojo.getId());
        assertEquals(2, petStorePojo.getCategory().get("id"));
        assertEquals("bird", petStorePojo.getCategory().get("name"));

        assertEquals("ross", petStorePojo.getName());
        assertEquals("https://www.birdlife.org/birds/eagle/", petStorePojo.getPhotoUrls().get(0));

        assertEquals(1, petStorePojo.getTags().get(0).get("id"));
        assertEquals("eagle", petStorePojo.getTags().get(0).get("name"));

        assertEquals("available", petStorePojo.getStatus());

        

    }
}
