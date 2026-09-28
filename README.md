# Task description
**End-to-end flow**  
**Focus:** User completes full flow from login to checkout  
**Launch URL:** https://www.saucedemo.com/

**UC-1 Checkout Flow (one item)**
* Login with standard_user.
* Add a specific product to the cart (parametrize product name, e.g., "Sauce Labs Backpack").
* Go to Cart and validate the item is present.
* Proceed to Checkout.
* Fill in Information form (First Name, Last Name, Zip).
* Complete checkout and validate success message: "Thank you for your order!"

**UC-2 Checkout Flow (several items)**
* Login with standard_user.
* Add a specific product to the cart (parametrize product name, e.g., "Sauce Labs Backpack").
* Add another product to the cart.
* Go to Cart and validate both items are present.
* Proceed to Checkout.
* Fill in Information form (First Name, Last Name, Zip).
* Validate final price equals the sum of both product prices.
* Complete checkout and validate success message: "Thank you for your order!"

**Technical Requirements**
**Tool:** Selenium WebDriver  
**Browsers:** Chrome, Edge (Run in Parallel)  
**Pattern:** Page Object Model (POM)  
**Locators:** CSS Selectors, Xpath  
**Reporting:** Allure (or similar HTML report)  
**Documentation:** README.md with execution and report instructions

# SauceDemo Automation Testing - EPAM Project

End-to-end test framework for https://www.saucedemo.com/ built as the final assignment of the EPAM Automated Testing course.

## Tech stack
* Java 17
* Selenium WebDriver 4 (drivers resolved automatically by Selenium Manager)
* Cucumber 7 (BDD, Given-When-Then)
* TestNG (test runner and parallel execution)
* Log4j2 (logging)
* Maven

## Project structure
```
src/main/java
  config/ConfigReader        reads config.properties (values can be overridden with -D)
  driver/DriverFactory       creates Chrome or Edge with the right options (Factory pattern)
  driver/DriverManager       keeps one WebDriver per thread (ThreadLocal)
  pages/                     Page Object Model: BasePage, LoginPage, ProductsPage, CartPage,
                             CheckoutPage, OverviewPage, ConfirmationPage
src/main/resources           config.properties, log4j2.xml
src/test/java
  runners/                   BaseRunner, ChromeRunner, EdgeRunner
  steps/                     Hooks (driver lifecycle, failure screenshots), StepDefinitions
src/test/resources
  features/checkout.feature  UC-1 and UC-2 scenarios
  testng.xml                 runs Chrome and Edge in parallel
```

## Design notes
* **Page Object Model:** each page exposes business actions and returns the next page object (`loginAs` returns `ProductsPage`, and so on). Locators are private constants; the step definitions never touch Selenium directly.
* **Locators:** CSS selectors for static elements and XPath for elements that depend on the product name.
* **Waits:** only explicit waits (`WebDriverWait`), timeout configurable in `config.properties`.
* **Parallel cross-browser:** `testng.xml` defines two `<test>` blocks (Chrome and Edge) with `parallel="tests"`, so both browsers execute the same feature files at the same time. Each thread owns its own driver.
* **Assertions:** the product prices are read from the catalog when the product is added, then compared with the "Item total" of the overview page (`BigDecimal`, no floating point errors). The total is also checked against item total plus tax.
* **Logging:** Log4j2 writes to the console and to `target/logs/test-run.log`.

## How to run
Requirements: JDK 17+, Maven 3.8+, Google Chrome and Microsoft Edge installed.

Both browsers in parallel:
```
mvn clean test
```

A single browser:
```
mvn clean test -Dtest=ChromeRunner
mvn clean test -Dtest=EdgeRunner
```

Headless mode:
```
mvn clean test -Dheadless=true
```

## Reports and logs
The task asks for Allure or a similar HTML report. This project uses the Cucumber HTML report, generated per browser:
* `target/cucumber-reports/chrome.html`
* `target/cucumber-reports/edge.html`

Failed scenarios include a screenshot in the report. Execution logs are in `target/logs/test-run.log`.