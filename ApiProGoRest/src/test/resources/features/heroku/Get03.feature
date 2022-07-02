Feature: Gorest co in testing

  Background:
    Given user given api "https://gorest.co.in"

  Scenario: Getting specific data
    When user sends a GET request to "/public/v1/users/3714"
    Then user validate status code should be 200
    Then user validates gorest user exist and sees information