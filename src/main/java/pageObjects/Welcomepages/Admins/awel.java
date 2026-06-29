package pageObjects.Welcomepages.Admins;

import Abstractclass.common_pro;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.FileNotFoundException;
import java.util.List;

public class awel extends common_pro {

    public WebDriver driver;
    public awel(WebDriver driver) throws FileNotFoundException {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//div/h1[contains(text(),'Admin')]")
    WebElement greeting;

    @FindBy(xpath = "//article")  // h3
    List<WebElement> active_requirements;

    @FindBy(xpath = "//button[@title='Open profile']")
    WebElement profile;

    @FindBy(xpath = "//button[contains(text(),'Approve')]")
   List<WebElement> approve_btn;

    @FindBy(xpath = "//button[contains(text(),'Reject')]")
    List<WebElement> reject_btn;

    @FindBy(xpath = "//div[@class='item']//strong")
    List<WebElement> vendor_names;

    @FindBy(xpath = "//div[@class='item']//p")
    List<WebElement> vendor_details;

    @FindBy(xpath = "//button[text()='Day']")
    WebElement day_btn;

    @FindBy(xpath = "//button[text()='Month']")
    WebElement month_btn;

    @FindBy(xpath = "//button[text()='Year']")
    WebElement year_btn;

    @FindBy(xpath = "//div[@class='graph-row']")
   List<WebElement> total_details;


    public List<WebElement> Total_details()
    {
        return total_details;
    }

    // buttons for day,month,year
    public void Day_btn()
    {
        day_btn.click();
    }

    public void Year_btn()
    {
        year_btn.click();
    }

    public void Month_btn()
    {
        month_btn.click();
    }



    public List<WebElement> vendor_names()
    {
        return vendor_names;
    }

    public List<WebElement> vendor_details()
    {
        return  vendor_details;
    }

    public List<WebElement> approver_btn()
    {
        return approve_btn;
    }
    public List<WebElement> Reject_btn()
    {
        return reject_btn;
    }


    public void profile_btn()
    {
        profile.click();
    }

    public List<WebElement> list_of_details()
    {
        return active_requirements;
    }

    public String welcome_mesg()
    {
        return greeting.getText();
    }


}
