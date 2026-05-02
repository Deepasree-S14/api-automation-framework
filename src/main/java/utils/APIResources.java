
package utils;

public enum APIResources {

    LoginAPI("/api/ecom/auth/login"),
    AddProductAPI("/api/ecom/product/add-product"),
    CreateOrderAPI("/api/ecom/order/create-order"),
    DeleteProductAPI("/api/ecom/product/delete-product/{productID}"),
	GetAllProductsAPI("/api/ecom/product/get-all-products"),
	GetOrderDetailsAPI("/api/ecom/order/get-orders-details"),
	DeleteOrderAPI("/api/ecom/order/delete-order/{orderId}");
	
	
    private String resource;

    APIResources(String resource) {
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }
}  

