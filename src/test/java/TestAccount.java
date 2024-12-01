import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import screens.ScreenHome;
import screens.ScreenSignIn;
import screens.ScreenProfile;
import user.AuthResponse;
import user.User;

import static java.net.HttpURLConnection.HTTP_ACCEPTED;
import static org.junit.Assert.assertTrue;

public class TestAccount extends WebDriverManager {
    String accessToken;

    @Before
    public void setUpUser(){
        User  user = new User();
        user.setEmail(RandomStringUtils.randomAlphabetic(13) + "@" + "yandex.ru");
        user.setPassword(RandomStringUtils.randomAlphabetic(6));
        user.setName(RandomStringUtils.randomAlphabetic(6));

        AuthResponse authResponse = apiStepsUser
                .registerUser(user)
                .extract().as (AuthResponse.class);
        accessToken = authResponse.getAccessToken();

        ScreenHome screenHome = new ScreenHome(webDriver);
        screenHome.clickButtonLogInAccount();

        ScreenSignIn screenSignIn = new ScreenSignIn(webDriver);
        screenSignIn.login(user.getEmail(), user.getPassword());
    }

    @After
    public void deleteUser(){
        apiStepsUser
                .deleteUser(accessToken)
                .statusCode(HTTP_ACCEPTED);
    }

    @Test
    @DisplayName("Осуществляется переход в Личный кабинет")
    public void testGoToPersonalAccount() {
        ScreenHome screenHome = new ScreenHome(webDriver);
        screenHome.clickButtonPersonalAccount();

        ScreenProfile screenProfile = new ScreenProfile(webDriver);
        assertTrue(screenProfile.displayingButtonExit());
    }

    @Test
    @DisplayName("Осуществляется переход на экран Конструктор через кнопку Конструктор")
    public void testGoConstructorPageByConstructorButton() {
        ScreenHome screenHome = new ScreenHome(webDriver);
        screenHome.clickButtonPersonalAccount();

        ScreenProfile screenProfile = new ScreenProfile(webDriver);
        screenProfile.clickButtonDesigner();

        assertTrue(screenHome.clickButtonMakeOrder());
    }

    @Test
    @DisplayName("Осуществляется переход на экран Конструктор через кнопку логотипа")
    public void testGoConstructorPageByLogo() {
        ScreenHome screenHome = new ScreenHome(webDriver);
        screenHome.clickButtonPersonalAccount();

        ScreenProfile screenProfile = new ScreenProfile(webDriver);
        screenProfile.clickButtonLogotype();

        assertTrue(screenHome.clickButtonMakeOrder());
    }

    @Test
    @DisplayName("Осуществляется выход из аккаунта")
    public void testLogout() {
        ScreenHome screenHome = new ScreenHome(webDriver);
        screenHome.clickButtonPersonalAccount();

        ScreenProfile screenProfile = new ScreenProfile(webDriver);
        screenProfile.clickButtonExit();

        ScreenSignIn screenSignIn = new ScreenSignIn(webDriver);
        assertTrue(screenSignIn.displayingFieldEmail());
    }
}
