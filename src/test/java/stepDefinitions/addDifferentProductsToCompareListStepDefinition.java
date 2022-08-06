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

public class addDifferentProductsToCompareListStepDefinition {
    WebDriver driver;
    PrintStream out = System.out;
    HomePage homePage;
    LoginPage loginPage;
    Utils util=new Utils();
    WebElement subMenu;
    Actions actions;
    @Given("user opens browser add products to his compare list")
    public void User_Open_Browser()
    {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\mohamed.aabdelwahab\\Desktop\\chromedriver.exe");
        driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/");
        this.homePage=new HomePage(driver);
        System.setOut(out);
    }
    @And("user is logged in to add products to his compare list")
    public void userLogin()
    {
        util.waitUntil(driver, By.className("ico-login"));
        this.homePage.LoginHeaderBtn.click();
        this.loginPage=new LoginPage(driver);
        this.loginPage.Email.sendKeys("Mohamed15@test.com");
        this.loginPage.Password.sendKeys("P@ssw0rd");
        this.loginPage.loginBtn.click();
    }
    @And("select  category to compare from")
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
    @When("user click on add to compare list on two products")
    public void add_product_to_compare_list(){
        util.waitUntilPageLoad(driver);
        util.scrollUntil(driver.findElement(By.cssSelector("div[data-productid='24']")));
        WebElement compareButton=driver.findElement(By.cssSelector("button[onclick='return AjaxCart.addproducttocomparelist(\"/compareproducts/add/25\"),!1']"));
        WebElement compareButton2=driver.findElement(By.cssSelector("button[onclick='return AjaxCart.addproducttocomparelist(\"/compareproducts/add/24\"),!1']"));
        compareButton.click();
        WebElement Notification=driver.findElement(By.cssSelector("p[class='content']"));
        Assert.assertEquals(Notification.getText(),"The product has been added to your product comparison");
        compareButton2.click();
    }
    @Then("user should see that there is a product in his compare list and success message")
    public void Seeing_results()
    {
        WebElement Notification=driver.findElement(By.cssSelector("p[class='content']"));
        Assert.assertEquals(Notification.getText(),"The product has been added to your product comparison");

    }
}
