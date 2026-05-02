Feature: Delete Order API

  @DeleteOrder @Regression @requiresLogin
  Scenario: Delete order successfully
    Given User has created an order
    When User deletes order with id
    Then API response status should be 200
    And Response field "message" should be "Orders Deleted Successfully"

  @DeleteOrder @Negative @requiresLogin
  Scenario: Delete order with invalid format id
    When User deletes order with id "abc"
    Then API response status should be 500

  @DeleteOrder @Negative @requiresLogin
  Scenario: Delete order with non-existing id
    When User deletes order with id "aaaaaaaaaaaaaaaaaaaaaaaa"
    Then API response status should be 400
