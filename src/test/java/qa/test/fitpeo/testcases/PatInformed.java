package qa.test.fitpeo.testcases;

import java.time.Duration;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class PatInformed {

	@Test
	public void verifyFitPeoHomepageScenarios() throws InterruptedException {

		
		// Get search keyword dynamically from user input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the search keyword: ");
        String searchKeyword = scanner.nextLine();
        scanner.close();
		// Initialize the ChromeDriver and launch a new browser window
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito"); // Open browser in incognito mode

		// Launch Chrome with options
		WebDriver driver = new ChromeDriver(options);
		// Maximize the browser window for better visibility
		driver.manage().window().maximize();

		// Open the specified URL in the browser
		driver.get("https://patinformed.wipo.int/");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		WebElement serrchField = driver.findElement(By.xpath("//input[@class='searchField']"));
		serrchField.sendKeys(searchKeyword);
//
		 WebElement alertPopUp = wait.until(ExpectedConditions.elementToBeClickable(
                 By.xpath("//button[text()='I have read and agree to the terms']")));
         alertPopUp.click();

         WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(
                 By.xpath("//button[@class='margin-right']")));
         searchButton.click();

         WebElement linkClick = wait.until(ExpectedConditions.elementToBeClickable(
                 By.xpath("//table[@class='results']/tbody/tr[1]/td[3]/ul/li[1]")));
         System.out.println("First Result: " + linkClick.getText());
         linkClick.click();

		System.out.println(linkClick.getText());

		List<WebElement> elements = driver
				.findElements(By.xpath("//b[text()='Publication date']/../following-sibling::td"));
		List<WebElement> elementsDates = driver
				.findElements(By.xpath("//b[text()='Filing date']/../following-sibling::td"));

// Loop through the elements to print the values in the desired format
		for (int i = 0; i < Math.min(Math.min(elements.size(), elementsDates.size()), 1); i++) {
			// Extract the text content from both elements
			String publicationDate = elements.get(i).getText();
			String filingDate = elementsDates.get(i).getText();

			// Calculate the year difference for the "X years ago" part
			int publicationYear = Integer.parseInt(publicationDate.split("-")[0]);
			int filingYear = Integer.parseInt(filingDate.split("-")[0]);

			// Print the formatted result
			System.out.println("Filing date: " + filingDate + " (" + filingYear + " years ago)");
			System.out.println("Publication date: " + publicationDate + " (" + publicationYear + " years ago)");
			
			
			driver.quit();
			}

	}

	public void highLighterMethod(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: red; border: 2px solid red;');", element);
	}
}
