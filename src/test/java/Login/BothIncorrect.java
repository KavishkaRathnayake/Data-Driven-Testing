package Login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class BothIncorrect {
    WebDriver driver;

    @BeforeMethod
    public void OpenPage(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBrowserVersion("121");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.get("https://practicetestautomation.com/practice-test-login/");
    }

    @Test
    @Parameters({"Username","Password"})
    public void LoginwithBotInCorrect(String Uname, String Pswd ){

        WebElement Username = driver.findElement(By.xpath("//input[@id='username']"));
        Username.sendKeys(Uname);

        WebElement Password = driver.findElement(By.xpath("//input[@id='password']"));
        Password.sendKeys(Pswd);

        WebElement loginbutton = driver.findElement(By.xpath("//button[@type='submit']"));
        loginbutton.click();
    }
    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }
}
