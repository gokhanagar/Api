Feature: Gorest co in testing

  Background:
    Given user given api "https://gorest.co.in"

  Scenario: Getting specific data
    When user sends a GET request to "/public/v1/users/3714"
    Then user validate status code should be 200
    Then user validates gorest user exist and sees information


  Scenario: Getting specific data2
    When user sends a GET request to "/public/v1/users"
    Then the value of pagination limit is 20
    And  the current link should be "https://gorest.co.in/public/v1/users?page=1"
    And  the number of users should  be 20
    And  we have at least one "active" status
    And "Vaishvi Gill", "Dharmaketu Saini", "Gauranga Pandey" are among the users
    And the female users are more than male users

  Scenario: Getting specific data3
    When user sends a GET request to "/public/v1/users/13"
    Then user validate status code should be 200
    Then user validates gorest user body exist and sees information




