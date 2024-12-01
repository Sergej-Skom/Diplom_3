import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Test;
import screens.ScreenHome;
import screens.ScreenSignIn;
import screens.ScreenRegistration;
import user.AuthResponse;
import user.User;

import static java.net.HttpURLConnection.HTTP_ACCEPTED;
import static org.junit.Assert.assertTrue;

public class TestRegistration extends WebDriverManager {
    private final String name = RandomStringUtils.randomAlphabetic(6);
    private final String email = RandomStringUtils.randomAlphabetic(13) + "@" + "yandex.ru";
    private final String password6 = RandomStringUtils.randomAlphabetic(6);
    private final String password5 = RandomStringUtils.randomAlphabetic(5);

    @After
    public void deleteUser() {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password6);
        user.setName(name);
        AuthResponse authResponse = apiStepsUser.loginUser(user).extract().as(AuthResponse.class);
        String accessToken = authResponse.getAccessToken();
        if (accessToken != null) {
            apiStepsUser.deleteUser(accessToken).statusCode(HTTP_ACCEPTED);
        }
    }

    @Test
    @DisplayName("Осуществилась регистрация")
    public void testRegister() {
        ScreenHome screenHome = new ScreenHome(webDriver);
        screenHome.clickButtonPersonalAccount();

        ScreenSignIn screenSignIn = new ScreenSignIn(webDriver);
        screenSignIn.clickButtonRegistration();

        ScreenRegistration screenRegistration = new ScreenRegistration(webDriver);
        screenRegistration.inputLoginInfoUser(name, email, password6);

        screenSignIn.waitingScreenLoadLogin();
        screenSignIn.login(email, password6);

        assertTrue(screenHome.clickButtonMakeOrder());
    }

    @Test
    @DisplayName("Произошла ошибка пароля. Минимальный пароль — шесть символов.")
    public void testRegisterFault() {
        ScreenHome screenHome = new ScreenHome(webDriver);
        screenHome.clickButtonPersonalAccount();

        ScreenSignIn screenSignIn = new ScreenSignIn(webDriver);
        screenSignIn.clickButtonRegistration();

        ScreenRegistration screenRegistration = new ScreenRegistration(webDriver);
        screenRegistration.inputLoginInfoUser(name, email, password5);

        assertTrue(screenSignIn.messageErrorIsDisplayed());

    }
}
