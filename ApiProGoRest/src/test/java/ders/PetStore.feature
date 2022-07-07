Feature: Pet store testing

  Background:
    Given user given api "https://petstore.swagger.io"

  Scenario: Getting specific pet
    When user sends a Get request to "/v2/pet/13"
    Then user validates status code should be 200
    And Content type should be JSON
    Then user validates pet exists and sees information




