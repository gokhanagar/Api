Feature: Json Place holder testing

  Background:
    Given user given api "https://dummy.restapiexample.com"

  Scenario:
    When I send PATCH request to "/todos/19"
    And user update a request and see information