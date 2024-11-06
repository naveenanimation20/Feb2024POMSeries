package parallel;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginPageSteps {

    private LoginPage loginPage;
    private AccountsPage accPage;
    private Hooks hooks;

    public LoginPageSteps(Hooks hooks) {
        this.loginPage = hooks.getLoginPage();
    }

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        // Assuming the driver is already on the login page
    }

    @When("the user checks the page title")
    public void the_user_checks_the_page_title() {
        String actTitle = loginPage.getLoginPageTitle();
        Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE);
    }

    @Then("the page title should be {string}")
    public void the_page_title_should_be(String expectedTitle) {
        String actTitle = loginPage.getLoginPageTitle();
        Assert.assertEquals(actTitle, expectedTitle);
    }

    @When("the user checks the page URL")
    public void the_user_checks_the_page_url() {
        String actURL = loginPage.getLoginPageURL();
        Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_FRACTION_URL));
    }

    @Then("the URL should contain {string}")
    public void the_url_should_contain(String fractionURL) {
        String actURL = loginPage.getLoginPageURL();
        Assert.assertTrue(actURL.contains(fractionURL));
    }

    @When("the user checks the forgot password link")
    public void the_user_checks_the_forgot_password_link() {
        Assert.assertTrue(loginPage.checkForgotPwdLinkExist());
    }

    @Then("the forgot password link should be displayed")
    public void the_forgot_password_link_should_be_displayed() {
        Assert.assertTrue(loginPage.checkForgotPwdLinkExist());
    }

    @When("the user logs in with username {string} and password {string}")
    public void the_user_logs_in_with_username_and_password(String username, String password) {
        accPage = loginPage.doLogin(username, password);
    }

    @Then("the user should be redirected to the accounts page with title {string}")
    public void the_user_should_be_redirected_to_the_accounts_page_with_title(String expectedTitle) {
        Assert.assertEquals(accPage.getAccPageTitle(), expectedTitle);
    }





}
