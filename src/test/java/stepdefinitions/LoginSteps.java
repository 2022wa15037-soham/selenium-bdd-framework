package stepdefinitions;

import org.testng.Assert;
import factory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import library.FunctionalLibrary;

public class LoginSteps {

    private final FunctionalLibrary funcLib = new FunctionalLibrary(DriverFactory.getDriver());

    @Given("user is on the login page")
    public void user_is_on_the_login_page() {
        Assert.assertTrue(funcLib.isLoginPageLoaded(), "Login page did not load properly!");
    }

    @When("user enters username {string} and password {string}")
    public void user_enters_username_and_password(String username, String password) {
        funcLib.enterCredentials(username, password);
    }

    @When("user clicks on the login button")
    public void user_clicks_on_the_login_button() {
        funcLib.clickLoginButton();
    }

    @Then("user should see the {string} page")
    public void user_should_see_the_page(String expectedHeader) {
        String actualHeader = funcLib.getDashboardHeader();
        Assert.assertEquals(actualHeader, expectedHeader, "Dashboard header mismatch!");
    }

    @Then("user should see the error message {string}")
    public void user_should_see_the_error_message(String expectedError) {
        String actualError = funcLib.getInvalidLoginMessage();
        Assert.assertEquals(actualError, expectedError, "Error message mismatch!");
    }
}
