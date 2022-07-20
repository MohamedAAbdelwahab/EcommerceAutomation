import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    WebDriver driver;
    WebElement RegisterHeaderButton;
    WebElement LoginHeaderBtn;

    public HomePage(WebDriver driver) {
        this.driver = driver;

        this.RegisterHeaderButton=driver.findElement(By.className("ico-register"));
        this.LoginHeaderBtn=driver.findElement(By.className("ico-login"));
    }

}
