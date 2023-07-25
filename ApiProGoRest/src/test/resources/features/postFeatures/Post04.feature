Feature: dummy rest api testing

  Background:
    Given user given api "https://dummy.restapiexample.com"

  Scenario: Creating a specific booking1
    When I send POST request to "/api/v1/create"
    And user creates a booking



