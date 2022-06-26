package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.internal.common.assertion.Assertion;
import junit.framework.AssertionFailedError;
import org.junit.Assert;
import pages.ApiValidation;

public class ApiSteps extends ApiValidation {
    @Given("user given api {string}")
    public void userGivenApi(String url) {
        RestAssured.baseURI = url;
        System.out.println("user given api url " + url);
    }

    @Given("set api endpoint {string}")
    public void setApiEndpoint(String endpoint) {
        RestAssured.basePath = endpoint;
        //RestAssured.port=8000;

        System.out.println("set api endpoint " + endpoint);
    }


    @And("user creates new user with request body {string},{string},{string},{string}")
    public void userCreatesNewUserWithRequestBody(String name, String gender, String email, String status) {
      response =  postMethod(name,gender,email,status);
      response.prettyPrint();

    }



    @Then("validate the status code {int}")
    public void validateTheStatusCode(int expectedStatusCode) {

        int actualStatusCode = response.getStatusCode();
        try{
            Assert.assertEquals(expectedStatusCode,actualStatusCode);
        }catch(AssertionFailedError ae){
            ae.printStackTrace();
        }
        System.out.println("Assertion Succesful");
    }


    @And("validate the userId is not null")
    public void validateTheUserIdIsNotNull() {

        int id = response.jsonPath().getInt("id");
        Assert.assertTrue("id is empty", id !=0);
    }

    @And("validate the user Name is {string}")
    public void validateTheUserNameIs(String expectedName) {

        String actualName = response.jsonPath().getString("name");
        Assert.assertEquals(expectedName,actualName);
        System.out.println("Assertion successful " + actualName);

    }

    @And("validate the user Gender is {string}")
    public void validateTheUserGenderIs(String expectedGender) {

        String actualGender = response.jsonPath().getString("gender");
        Assert.assertEquals(expectedGender,actualGender);
        System.out.println("Assertion successful " + actualGender);

    }

    @And("validate the user Email is {string}")
    public void validateTheUserEmailIs(String expectedEmail) {

        String actualEmail = response.jsonPath().getString("email");
        Assert.assertEquals(expectedEmail,actualEmail);
        System.out.println("Assertion successful " + actualEmail);
    }

    @And("validate the user Status is {string}")
    public void validateTheUserStatusIs(String expectedStatus) {

        String actualStatus = response.jsonPath().getString("status");
        Assert.assertEquals(expectedStatus,actualStatus);
        System.out.println("Assertion successful " + actualStatus);
    }
}
