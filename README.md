### FitPeo Automation Script

- This project contains a Selenium WebDriver-based automation script to test the FitPeo website's functionality, specifically focusing on the Revenue Calculator feature.

#####Project Structure
FitPeoAutomation/
├── src/
│   └── com/
│       └── fitpeo/
│           └── test/
│               └── FitPeoTest.java    # Main automation script
├── lib/                              # Selenium JARs and dependencies
├── chromedriver/                     # ChromeDriver executable
├── README.md                         # Project documentation
└── pom.xml                           # Maven configuration (if using Maven)

####Features

- Launches the FitPeo website.
- Navigates to the Revenue Calculator page.
- Interacts with a slider and verifies its value.
- Selects specific CPT codes checkboxes based on predefined criteria.
- Validates the Total Recurring Reimbursement amount.


**Prerequisites**

######Software Requirements

- Java JDK (version 8 or higher)
- Google Chrome (latest version recommended)
- ChromeDriver (matching the version of Chrome installed)
- Eclipse IDE or another Java IDE
- Maven (optional, for dependency management)

**Setup Instructions**

######Step 1: Clone the Repository

Clone this repository to your local system using:
git clone <repohttps://github.com/Mahadeva1437/FitPeo-Automation-Analyst-Assignment.gitsitory-url>

######Step 2: Install ChromeDriver

Download ChromeDriver: ChromeDriver Downloads
Place the chromedriver.exe file in a directory and add its path to your system's PATH environment variable.

######Step 3: Set Up the Project

Open the project in Eclipse IDE.
Add Selenium JAR files to the project:
Navigate to Project > Properties > Java Build Path > Libraries.
Add Selenium JARs (downloaded from Selenium Downloads).

######Step 4: Add Dependencies

If using Maven, include the following in your pom.xml:
<dependencies>
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.12.0</version>
    </dependency>
    <dependency>
        <groupId>org.testng</groupId>
        <artifactId>testng</artifactId>
        <version>7.7.1</version>
        <scope>test</scope>
    </dependency>
</dependencies>

**How to Run the Script**
#######Using Eclipse

- Import the project into Eclipse.
- Open the FitPeoTest.java file.
- Right-click on the file and select:
- Run As > TestNG Test (if using TestNG)
- Using Maven
- Ensure Maven is installed and configured.
- Run the test using:
- mvn test
-  Blockquotes

**Validation Details**

- Assertions in the Script
- Slider Value: Confirms the slider value is updated to 820.
- Recurring Reimbursement: Checks that the total reimbursement amount is $110700.

**Troubleshooting**

Common Issues
WebDriverException: Ensure ChromeDriver is installed and matches your browser version.
ElementNotInteractableException: Increase the implicit wait time to allow elements to load.
Assertion Failure: Verify that expected values align with the application behavior.
