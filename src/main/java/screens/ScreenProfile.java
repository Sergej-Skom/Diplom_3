package screens;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ScreenProfile {
    private final WebDriver webDriver;

    // локаторы следующих кнопок/полей: "Конструктор", "Логотип", "Вход"
    private final By locatorButtonDesigner = By.xpath(".//a[@class='AppHeader_header__link__3D_hX' and @href = '/']");

    private final By locatorButtonLogotype = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");

    private final By locatorButtonExit = By.xpath(".//button[text()='Выход']");

    public ScreenProfile(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Кнопка Конструктор - нажать")
    public void clickButtonDesigner(){
        webDriver.findElement(locatorButtonDesigner).click();
    }

    @Step("Кнопка Логотип - нажать")
    public void clickButtonLogotype(){
        webDriver.findElement(locatorButtonLogotype).click();
    }

    @Step("Кнопка Выход - нажать")
    public void clickButtonExit(){
        new WebDriverWait(webDriver, Duration.ofSeconds(13)).until(ExpectedConditions.elementToBeClickable (locatorButtonExit));
        webDriver.findElement(locatorButtonExit).click();
    }

    @Step("Отображение кнопки Выход")
    public boolean displayingButtonExit(){
        new WebDriverWait(webDriver, Duration.ofSeconds(13))
                .until(ExpectedConditions.elementToBeClickable (locatorButtonExit));
        return webDriver.findElement(locatorButtonExit).isDisplayed();
    }

}

