package pageObjects.Registrations;

import Abstractclass.common_pro;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.FileNotFoundException;

public class Vendor_registration extends common_pro {

    public WebDriver driver;
    public Vendor_registration(WebDriver driver) throws FileNotFoundException {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);

    }
    @FindBy(xpath = "//div[@class='role-grid'] //button[contains(text(),'Vendor')]")
    WebElement vendorbtn;

    @FindBy(xpath = "//div[@class='mode-toggle'] //button[contains(text(),'Register')]")
    WebElement regbtn;

    @FindBy(xpath = "//input[@formcontrolname='fullName']")
    WebElement fullname;

    @FindBy(xpath ="//input[@formcontrolname='email']" )
    WebElement e_mail;

    @FindBy(xpath = "//input[@formcontrolname='password']")
    WebElement password;

    @FindBy(xpath = "//input[@formcontrolname='mobile']" )
    WebElement mobile;

    @FindBy(xpath = "//input[@formcontrolname='city']" )
    WebElement city;

    @FindBy(xpath = "//input[@formcontrolname='shopName']")
    WebElement shop;

    @FindBy(xpath = "//button[contains(text(),'Register & Continue')]" )
    public WebElement proceedbtn;

    @FindBy(className = "toastmsg")
    WebElement errmsg;



    public void VendorRegistrationprocess(String name, String email,String p_assword,String mob,String c_ity,String shopname)
    {
        vendorbtn.click();
        regbtn.click();
        fullname.sendKeys(name);
        e_mail.sendKeys(email);
        password.sendKeys(p_assword);
        mobile.sendKeys(mob);
        city.sendKeys(c_ity);
        shop.sendKeys(shopname);

    }

    public void proceed_with_Vregistration()
    {
        //WebElement btn = driver.findElement(By.xpath("//button[contains(text(),'Register & Continue')]"));
        proceedbtn.click();
        WebElement btn = driver.findElement(By.cssSelector("span.btn-spinner"));
        waitforElementToDisappear(btn);
        System.out.println(driver.findElement(By.tagName("div h1")).getText());
    }
    public String error_validation()
    {

        waitforWebElementToAppear(errmsg);
        String msg = errmsg.getText();
        return msg;

    }

}
