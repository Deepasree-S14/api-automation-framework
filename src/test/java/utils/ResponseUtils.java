package utils;

import io.restassured.response.Response;

public class ResponseUtils {

    // 🔹 Get value from JSON response
    public static String getString(Response response, String key) {
        return response.jsonPath().getString(key);
    }

    // 🔹 Get integer value
    public static int getInt(Response response, String key) {
        return response.jsonPath().getInt(key);
    }

    // 🔹 Get status code
    public static int getStatusCode(Response response) {
        return response.getStatusCode();
    }

    // 🔹 Check if response contains text
    public static boolean contains(Response response, String value) {
        return response.asString().contains(value);
    }
}
