package steps;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import pages.SmartBearSoftwareHomePage;
import pages.SmartBearSoftwareOrderTabPage;
import pages.SmartBearSoftwareWebOrdersPage;
import texts.ExpectedTexts;
import utilities.Driver;
import utilities.DropdownUtils;
import utilities.ElementUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SmartBearSoftwareSteps {

    public WebDriver driver;
    public SmartBearSoftwareHomePage smartBearSoftwareHomePage;
    public SmartBearSoftwareWebOrdersPage smartBearSoftwareWebOrdersPage;
    public SmartBearSoftwareOrderTabPage smartBearSoftwareOrderTabPage;


    @Before
    public void setUp() {
        driver = Driver.getDriver();
        smartBearSoftwareHomePage = new SmartBearSoftwareHomePage(driver);
        smartBearSoftwareWebOrdersPage = new SmartBearSoftwareWebOrdersPage(driver);
        smartBearSoftwareOrderTabPage = new SmartBearSoftwareOrderTabPage(driver);

    }

    @When("user enters username as {string}")
    public void userEntersUsernameAs(String userName) {
        smartBearSoftwareHomePage.userNamePassword(userName);
    }

    @And("user enters password as {string}")
    public void userEntersPasswordAs(String password) {
        smartBearSoftwareHomePage.userNamePassword(password);
    }

    @And("user clicks on Login button")
    public void userClicksOnLoginButton() {
        smartBearSoftwareHomePage.loginButton.click();
    }

    @Then("user should see {string} Message\"")
    public void userShouldSeeMessage(String message) {
        Assert.assertEquals(message, smartBearSoftwareHomePage.errorMessage.getText());

    }

    @Then("user should be routed to {string}")
    public void userShouldBeRoutedTo(String url) {
        Assert.assertEquals(url, driver.getCurrentUrl());
    }

    @And("validate below menu items are displayed")
    public void validateBelowMenuItemsAreDisplayed(DataTable dataTable) {
        List<String> expectedMenuItems = dataTable.asList();

        for (int i = 0; i < smartBearSoftwareWebOrdersPage.menuItems.size(); i++) {
            Assert.assertTrue(smartBearSoftwareWebOrdersPage.menuItems.get(i).isDisplayed());
            Assert.assertEquals(expectedMenuItems.get(i), smartBearSoftwareWebOrdersPage.menuItems.get(i).getText());
        }
    }

    @When("user clicks on {string} button")
    public void userClicksOnButton(String buttons) {
        switch (buttons) {
            case ExpectedTexts.checkAllButton:
            case ExpectedTexts.unCheckAllButton:
                smartBearSoftwareWebOrdersPage.checkUncheck(buttons);
                break;
            case ExpectedTexts.deleteSelectedButton:
                smartBearSoftwareWebOrdersPage.deleteButton.click();
                break;
            case ExpectedTexts.processButton:
                smartBearSoftwareOrderTabPage.processButton.click();
                break;
            default:
                throw new NotFoundException(buttons + " can not be found!!!");
        }
    }

    @Then("all rows should be checked")
    public void allRowsShouldBeChecked() {

        for (int i = 0; i < smartBearSoftwareWebOrdersPage.checkBoxes.size(); i++) {
            Assert.assertTrue(smartBearSoftwareWebOrdersPage.checkBoxes.get(i).isSelected());
        }
    }

    @Then("all rows should be unchecked")
    public void allRowsShouldBeUnchecked() {
        for (int i = 0; i < smartBearSoftwareWebOrdersPage.checkBoxes.size(); i++) {
            Assert.assertFalse(smartBearSoftwareWebOrdersPage.checkBoxes.get(i).isSelected());
        }
    }

    @When("user clicks on {string} menu item")
    public void userClicksOnMenuItem(String menu) {
        switch (menu) {
            case ExpectedTexts.orderMenu:
                smartBearSoftwareWebOrdersPage.menuItems.get(2).click();
                break;
            case ExpectedTexts.viewAllOrdersMenu:
                smartBearSoftwareWebOrdersPage.menuItems.get(0).click();
                break;
            default:
                throw new NotFoundException(menu + " can not be found!!!");
        }
    }

    @And("user selects {string} as product")
    public void userSelectsAsProduct(String product) {
        DropdownUtils.selectDropdownByText(smartBearSoftwareOrderTabPage.dropDown, product);
    }

    @And("user enters {int} as quantity")
    public void userEntersAsQuantity(int quantity) {
        smartBearSoftwareOrderTabPage.inputBoxes.get(0).sendKeys(String.valueOf(quantity));
    }

    @And("user enters all address information")
    public void userEntersAllAddressInformation() {
        smartBearSoftwareOrderTabPage.userAddressInfo();
    }

    @And("user enters all payment information")
    public void userEntersAllPaymentInformation() {
        smartBearSoftwareOrderTabPage.userPaymentInfo();
    }

    @Then("user should see their order displayed in the {string} table")
    public void userShouldSeeTheirOrderDisplayedInTheTable(String order) {
        LocalDate today = LocalDate.now();
        String formattedDate = today.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        Assert.assertEquals(formattedDate, smartBearSoftwareWebOrdersPage.currentOrderDate.getText());
    }

    @And("validate all information entered displayed correct with the order")
    public void validateAllInformationEnteredDisplayedCorrectWithTheOrder(DataTable dataTable) {
        List<List<String>> dataLists = dataTable.asLists();

        for (int i = 1; i < smartBearSoftwareWebOrdersPage.getTableDataLists().size(); i++) {
            for (int j = 1; j < smartBearSoftwareWebOrdersPage.getTableDataLists().get(i).size(); j++) {
                if (i == 4 || j == 4)
                    continue;
                Assert.assertEquals(dataLists.get(i).get(j), smartBearSoftwareWebOrdersPage.getTableDataLists().get(i).get(j).getText());
            }
        }
    }

    @Then("validate all orders are deleted from the {string}")
    public void validateAllOrdersAreDeletedFromThe(String arg0) {
        Assert.assertFalse(ElementUtils.isElementDisplayed(smartBearSoftwareWebOrdersPage.table));
    }

    @And("validate user sees {string} Message")
    public void validateUserSeesMessage(String message) {

        Assert.assertTrue(smartBearSoftwareWebOrdersPage.deletedMessage.isDisplayed());
        Assert.assertEquals(message, smartBearSoftwareWebOrdersPage.deletedMessage.getText());
    }
}