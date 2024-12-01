package screens;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ScreenSignIn {
    private final WebDriver webDriver;

    // локаторы следующих кнопок/полей/подсказок: "Email", "Пароль", "Войти", "Зарегистрироваться", "Восстановить пароль", "Некорректный пароль"
    private final By locatorButtonEmail = By.xpath(".//input[@class = 'text input__textfield text_type_main-default' and@type = 'text']");

    private final By locatorButtonPassword = By.xpath(".//input[@class = 'text input__textfield text_type_main-default' and@type = 'password']");

    private final By locatorButtonEnter = By.xpath(".//button[text()='Войти']");

    private final By locatorButtonRegistration = By.xpath(".//a[text()='Зарегистрироваться']");

    private final By locatorButtonRecoveryPassword = By.xpath(".//a[text()='Восстановить пароль']");

    private final By locatorInvalidPassword = By.xpath(".//p[text()='Некорректный пароль']");

    public ScreenSignIn(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Осуществление ввода учётных данных")
    public void login(String email, String password) {
        new WebDriverWait(webDriver, Duration.ofSeconds(13))
                .until(ExpectedConditions.elementToBeClickable (locatorButtonEmail));
        webDriver.findElement(locatorButtonEmail).sendKeys(email);
        webDriver.findElement(locatorButtonPassword).sendKeys(password);
        new WebDriverWait(webDriver, Duration.ofSeconds(13)).until(ExpectedConditions.elementToBeClickable (locatorButtonEnter));
        webDriver.findElement(locatorButtonEnter).click();
    }

    @Step("Кнопка Зарегистрироваться - нажать")
    public void clickButtonRegistration(){
        webDriver.findElement(locatorButtonRegistration).click();
    }

    @Step("Кнопка Восстановить пароль - нажать")
    public void clickButtonRecoveryPassword(){
        webDriver.findElement(locatorButtonRecoveryPassword).click();
    }

    @Step("Отображение поля email")
    public boolean displayingFieldEmail(){
        new WebDriverWait(webDriver, Duration.ofSeconds(13))
                .until(ExpectedConditions.elementToBeClickable (locatorButtonEmail));
        return webDriver.findElement(locatorButtonEmail).isDisplayed();
    }

    @Step("Ожидание полной загрузки страницы входа")
    public void waitingScreenLoadLogin() {
        new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Вход']")));
    }

    @Step("Проверка наличия ошибки при неверном пароле")
    public boolean messageErrorIsDisplayed (){
        return webDriver.findElement(locatorInvalidPassword).isDisplayed();
    }
}
