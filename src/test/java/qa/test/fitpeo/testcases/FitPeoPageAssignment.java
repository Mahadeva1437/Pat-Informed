package qa.test.fitpeo.testcases;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FitPeoPageAssignment {
	
	@Test
	public void verifyFitPeoHomepageScenarios() {
		
		// Initialize the ChromeDriver and launch a new browser window
		WebDriver driver = new ChromeDriver();
		// Maximize the browser window for better visibility
		driver.manage().window().maximize();

		/* ========= Navigate to the FitPeo Homepage:========= */

		// Open the specified URL in the browser
		driver.get("https://www.fitpeo.com/");
		// Refresh the browser to ensure the page loads freshly
		driver.navigate().refresh();

		// Set an implicit wait timeout of 5 seconds for finding elements
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		/* ========= Navigate to the Revenue Calculator Page:========= */

		// Find the "Revenue Calculator" element on the page and click it
		driver.findElement(By.xpath("//div[contains(text(),\"Revenue Calculator\")]")).click();

		/* ========= Scroll Down to the Slider section:========= */

		// Create a JavascriptExecutor instance for executing JavaScript on the browser
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Locate the "Medicare Eligible Patients" element and scroll it into view using
		// JavaScript
		WebElement scrollElement = driver
				.findElement(By.xpath("//h4[contains(text(),\"Medicare Eligible Patients\")]"));
		js.executeScript("arguments[0].scrollIntoView(true);", scrollElement);

		/* ========= Adjust the Slider:========= */

		// Locate the slider input element used for adjusting values
		WebElement slider = driver.findElement(By.xpath("//input[@aria-orientation=\"horizontal\"]"));

		// Move the slider by sending "ARROW_RIGHT" key inputs for 620 times
		for (int i = 1; i <= 620; i++) {
			highLighterMethod(driver, slider);
			slider.sendKeys(Keys.ARROW_RIGHT);

		}

		/* ========= Update the Text Field:========= */

		// Locate the text box that displays the slider's current value
		WebElement textBox = driver.findElement(By.xpath(
				"//input[@class=\"MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputSizeSmall css-1o6z5ng\"]"));
//Retrieve the value displayed in the text box
		String textDetails = textBox.getAttribute("value");
//	System.out.println(textDetails);
		// Assert that the value in the text box matches the expected value "820"
		Assert.assertEquals("820", textDetails);

		/* ========= Select CPT Codes:========= */

		// Find all elements within a specific section containing the desired values
		List<WebElement> elements = driver.findElements(By.xpath("//div[@class=\"MuiBox-root css-1eynrej\"]"));

		// Create a list to store the text content of these elements
		List<String> values = new ArrayList<>();
		for (WebElement element : elements) {
			// Extract text content from the paragraph within each element
			WebElement paragraph = element.findElement(By.tagName("p"));
			values.add(paragraph.getText());
		}

		// Define a list of target text values to search for
		List<String> findThese = Arrays.asList("CPT-99091", "CPT-99453", "CPT-99454", "CPT-99474");

		// Create a list to store the indices of found target values
		List<Integer> foundIndices = new ArrayList<>();
		for (String target : findThese) {
			int index = -1;
			// Check if the target text exists in the list of values
			for (int i = 0; i < values.size(); i++) {
				if (values.get(i).contains(target)) {
					index = i;
					break;
				}
			}
			foundIndices.add(index);
		}

		// Iterate through the indices of the found target values
		for (Integer foundIndex : foundIndices) {
			if (foundIndex != -1) {
				// Construct a CSS selector to locate the checkbox for the corresponding target
				// value
				String selector = String.format(".MuiBox-root.css-1p19z09 > div:nth-of-type(%d) input", foundIndex + 1);
				WebElement checkbox = driver.findElement(By.cssSelector(selector));
				// Click the checkbox
				checkbox.click();
				System.out.println("Clicked checkbox at index: " + (foundIndex + 1));
			} else {
				// Print a message if the target text is not found
				System.out.println("Target text not found in values.");
			}
		}

		/*
		 * ========= Verify that the header displaying Total Recurring Reimbursement for
		 * all Patients Per Month: shows the value $110700. =========
		 */

		// Locate the element displaying the total recurring reimbursement amount
		WebElement ValidateAmount = driver.findElement(
				By.xpath("//p[contains(text(),\"Total Recurring Reimbursement for all Patients Per Month:\")]//p"));
		highLighterMethod(driver, ValidateAmount);

		System.out.println(ValidateAmount.getText());
		// Store the actual amount displayed
		String ActualResult = ValidateAmount.getText();
		// Define the expected result for validation
		String ExpectedResult = "$110700";
		// Assert that the actual amount matches the expected result
		Assert.assertEquals(ExpectedResult, ActualResult);

		// close the browser session
		driver.close();
		
	}
	
	/*
	 * This method highlights a specified WebElement by modifying its CSS style
	 * dynamically using JavaScript. This can be helpful for debugging, as it
	 * visually distinguishes the element being interacted with.
	 */
	public void highLighterMethod(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: red; border: 2px solid red;');", element);
	}


}
