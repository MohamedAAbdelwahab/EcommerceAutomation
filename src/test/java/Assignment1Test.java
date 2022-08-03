import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Assignment1Test {
    WebDriver driver;
    Utils util=new Utils();
    PrintStream out = System.out;
    HomePage homePage;
    LoginPage loginPage;
    @BeforeClass
    public void setup()
    {

    }
    @BeforeMethod
    public void reSetup()
    {
        driver=util.setup();
        driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/");
        this.homePage=new HomePage(driver);
        System.setOut(out);
    }

    @AfterMethod
    public void cleanUp(){
        driver.close();
    }
    @Test(priority = 1)
     public  void registerWithValidData()  {
        this.homePage.RegisterHeaderButton.click();
        WebElement genderRadioBtn=driver.findElement(By.id("gender-male"));
        genderRadioBtn.click();
        WebElement Firstname=driver.findElement(By.id("FirstName"));
        Firstname.sendKeys("Mohamed2");
        WebElement Lastname=driver.findElement(By.id("LastName"));
        Lastname.sendKeys("Abdelwahab2");
        Select Day=new Select(driver.findElement(By.name("DateOfBirthDay")));
        Day.selectByIndex(5);
        util.scrollUntilSelector(Day);
        Select Month=new Select(driver.findElement(By.name("DateOfBirthMonth")));
        Month.selectByIndex(5);
        util.scrollUntilSelector(Month);
        Select Year=new Select(driver.findElement(By.name("DateOfBirthYear")));
        Year.selectByIndex(5);
        util.scrollUntilSelector(Year);
        WebElement Email=driver.findElement(By.id("Email"));
        Email.sendKeys("Mohamed14@test.com");
        util.scrollUntil(Email);
        WebElement Company=driver.findElement(By.id("Company"));
        Company.sendKeys("fawry");
        util.scrollUntil(Company);
        WebElement password=driver.findElement(By.id("Password"));
        password.sendKeys("P@ssw0rd");
        util.scrollUntil(password);
        WebElement confirmPassword=driver.findElement(By.id("ConfirmPassword"));
        confirmPassword.sendKeys("P@ssw0rd");
        util.scrollUntil(confirmPassword);
        WebElement RegisterBtn=driver.findElement(By.id("register-button"));
        RegisterBtn.click();
        WebElement Result=driver.findElement(By.className("result"));
        util.scrollUntil(Result);
        String result=Result.getText().toString();
        Assert.assertEquals(result,"Your registration completed");
        WebElement logoutBtn=driver.findElement(By.className("ico-logout"));
        logoutBtn.click();
    }

    @Test(priority = 2)
    public void LoginWithValidData()
    {
        util.waitUntil(driver,By.className("ico-login"));
        this.homePage.LoginHeaderBtn.click();
        this.loginPage=new LoginPage(driver);
        this.loginPage.Email.sendKeys("Mohamed14@test.com");
        this.loginPage.Password.sendKeys("P@ssw0rd");
        this.loginPage.loginBtn.click();
    }

    @Test(priority = 3)
    public void ResetPassword()
    {
        util.waitUntil(driver,By.className("ico-login"));
        this.homePage.LoginHeaderBtn.click();
        this.loginPage=new LoginPage(driver);
        this.loginPage.forgetpassword.click();
        WebElement emailInputField= driver.findElement(By.id("Email"));
        emailInputField.sendKeys("Mohamed14@test.com");
        WebElement Recover=driver.findElement(By.cssSelector("button[class='button-1 password-recovery-button']"));
        Recover.click();
        WebElement Notification=driver.findElement(By.cssSelector("p[class='content']"));
        Assert.assertEquals(Notification.getText(),"Email with instructions has been sent to you.");
    }
    @Test(priority = 4)
    public void SearchItem()
    {
        LoginWithValidData();
        WebElement searchBar=driver.findElement(By.id("small-searchterms"));
        searchBar.sendKeys("adidas Consortium Campus 80s Running Shoes");
        WebElement SearchBtn=driver.findElement(By.cssSelector("button[class='button-1 search-box-button']"));
        SearchBtn.click();
        util.waitUntilPageLoad(driver);
        util.scrollUntil(driver.findElement(By.cssSelector("img[alt='Picture of adidas Consortium Campus 80s Running Shoes']")));
        Assert.assertTrue(driver.findElement(By.cssSelector("img[alt='Picture of adidas Consortium Campus 80s Running Shoes']")).isDisplayed());
    }

    @Test(priority = 5)
    public void ChangeCurrency()
    {
        SearchItem();
        Select CurrencySelector=new Select(driver.findElement(By.id("customerCurrency")));
        CurrencySelector.selectByIndex(1);
        util.waitUntilPageLoad(driver);
        util.scrollUntil(driver.findElement(By.cssSelector("span[class='price actual-price']")));
        Assert.assertTrue(driver.findElement(By.cssSelector("span[class='price actual-price']")).getText().contains("€"));
    }
    @Test(priority = 6)
    public void SelectDifferentCategories()
    {
        LoginWithValidData();
        WebElement electronics=driver.findElement(By.cssSelector("a[href='/apparel']"));
        util.HoverOnItemAndSelectSub(electronics,By.cssSelector("a[href='/shoes']"));
    }
    @Test(priority = 7)
    public void filterWithColor() throws InterruptedException {
        SelectDifferentCategories();
        WebElement redColor=driver.findElement(By.cssSelector("input[id='attribute-option-15']"));
        redColor.click();
        Thread.sleep(1000);
        List<WebElement> countOfProducts=driver.findElements(By.className("item-box"));
        Assert.assertEquals(countOfProducts.size(),1);
    }
    @Test(priority = 8)
    public void selectDifferentTags()  {
        SelectDifferentCategories();
        util.waitUntilPageLoad(driver);
        util.scrollUntil(driver.findElement(By.className("tags")));
        WebElement element= driver.findElement(By.cssSelector("a[href='/computer'"));
        element.click();

    }
    @Test(priority = 9)
    public void addDifferentProductsToShoppingCart() throws InterruptedException {
        SelectDifferentCategories();
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
        WebElement Notification=driver.findElement(By.cssSelector("p[class='content']"));
        Assert.assertEquals(Notification.getText(),"The product has been added to your shopping cart");

    }
    @Test(priority = 10)
    public void addDifferentProductswishlist() throws InterruptedException {
        SelectDifferentCategories();
        util.waitUntilPageLoad(driver);
        util.scrollUntil(driver.findElement(By.className("tags")));
        WebElement element= driver.findElement(By.cssSelector("button[class='button-2 add-to-wishlist-button']"));
        element.click();
        util.scrollUntil(driver.findElement(By.cssSelector("Select[name='product_attribute_9']")));
        Select select=new Select(driver.findElement(By.cssSelector("Select[name='product_attribute_9']")));
        select.selectByIndex(1);

        util.scrollUntil(driver.findElement(By.cssSelector("button[id='add-to-wishlist-button-25']")));
        driver.findElement(By.cssSelector("button[id='add-to-wishlist-button-25']")).click();
        WebElement Notification=driver.findElement(By.cssSelector("p[class='content']"));
        Assert.assertEquals(Notification.getText(),"The product has been added to your wishlist");

    }

    @Test(priority = 11)
    public void addDifferentProductsToCompareList() throws InterruptedException {
        SelectDifferentCategories();
        util.waitUntilPageLoad(driver);
        util.scrollUntil(driver.findElement(By.cssSelector("div[data-productid='24']")));
        WebElement compareButton=driver.findElement(By.cssSelector("button[onclick='return AjaxCart.addproducttocomparelist(\"/compareproducts/add/25\"),!1']"));
        WebElement compareButton2=driver.findElement(By.cssSelector("button[onclick='return AjaxCart.addproducttocomparelist(\"/compareproducts/add/24\"),!1']"));
        compareButton.click();
        WebElement Notification=driver.findElement(By.cssSelector("p[class='content']"));
        Assert.assertEquals(Notification.getText(),"The product has been added to your product comparison");
        compareButton2.click();
        Assert.assertEquals(Notification.getText(),"The product has been added to your product comparison");
    }
    @Test(priority = 12)
    public void CreateSuccessfulOrder() throws InterruptedException {
        addDifferentProductsToShoppingCart();
        util.waitUntilPageLoad(driver);
        driver.navigate().to("https://demo.nopcommerce.com/cart");
        util.waitUntilPageLoad(driver);
        util.scrollUntil(driver.findElement(By.id("termsofservice")));
        WebElement AgreeCheckBox=driver.findElement(By.id("termsofservice"));
        AgreeCheckBox.click();
        WebElement submit=driver.findElement(By.id("checkout"));
        submit.click();
        util.waitUntilPageLoad(driver);
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
        util.scrollUntil(driver.findElement(By.cssSelector("button[class='button-1 shipping-method-next-step-button']")));
        WebElement continueBtn2=driver.findElement(By.cssSelector("button[class='button-1 shipping-method-next-step-button']"));
        continueBtn2.click();
        util.scrollUntil(driver.findElement(By.cssSelector("button[class='button-1 payment-method-next-step-button']")));
        WebElement continueBtn3=driver.findElement(By.cssSelector("button[class='button-1 payment-method-next-step-button']"));
        continueBtn3.click();
        util.scrollUntil(driver.findElement(By.cssSelector("button[class='button-1 payment-info-next-step-button']")));
        WebElement continueBtn4=driver.findElement(By.cssSelector("button[class='button-1 payment-info-next-step-button']"));
        continueBtn4.click();
        util.scrollUntil(driver.findElement(By.cssSelector("button[class='button-1 confirm-order-next-step-button']")));
        WebElement continueBtn5=driver.findElement(By.cssSelector("button[class='button-1 confirm-order-next-step-button']"));
        continueBtn5.click();
    }
//
//    @Test(priority = 3)
//    public void ComposePage()
//    {
//        util.waitUntil(driver,By.cssSelector(".oj div textarea"));
//        WebElement tofield=driver.findElement(By.cssSelector(".oj div textarea"));
//        tofield.sendKeys("AutomationTesting12344@gmail.com");
//        util.waitUntil(driver,By.cssSelector(".aoD.az6 input"));
//        WebElement subjectfield=driver.findElement(By.cssSelector(".aoD.az6 input"));
//        subjectfield.sendKeys("Test Mail");
//        util.waitUntil(driver,By.cssSelector(".Ap .Ar.Au"));
//        WebElement emailBody=driver.findElement(By.cssSelector(".Ap .Ar.Au div"));
//        emailBody.sendKeys("Test Email Body");
//        WebElement send=driver.findElement(By.cssSelector(".gU.Up .J-J5-Ji.btA .dC div"));
//        send.click();
//        util.waitUntil(driver,By.cssSelector(".zA.zE .apU.xY"));
//        WebElement star=driver.findElement(By.cssSelector(".zA.zE .apU.xY"));
//        star.click();
//    }
//    @Test(priority = 4)
//    public void OpenEmail()
//    {
//        util.waitUntil(driver,By.cssSelector(".Cp div table tbody tr"));
//        WebElement email=driver.findElement(By.cssSelector(".Cp div table tbody tr"));
//        email.click();
//        WebElement body=driver.findElement(By.xpath("//*[contains(text(), 'Test Email Body')]"));
//        String bodystr =body.getText();
//
//        Assert.assertEquals(bodystr.substring(bodystr.indexOf("Test")),"Test Email Body");
//    }

}
