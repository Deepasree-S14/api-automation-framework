package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.Map;

import org.testng.Assert;

import api.EcomAPIClient;
import io.cucumber.java.en.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.APIResources;
import utils.ResponseUtils;
import utils.SpecBuilder;
import utils.TestData;

public class APISteps {

    RequestSpecification request;
    Response response;

    TestData data = new TestData();
    EcomAPIClient api = new EcomAPIClient();

    String productId;
    String orderId;

    // Common reusable file path
    String imagePath = System.getProperty("user.dir") + "/src/test/resources/testdata/image.jpg";

    // ================= LOGIN =================

    @Given("User prepares login payload with {string} and {string}")
    public void login_payload(String email, String password) throws Exception {
        request = given()
                .spec(SpecBuilder.getJsonSpec())
                .body(data.loginRequestPayload(email, password));
    }

    // ================= ADD PRODUCT =================

    @Given("User prepares add product request for {string}")
    public void add_product_by_name(String productName) throws Exception {

        request = given()
                .spec(SpecBuilder.getAuthSpec(Hooks.token))
                .multiPart("productImage", new File(imagePath));

        Map<String, String> map = data.getProductByName(productName, Hooks.userId);

        for (Map.Entry<String, String> entry : map.entrySet()) {
            request.param(entry.getKey(), entry.getValue());
        }
    }

    @Given("User prepares add product request without auth")
    public void add_product_no_auth() throws Exception {

        request = given()
                .spec(SpecBuilder.getBaseSpec())
                .multiPart("productImage", new File(imagePath));
    }

    // ================= CREATE ORDER =================

    @Given("User has a product")
    public void create_product_for_order() throws Exception {

        productId = api.addProduct(Hooks.token, Hooks.userId, imagePath);
    }

    @Given("User prepares create order payload")
    public void create_order_payload() throws Exception {

        request = given()
                .spec(SpecBuilder.getJsonAuthSpec(Hooks.token))
                .body(data.createOrderPayload(productId));
    }

    @Given("User prepares create order payload without auth")
    public void create_order_no_auth() throws Exception {

        request = given()
                .spec(SpecBuilder.getJsonSpec())
                .body(data.createOrderPayload("dummy"));
    }

    // ================= DELETE PRODUCT =================

    @When("User deletes product")
    public void delete_product() throws Exception {

        request = given()
                .spec(SpecBuilder.getAuthSpec(Hooks.token))
                .pathParam("productID", productId);

        response = request.when()
                .delete(APIResources.DeleteProductAPI.getResource());
    }

    @When("User deletes product with id {string}")
    public void delete_product_with_id(String productId) throws Exception {

        request = given()
                .spec(SpecBuilder.getAuthSpec(Hooks.token))
                .pathParam("productID", productId);

        response = request.when()
                .delete(APIResources.DeleteProductAPI.getResource());
    }

    // ================= GET ALL PRODUCTS =================

    @Given("User prepares get all product request")
    public void get_all_products_payload() throws Exception {

        request = given()
                .spec(SpecBuilder.getJsonAuthSpec(Hooks.token));
    }

    // ================= GET ORDER DETAILS =================

    @Given("User has created an order")
    public void user_has_created_order() throws Exception {

        productId = api.addProduct(Hooks.token, Hooks.userId, imagePath);

        String orderResponse = api.createOrder(Hooks.token, productId);

        orderId = new JsonPath(orderResponse).getString("orders[0]");
    }

    @When("User gets order details")
    public void get_order_details() throws Exception {

        request = given()
                .spec(SpecBuilder.getAuthSpec(Hooks.token))
                .queryParam("id", orderId);

        response = request.when()
                .get(APIResources.GetOrderDetailsAPI.getResource());
    }

    @When("User gets order details with id {string}")
    public void get_order_details_invalid(String id) throws Exception {

        request = given()
                .spec(SpecBuilder.getAuthSpec(Hooks.token))
                .queryParam("id", id);

        response = request.when()
                .get(APIResources.GetOrderDetailsAPI.getResource());
    }

    // ================= DELETE ORDER =================

    @When("User deletes order with id {string}")
    public void delete_order_with_id(String id) throws Exception {

        request = given()
                .spec(SpecBuilder.getAuthSpec(Hooks.token))
                .pathParam("orderId", id);

        response = request.when()
                .delete(APIResources.DeleteOrderAPI.getResource());
    }

    @When("User deletes order with id")
    public void delete_order() throws Exception {

        request = given()
                .spec(SpecBuilder.getAuthSpec(Hooks.token))
                .pathParam("orderId", orderId);

        response = request.when()
                .delete(APIResources.DeleteOrderAPI.getResource());
    }

    // ================= GENERIC CALL =================

    @When("User calls {string} with {string}")
    public void call_api(String resource, String method) {

        APIResources res = APIResources.valueOf(resource);

        if (method.equalsIgnoreCase("POST")) {
            response = request.when().post(res.getResource());
        } else if (method.equalsIgnoreCase("GET")) {
            response = request.when().get(res.getResource());
        } else if (method.equalsIgnoreCase("DELETE")) {
            response = request.when().delete(res.getResource());
        }
    }

    // ================= ASSERTIONS =================

    @Then("API response status should be {int}")
    public void verify_status(int code) {
        
        Assert.assertEquals(ResponseUtils.getStatusCode(response), code);
    }

    @Then("Response should have field {string}")
    public void verify_json_key(String key) {
   
        Assert.assertNotNull(ResponseUtils.getString(response, key));
    }

    @Then("Response should contain {string}")
    public void verify_response_contains(String expectedValue) {
        
        Assert.assertTrue(ResponseUtils.contains(response, expectedValue));
    }
    
    
    @Then("Response field {string} should be {string}")
    public void validateResponseField(String key, String expectedValue) {
        Assert.assertEquals(ResponseUtils.getString(response, key), expectedValue);
    }
}