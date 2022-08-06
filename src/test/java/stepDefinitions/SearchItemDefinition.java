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

public class SearchItemDefinition {
    WebDriver driver;
    PrintStream out = System.out;
    HomePage homePage;
    LoginPage loginPage;
    Utils util=new Utils();
    @Given("user opens browser to search for item")
    public void User_Open_Browser()
    {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\mohamed.aabdelwahab\\Desktop\\chromedriver.exe");
        driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/");
        this.homePage=new HomePage(driver);
        System.setOut(out);
    }
    @And("user is logged in")
    public void userLogin()
    {
        util.waitUntil(driver,By.className("ico-login"));
        this.homePage.LoginHeaderBtn.click();
        this.loginPage=new LoginPage(driver);
        this.loginPage.Email.sendKeys("Mohamed15@test.com");
        this.loginPage.Password.sendKeys("P@ssw0rd");
        this.loginPage.loginBtn.click();
    }
    @When("user type item name")
    public void enter_item_name()
    {
        WebElement searchBar=driver.findElement(By.id("small-searchterms"));
        searchBar.sendKeys("adidas Consortium Campus 80s Running Shoes");
    }
    @And("user click on search")
    public void user_click_on_search()
    {
        WebElement SearchBtn=driver.findElement(By.cssSelector("button[class='button-1 search-box-button']"));
        SearchBtn.click();
        util.waitUntilPageLoad(driver);
    }
    @Then("user could find his item")
    public void user_find_item()
    {
        this.util.setDriver(driver);
        util.scrollUntil(driver.findElement(By.cssSelector("img[alt='Picture of adidas Consortium Campus 80s Running Shoes']")));
        Assert.assertTrue(driver.findElement(By.cssSelector("img[alt='Picture of adidas Consortium Campus 80s Running Shoes']")).isDisplayed());

    }
}
