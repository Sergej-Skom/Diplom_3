package user;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class ApiStepsUser {
    public static final String API_AUTH_REGISTER = "/api/auth/register";
    public static final String API_AUTH_LOGIN = "/api/auth/login";
    public static final String API_AUTH_USER = "/api/auth/user";

    @Step("Осуществляется регистрация пользователя")
    public ValidatableResponse registerUser(User user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(API_AUTH_REGISTER)
                .then();
    }

    @Step("Осуществляется авторизация пользователя")
    public ValidatableResponse loginUser(User user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(API_AUTH_LOGIN)
                .then();
    }

    @Step("Осуществляется удаление пользователя")
    public ValidatableResponse deleteUser(String accessToken) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .header("authorization", accessToken)
                .when()
                .delete(API_AUTH_USER)
                .then();
    }

}
