    Feature: Login API

@Login @Regression
Scenario Outline: Valid login
  Given User prepares login payload with "<email>" and "<password>"
  When User calls "LoginAPI" with "POST"
  Then API response status should be 200
  And Response should have field "token"

Examples:
  | email              | password   |
  | dpa14399@gmail.com | Tester#123 |


@Login @Negative
Scenario Outline: Invalid login
  Given User prepares login payload with "<email>" and "<password>"
  When User calls "LoginAPI" with "POST"
  Then API response status should be 400

Examples:
  | email           | password |
  | wrong@gmail.com | wrong123 |
  |                 | Tester#1 |               
