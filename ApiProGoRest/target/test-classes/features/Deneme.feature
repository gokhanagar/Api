Feature: Pet store testing

  Scenario: Getting specific pet
    Given user send Get request to "v2/pet/1247"
    Then user validate status codee is 200
    Then user validates pet exist and sees informations







