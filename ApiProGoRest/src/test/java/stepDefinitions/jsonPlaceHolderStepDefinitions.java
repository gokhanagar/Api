package stepDefinitions;


import Data.HerOkuAppData;
import Data.JsonPlaceHolderTestData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pojos.JsonPlaceHolderPojo;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class jsonPlaceHolderStepDefinitions {
    Response response;
    JsonPath json;

    @And("title is {string}")
    public void titleIs(String title) {

        response = RestAssured.given().accept("application/json").when().get();
        json = response.jsonPath();
        assertEquals(title, json.getString("title"));
    }

    @And("completed is false")
    public void completedIsFalse() {

        assertEquals(false, json.getBoolean("completed"));
    }


    @And("userId is {int}")
    public void useridIs(int userId) {
        response = RestAssured.given().accept("application/json").when().get();
        json = response.jsonPath();
        assertEquals(userId, json.getInt("userId"));
    }


    @And("there should be {int} todos")
    public void thereShouldBeTodos(int todos) {
        response = RestAssured.given().accept("application/json").when().get();
        json = response.jsonPath();

        assertEquals(todos, json.getList("id").size());
    }

    @And("{string} should be one of the todos title")
    public void shouldBeOneOfTheTodosTitle(String expTitle) {

        assertTrue(json.getList("title").contains(expTitle));
        System.out.println(json.getList("title").contains(expTitle));
    }

    @And("{int}, {int}, and {int} should be among the userIds")
    public void andShouldBeAmongTheUserIds(int userId1, int userId2, int userId3) {

        List<Integer> userIdList = json.getList("id");

        assertTrue(userIdList.contains(userId1));
        assertTrue(userIdList.contains(userId2));
        assertTrue(userIdList.contains(userId3));

    }

    @And("print all user ids greaater than {int} and assert that there are {int} ids greater than {int}")
    public void printAllUserIdsGreaaterThanAndAssertThatThereAreIdsGreaterThan(int yuzDoksan, int on, int yuzDoksann) {
        response = RestAssured.given().accept("application/json").when().get();
        json = response.jsonPath();

        List<Integer> userIdList = json.getList("findAll{it.id>190}.id");
        System.out.println(userIdList);
        assertEquals(on, userIdList.size());

    }

    @And("print all user ids less than {int}")
    public void printAllUserIdsLessThan(int five) {
        List<Integer> userIdList = json.getList("findAll{it.id<5}.id");
        System.out.println(userIdList);

    }

    @And("print all titles whose ids are less than {int} Assert that {string} is one of the titles whose id is less than {int}")
    public void printAllTitlesWhoseIdsAreLessThanAssertThatIsOneOfTheTitlesWhoseIdIsLessThan(int five, String title, int fivee) {
        List<String> titles = json.getList("findAll{it.id<5}.title");
        System.out.println(titles);

        //Assert that "delectus aut autem" is one of the titles whose id is less than 5
        //1.Way:
        assertTrue("Expected title is not among them", titles.contains(title));

        //2. way
        assertTrue(titles.stream().anyMatch(t -> t.equals(title)));

    }


    @And("header via is {string}")
    public void headerViaIs(String via) {
        assertEquals(via, response.getHeader("via"));


    }

    @And("header server is {string}")
    public void headerServerIs(String server) {
        assertEquals(server, response.getHeader("server"));
    }

    @And("user creates a booking and see information")
    public void userCreatesABookingAndSeeInformation() {

        //2.Step: Set the Expected Data
        JsonPlaceHolderTestData expectedData = new JsonPlaceHolderTestData();
        Map<String, Object> expectedDataMap = expectedData.expectedDataWithAllKeys(55, "Tidy your room", false);


        //3.Step: Send POST Request and get the Response
        response = RestAssured.given().contentType(ContentType.JSON).body(expectedDataMap).
                when().post();
        response.prettyPrint();

        //4.Step: Do Assertion
        Map<String, Object> actualDataMap = response.as(HashMap.class);

        assertEquals(expectedDataMap.get("userId"), actualDataMap.get("userId"));
        assertEquals(expectedDataMap.get("title"), actualDataMap.get("title"));
        assertEquals(expectedDataMap.get("completed"), actualDataMap.get("completed"));
    }


    @And("user creates a booking and see informationn")
    public void userCreatesABookingAndSeeInformationn() {

        //Set the expected data
        JsonPlaceHolderPojo expectedBody = new JsonPlaceHolderPojo(55, "Tidy your room", false);

        //Send Post request and get the response
        response = RestAssured.given().contentType(ContentType.JSON).body(expectedBody).when().post();
        response.prettyPrint();

        //Do assertions
        JsonPlaceHolderPojo actualBody = response.as(JsonPlaceHolderPojo.class);


        assertEquals(expectedBody.getUserId(), actualBody.getUserId());
        assertEquals(expectedBody.getTitle(), actualBody.getTitle());
        assertEquals(expectedBody.getCompleted(), actualBody.getCompleted());


    }
    //=================================================================================
    // Put method

    @When("I send PUT request to {string}")
    public void iSendPUTRequestTo(String endPoint) {

        RestAssured.basePath = endPoint;
        System.out.println(endPoint);
    }

    @And("user updates a request and sees information")
    public void userUpdatesARequestAndSeesInformation() {

        //Set the expected data
        JsonPlaceHolderTestData expectedData = new JsonPlaceHolderTestData();
        Map<String, Object> expectedDataMap = expectedData.expectedDataWithAllKeys(21, "Wash the dishes", false);

        //Send the put request
        response = RestAssured.given().contentType(ContentType.JSON).body(expectedDataMap).when().put();
        response.prettyPrint();

        //4.Step: Do Assertions
        Map<String, Object> actualDataMap = response.as(HashMap.class);

        assertEquals(expectedDataMap.get("userId"), actualDataMap.get("userId"));
        assertEquals(expectedDataMap.get("completed"), actualDataMap.get("completed"));
        assertEquals(expectedDataMap.get("title"), actualDataMap.get("title"));

    }
    //====================================================================================
    //Patch method

    @When("I send PATCH request to {string}")
    public void iSendPATCHRequestTo(String endpoint) {

        RestAssured.basePath = endpoint;
    }

    @And("user update a request and see information")
    public void userUpdateARequestAndSeeInformation() {

        //Set the expected data
        JsonPlaceHolderTestData requestBody = new JsonPlaceHolderTestData();
        Map<String, Object> requestBodyMap = requestBody.expectedDataWithMissingKeys(null, "Wash the dishes", null);

        //3.Step: Send the PATCH Request and get the response
        response = RestAssured.given().accept("application/json").when().patch();
        response.prettyPrint();

        //4. Step: Do Assertions
        //1.Way:
        response.then().assertThat().statusCode(200).body("title", equalTo(requestBodyMap.get("title")));

        //When you do PATCH Assertion, just the data you updated should be asserted. But if someone insists on assert all parts do the following
        Map<String, Object> MapToAssertAllDetails = requestBody.expectedDataWithAllKeys(10, "Wash the dishes", true);
        response.then().assertThat().statusCode(200).body("title", equalTo(MapToAssertAllDetails.get("title")),
                "userId", equalTo(MapToAssertAllDetails.get("userId")),
                "completed", equalTo(MapToAssertAllDetails.get("completed")));

    }


    @When("I send DELETE request to the url {string}")
    public void iSendDELETERequestToTheUrl(String endPoint) {

        RestAssured.basePath = endPoint;
    }

    @Then("response body is")
    public void responseBodyIs() {

        //2.Step: Set the Expected Data
        Map<String, Object> expectedMap = new HashMap<>();

        //3.Step: Send DELETE Request and get the Response
        response = RestAssured.given().contentType(ContentType.JSON).when().delete();
        response.prettyPrint();

        //4.Step: Do Assertion
        //1.Way:
        Map<String, Object> actualMap = response.as(HashMap.class);
        response.then().assertThat().statusCode(200);
        assertEquals(expectedMap, actualMap);

        //2.Way:
        response.then().assertThat().statusCode(200);
        assertTrue(actualMap.size() == 0);
        assertTrue(actualMap.isEmpty());

            /*

            How to automate "DELETE Request" in API Testing?
            i)Create new data by using "POST Request"
            ii)Use "DELETE Request" to delete newly created data.

            Note: Do not use "DELETE Request" for the existing data, create your own data then delete it.
            */


    }
}
