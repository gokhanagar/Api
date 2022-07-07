Feature: heroku app testing

  Background:
    Given user given api "https://restful-booker.herokuapp.com"

  Scenario: Getting specific booking1
    When user sends a GET request to "/booking/3"
    Then user validate status code should be 200
    And content type should be JSON
    And status line should be "HTTP/1.1 200 OK"


  Scenario: Getting specific booking2
    When user sends a GET request to "/booking/10001"
    Then user validate status code should be 404
    And status line should be "HTTP/1.1 404 Not Found"
    And response body contains "Not Found"
    And response body does not contain "Twitter"
    And server is "Cowboy"

  Scenario: Getting specific booking3
    When user sends a GET request to "/booking/7"
    Then user validate status code should be 200
    And content type should be JSON
    Then user validates booking exist and sees information

  Scenario: Getting specific booking4
    When user sends a GET request to "/booking/8"
    Then user validate status code should be 200
    And content type should be JSON
    Then user validates related booking exist and sees information






