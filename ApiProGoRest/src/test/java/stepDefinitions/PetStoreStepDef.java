package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import pojos.PetStorePojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

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
        Assert.assertEquals(statusCode, actualStatusCode);
    }

    @Then("user validates pet exist and sees information")
    public void userValidatesPetExistAndSeesInformation() {

        Map<String, Object> deserializedResponse = response.as(new TypeRef<Map<String, Object>>() {});
        ArrayList<Map<String, Object>> tagValues2 = (ArrayList<Map<String, Object>>) deserializedResponse.get("tags");

        ArrayList<Map<String, Object>> tagValues = response.jsonPath().get("tags");
        Map<String, Object> categoryValues = response.jsonPath().get("category");

        List<String> photoUrlsList = response.jsonPath().get("photoUrls");
        List<String> photoUrlsList2 = (List<String>) deserializedResponse.get("photoUrls");


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
        System.out.println("================================================");

        //2. yol
        Map<String, Object> deserializedResponse2 = response.jsonPath().get("$");
        //$ isareti bizim yapimizin en temel seviyesini belirtir
        ArrayList<Map<String, Object>> tagValues3 = response.jsonPath().get("tags");
        Map<String, Object> categoryValues2 = response.jsonPath().get("category");
        String tagName = response.jsonPath().getString("tags.name");
        System.out.println(tagName);


        System.out.println(deserializedResponse2);
        System.out.println(deserializedResponse2.get("id"));
        System.out.println(tagValues3.get(0).get("id"));

        System.out.println("================================================");

        //3. yol
        PetStorePojo petStorePojo = response.as(PetStorePojo.class);


        System.out.println(petStorePojo.getStatus());
        System.out.println(petStorePojo.getTags().get(0));
        System.out.println(petStorePojo.getCategory());
        System.out.println(petStorePojo.getName());

        //4. way JsonPath
        JsonPath json= response.jsonPath();
        json.prettyPrint();

        assertEquals(13, json.getInt("id"));
        assertEquals(2, json.getInt("category.id"));
        assertEquals("bird", json.getString("category.name"));
        System.out.println(json.getString("category.name"));


        assertEquals("ross",json.getString("name"));
        assertEquals("https://www.birdlife.org/birds/eagle/",json.getString("photoUrls[0]"));

        assertEquals(1, json.getInt("tags[0].id"));
        assertEquals("eagle",json.getString("tags[0].name"));

        assertEquals("available",json.getString("status"));

    }
}
