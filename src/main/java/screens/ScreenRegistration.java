package screens;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ScreenRegistration {
    private final WebDriver webDriver;

    // локаторы следующих кнопок/полей: "Имя", "Email", "Пароль", "Зарегистрироваться", "Войти"
    private final By locatorButtonName = By.xpath(".//label[text()='Имя']/parent::div/input[@class = 'text input__textfield text_type_main-default']");

    private final By locatorButtonEmail = By.xpath(".//label[text()='Email']/parent::div/input[@class = 'text input__textfield text_type_main-default']");

    private final By locatorButtonPassword = By.xpath(".//input[@class = 'text input__textfield text_type_main-default' and@type = 'password']");

    private final By locatorButtonEnter = By.xpath(".//a[text()='Войти']");

    private final By locatorButtonRegistration = By.xpath(".//button[text()='Зарегистрироваться']");



    public ScreenRegistration(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Осуществление регистрации")
    public void inputLoginInfoUser(String name, String email,String password) {
        webDriver.findElement(locatorButtonName).sendKeys(name);
        webDriver.findElement(locatorButtonEmail).sendKeys(email);
        webDriver.findElement(locatorButtonPassword).sendKeys(password);
        webDriver.findElement(locatorButtonRegistration).click();
    }

    @Step("Кнопка Войти - нажать")
    public void clickLoginButton(){
        webDriver.findElement(locatorButtonEnter).click();
    }
}