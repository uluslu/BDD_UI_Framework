package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class SmartBearSoftwareWebOrdersPage {

    public SmartBearSoftwareWebOrdersPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#ctl00_menu > li")
    public List<WebElement> menuItems;

    @FindBy(css = ".CheckUncheck > a")
    public List<WebElement> checkUncheck;

    @FindBy(xpath = "//table[@class='SampleTable']//td[1]/input")
    public List<WebElement> checkBoxes;

    @FindBy(xpath = "//table[@id='ctl00_MainContent_orderGrid']/tbody[1]/tr[2]/td[5]")
    public WebElement currentOrderDate;

    @FindBy(id = "ctl00_MainContent_btnDelete")
    public WebElement deleteButton;

    @FindBy(id = "ctl00_MainContent_orderMessage")
    public WebElement deletedMessage;

    @FindBy(xpath = "(//table[@id='ctl00_MainContent_orderGrid']//tr)[1]/th")
    public List<WebElement> tableHeaders;

    @FindBy(xpath = "(//table[@id='ctl00_MainContent_orderGrid']//tr)[2]/td")
    public List<WebElement> tableRow1;

    @FindBy(id = "ctl00_MainContent_orderGrid")
    public WebElement table;

    public List<List<WebElement>> getTableDataLists(){
        List<List<WebElement>> dataTableList = new ArrayList<>();
        dataTableList.add(tableHeaders);
        dataTableList.add(tableRow1);

        return dataTableList;
    }

    public void checkUncheck(String linkText) {
        for (WebElement link : checkUncheck) {
            if (link.getText().equals(linkText)) {
                link.click();
                break;
            }
        }
    }
}