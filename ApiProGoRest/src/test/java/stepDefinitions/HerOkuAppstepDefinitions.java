package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;

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
        assertEquals(statusCode, actualStatusCode);

    }

    @And("content type should be JSON")
    public void contentTypeShouldBeJSON() {
        String actualContentType = response.getContentType();
        assertEquals("application/json; charset=utf-8", actualContentType);
    }

    @And("status line should be {string}")
    public void statusLineShouldBe(String statusLine) {
        assertEquals(statusLine, response.getStatusLine());

    }

    @And("response body contains {string}")
    public void responseBodyContains(String included) {

        assertTrue(response.asString().contains(included));

    }

    @And("response body does not contain {string}")
    public void responseBodyDoesNotContain(String excluded) {

        assertFalse(response.asString().contains(excluded));
    }

    @And("server is {string}")
    public void serverIs(String server) {

        assertEquals(server, response.getHeader("Server"));
    }


    @Then("user validates booking exist and sees information")
    public void userValidatesBookingExistAndSeesInformation() {

        Map<String, Object> deserializedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });
        Map<String, Object> actualData = (Map<String, Object>) deserializedResponse.get("bookingdates");
        System.out.println(deserializedResponse);

        assertEquals("James", deserializedResponse.get("firstname"));
        assertEquals("Brown", deserializedResponse.get("lastname"));
        assertEquals(111, deserializedResponse.get("totalprice"));
        assertEquals(true, deserializedResponse.get("depositpaid"));
        assertEquals("2018-01-01", actualData.get("checkin"));
        assertEquals("2019-01-01", actualData.get("checkout"));


    }
}
