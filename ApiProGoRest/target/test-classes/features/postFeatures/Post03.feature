Feature: agro monitoring testing

  Background:
    Given user given api "http://api.agromonitoring.com"

  Scenario: Creating a specific booking1
    When I send POST request to "/agro/1.0/polygons?appid=f4ffe3b2ef1fcb3600ab1d7fbc88c2f0&duplicated=true"
    And user create a booking and see information








