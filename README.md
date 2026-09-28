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