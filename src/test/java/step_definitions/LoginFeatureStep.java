package step_definitions;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;

public class LoginFeatureStep {
    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();

    @Given("User should be on the login screen")
    public void userShouldBeOnTheLoginScreen() {
        loginPage.navigateToLoginPage();
    }

    @When("User enter valid username and password")
    public void userEnterValidUsernameAndPassword() {
        loginPage.writeOnElement(loginPage.loginEmail, "shobuj@yopmail.com");
        loginPage.writeOnElement(loginPage.loginPassword, "shobuj123");

    }

    @And("User click on the login button")
    public void userClickOnTheLoginButton() {
        loginPage.clickOnElement(loginPage.login_button);

    }

    @Then("User should be see the log out button")
    public void userShouldBeSeeTheLogOutButton() {
        Assert.assertTrue(homePage.getDisplayStatus(homePage.logout_Button));
    }

    @And("User should be navigate to the inventory page")
    public void userShouldBeNavigateToTheInventoryPage() {
        Assert.assertEquals(homePage.getCurrentURL(), homePage.homepageUrl);
    }

    @But("User should not be on the login page")
    public void userShouldNotBeOnTheLoginPage() {
        Assert.assertNotEquals(homePage.getCurrentURL(), loginPage.loginPageURL);
    }

    @When("User enter {string} on the username filed")
    public void userEnterOnTheUsernameFiled(String arg0) {
        loginPage.writeOnElement(loginPage.loginEmail, arg0);
    }

    @And("User enter {string} on the password filed")
    public void userEnterOnThePasswordFiled(String arg0) {
        loginPage.writeOnElement(loginPage.loginPassword, arg0);
    }

    @Then("User should see {string} error message")
    public void userShouldSeeErrorMessage(String arg0) {
        if (loginPage.getDisplayStatus(loginPage.errorMsg))
        Assert.assertEquals(loginPage.getElement(loginPage.errorMsg).getText(), arg0);
    }

    @Then("User should see {string} validation message for username")
    public void userShouldSeeValidationMessageForUsername(String arg0) {
        Assert.assertEquals(loginPage.getElement(loginPage.loginEmail).getAttribute("validationMessage"), arg0);

    }

    @Then("User should see {string} validation message for password")
    public void userShouldSeeValidationMessageForPassword(String arg0) {
        Assert.assertEquals(loginPage.getElement(loginPage.loginPassword).getAttribute("validationMessage"), arg0);

    }
}
