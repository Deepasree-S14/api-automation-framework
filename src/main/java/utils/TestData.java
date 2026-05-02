package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pojo.LoginRequest;
import pojo.OrderDetails;
import pojo.Orders;
public class TestData {
	
	public LoginRequest loginRequestPayload(String email, String password) {
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUserEmail(email);
		loginRequest.setUserPassword(password);
		return loginRequest;
	}
	
	
	public Orders createOrderPayload(String productId) {
	        OrderDetails order = new OrderDetails();
	        order.setCountry("India");
	        order.setProductOrderedId(productId);

	        List<OrderDetails> list = new ArrayList<>();
	        list.add(order);

	        Orders orders = new Orders();
	        orders.setOrders(list);

	        return orders;
	    }
	
	public Map<String, String> addProductPayload(String userID) {

	    Map<String, String> data = new HashMap<>();

	    data.put("productName", "Shirt");
	    data.put("productAddedBy", userID);
	    data.put("productCategory", "Dress");
	    data.put("productSubCategory", "Shirt");
	    data.put("productPrice", "500");
	    data.put("productDescription", "White Shirt");
	    data.put("productFor", "Women");

	    return data;
	}
	
	
	

	public Map<String, String> getProductByName(String name, String userID) {

	    List<Map<String, String>> allProducts = JsonDataReader.getProductData();

	    for (Map<String, String> product : allProducts) {
	        if (product.get("name").equalsIgnoreCase(name)) {
	            product.put("productAddedBy", userID);
	            return product;
	        }
	    }

	    throw new RuntimeException("Product not found: " + name);
	}
}

