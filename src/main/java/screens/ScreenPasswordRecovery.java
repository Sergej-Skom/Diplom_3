package screens;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ScreenPasswordRecovery {
    private final WebDriver webDriver;

    private final By loginButtonLocator = By.xpath(".//a[@class='Auth_link__1fOlj']");

    public ScreenPasswordRecovery(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Кнопка Войти - нажать")
    public void clickLoginButton(){
        WebElement loginButton = webDriver.findElement(loginButtonLocator);
        loginButton.click();
    }
}
