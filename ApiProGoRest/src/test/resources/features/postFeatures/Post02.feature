Feature: heroku app testing

  Background:
    Given user given api "https://jsonplaceholder.typicode.com"

  Scenario: Creating a specific booking1
    When I send POST request to "/todos"
    And user creates a booking and see information