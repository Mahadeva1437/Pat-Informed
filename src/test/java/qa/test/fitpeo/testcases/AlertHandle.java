package qa.test.fitpeo.testcases;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class AlertHandle {

    @Test
    public void TestAlertHandle() throws InterruptedException, AWTException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate to the login page
        driver.get("https://field-tracking-dev.empmonitor.com/admin/login");
        String filePath = "suresh.jpg";
        // Perform login
        driver.findElement(By.xpath("//input[@placeholder='Email Address']")).sendKeys("track2024@blondmail.com");
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("@Mahantesh123");
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        // Navigate to the menu
        WebElement menuIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='flex gap-4 2xl:gap-8 items-center']")));
        menuIcon.click();
        WebElement menuItem = driver.findElement(By.xpath("//div[@role='menuitem']//span"));
        menuItem.click();

        // Wait and click on the camera icon
        WebElement cameraIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='camera']")));
        cameraIcon.click();
 Thread.sleep(5000);
        // Wait and ensure the file input element is clickable
        WebElement fileInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role=\"menuitem\"]//img[@alt=\"edit\"]")));
        fileInput.click();
       
        Thread.sleep(1000);  // Adjust the wait time if needed

        
        char[] name=filePath.toCharArray();
        // Create Robot instance to simulate keyboard actions
        Robot robot = new Robot();

        // Type the file path into the file dialog
        

        for (char C : name) {
            robot.keyPress(KeyEvent.getExtendedKeyCodeForChar(C));
            robot.keyRelease(KeyEvent.getExtendedKeyCodeForChar(C));
        }

        // Press "Enter" to select the file
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        // Wait and verify upload success
        Thread.sleep(2000);  // Wait for upload to finish or success message
        Alert alert = driver.switchTo().alert();
        //Store the alert text in a variable and verify it
        String text = alert.getText();
        
        //Press the OK button
        alert.accept();
        
        
        driver.findElement(By.xpath("//button[text()=\"Save Changes\"]")).click();
    
    }
}
