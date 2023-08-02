Feature: agro monitoring testing

  Background:
    Given user given api "http://api.agromonitoring.com"

  Scenario: Creating a specific booking1
    When I send POST request to "/agro/1.0/polygons?appid=253ec4743133f9a097d88224f83dc785&duplicated=true"
    And user create a booking and see information



