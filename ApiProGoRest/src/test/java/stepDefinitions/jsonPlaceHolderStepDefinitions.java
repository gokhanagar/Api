package stepDefinitions;


import Data.HerOkuAppData;
import Data.JsonPlaceHolderTestData;
import io.cucumber.java.en.And;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        response = RestAssured.given().accept("application/json").when().get();
        json = response.jsonPath();
        assertEquals(userId,json.getInt("userId"));
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
        assertTrue("Expected title is not among them",titles.contains(title));

        //2. way
        assertTrue(titles.stream().anyMatch(t-> t.equals(title)));

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
}
