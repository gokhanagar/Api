Feature: Json Place holder testing

  Background:
    Given user given api "https://jsonplaceholder.typicode.com"

  Scenario:
  When I send PUT request to "/todos/198"
  And user updates a request and sees information







