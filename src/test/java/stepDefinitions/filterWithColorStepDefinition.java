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
import java.util.List;
import java.util.concurrent.TimeUnit;

public class filterWithColorStepDefinition {
    WebDriver driver;
    PrintStream out = System.out;
    HomePage homePage;
    LoginPage loginPage;
    Utils util=new Utils();
    WebElement subMenu;
    Actions actions;
    @Given("user opens browser to filter by color")
    public void User_Open_Browser()
    {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\mohamed.aabdelwahab\\Desktop\\chromedriver.exe");
        driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/");
        this.homePage=new HomePage(driver);
        System.setOut(out);
    }
    @And("user is logged in by valid data")
    public void userLogin()
    {
        util.waitUntil(driver, By.className("ico-login"));
        this.homePage.LoginHeaderBtn.click();
        this.loginPage=new LoginPage(driver);
        this.loginPage.Email.sendKeys("Mohamed15@test.com");
        this.loginPage.Password.sendKeys("P@ssw0rd");
        this.loginPage.loginBtn.click();
    }
    @When("user choose an category")
    public void hover_on_category()
    {
        util.setDriver(driver);
        util.waitUntilPageLoad(driver);
        WebElement electronics=driver.findElement(By.cssSelector("a[href='/apparel']"));
        actions = new Actions(driver);

        actions.moveToElement(electronics);

        subMenu = driver.findElement(By.cssSelector("a[href='/shoes']"));
        actions.moveToElement(subMenu);

        actions.click().build().perform();

    }
    @And("select filter by red color")
    public void filter_by_red() throws InterruptedException {
        WebElement redColor=driver.findElement(By.cssSelector("input[id='attribute-option-15']"));
        redColor.click();
        Thread.sleep(1000);
    }
    @Then("user should see the products with red colors only")
    public void user_see_the_results()
    {
        List<WebElement> countOfProducts=driver.findElements(By.className("item-box"));
        Assert.assertEquals(countOfProducts.size(),1);
    }

}
