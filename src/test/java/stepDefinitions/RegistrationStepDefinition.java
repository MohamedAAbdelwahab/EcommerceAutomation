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
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

public class RegistrationStepDefinition {
    WebDriver driver;
    PrintStream out = System.out;
    HomePage homePage;

    Utils util=new Utils();
    @Given("user opens browser To Register")
    public void user_open_browser()
    {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\mohamed.aabdelwahab\\Desktop\\chromedriver.exe");
        driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/");

        System.setOut(out);
    }
    @And("user navigates to registration page")
    public void user_navigates_to_registration_page()
    {
        this.homePage=new HomePage(driver);
        util.waitUntil(driver, By.className("ico-login"));

        this.homePage.RegisterHeaderButton.click();
    }
    @When("user enter valid data")
    public void user_enters_valid_data()
    {
        util=new Utils();
        this.util.setDriver(driver);
        WebElement genderRadioBtn=driver.findElement(By.id("gender-male"));
        genderRadioBtn.click();
        WebElement Firstname=driver.findElement(By.id("FirstName"));
        Firstname.sendKeys("Mohamed2");
        WebElement Lastname=driver.findElement(By.id("LastName"));
        Lastname.sendKeys("Abdelwahab2");
        Select Day=new Select(driver.findElement(By.name("DateOfBirthDay")));
        Day.selectByIndex(5);
        util.scrollUntilSelector(Day,driver);
        Select Month=new Select(driver.findElement(By.name("DateOfBirthMonth")));
        Month.selectByIndex(5);
        util.scrollUntilSelector(Month,driver);
        Select Year=new Select(driver.findElement(By.name("DateOfBirthYear")));
        Year.selectByIndex(5);
        util.scrollUntilSelector(Year,driver);
        WebElement Email=driver.findElement(By.id("Email"));
        Email.sendKeys("Mohamed10@test.com");
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

    }
    @And("user click on register")
    public void user_click_on_register()
    {
        WebElement RegisterBtn=driver.findElement(By.id("register-button"));
        RegisterBtn.click();
    }
    @Then("user could register successfully")
    public void use_could_regiser_successfully()
    {
        WebElement Result=driver.findElement(By.className("result"));
        util.scrollUntil(Result);
        String result=Result.getText().toString();
        Assert.assertEquals(result,"Your registration completed");

    }
}