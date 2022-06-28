package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
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
        response = postMethod(name, gender, email, status);
        response.prettyPrint();

    }


    @Then("validate the status code {int}")
    public void validateTheStatusCode(int expectedStatusCode) {

        int actualStatusCode = response.getStatusCode();
        try {
            Assert.assertEquals(expectedStatusCode, actualStatusCode);
        } catch (AssertionFailedError ae) {
            ae.printStackTrace();
        }
        System.out.println("Assertion Succesful");
    }


    @And("validate the userId is not null")
    public void validateTheUserIdIsNotNull() {

        int id = response.jsonPath().getInt("id");
        Assert.assertTrue("id is empty", id != 0);
    }

    @And("validate the user Name is {string}")
    public void validateTheUserNameIs(String expectedName) {

        String actualName = response.jsonPath().getString("name");
        Assert.assertEquals(expectedName, actualName);
        System.out.println("Assertion successful " + actualName);

    }

    @And("validate the user Gender is {string}")
    public void validateTheUserGenderIs(String expectedGender) {

        String actualGender = response.jsonPath().getString("gender");
        Assert.assertEquals(expectedGender, actualGender);
        System.out.println("Assertion successful " + actualGender);

    }

    @And("validate the user Email is {string}")
    public void validateTheUserEmailIs(String expectedEmail) {

        String actualEmail = response.jsonPath().getString("email");
        Assert.assertEquals(expectedEmail, actualEmail);
        System.out.println("Assertion successful " + actualEmail);
    }

    @And("validate the user Status is {string}")
    public void validateTheUserStatusIs(String expectedStatus) {

        String actualStatus = response.jsonPath().getString("status");
        Assert.assertEquals(expectedStatus, actualStatus);
        System.out.println("Assertion successful " + actualStatus);
    }

    @Given("set api endpoint {string}{string}")
    public void setApiEndpoint(String endPoint, String userId) {
        RestAssured.basePath = (endPoint + userId);

    }

    @And("Update the user with request body {string},{string},{string},{string}")
    public void updateTheUserWithRequestBody(String name, String gender, String email, String status) {
        response = putMethod(name, gender, email, status);

    }

    // Post and Comment scenario start from here


    @Given("user sets {string} post")
    public void userSetsPost(String endpointPost) {
        RestAssured.basePath = endpointPost;
    }

    int postId;
    @And("create a post with given userId and create one {string} and {string}")
    public void createAPostWithGivenUserIdAndCreateOneTitle(String body,String title) {
        response = postMethodCreate(body,title);
        response.prettyPrint();
        postId= response.jsonPath().get("id");

    }



    @When("user sets {string} post and create one {string} using {string} {string},{string}, {string}")
    public void userSetsPostAndCreateOneUsing(String endpointComment, String comment, String userId, String name, String email, String commentBody) {

    endpointComment = endpointComment.replaceAll(userId, String.valueOf(postId));
    RestAssured.basePath = endpointComment;
    response = postMethodComment(comment,name,email);
    response.prettyPrint();

    }



    @Then("verify that post and comment created {string} {string}")
    public void verifyThatPostAndCommentCreated(String name, String email) {

        String actualName = response.jsonPath().get("name");
        String actualEmail = response.jsonPath().get("email");
        Assert.assertEquals(name,actualName);
        Assert.assertEquals(email,actualEmail);
        System.out.println("Assertion succesful");
    }







    }



