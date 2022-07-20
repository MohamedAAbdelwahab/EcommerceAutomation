import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    WebDriver driver;
    WebElement Email;
    WebElement Password;
    WebElement loginBtn;

    public LoginPage(WebDriver driver)
    {
        this.driver=driver;
        this.Email=driver.findElement(By.id("Email"));
        this.Password=driver.findElement(By.id("Password"));
        this.loginBtn=driver.findElement(By.cssSelector("button[class='button-1 login-button']"));
    }

}
