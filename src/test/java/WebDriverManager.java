import configurations.BrowserDriverManager;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import user.ApiStepsUser;

import static configurations.WebAddress.URL;

public abstract class WebDriverManager {
    public static WebDriver webDriver;
    public final ApiStepsUser apiStepsUser = new ApiStepsUser();

    @Before
    public void setup(){
        webDriver = BrowserDriverManager.getWebDriver();
        webDriver.get(URL);
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.baseURI = URL;
    }

    @After
    public void tearDown(){
        webDriver.quit();
    }
}