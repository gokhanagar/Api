Feature:Api testing for CRUD operators

  Background:
    Given user given api "https://gorest.co.in/"

  Scenario Outline: Create a  post
    Given user sets "<EndpointPost>" post
    And create a post with given userId and create one "<Body>" and "<Title>"
    When user sets "<EndpointComment>" post and create one "<Comment>" using "<userId>" "<name>","<email>", "<comment body>"
    Then verify that post and comment created "<name>" "<email>"


    Examples:
      | EndpointPost                   | EndpointComment                   | userId | Body              | Title                  | Comment       | name    | email         | comment body    |
      | public/v2/users/<userId>/posts | public/v2/users/<userId>/comments | 6396   | Body post message | This is my first title | first comment | My name | asd@gmail.com | This is comment |









