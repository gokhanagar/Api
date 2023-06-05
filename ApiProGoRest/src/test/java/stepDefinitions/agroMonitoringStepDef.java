package stepDefinitions;

import Data.AgroMonitoringApiTestData;

import io.cucumber.java.en.And;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class agroMonitoringStepDef {
    Response response;


    @And("user create a booking and see information")
    public void userCreateABookingAndSeeInformation() {

        //2.Step: Set the Expected Data
        AgroMonitoringApiTestData requestBody = new AgroMonitoringApiTestData();
        Map<String, Object> requestBodyMap = requestBody.requestBody();

        //3.Step: Send POST Request and get the Response
        response = RestAssured.given().accept("application/json").body(requestBody).
                when().post();
        response.prettyPrint();

        //RestAssured.given().accept("application/json").accept("stage").body().when().post();

        //4.Step: Do Assertion
        //4.Step: Do Assertions
        //1.Way:
        //Map<String, Object> actualDataMap = response.as(HashMap.class);

        //assertEquals(requestBodyMap.get("name"), actualDataMap.get("name"));
        //assertEquals(requestBodyMap.get("center"), actualDataMap.get("center"));
        //assertEquals(requestBodyMap.get("area"), actualDataMap.get("area"));
        //assertEquals(String.valueOf(requestBody.coordinates[0][0][0]), ((Map)((Map)actualDataMap.get("geo_json")).get("geometry")).get("coordinates").toString().substring(3, 12));
        //assertEquals(requestBody.geometrySetUp().get("type"), ((Map)((Map)actualDataMap.get("geo_json")).get("geometry")).get("type"));

        //2.Way:
        JsonPath json = response.jsonPath();

        assertEquals(json.getFloat("geo_json.geometry.coordinates[0][0][0]"), requestBody.coordinates[0][0][0], 0.0);
        assertEquals(json.getString("geo_json.geometry.type"), requestBody.geometrySetUp().get("type"));
        assertEquals(json.getString("geo_json.type"), requestBody.geo_jsonSetUp().get("type"));
        assertEquals(json.getJsonObject("geo_json.properties"), requestBody.geo_jsonSetUp().get("properties"));
        assertEquals(json.getString("name"), requestBodyMap.get("name"));
        assertEquals(json.getFloat("center[0]"), requestBody.center[0], 0.0);
        assertEquals(json.getFloat("center[1]"), requestBody.center[1], 0.0);

        //To assert "area" value you can use both of the followings
        assertEquals(json.get("area").toString(), requestBodyMap.get("area").toString());
        assertEquals(json.getDouble("area"), (Double) requestBodyMap.get("area"), 0.0);


    }
}
