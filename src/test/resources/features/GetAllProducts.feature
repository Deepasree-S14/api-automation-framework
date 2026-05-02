Feature: All Products API

@requiresLogin @ViewAllProduct @Regression
Scenario: View all product details

Given User prepares get all product request
When User calls "GetAllProductsAPI" with "POST"
Then API response status should be 200
And Response field "message" should be "All Products fetched Successfully"
