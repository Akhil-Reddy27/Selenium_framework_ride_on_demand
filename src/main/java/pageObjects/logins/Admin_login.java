package pageObjects.logins;

import Abstractclass.common_pro;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.FileNotFoundException;

public class Admin_login extends common_pro {

    public WebDriver driver;
    public Admin_login(WebDriver driver) throws FileNotFoundException {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);

    }
    @FindBy(xpath = "//div[@class='role-grid'] //button[contains(text(),'Admin')]")
    WebElement adminbtn;

    @FindBy(xpath = "//div[@class='mode-toggle'] //button[contains(text(),'Login')]")
    WebElement loginopbtn;

    @FindBy(xpath = "//input[@type='email']")
    WebElement email;

    @FindBy(xpath ="//input[@type='password']" )
    WebElement password;

    @FindBy(xpath = "//button[@class='primary-btn']")
    public WebElement log_proc;

    @FindBy(className = "toastmsg")
    WebElement errmsg;

    public void login_application(String e_mail,String pass)
    {
        adminbtn.click();
        loginopbtn.click();
        email.sendKeys(e_mail);
        password.sendKeys(pass);

    }
    public String error_validation()
    {
        waitforWebElementToAppear(errmsg);
        String msg = errmsg.getText();
        return msg;
    }

    public void login_into()
    {
        log_proc.click();
        WebElement btn = driver.findElement(By.cssSelector("span.btn-spinner"));
        waitforElementToDisappear(btn);
        WebElement tst = driver.findElement(By.xpath("//div/h1[contains(text(),'Admin Dashboard')]"));
        waitforWebElementToAppear(tst);
        System.out.println(tst.getText());
    }

    public void login()
    {
        log_proc.click();
    }
}

