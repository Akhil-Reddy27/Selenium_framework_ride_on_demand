package pageObjects.Welcomepages.Customers;

import Abstractclass.common_pro;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.FileNotFoundException;
import java.util.List;

public class profile_page extends common_pro {

    public WebDriver driver;
    public profile_page(WebDriver driver) throws FileNotFoundException {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//button[contains(text(),'Sign out')]")
    WebElement signout_btn;

    @FindBy(xpath = "(//div[@class=\"details-grid\"]/div/strong)")
    List<WebElement> acc_details;
    @FindBy(xpath = "//div[@class=\"details-grid\"]")
    WebElement acc_st;


    public void signout()
    {
        waitforWebElementToAppear(signout_btn);
        signout_btn.click();
    }
    public List<WebElement> getAccountDetails()
    {
        waitforWebElementToAppear(acc_st);
        return acc_details;
    }
}
