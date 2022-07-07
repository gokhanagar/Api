package stepDefinitions;

import Data.HerOkuAppData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pojos.HerOkuAppPojo;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class HerOkuAppstepDefinitions {
    Response response;

    @When("user sends a GET request to {string}")
    public void userSendsAGETRequestTo(String endpoint) {
        RestAssured.basePath = endpoint;
        System.out.println("set api endpoint " + endpoint);

        response = RestAssured.given().accept("application/json").
                when().get();
        response.prettyPrint();
    }

    @Then("user validate status code should be {int}")
    public void userValidateStatusCodeShouldBe(int statusCode) {
       int actualStatusCode = response.getStatusCode();
       assertEquals(statusCode,actualStatusCode);

    }

    @And("content type should be JSON")
    public void contentTypeShouldBeJSON() {

        String actualContentType = response.contentType();
        assertEquals("application/json; charset=utf-8",actualContentType);

    }

    @And("status line should be {string}")
    public void statusLineShouldBe(String statusLine) {

        assertEquals(statusLine,response.getStatusLine());
    }


    @And("response body contains {string}")
    public void responseBodyContains(String included) {

        assertTrue(response.asString().contains(included));
    }

    @And("response body does not contain {string}")
    public void responseBodyDoesNotContain(String exluded) {

        assertFalse(response.asString().contains(exluded));
    }

    @And("server is {string}")
    public void serverIs(String server) {

        assertEquals(server, response.getHeader("Server"));
    }

    @Then("user validates booking exist and sees information")
    public void userValidatesBookingExistAndSeesInformation() {

        //Set the expected data
        Map<String, Object> expectedData = new HashMap<String, Object>();
        expectedData.put("firstname", "Jim");
        expectedData.put("lastname", "Jackson");
        expectedData.put("totalprice", 626);
        expectedData.put("depositpaid", false);

        Map<String, Object> bookingdates = new HashMap<String, Object>();
        expectedData.put("checkin", "2019-02-15");
        expectedData.put("checkout", "2020-06-17");

        //===================================================================================

        response = RestAssured.given().contentType(ContentType.JSON).when().get();
        //1. way
        Map<String,Object> deserializedResponse = response.as(new TypeRef<Map<String, Object>>(){});
        Map<String,Object> actualResponse = (Map<String, Object>) deserializedResponse.get("bookingdates");
        System.out.println(deserializedResponse);

        assertEquals("Jim", deserializedResponse.get("firstname"));
        assertEquals("Jackson", deserializedResponse.get("lastname"));
        assertEquals(626, deserializedResponse.get("totalprice"));
        assertEquals(false, deserializedResponse.get("depositpaid"));

        assertEquals("2019-02-15",actualResponse.get("checkin"));
        assertEquals("2020-06-17",actualResponse.get("checkout"));

        //2. way
        JsonPath json = response.jsonPath();
        assertEquals(expectedData.get("firstname"), json.get("firstname") );
        assertEquals(expectedData.get("lastname"), json.get("lastname") );
        assertEquals(expectedData.get("totalprice"), json.get("totalprice") );
        assertEquals(expectedData.get("depositpaid"), json.get("depositpaid") );

        assertEquals(actualResponse.get("checkin"),json.get("bookingdates.checkin"));
        assertEquals(actualResponse.get("checkout"),json.get("bookingdates.checkout"));


    }

    @Then("user validates related booking exist and sees information")
    public void userValidatesRelatedBookingExistAndSeesInformation() {

        response = RestAssured.given().accept("application/json").when().get();
        //Set The expected Data
        HerOkuAppData herOkuAppData = new HerOkuAppData();
        Map<String,Object> dataKeyMap =  herOkuAppData.dataKeyMap("Eric","Wilson",493,true);
        Map<String,Object> dataMap = herOkuAppData.dataMap("2017-03-23","2019-03-05");
        //Do Assertions
        HerOkuAppPojo herokuapp = response.as(HerOkuAppPojo.class);
        assertEquals(dataKeyMap.get("firstname"),herokuapp.getFirstname());
        assertEquals(dataKeyMap.get("lastname"),herokuapp.getLastname());
        assertEquals(dataKeyMap.get("totalprice"),herokuapp.getTotalprice());
        assertTrue(herokuapp.getDepositpaid());
        System.out.println(herokuapp.getAdditionalneeds());
    }

    //====================================================================
    // Post method

    @When("I send POST request to {string}")
    public void iSendPOSTRequestTo(String endpoint) {
        //1.Step: Set the URL
      //  RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.basePath = endpoint;
        System.out.println("set api endpoint " + endpoint);

    }

    @And("user creates a booking and sees information")
    public void userCreatesABookingAndSeesInformation() {

        //2.Step: Set the Expected Data
        HerOkuAppData herOkuAppData = new HerOkuAppData();
        Map<String,Object> bookingDatesMap = herOkuAppData.dataMap("2020-09-09", "2020-09-21");
        Map<String, Object> expectedDataMap = herOkuAppData.expectedDataSetUp("Selim", "Ak", 11111, true, bookingDatesMap);

        //3.Step: Send POST Request and get the Response
        response = RestAssured.given().accept("application/json").body(expectedDataMap).
                when().post();
        response.prettyPrint();

        //4.Step: Do Assertion
        Map<String, Object> actualDataMap = response.as(HashMap.class);

        assertEquals(expectedDataMap.get("firstname"), ((Map)actualDataMap.get("booking")).get("firstname"));
        assertEquals(expectedDataMap.get("lastname"), ((Map)actualDataMap.get("booking")).get("lastname"));
        assertEquals(expectedDataMap.get("totalprice"), ((Map)actualDataMap.get("booking")).get("totalprice"));
        assertEquals(expectedDataMap.get("depositpaid"), ((Map)actualDataMap.get("booking")).get("depositpaid"));

        assertEquals(bookingDatesMap.get("checkin"), ((Map)((Map)actualDataMap.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(bookingDatesMap.get("checkout"), ((Map)((Map)actualDataMap.get("booking")).get("bookingdates")).get("checkout"));

    }


}
