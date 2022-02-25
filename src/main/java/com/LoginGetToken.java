package com;
import com.model.Tokens;
import com.model.UserRegisterResponse;
import io.qameta.allure.Step;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;

public class LoginGetToken {

    @Step
    public void login(String email, String password) {
        Map<String, String> inputDataMap = new HashMap<>();
        inputDataMap.put("email", email);
        inputDataMap.put("password", password);

        String response = given()
                .spec(Base.getBaseSpec())
                .and()
                .body(inputDataMap)
                .when()
                .post("auth/login")
                .then()
                .log().all()
                .assertThat()
                .statusCode(SC_OK)
                .extract()
                .path("accessToken");

        if (response != null) {
            Tokens.setAccessToken(response.substring(7));
        }
    }

    @Step
    public void delete() {
        if (Tokens.getAccessToken() == null) {
            return;
        }
        given()
                .spec(Base.getBaseSpec())
                .auth().oauth2(Tokens.getAccessToken())
                .when()
                .delete("auth/user")
                .then()
                .statusCode(202);
    }
}
