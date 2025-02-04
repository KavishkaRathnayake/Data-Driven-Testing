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

public class BothCorrect {
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
    @Parameters({"Username","Password"})
    public void LoginwithBothCorrect(String Uname, String Pswd ){

        WebElement Username = driver.findElement(By.xpath("//input[@placeholder='Username']"));
        Username.sendKeys(Uname);

        WebElement Password = driver.findElement(By.xpath("//input[@placeholder='Password']"));
        Password.sendKeys(Pswd);

        WebElement loginbutton = driver.findElement(By.xpath("//button[@type='submit']"));
        loginbutton.click();
    }
    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }
}
