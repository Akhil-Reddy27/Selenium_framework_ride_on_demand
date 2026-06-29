package pageObjects.Registrations;

import Abstractclass.common_pro;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.FileNotFoundException;

public class Admin_registration extends common_pro {

    public WebDriver driver;

    public Admin_registration(WebDriver driver) throws FileNotFoundException {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//div[@class='role-grid'] //button[contains(text(),'Admin')]")
   private WebElement adminbtn;

    @FindBy(xpath = "//div[@class='mode-toggle'] //button[contains(text(),'Register')]")
   private WebElement regbtn;

    @FindBy(xpath = "//input[@formcontrolname='fullName']")
  private  WebElement fullname;

    @FindBy(xpath ="//input[@formcontrolname='email']" )
   private WebElement e_mail;

    @FindBy(xpath = "//input[@formcontrolname='password']")
   private WebElement password;

    @FindBy(xpath = "//input[@formcontrolname='mobile']" )
   private WebElement mobile;

    @FindBy(xpath = "//input[@formcontrolname='city']" )
   private WebElement city;

    @FindBy(xpath = "//button[contains(text(),'Register & Continue')]" )
    public WebElement proceedbtn;
    @FindBy(className = "toastmsg")
    WebElement errmsg;


    public void AdminRegistrationprocess(String name, String email,String p_assword,String mob,String c_ity)
    {
        adminbtn.click();
        regbtn.click();
        fullname.sendKeys(name);
        e_mail.sendKeys(email);
        password.sendKeys(p_assword);
        mobile.sendKeys(mob);
        city.sendKeys(c_ity);

    }

    public void proceed_with_Aregistration()
    {

        proceedbtn.click();
        WebElement btn = driver.findElement(By.cssSelector("span.btn-spinner"));
        waitforElementToDisappear(btn);
        waitforElementToDisappear(btn);
        WebElement tst = driver.findElement(By.xpath("//div/h1[contains(text(),'Admin Dashboard')]"));
        waitforWebElementToAppear(tst);
        System.out.println(tst.getText());
        //waitforWebElementToAppear();
    }
    public String error_validation()
    {

        waitforWebElementToAppear(errmsg);
        String msg = errmsg.getText();
        return msg;

    }
}
