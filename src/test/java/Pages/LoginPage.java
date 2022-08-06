package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    public  WebDriver driver;
    public  WebElement Email;
    public  WebElement Password;
    public  WebElement loginBtn;
    public  WebElement forgetpassword;
    public LoginPage(WebDriver driver)
    {
        this.driver=driver;
        this.Email=driver.findElement(By.id("Email"));
        this.Password=driver.findElement(By.id("Password"));
        this.loginBtn=driver.findElement(By.cssSelector("button[class='button-1 login-button']"));
        this.forgetpassword= driver.findElement(By.className("forgot-password"));
    }

}
