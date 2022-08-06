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
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

public class CreateSuccessfulOrderStepDefinition {
    WebDriver driver;
    PrintStream out = System.out;
    HomePage homePage;
    LoginPage loginPage;
    Utils util=new Utils();
    WebElement subMenu;
    Actions actions;
    @Given("user opens browser to make successful order")
    public void User_Open_Browser()
    {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\mohamed.aabdelwahab\\Desktop\\chromedriver.exe");
        driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/");
        this.homePage=new HomePage(driver);
        System.setOut(out);
    }
    @And("user is logged in to make successful order")
    public void userLogin()
    {
        util.waitUntil(driver, By.className("ico-login"));
        this.homePage.LoginHeaderBtn.click();
        this.loginPage=new LoginPage(driver);
        this.loginPage.Email.sendKeys("Mohamed10@test.com");
        this.loginPage.Password.sendKeys("P@ssw0rd");
        this.loginPage.loginBtn.click();
    }
    @And("select  category to add products from")
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
    @And("user click on add to cart")
    public void user_add_first_product_to_his_Cart()
    {
        util.waitUntilPageLoad(driver);
        util.scrollUntil(driver.findElement(By.className("tags")));
        WebElement element= driver.findElement(By.cssSelector("button[class='button-2 product-box-add-to-cart-button']"));
        element.click();
        util.waitUntilPageLoad(driver);
        util.scrollUntil(driver.findElement(By.cssSelector("Select[name='product_attribute_9']")));
        Select select=new Select(driver.findElement(By.cssSelector("Select[name='product_attribute_9']")));
        select.selectByIndex(1);
        util.scrollUntil(driver.findElement(By.cssSelector("button[id='add-to-cart-button-25']")));
        driver.findElement(By.cssSelector("button[id='add-to-cart-button-25']")).click();

    }
    @When("user accept terms and condition")
    public void click_on_agree_to_terms()
    {
        util.setDriver(driver);
        util.waitUntilPageLoad(driver);
        driver.navigate().to("https://demo.nopcommerce.com/cart");
        util.waitUntilPageLoad(driver);
        util.scrollUntil(driver.findElement(By.id("termsofservice")));
        WebElement AgreeCheckBox=driver.findElement(By.id("termsofservice"));
        AgreeCheckBox.click();
    }
    @And("user click on checkout")
    public void click_on_checkout()
    {
        WebElement submit=driver.findElement(By.id("checkout"));
        submit.click();
        util.waitUntilPageLoad(driver);

    }
    @And("user enter his billing address")
    public void enter_billing_address()
    {
        util.scrollUntil(driver.findElement(By.id("BillingNewAddress_CountryId")));
        Select country=new Select(driver.findElement(By.id("BillingNewAddress_CountryId")));
        country.selectByValue("123");
        util.scrollUntil(driver.findElement(By.id("BillingNewAddress_City")));
        WebElement city=driver.findElement(By.id("BillingNewAddress_City"));
        city.sendKeys("cairo");
        util.scrollUntil(driver.findElement(By.id("BillingNewAddress_Address1")));
        WebElement Address1=driver.findElement(By.id("BillingNewAddress_Address1"));
        Address1.sendKeys("cairo");
        util.scrollUntil(driver.findElement(By.id("BillingNewAddress_ZipPostalCode")));
        WebElement ZipCode=driver.findElement(By.id("BillingNewAddress_ZipPostalCode"));
        ZipCode.sendKeys("02002");
        util.scrollUntil(driver.findElement(By.id("BillingNewAddress_PhoneNumber")));
        WebElement phoneNumber=driver.findElement(By.id("BillingNewAddress_PhoneNumber"));
        phoneNumber.sendKeys("01006264343");
        util.scrollUntil(driver.findElement(By.cssSelector("button[class='button-1 new-address-next-step-button']")));
        WebElement continueBtn=driver.findElement(By.cssSelector("button[class='button-1 new-address-next-step-button']"));
        continueBtn.click();
    }
    @And("user select shipping method")
    public void user_select_shipping_method()
    {
        util.scrollUntil(driver.findElement(By.cssSelector("button[class='button-1 shipping-method-next-step-button']")));
        WebElement continueBtn2=driver.findElement(By.cssSelector("button[class='button-1 shipping-method-next-step-button']"));
        continueBtn2.click();
    }

    @And("user choose his payment method")
    public void user_choose_payment_method()
    {
        util.scrollUntil(driver.findElement(By.cssSelector("button[class='button-1 payment-method-next-step-button']")));
        WebElement continueBtn3=driver.findElement(By.cssSelector("button[class='button-1 payment-method-next-step-button']"));
        continueBtn3.click();
    }
    @And("user check his payment info")
    public void user_check_his_payment_info()
    {
        util.scrollUntil(driver.findElement(By.cssSelector("button[class='button-1 payment-info-next-step-button']")));
        WebElement continueBtn4=driver.findElement(By.cssSelector("button[class='button-1 payment-info-next-step-button']"));
        continueBtn4.click();
    }
    @And("user click on confirm payment")
    public void user_click_confirm_payment()
    {
        util.scrollUntil(driver.findElement(By.cssSelector("button[class='button-1 confirm-order-next-step-button']")));
        WebElement continueBtn5=driver.findElement(By.cssSelector("button[class='button-1 confirm-order-next-step-button']"));
        continueBtn5.click();
    }
    @Then("user see his order number on the screen")
    public void user_see_his_order_number()
    {
        Assert.assertTrue(driver.findElement(By.className("order-number")).getText().contains("ORDER NUMBER"));

    }

}
