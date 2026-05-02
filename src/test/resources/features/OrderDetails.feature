    Feature: Get Order Details API

@GetOrder @Regression @requiresLogin
Scenario: Get order details successfully
  Given User has created an order
  When User gets order details
  Then API response status should be 200
  And Response field "message" should be "Orders fetched for customer Successfully"

@GetOrder @Negative @requiresLogin
Scenario: Get order details with invalid id
  When User gets order details with id "aaaaaaaaaaaaaaaaaaaaaaaa"
  Then API response status should be 400
  And Response should contain "Order not found"     
