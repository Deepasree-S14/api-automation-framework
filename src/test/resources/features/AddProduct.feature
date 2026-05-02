Feature: Add Product API

  @AddProduct @Regression @requiresLogin
  Scenario: Add product successfully
    Given User prepares add product request for "Shirt"
    When User calls "AddProductAPI" with "POST"
    Then API response status should be 201
    And Response should have field "productId"

  @AddProduct @Negative
  Scenario: Add product without token
    Given User prepares add product request without auth
    When User calls "AddProductAPI" with "POST"
    Then API response status should be 401
