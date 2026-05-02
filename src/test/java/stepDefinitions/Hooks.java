package stepDefinitions;

import io.cucumber.java.Before;
import pojo.LoginResponse;
import utils.SpecBuilder;
import api.EcomAPIClient;

public class Hooks {

    // shared across steps
    public static String token;
    public static String userId;

    EcomAPIClient api = new EcomAPIClient();

    @Before("@requiresLogin")
    public void setupLogin() throws Exception {

        
    String email = SpecBuilder.getGlobalData("email");
    String password = SpecBuilder.getGlobalData("password");

    if (email == null || password == null) {
        throw new RuntimeException("Email or Password not found in global.properties");
    }

    LoginResponse res = api.login(email, password);

    token = res.getToken();
    userId = res.getUserId();
}
}
