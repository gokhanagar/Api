Feature: Pet store testing

  Scenario: Getting specific pet
    Given user send GET request to "/v2/pet/1246"
    Then user validate status code is 200
    Then user validates pet exist and sees information