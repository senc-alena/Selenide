package tests;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.AccountPage;
import pages.LoginPage;
import pages.HomePage;

import static com.codeborne.selenide.Selenide.*;

public class BaseTest {
    LoginPage loginPage;
    HomePage homePage;
    AccountPage accountPage;


    @BeforeMethod
    public void setup() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
        Configuration.headless = false;
        Configuration.baseUrl = "https://test.skyrexio.com/";
        Configuration.holdBrowserOpen = false;

        loginPage = new LoginPage();
        homePage = new HomePage();
        accountPage = new AccountPage();
    }

    @AfterMethod
    public void close() {
        clearBrowserCookies();
        closeWebDriver();
    }
}
