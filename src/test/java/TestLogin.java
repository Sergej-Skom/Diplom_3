import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import screens.ScreenPasswordRecovery;
import screens.ScreenHome;
import screens.ScreenSignIn;
import screens.ScreenRegistration;
import user.AuthResponse;
import user.User;

import static java.net.HttpURLConnection.HTTP_ACCEPTED;
import static org.junit.Assert.assertTrue;

public class TestLogin extends WebDriverManager {
    private User user;
    String accessToken;

    @Before
    public void setUpUser(){
        user = new User();
        user.setEmail(RandomStringUtils.randomAlphabetic(10) + "@" + "gmail.com");
        user.setPassword(RandomStringUtils.randomAlphabetic(6));
        user.setName(RandomStringUtils.randomAlphabetic(6));

        AuthResponse authResponse = apiStepsUser
                .registerUser(user)
                .extract().as (AuthResponse.class);
        accessToken = authResponse.getAccessToken();

    }

    @After
    public void deleteUser(){
        apiStepsUser
                .deleteUser(accessToken)
                .statusCode(HTTP_ACCEPTED);
    }

    @Test
    @DisplayName("Осуществляется проверка входа после нажатия кнопки Войти в аккаунт")
    public void loginToAccountButtonTest () {
        ScreenHome screenHome = new ScreenHome(webDriver);
        screenHome.clickButtonLogInAccount();

        ScreenSignIn screenSignIn = new ScreenSignIn(webDriver);
        screenSignIn.login(user.getEmail(), user.getPassword());

        assertTrue(screenHome.clickButtonMakeOrder());
    }

    @Test
    @DisplayName("Осуществляется проверка входа после нажатия кнопки Личный кабинет")
    public void loginPersonalAccountButtonTest () {
        ScreenHome screenHome = new ScreenHome(webDriver);
        screenHome.clickButtonPersonalAccount();

        ScreenSignIn screenSignIn = new ScreenSignIn(webDriver);
        screenSignIn.login(user.getEmail(), user.getPassword());

        assertTrue(screenHome.clickButtonMakeOrder());
    }

    @Test
    @DisplayName("Осуществляется проверка входа в аккаунт после нажатия кнопки в форме регистрации")
    public void loginFromRegistrationButtonTest () {
        ScreenHome screenHome = new ScreenHome(webDriver);
        screenHome.clickButtonPersonalAccount();

        ScreenSignIn screenSignIn = new ScreenSignIn(webDriver);
        screenSignIn.clickButtonRegistration();

        ScreenRegistration screenRegistration = new ScreenRegistration(webDriver);
        screenRegistration.clickLoginButton();

        screenSignIn.login(user.getEmail(), user.getPassword());

        assertTrue(screenHome.clickButtonMakeOrder());
    }

    @Test
    @DisplayName("Осуществляется проверка входа в аккаунт после нажатия кнопки в форме восстановления пароля")
    public void loginFromRecoverPasswordTest () {
        ScreenHome screenHome = new ScreenHome(webDriver);
        screenHome.clickButtonPersonalAccount();

        ScreenSignIn screenSignIn = new ScreenSignIn(webDriver);
        screenSignIn.clickButtonRecoveryPassword();

        ScreenPasswordRecovery screenPasswordRecovery = new ScreenPasswordRecovery(webDriver);
        screenPasswordRecovery.clickLoginButton();

        screenSignIn.login(user.getEmail(), user.getPassword());

        assertTrue(screenHome.clickButtonMakeOrder());
    }
}
