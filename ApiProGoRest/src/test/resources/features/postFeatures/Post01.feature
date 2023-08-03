Feature: heroku app testing

  Background:
    Given user given api "https://restful-booker.herokuapp.com"

  Scenario: Creating a specific booking1
    When I send POST request to "/booking"
    And user creates a booking and sees information







