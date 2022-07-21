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
        Email.sendKeys("Mohamed13@test.com");
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
        this.loginPage.Email.sendKeys("Mohamed13@test.com");
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
        emailInputField.sendKeys("Mohamed13@test.com");
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
