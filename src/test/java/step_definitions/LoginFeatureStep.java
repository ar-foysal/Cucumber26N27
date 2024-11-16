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
}
