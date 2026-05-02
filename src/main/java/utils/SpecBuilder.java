package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.*;
import io.restassured.specification.RequestSpecification;

public class SpecBuilder {

    // Common reusable logging stream
	public static PrintStream log;

	static {
	    try {
	        // ensure logs folder exists
	        java.io.File dir = new java.io.File("logs");
	        if (!dir.exists()) {
	            dir.mkdirs();
	        }

	        // create readable timestamp log file
	        String fileName = "logs/log_" +
	                new java.text.SimpleDateFormat("yyyyMMdd_HHmmss")
	                        .format(new java.util.Date()) + ".txt";

	        log = new PrintStream(new FileOutputStream(fileName));

	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

    // 🔹 Base Spec (No headers)
    public static RequestSpecification getBaseSpec()  {
        return createBaseBuilder().build();
    }
    private static RequestSpecBuilder createBaseBuilder() {
        try {
            return new RequestSpecBuilder()
                    .setBaseUri(getGlobalData("baseURL"))
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    // 🔹 JSON Spec (for login, create order)
    public static RequestSpecification getJsonSpec()  {
        return createBaseBuilder()
                .setContentType("application/json")
                .build();
    }

    // 🔹 Auth Spec (dynamic token)
    public static RequestSpecification getAuthSpec(String token)  {
        return createBaseBuilder()
                .addHeader("Authorization", token)
                .build();
    }

    // 🔹 JSON + Auth Spec
    public static RequestSpecification getJsonAuthSpec(String token)  {
        return createBaseBuilder()
                .setContentType("application/json")
                .addHeader("Authorization", token)
                .build();
    }
    // 🔹 Properties Reader
    public static String getGlobalData(String key) throws Exception {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("src/test/resources/global.properties");
        prop.load(fis);
        return prop.getProperty(key);
    }
}