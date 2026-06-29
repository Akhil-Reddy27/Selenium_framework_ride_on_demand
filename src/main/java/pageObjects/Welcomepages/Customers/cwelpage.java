package pageObjects.Welcomepages.Customers;

import Abstractclass.common_pro;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.FileNotFoundException;
import java.util.List;

public class cwelpage extends common_pro {
    public WebDriver driver;
    public cwelpage(WebDriver driver) throws FileNotFoundException {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }


@FindBy(xpath = "//div/h1[contains(text(),'Welcome')]")
 public WebElement cname;

    @FindBy(xpath = "//button[contains(text(),'Post Requirement')]")
    WebElement post_req;

    @FindBy(linkText = "Open Profile")
    WebElement open_profile;

    @FindBy(xpath = "//div[@class='hero-metric']/strong")
    WebElement active_requests;

    @FindBy(xpath = "//div[@class='item clickable']/div[@class='item-main']")
    List<WebElement> all_request_details;

    @FindBy(xpath = "//div[@class='item clickable']/div[@class='item-action']")
    List<WebElement> rdetails_btn_click;

    @FindBy(xpath = "//section/p[contains(text(),'No offers available for this requirement')]")
   List<WebElement> no_req_mss;

    @FindBy(xpath = "//article/button[contains(text(),'Accept')]")
    List<WebElement> accept_btn_offer;

    @FindBy(xpath = "//article/button[contains(text(),'Reject')]")
    List<WebElement> reject_btn_offer;

    @FindBy(xpath = "//div[@class='meta-list']")
    List<WebElement> offer_details;

    @FindBy(xpath = "//article//div[@class='price']")
    List<WebElement> price_details;

    @FindBy(xpath = "//article/p[@class='notes']")
    List<WebElement> additional_details;

    @FindBy(xpath = "//article[@class='card']")
    List<WebElement> toffercards;

    @FindBy(xpath = "//span[contains(text(),'→')]")
    List<WebElement> app;

    public List<WebElement> App_text_present(){
        return app;
    }

    public List<WebElement> Toffercards()
    {
        return toffercards;
    }


    public List<WebElement> Accept_btn_offer()
    {
        return accept_btn_offer;
    }

    public List<WebElement> Reject_btn_offer()
    {
        return reject_btn_offer;
    }

    public List<WebElement> Offer_details()
    {
        return offer_details;
    }
    public List<WebElement> Price_details()
    {
        return price_details;
    }

    public List<WebElement> Additional_details()
    {
        return  additional_details;
    }



    public List<WebElement> Allin_request_details()
    {
        return all_request_details;
    }

    public List<WebElement> Rdetails_btn()
    {
        return rdetails_btn_click;
    }

    public List<WebElement> mssg_req_active()
    {
        return no_req_mss;
    }


    public void clickOpen_profile()
    {
        open_profile.click();
    }
    public void clickpost_req()
    {
        post_req.click();
    }

    public String getActiverequests()
    {
        return active_requests.getText();
    }

    public String custome_name_greetings()
    {
        return cname.getText();
    }



}
