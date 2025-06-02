package steps;

import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.utils.StringUtils;
import io.cucumber.java.en.*;

import org.junit.Assert;
import utils.ScenarioContext;

public class RegisterPageSteps {

    private final LoginPage loginPage;
    private RegisterPage registerPage;
    private final ScenarioContext scenarioContext;

    //private boolean regSuccess;

    public RegisterPageSteps(Hooks hooks, ScenarioContext scenarioContext) {
        this.loginPage = hooks.getLoginPage();
        this.scenarioContext = scenarioContext;
    }

    @Given("the user navigates to the registration page")
    public void the_user_navigates_to_the_registration_page() {
        registerPage = loginPage.navigateToRegisterPage();
    }

    @When("the user enters {string}, {string}, {string}, {string} and subscribes {string}")
    public void the_user_enters_and_subscribes(String firstName, String lastName, String telephone, String password, String subscribe) {
        boolean regSuccess = registerPage.userRegister(firstName, lastName, StringUtils.getRandomEmailId(), telephone, password, subscribe);
        scenarioContext.setContext("REG_SUCCESS", regSuccess);
    }

    @Then("the user registration should be successful")
    public void the_user_registration_should_be_successful() {
       Assert.assertTrue((Boolean) scenarioContext.getContext("REG_SUCCESS"));
    }
}

