package DataDrivenTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginUsing_Parameter {
    WebDriver driver;

    @BeforeMethod
    public void OpenPage(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBrowserVersion("121");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @Test
    @Parameters({"Username","Password","Validation"})
    public void LoginwithBotInCorrect(String Uname, String Pswd, String expValidation) throws InterruptedException {

        WebElement Username = driver.findElement(By.xpath("//input[@placeholder='Username']"));
        Username.sendKeys(Uname);

        WebElement Password = driver.findElement(By.xpath("//input[@placeholder='Password']"));
        Password.sendKeys(Pswd);

        WebElement loginbutton = driver.findElement(By.xpath("//button[@type='submit']"));
        loginbutton.click();
        Thread.sleep(3000);

        boolean urlVerification =  driver.getCurrentUrl().contains("dashboard");


        if(expValidation.equals("Valid")) {
            Assert.assertTrue(urlVerification, "Expected login success, But navigated to the dashboard");
        }
        else {
            Assert.assertFalse(urlVerification, "Expected login fail, But not navigated to the dashboard");
        }
    }
    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }
}
