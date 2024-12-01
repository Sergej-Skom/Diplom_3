import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import screens.ScreenHome;

public class TestDesigner extends WebDriverManager {

    @Test
    @DisplayName("Осуществляется переход в раздел Соусы")
    public void testOpenSaucesSection() {
        ScreenHome screenHome = new ScreenHome(webDriver);
        screenHome.clickButtonSauces();
        Assert.assertEquals("Соусы", screenHome.checkingCurrentSection());
    }

    @Test
    @DisplayName("Осуществляется переход в раздел Булки")
    public void testOpenBunsSection() {
        ScreenHome screenHome = new ScreenHome(webDriver);
        screenHome.clickButtonToppings();
        screenHome.clickButtonDesignerBuns();
        Assert.assertEquals("Булки", screenHome.checkingCurrentSection());
    }

    @Test
    @DisplayName("Осуществляется переход в раздел Начинки")
    public void testOpenToppingsSection() {
        ScreenHome screenHome = new ScreenHome(webDriver);
        screenHome.clickButtonToppings();
        Assert.assertEquals("Начинки", screenHome.checkingCurrentSection());
    }
}
