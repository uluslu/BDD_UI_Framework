package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SmartBearSoftwareOrderTabPage {
    public SmartBearSoftwareOrderTabPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "ctl00_MainContent_fmwOrder_ddlProduct")
    public WebElement dropDown;

    @FindBy(xpath = "//table[@id='ctl00_MainContent_fmwOrder']//li/input")
    public List<WebElement> inputBoxes;

    @FindBy(xpath = "//table[@class='RadioList']//input")
    public List<WebElement> radioButtons;

    @FindBy(id = "ctl00_MainContent_fmwOrder_InsertButton")
    public WebElement processButton;

    public void userAddressInfo(){
        inputBoxes.get(5).sendKeys("Burak Simsek");
        inputBoxes.get(6).sendKeys("3436 N Abc Ave");
        inputBoxes.get(7).sendKeys("Chicago");
        inputBoxes.get(8).sendKeys("IL");
        inputBoxes.get(9).sendKeys("60641");
    }

    public void userPaymentInfo(){
        radioButtons.get(0).click();
        inputBoxes.get(10).sendKeys("12345");
        inputBoxes.get(11).sendKeys("12/23");
    }
}