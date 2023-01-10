package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import Data.GoRestTestData;
import pojos.GoRestPojo;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class GorestStepDefinitions {
    Response response;
    JsonPath json;

    @Then("user validates gorest user exist and sees information")
    public void userValidatesGorestUserExistAndSeesInformation() {

        //Set the Expected Data
        GoRestTestData dataKey = new GoRestTestData();
        Map<String, String> dataKeyMap = dataKey.dataKeyMap("Daevi Jain", "jain_daevi@bogisich.co", "female", "inactive");
        Map<String, Object> expectedDataMap = dataKey.expectedDataMap(null, dataKeyMap);

        //Send the Request and Get the Response
        response = RestAssured.given().accept("application/json").
                when().get();

        // Do assertion
        Map<String, Object> deserializedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });
        Map<String, Object> actualResponse = (Map<String, Object>) deserializedResponse.get("data");


        assertEquals(expectedDataMap.get("meta"), deserializedResponse.get("meta"));
        assertEquals(dataKeyMap.get("name"), actualResponse.get("name"));
        assertEquals(dataKeyMap.get("email"), actualResponse.get("email"));
        assertEquals(dataKeyMap.get("gender"), actualResponse.get("gender"));
        assertEquals(dataKeyMap.get("status"), actualResponse.get("status"));

    }


    @Then("the value of pagination limit is {int}")
    public void theValueOfPaginationLimitIs(int limit) {
        response = RestAssured.given().accept("application/json").
                when().get();

        json = response.jsonPath();

        assertEquals(limit, json.getInt("meta.pagination.limit"));

    }

    @And("the current link should be {string}")
    public void theCurrentLinkShouldBe(String currentLink) {
        assertEquals(currentLink, json.getString("meta.pagination.links.current"));

    }


    @And("the number of users should  be {int}")
    public void theNumberOfUsersShouldBe(int userNumber) {
        assertEquals(userNumber, json.getList("data").size());
        System.out.println(json.getList("data").size());
    }

    @And("we have at least one {string} status")
    public void weHaveAtLeastOneStatus(String status) {
        //1.way
        assertTrue(json.get("data.status").toString().contains("active"));

        //2.way
        assertTrue(json.getList("data.findAll{it.status == 'active'}.status").contains("active"));
    }

    @And("{string}, {string}, {string} are among the users")
    public void areAmongTheUsers(String userName1, String userName2, String userName3) {
        //1.way
        assertTrue(json.get("data.name").toString().contains(userName1));
        assertTrue(json.get("data.name").toString().contains(userName2));
        assertTrue(json.get("data.name").toString().contains(userName3));

        //2.way
        Set<String> users = new HashSet<String>();
        users.add(userName1);
        users.add(userName2);
        users.add(userName3);
        System.out.println(json.getList("data.findAll{it.name}.name"));
        assertTrue(json.getList("data.findAll{it.name}.name").containsAll(users));
    }

    @And("the female users are more than male users")
    public void theFemaleUsersAreMoreThanMaleUsers() {

        List<String> genderList = json.getList("data.gender");
        int numOfFemales = 0;

        for (String w : genderList) {

            if (w.equals("female")) {

                numOfFemales++;
            }
        }
        System.out.println(numOfFemales);
        assertTrue(numOfFemales > (genderList.size() - numOfFemales));


    }


    @Then("user validates gorest user body exist and sees information")
    public void userValidatesGorestUserBodyExistAndSeesInformation() {
        response = RestAssured.given().accept("application/json").when().get();

        //Set the expected data
        GoRestTestData dataKey = new GoRestTestData();
        Map<String, String> dataKeyMap = dataKey.dataKeyMap("Purushottam Mehra", "purushottam_mehra@kub.co", "male", "inactive");
        Map<String, Object> expectedDataMap = dataKey.expectedDataMap(null, dataKeyMap);

        //Do assertions
        GoRestPojo gorestPojo = response.as(GoRestPojo.class);
        System.out.println(gorestPojo.getData());

        assertEquals(dataKeyMap.get("name"), gorestPojo.getData().get("name"));
        assertEquals(dataKeyMap.get("email"), gorestPojo.getData().get("email"));
        assertEquals(dataKeyMap.get("gender"), gorestPojo.getData().get("gender"));
        assertEquals(dataKeyMap.get("status"), gorestPojo.getData().get("status"));
        assertEquals(expectedDataMap.get("meta"), gorestPojo.getMeta());

    }

}