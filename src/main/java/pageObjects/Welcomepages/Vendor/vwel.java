package pageObjects.Welcomepages.Vendor;

import Abstractclass.common_pro;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v146.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.FileNotFoundException;
import java.util.List;

public class vwel extends common_pro {

    public WebDriver driver;
    public vwel(WebDriver driver) throws FileNotFoundException {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "div h1")
    WebElement welcome;

    @FindBy(xpath = "//div[@class='stat-tile']")
    List<WebElement> details;

    @FindBy(xpath = "//button[@title='Open profile']")
    WebElement profile_btn;

    @FindBy(xpath = "//div[@class='item']")
    List<WebElement> live_requirements;

    @FindBy(xpath = "//div[@class='item']//button[@class='ghost-btn']")
    List<WebElement> offer_btn;

    @FindBy(xpath = "//article[@class='card panel panel-sticky']")
   List<WebElement> offer_page;  //tocheck whether it exsists or not

    @FindBy(xpath = "//input[@formcontrolname='pricePerDay']")
    WebElement price;

    @FindBy(xpath = "//input[@formcontrolname='vehicleModel']")
    WebElement vehiclemodel;

    @FindBy(xpath = "//input[@formcontrolname='registrationNumber']")
    WebElement reg_no;

    @FindBy(xpath = "//textarea[@formcontrolname='notes']")
    WebElement notes;

    @FindBy(xpath = "//article[@class='card panel panel-sticky']//button[contains(text(),'Clear')]")
    WebElement offerpage_clear_btn;

    @FindBy(xpath = "//article[@class='card panel panel-sticky']//button[contains(text(),'Submit offer')]")
    WebElement offerpage_submit_btn;

    @FindBy(xpath = "//article[@class='card panel panel-sticky']//button[contains(text(),'Cancel')]")
    WebElement offerpage_cancel_btn;

    @FindBy(xpath = "//div[@class='item offer-item']")
    List<WebElement> my_offers_list;

    @FindBy(xpath = "//div[@class='item offer-item']//button[@class='danger-btn']")
    List<WebElement> withdraw_btn;


    public List<WebElement> My_offer_list()
    {
        return my_offers_list;
    }

    public List<WebElement> Withdraw_btn()
    {
        return withdraw_btn;
    }



    public String Welcome_mssg()
    {
        return welcome.getText();
    }

    public List<WebElement> Details()
    {
        return details;
    }

    public void Profile_btn()
    {
        profile_btn.click();
    }

    public List<WebElement> Live_requirements_req()
    {
        return live_requirements;
    }

    public List<WebElement> Send_offer_btn()
    {
        return offer_btn;
    }

    public List<WebElement> Offer_page()
    {
        return offer_page;
    }

    public void Price(String p_rice)
    {
        price.clear();
        price.sendKeys(p_rice);
    }

    public void Vehiclemodel(String model)
    {
        vehiclemodel.clear();
        vehiclemodel.sendKeys(model);
    }

    public void Vehicleno(String no)
    {
        reg_no.clear();
        reg_no.sendKeys(no);
    }

    public void Notes(String txt)
    {
        notes.clear();
        notes.sendKeys(txt);
    }

    public void Offerpage_clear_btn()
    {
        offerpage_clear_btn.click();
    }

    public void Offerpage_submit_btn()
    {
        offerpage_submit_btn.click();
    }

    public void Offerpage_cancel_btn()
    {
        offerpage_cancel_btn.click();
    }












}
