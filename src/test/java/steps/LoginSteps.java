package steps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

public class LoginSteps {

    @Given("I am on the SauceDemo login page")
    public void i_am_on_the_sauce_demo_login_page() {
        System.out.println("Step 1");
    }

    @When("I enter my valid username and password")
    public void i_enter_my_valid_username_and_password() {
        System.out.println("Step 2");
    }

    @And("I click the login button")
    public void i_click_the_login_button() {
        System.out.println("Step 3");
    }

    @Then("I should be redirected to the products page")
    public void i_should_be_redirected_to_the_products_page() {
        System.out.println("Step 4");
    }
}