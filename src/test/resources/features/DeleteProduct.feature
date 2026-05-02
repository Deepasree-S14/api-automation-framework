Feature: Delete Product API

  @DeleteProduct @Regression @requiresLogin
  Scenario: Delete product successfully
    Given User has a product
    When User deletes product
    Then API response status should be 200
    And Response field "message" should be "Product Deleted Successfully"

  @requiresLogin @DeleteProduct @Negative
  Scenario: Delete product with invalid id
    When User deletes product with id "aaaaaaaaaaaaaaaaaaaaaaaa"
    Then API response status should be 400
