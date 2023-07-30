Feature: Json Place holder testing

  Background:
    Given user given api "https://jsonplaceholder.typicode.com"

  Scenario:
    When I send DELETE request to the url "/todos/198"
    Then response body is
