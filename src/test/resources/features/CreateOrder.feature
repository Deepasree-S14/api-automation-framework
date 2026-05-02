Feature: Create Order API

  @CreateOrder @Regression @requiresLogin
  Scenario: Create order successfully
    Given User has a product
    And User prepares create order payload
    When User calls "CreateOrderAPI" with "POST"
    Then API response status should be 201

  @CreateOrder @Negative
  Scenario: Create order without token
    Given User prepares create order payload without auth
    When User calls "CreateOrderAPI" with "POST"
    Then API response status should be 401
