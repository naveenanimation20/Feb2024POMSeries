package parallel;

import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.SearchResultsPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class AccountsPageSteps {

    private Hooks hooks;
    private LoginPage loginPage;
    private AccountsPage accPage;
    private SearchResultsPage searchResultsPage;
    private String title;
    private String url;
    private List<String> headers;

    public AccountsPageSteps(Hooks hooks) {
        this.hooks = hooks;
        loginPage = hooks.getLoginPage();
    }

    @Given("the user is logged in and on the accounts page")
    public void the_user_is_logged_in_and_on_the_accounts_page() {
        accPage = loginPage.doLogin(hooks.getProperties().getProperty("username"), hooks.getProperties().getProperty("password"));
    }


    @When("the user checks the page headers")
    public void the_user_checks_the_page_headers() {
        headers = accPage.getAccPageHeaders();
    }

    @Then("the page headers should be:")
    public void the_page_headers_should_be(DataTable expectedHeadersTable) {
        List<String> expectedHeaders = expectedHeadersTable.asList();
        Assert.assertEquals(expectedHeaders, headers);
    }

    @When("the user searches for {string}")
    public void the_user_searches_for(String searchKey) {
        searchResultsPage = accPage.doSearch(searchKey);
    }

    @Then("the result count should be {int}")
    public void the_result_count_should_be(int expectedCount) {
        Assert.assertEquals(expectedCount, searchResultsPage.getSearchResultsCount());
    }
}
