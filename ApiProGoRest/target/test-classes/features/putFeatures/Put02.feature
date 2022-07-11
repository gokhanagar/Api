Feature: Json Place holder testing

  Background:
    Given user given api "https://dummy.restapiexample.com"

  Scenario:
    When I send PUT request to "/api/v1/update/21"
    And user update a request and sees information