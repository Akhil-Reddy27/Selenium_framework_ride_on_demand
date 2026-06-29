package pageObjects.Validations;

import Abstractclass.common_pro;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.FileNotFoundException;

public class Login_Validations extends common_pro {
    public WebDriver driver;

    public Login_Validations(WebDriver driver) throws FileNotFoundException {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);

    }

    @FindBy(className = "toastmsg")
    WebElement errmsg;



    public String error_validation()
    {

       waitforWebElementToAppear(errmsg);
        String msg = errmsg.getText();
        return msg;

    }
}
