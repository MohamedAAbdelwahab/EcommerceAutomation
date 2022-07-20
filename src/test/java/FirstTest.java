import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class FirstTest {
    WebDriver driver;
    String email;
    String referenceNumber;
    int index;
    @BeforeClass
    private void setup()
    {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\moham\\Desktop\\chromedriver.exe");
        driver=new ChromeDriver();
    }
    @Test(priority=1)
    public void CreateAccount(){
        driver.get("http://automationpractice.com/index.php");
        driver.findElement(By.className("login")).click();
        WebElement element=driver.findElement(By.id("email_create"));
        scrollUntil(element);
        email=randomEmailGenerator();
        element.sendKeys(email+"@gmail.com");
        WebElement createAccountBtn=driver.findElement(By.id("SubmitCreate"));
        createAccountBtn.click();
        waitUntil(driver,By.id("id_gender1"));
        WebElement titleRadioBtn=driver.findElement(By.id("id_gender1"));
        titleRadioBtn.click();
        WebElement fName=driver.findElement(By.id("customer_firstname"));
        fName.sendKeys("Mohamed");
        WebElement lName=driver.findElement(By.id("customer_lastname"));
        lName.sendKeys("Seleem");
        element=driver.findElement(By.id("email"));
        element.click();
        WebElement password=driver.findElement(By.id("passwd"));
        password.sendKeys("P@ssw0rd");
        WebElement days=driver.findElement(By.id("days"));
        Select dropdownDays=new Select(days);
        dropdownDays.selectByIndex(1);
        WebElement months=driver.findElement(By.id("months"));
        Select dropdownMonths=new Select(months);
        dropdownMonths.selectByIndex(1);
        WebElement years=driver.findElement(By.id("years"));
        Select dropdownYears=new Select(years);
        dropdownYears.selectByIndex(1);
        WebElement newsletter=driver.findElement(By.id("newsletter"));
        newsletter.click();
        WebElement optin=driver.findElement(By.id("optin"));
        optin.click();
        WebElement company=driver.findElement(By.id("company"));
        scrollUntil(company);
        company.sendKeys("test");
        WebElement address1=driver.findElement(By.id("address1"));
        address1.sendKeys("test");
        WebElement city=driver.findElement(By.id("city"));
        city.sendKeys("test");
        WebElement state=driver.findElement(By.id("id_state"));
        Select selectState=new Select(state);
        selectState.selectByIndex(1);
        WebElement postcode=driver.findElement(By.id("postcode"));
        postcode.sendKeys("02002");
        WebElement phone_mobile=driver.findElement(By.id("phone_mobile"));
        scrollUntil(phone_mobile);
        phone_mobile.sendKeys("01006264343");
        WebElement submitAccountBtn=driver.findElement(By.id("submitAccount"));
        submitAccountBtn.click();
    }
    @Test(priority=2)
    public void subcategoryFromCategory() {
        WebElement header_logo=driver.findElement(By.id("header_logo"));
        header_logo.click();
        WebElement Women = driver.findElement(By.className("sf-with-ul"));
        Actions action = new Actions(driver);
        action.moveToElement(Women).perform();
        WebElement Blouses = driver.findElement(By.cssSelector("a[title=\"Blouses\"]"));
        Blouses.click();
        WebElement Blouse=driver.findElement(By.cssSelector("img[title=\"Blouse\"]"));
        scrollUntil(Blouse);
        Blouse.click();
    }
    @Test(priority=3)
    public void checkOutFn() {
        WebElement add_to_cart=driver.findElement(By.cssSelector("p[id=\"add_to_cart\"]"));
        scrollUntil(add_to_cart);
        add_to_cart.click();
        waitUntil(driver,By.cssSelector("a[class=\"btn btn-default button button-medium\"]"));
        WebElement proceedToCheckOutBtn1=driver.findElement(By.cssSelector("a[class=\"btn btn-default button button-medium\"]"));
        scrollUntil(proceedToCheckOutBtn1);
        proceedToCheckOutBtn1.click();
        waitUntil(driver,By.cssSelector("a[class=\"button btn btn-default standard-checkout button-medium\"]"));
        WebElement proceedToCheckOutBtn2=driver.findElement(By.cssSelector("a[class=\"button btn btn-default standard-checkout button-medium\"]"));
        scrollUntil(proceedToCheckOutBtn2);
        proceedToCheckOutBtn2.click();
        waitUntil(driver,By.cssSelector("button[class=\"button btn btn-default button-medium\"]"));
        WebElement proceedToCheckOutBtn3=driver.findElement(By.cssSelector("button[class=\"button btn btn-default button-medium\"]"));
        scrollUntil(proceedToCheckOutBtn3);
        proceedToCheckOutBtn3.click();
        waitUntil(driver,By.cssSelector("button[class=\"button btn btn-default standard-checkout button-medium\"]"));
        WebElement agreeRadioBtn=driver.findElement(By.id("cgv"));
        agreeRadioBtn.click();
        WebElement proceedToCheckOutBtn4=driver.findElement(By.cssSelector("button[class=\"button btn btn-default standard-checkout button-medium\"]"));
        scrollUntil(proceedToCheckOutBtn4);
        proceedToCheckOutBtn4.click();
        WebElement PayByBankWire=driver.findElement(By.cssSelector("a[class=\"bankwire\"]"));
        scrollUntil(PayByBankWire);
        PayByBankWire.click();
        WebElement confirmMyOrder=driver.findElement(By.cssSelector("button[class=\"button btn btn-default button-medium\"]"));
        scrollUntil(confirmMyOrder);
        confirmMyOrder.click();
        WebElement ConfirmationMessage=driver.findElement(By.cssSelector("p[class=\"cheque-indent\"]"));
        scrollUntil(ConfirmationMessage);
        WebElement referenceNumberElement=driver.findElement(By.cssSelector("div[class=\"box\""));
        String sd= referenceNumberElement.getText();
        String[] arr=sd.split("\n");
        String[] reference=arr[6].split(" ");
        List<String> array=Arrays.asList(reference);
        ArrayList<String> listOfString = new ArrayList<String>(array);
        referenceNumber=listOfString.get(listOfString.indexOf("reference")+1);
        System.out.println(referenceNumber);
        Assert.assertEquals(ConfirmationMessage.getText(),"Your order on My Store is complete.");
    }
    @Test (priority = 4)
    public void validateOrder(){
        WebElement profile=driver.findElement(By.cssSelector("a[class=\"account\"]"));
        profile.click();
        waitUntil(driver,By.cssSelector("a[class=\"account\"]"));
        WebElement orderHistory=driver.findElement(By.cssSelector("i[class=\"icon-list-ol\"]"));
        orderHistory.click();
    }
    public void scrollUntil(WebElement element)
    {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element);
    }
    public String randomEmailGenerator() { //copied from https://stackoverflow.com/questions/45841500/generate-random-emails
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();
    }
    public void waitUntil(WebDriver driver,By element){
        WebDriverWait wait=new WebDriverWait(driver, 10L);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));

    }
}
