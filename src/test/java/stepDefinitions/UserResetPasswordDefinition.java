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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

public class UserResetPasswordDefinition {
    WebDriver driver;
    PrintStream out = System.out;
    HomePage homePage;
    LoginPage loginPage;
    Utils util=new Utils();
    @Given("user opens browser to reset his password")
    public void user_opens_browser_to_reset_his_password()
    {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\mohamed.aabdelwahab\\Desktop\\chromedriver.exe");
        driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/");
        this.homePage=new HomePage(driver);
        System.setOut(out);
    }
    @And("user click on forget password button")
    public void user_click_on_forget_password(){
        util.waitUntil(driver, By.className("ico-login"));
        this.homePage.LoginHeaderBtn.click();
        this.loginPage=new LoginPage(driver);
        this.loginPage=new LoginPage(driver);
        this.loginPage.forgetpassword.click();
    }
    @When("user enter valid email")
    public void user_enter_valid_email()
    {
        WebElement emailInputField= driver.findElement(By.id("Email"));
        emailInputField.sendKeys("Mohamed15@test.com");

    }

    @And("user click on recover button")
    public void user_click_on_recover_button()
    {
        WebElement Recover=driver.findElement(By.cssSelector("button[class='button-1 password-recovery-button']"));
        Recover.click();

    }

    @Then("user could reset his password")
    public void confirmation_message()
    {
        WebElement Notification=driver.findElement(By.cssSelector("p[class='content']"));
        Assert.assertEquals(Notification.getText(),"Email with instructions has been sent to you.");

    }
}
