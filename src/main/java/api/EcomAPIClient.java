package api;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.Map;

import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import pojo.LoginResponse;
import utils.APIResources;
import utils.SpecBuilder;
import utils.TestData;

public class EcomAPIClient {

    TestData data = new TestData();

    // 🔹 LOGIN
    public LoginResponse login(String email, String password) throws Exception {

        RequestSpecification req = given()
                .spec(SpecBuilder.getJsonSpec())
                .body(data.loginRequestPayload(email, password));

        return req.when()
                .post(APIResources.LoginAPI.getResource())
                .then()
                .extract()
                .as(LoginResponse.class);
    }

    // 🔹 ADD PRODUCT (Now fully dynamic)
    public String addProduct(String token, String userId, String imagePath) throws Exception {

        Map<String, String> productData = data.addProductPayload(userId);

        RequestSpecification req = given()
                .spec(SpecBuilder.getAuthSpec(token))
                .multiPart("productImage", new File(imagePath));

        // Add all form params dynamically
        for (Map.Entry<String, String> entry : productData.entrySet()) {
            req.param(entry.getKey(), entry.getValue());
        }

        String response = req.when()
                .post(APIResources.AddProductAPI.getResource())
                .then()
                .extract()
                .asString();

        return new JsonPath(response).getString("productId");
    }

    // 🔹 CREATE ORDER
    public String createOrder(String token, String productId) throws Exception {

        RequestSpecification req = given()
                .spec(SpecBuilder.getJsonAuthSpec(token))
                .body(data.createOrderPayload(productId));

        return req.when()
                .post(APIResources.CreateOrderAPI.getResource())
                .then()
                .extract()
                .asString();
    }

    // 🔹 DELETE PRODUCT
    public String deleteProduct(String token, String productId) throws Exception {

        RequestSpecification req = given()
                .spec(SpecBuilder.getAuthSpec(token))
                .pathParam("productID", productId);

        String response = req.when()
                .delete(APIResources.DeleteProductAPI.getResource())
                .then()
                .extract()
                .asString();

        return new JsonPath(response).getString("message");
    }


public String getOrderDetails(String token, String orderId) throws Exception {

    RequestSpecification req = given()
            .spec(SpecBuilder.getAuthSpec(token))
            .queryParam("id", orderId);

    return req.when()
            .get(APIResources.GetOrderDetailsAPI.getResource())
            .then()
            .extract()
            .asString();
}

public String deleteOrder(String token, String orderId) throws Exception {

    RequestSpecification req = given()
            .spec(SpecBuilder.getAuthSpec(token))
            .pathParam("orderId", orderId);

    return req.when()
            .delete(APIResources.DeleteOrderAPI.getResource())
            .then()
            .extract()
            .asString();
}

}