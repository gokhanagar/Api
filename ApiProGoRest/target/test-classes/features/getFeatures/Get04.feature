Feature: dummy rest api testing

  Background:
    Given user given api "https://dummy.restapiexample.com"

  Scenario: Getting specific data
    When user sends a GET request to "/api/v1/employees"
    Then user validate status code should be 200
    And there are 24 employees
    And "Tiger Nixon" and "Garrett Winters" are among the employees
    And the greatest age is 66
    And the name of the lowest age is "Tatyana Fitzpatrick"
    And total salary of all employees is 6644770

















