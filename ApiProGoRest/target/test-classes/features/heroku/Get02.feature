Feature: json place holder testing

  Background:
    Given user given api "https://jsonplaceholder.typicode.com"

  Scenario: Getting some information
    When user sends a GET request to "/todos/23"
    Then user validate status code should be 200
    And content type should be JSON
    And title is "et itaque necessitatibus maxime molestiae qui quas velit"
    And completed is false
    And userId is 2