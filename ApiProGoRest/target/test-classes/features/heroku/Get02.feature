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

  Scenario: Getting some information2
    When user sends a GET request to "/todos"
    Then user validate status code should be 200
    And content type should be JSON
    And there should be 200 todos
    And "quis eius est sint explicabo" should be one of the todos title
    And 2, 7, and 9 should be among the userIds

  Scenario: Getting some information3
    When user sends a GET request to "/todos"
    Then user validate status code should be 200
    And print all user ids greaater than 190 and assert that there are 10 ids greater than 190
    And print all user ids less than 5
    And print all titles whose ids are less than 5 Assert that "delectus aut autem" is one of the titles whose id is less than 5

  Scenario: Getting some information4
    When user sends a GET request to "/todos/2"
    Then user validate status code should be 200
    And userId is 1
    And title is "quis ut nam facilis et officia qui"
    And header via is "1.1 vegur"
    And header server is "cloudflare"


























