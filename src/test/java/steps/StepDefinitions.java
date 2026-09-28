package steps;
import driver.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.CartPage;
import pages.CheckoutPage;
import pages.ConfirmationPage;
import pages.LoginPage;
import pages.OverviewPage;
import pages.ProductsPage;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class StepDefinitions {

    private final List<BigDecimal> selectedPrices = new ArrayList<>();
    private ProductsPage productsPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private OverviewPage overviewPage;
    private ConfirmationPage confirmationPage;

    @Given("I am logged in as {string} with password {string}")
    public void iAmLoggedIn(String username, String password) {
        productsPage = new LoginPage(DriverManager.getDriver()).open().loginAs(username, password);
        Assert.assertTrue(productsPage.isLoaded(),
                "Login failed for user '" + username + "': the products page was not displayed");
    }

    @When("I add the product {string} to the cart")
    public void iAddTheProductToTheCart(String productName) {
        selectedPrices.add(productsPage.getProductPrice(productName));
        productsPage.addProductToCart(productName);
    }

    @When("I go to the cart")
    public void iGoToTheCart() {
        cartPage = productsPage.openCart();
    }

    @Then("the cart should contain {int} item(s)")
    public void theCartShouldContainItems(int expectedCount) {
        List<String> items = cartPage.getItemNames();
        Assert.assertEquals(items.size(), expectedCount, "Unexpected number of items in the cart: " + items);
    }

    @Then("I should see {string} in the cart")
    public void iShouldSeeInTheCart(String productName) {
        List<String> items = cartPage.getItemNames();
        Assert.assertTrue(items.contains(productName),
                "Expected '" + productName + "' in the cart but found " + items);
    }

    @When("I proceed to checkout")
    public void iProceedToCheckout() {
        checkoutPage = cartPage.proceedToCheckout();
    }

    @When("I fill in my information with {string}, {string}, {string}")
    public void iFillInMyInformation(String firstName, String lastName, String zip) {
        checkoutPage.fillInformation(firstName, lastName, zip);
    }

    @When("I continue to the order overview")
    public void iContinueToTheOrderOverview() {
        overviewPage = checkoutPage.continueToOverview();
    }

    @Then("the final price should equal the sum of both product prices")
    public void theFinalPriceShouldEqualTheSumOfBothProductPrices() {
        BigDecimal expectedItemTotal = selectedPrices.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal itemTotal = overviewPage.getItemTotal();
        Assert.assertEquals(itemTotal, expectedItemTotal,
                "Item total does not match the sum of the selected product prices " + selectedPrices);

        BigDecimal expectedTotal = itemTotal.add(overviewPage.getTax());
        Assert.assertEquals(overviewPage.getTotal(), expectedTotal, "Total does not equal item total plus tax");
    }

    @When("I complete the checkout")
    public void iCompleteTheCheckout() {
        confirmationPage = overviewPage.finishCheckout();
    }

    @Then("I should see the success message {string}")
    public void iShouldSeeTheSuccessMessage(String expectedMessage) {
        Assert.assertEquals(confirmationPage.getSuccessMessage(), expectedMessage, "Success message mismatch");
    }
}