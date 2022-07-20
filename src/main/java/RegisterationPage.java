import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class RegisterationPage {
    WebDriver driver;

    WebElement genderRadioBtn;
    WebElement Firstname;
    WebElement Lastname;
    Select Day;
    Select Month;
    Select Year;
    WebElement Email;
    WebElement Company;
    WebElement password;
    WebElement confirmPassword;
    WebElement RegisterBtn;

    public RegisterationPage(WebDriver driver)
    {
        this.driver=driver;
        this.genderRadioBtn=driver.findElement(By.id("gender-male"));
        this.Firstname=driver.findElement(By.id("FirstName"));
        this.Lastname=driver.findElement(By.id("LastName"));
        this.Day=new Select(driver.findElement(By.name("DateOfBirthDay")));
        this.Month=new Select(driver.findElement(By.name("DateOfBirthMonth")));
        this.Year=new Select(driver.findElement(By.name("DateOfBirthYear")));
        this.Email=driver.findElement(By.id("Email"));
        this.Company=driver.findElement(By.id("Company"));
        this.password=driver.findElement(By.id("Password"));
        this.confirmPassword=driver.findElement(By.id("ConfirmPassword"));
        this.RegisterBtn=driver.findElement(By.id("register-button"));
    }
}
