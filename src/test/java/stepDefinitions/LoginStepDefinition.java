package stepDefinitions;

import Pages.HomePage;
import Pages.LoginPage;
import Utils.Utils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

public class LoginStepDefinition {
    WebDriver driver;
    PrintStream out = System.out;
    HomePage homePage;
    LoginPage loginPage;
    Utils util=new Utils();
    @Given("user opens browser")
    public void user_open_browser()
    {


        System.setProperty("webdriver.chrome.driver", "C:\\Users\\mohamed.aabdelwahab\\Desktop\\chromedriver.exe");
        driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/");

        System.setOut(out);
    }
    @And("user navigates to login page")
    public void user_navigate_to_login()
    {
        this.homePage=new HomePage(driver);
        util.waitUntil(driver, By.className("ico-login"));
        this.homePage.LoginHeaderBtn.click();

    }
    @When("user enter valid username and password")
    public void user_enter_valid_username_and_password()
    {
        this.loginPage=new LoginPage(driver);
        this.loginPage.Email.sendKeys("Mohamed10@test.com");
        this.loginPage.Password.sendKeys("P@ssw0rd");
    }

    @And("user click on login button")
    public void user_click_login_button()
    {
        this.loginPage.loginBtn.click();
    }

    @Then("user could login successfully")
    public void user_login_successfully()
    {
        this.util.waitUntilPageLoad(driver);
        Assert.assertEquals(driver.getCurrentUrl(),"https://demo.nopcommerce.com/");
    }
}
