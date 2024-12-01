package screens;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ScreenHome {
    private final WebDriver webDriver;
    // локаторы следующих кнопок/полей/подсказок: "Личный кабинет", "Войти в аккаунт", "Булки", "Соусы", "Начинки", "Оформить заказ", "Меню - разделы"
    private final By locatorButtonPersonalAccount = By.xpath(".//a[@class = 'AppHeader_header__link__3D_hX' and @href='/account']");

    private final By locatorButtonLogInAccount = By.xpath(".//button[@class = 'button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']");

    private final By locatorButtonBuns = By.xpath(".//span[contains(text(),'Булки')]");

    private final By locatorButtonSauces = By.xpath(".//span[contains(text(),'Соусы')]");

    private final By locatorButtonToppings = By.xpath(".//span[contains(text(),'Начинки')]");

    private final By locatorButtonMakeOrder = By.xpath(".//button[text()='Оформить заказ']");

    private final By locatorMenuSections = By.xpath(".//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']");


    public ScreenHome(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Кнопка Личный кабинет - нажать")
    public void clickButtonPersonalAccount(){
        WebElement personalAccountButton = webDriver.findElement(locatorButtonPersonalAccount);
        personalAccountButton.click();
    }

    @Step("Кнопку Войти в аккаунт - нажать")
    public void clickButtonLogInAccount(){
        WebElement logInToAccountButton = webDriver.findElement(locatorButtonLogInAccount);
        logInToAccountButton.click();
    }

    @Step("Кнопка Конструктор - нажать")
    public void clickButtonDesignerBuns(){
        WebElement bunsConstructorButton = webDriver.findElement(locatorButtonBuns);
        bunsConstructorButton.click();
    }

    @Step("Кнопка Соусы - нажать")
    public void clickButtonSauces(){
        WebElement saucesConstructorButton = webDriver.findElement(locatorButtonSauces);
        saucesConstructorButton.click();
    }

    @Step("Кнопка Начинки - нажать")
    public void clickButtonToppings(){
        WebElement fillingsConstructorButton = webDriver.findElement(locatorButtonToppings);
        fillingsConstructorButton.click();
    }

    @Step("Кнопка Создать заказ - нажать")
    public boolean clickButtonMakeOrder(){
        new WebDriverWait(webDriver, Duration.ofSeconds(13))
                .until(ExpectedConditions.elementToBeClickable (locatorButtonMakeOrder));
        return webDriver.findElement(locatorButtonMakeOrder).isEnabled();
    }

    @Step("Осуществление проверки текущего раздела")
    public String checkingCurrentSection(){
        WebElement currentSection = webDriver.findElement(locatorMenuSections);
        return currentSection.getText();
    }
}
