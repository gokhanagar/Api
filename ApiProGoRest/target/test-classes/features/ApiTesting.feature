Feature:Api testing for CRUD operators

  Background:
    Given user given api "https://gorest.co.in/"

  Scenario Outline: POST-Create a new user
    Given set api endpoint "public/v2/users"
    And user creates new user with request body "<Name>","<Gender>","<Email>","<Status>"
    Then validate the status code 201
    And validate the userId is not null
    And validate the user Name is "<Name>"
    And validate the user Gender is "<Gender>"
    And validate the user Email is "<Email>"
    And validate the user Status is "<Status>"


    Examples:
      | Name       | Gender | Email            | Status |
      | John Doe 8 | male   | john23@gmail.com | active |





















