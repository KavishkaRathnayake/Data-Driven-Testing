package DataDrivenTests;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

public class LoginDataProviderby_readingExcel {
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


//    public void ReadExcel() throws IOException {
//        //E:\QA\My QA Projects\DataDrivenFramework\TestData
//        FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "\\TestData\\TestData.xlsx");
//        XSSFWorkbook workbook = new XSSFWorkbook(file);
//        XSSFSheet sheet = workbook.getSheet("Sheet1"); //work.getsheetAt(indexValue);
//        int totalRows = sheet.getLastRowNum();  //4 actually have 5
//        int totalColumns = sheet.getRow(0).getLastCellNum(); //3
//        System.out.println("total Rows Count: "+totalRows);
//        System.out.println("total Columns Count: "+totalColumns);
//
//
//        for(int r =0; r<=totalRows; r++){
//            XSSFRow currentRow = sheet.getRow(r);
//
//            for(int c=0; c<totalColumns; c++){
//                XSSFCell currentCell = currentRow.getCell(c);
//                String cellValue = currentCell.toString(); //covert all data types to string
//                System.out.print(cellValue + "\t\t");
//            }
//            System.out.println();
//        }
//}

@DataProvider(name = "loginata")
public Object[][] getExcelData() throws IOException {
    //E:\QA\My QA Projects\DataDrivenFramework\TestData
    FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "\\TestData\\TestData.xlsx");
    XSSFWorkbook workbook = new XSSFWorkbook(file);
    XSSFSheet sheet = workbook.getSheet("Sheet1"); //work.getsheetAt(indexValue);
    int totalRows = sheet.getLastRowNum();  //4 actually have 5
    int totalColumns = sheet.getRow(0).getLastCellNum(); //3
    System.out.println("total Rows Count: "+totalRows);
    System.out.println("total Columns Count: "+totalColumns);

    String[][] TestData = new String[totalRows][totalColumns];

    for (int r=1; r<=totalRows;r++){
        for(int c=0; c<totalColumns; c++){
            TestData[r-1][c] = sheet.getRow(r).getCell(c).toString();
        }
    }

    workbook.close(); //close workbook
    file.close(); //close file

    return TestData;


}

   @Test(dataProvider = "loginata")
    public void LoginTestScenario(String Uname, String Pswd, String expValidation) throws InterruptedException {

       WebElement Username = driver.findElement(By.xpath("//input[@placeholder='Username']"));
       Username.sendKeys(Uname);

       WebElement Password = driver.findElement(By.xpath("//input[@placeholder='Password']"));
       Password.sendKeys(Pswd);

       WebElement loginbutton = driver.findElement(By.xpath("//button[@type='submit']"));
       loginbutton.click();

       Thread.sleep(3000);

       boolean urlVerification = driver.getCurrentUrl().contains("dashboard");

       if (expValidation.equals("Valid")) {
           Assert.assertTrue(urlVerification, "Expected login success, But navigated to the dashboard");
       } else {
           Assert.assertFalse(urlVerification, "Expected login fail, But not navigated to the dashboard");
       }
   }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }
}
