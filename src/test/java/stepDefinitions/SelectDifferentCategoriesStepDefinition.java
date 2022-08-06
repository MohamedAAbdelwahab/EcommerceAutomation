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
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

public class SelectDifferentCategoriesStepDefinition {
   public WebDriver driver;
    public PrintStream out = System.out;
    public HomePage homePage;
    public LoginPage loginPage;
    public Utils util=new Utils();
    public WebElement subMenu;
    public  Actions actions;
    @Given("user opens browser to change category")
    public void User_Open_Browser()
    {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\mohamed.aabdelwahab\\Desktop\\chromedriver.exe");
        driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/");
        this.homePage=new HomePage(driver);
        System.setOut(out);
    }
    @And("user is logged in with valid data")
    public void userLogin()
    {
        util.waitUntil(driver, By.className("ico-login"));
        this.homePage.LoginHeaderBtn.click();
        this.loginPage=new LoginPage(driver);
        this.loginPage.Email.sendKeys("Mohamed15@test.com");
        this.loginPage.Password.sendKeys("P@ssw0rd");
        this.loginPage.loginBtn.click();
    }
    @When("hover on category")
    public void hover_on_category()
    {

        util.setDriver(driver);
        util.waitUntilPageLoad(driver);
        WebElement electronics=driver.findElement(By.cssSelector("a[href='/apparel']"));
         actions = new Actions(driver);

        actions.moveToElement(electronics);

         subMenu = driver.findElement(By.cssSelector("a[href='/shoes']"));


    }
    @And("user select sub category")
     public void user_select_sub_category()
    {
        actions.moveToElement(subMenu);

        actions.click().build().perform();

    }

    @Then("user navigate to subcategory")
    public void user_navigated_to_sub_category()
    {
        Assert.assertEquals(driver.getCurrentUrl(),"https://demo.nopcommerce.com/shoes");
    }
}
