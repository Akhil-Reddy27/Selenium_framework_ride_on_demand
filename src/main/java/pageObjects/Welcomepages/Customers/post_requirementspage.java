package pageObjects.Welcomepages.Customers;

import Abstractclass.common_pro;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.FileNotFoundException;
import java.util.List;

public class post_requirementspage extends common_pro {

    public WebDriver driver;
    public post_requirementspage(WebDriver driver) throws FileNotFoundException {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//select[@formcontrolname='vehicleType']")
    WebElement select_vehicle;

    @FindBy(xpath = "//input[@formcontrolname='startDate']")
    WebElement start_date;

    @FindBy(xpath = "//input[@formcontrolname='endDate']")
    WebElement end_date;

    @FindBy(xpath = "//button[contains(text(),'Next')]")
    WebElement nextbtn;

    @FindBy(xpath = "//input[@formcontrolname='location']")
    WebElement pickup_location;

    @FindBy(xpath = "//input[@formcontrolname='budgetPerDay']")
    WebElement budget_day;

    @FindBy(xpath = "//textarea[@formcontrolname='notes']")
    WebElement notes;

    @FindBy(xpath = "//button[contains(text(),'Back')]")
    WebElement backbtn;

    @FindBy(xpath = "(//div[@class=\"review-card\"]/p)")
    List<WebElement> review_details;

    @FindBy(xpath = "//button[contains(text(),'Post Requirement')]")
    WebElement postreq_btn;

    @FindBy(className = "btn-spinner")
    WebElement spinner;

    @FindBy(xpath = "//h1[contains(text(),'Welcome,')]")
    WebElement success_card;

    public void post_details(String vehicletype,String plocation,String budget,String sdate,String edate,String note)
    {
        Select s = new Select(select_vehicle);
        s.selectByVisibleText(vehicletype);

        start_date.clear();
        end_date.clear();

        start_date.sendKeys(sdate);

        end_date.sendKeys(edate);

        nextbtn.click();
        pickup_location.clear();

        pickup_location.sendKeys(plocation);

        budget_day.clear();

        budget_day.sendKeys(budget);
        notes.clear();
        notes.sendKeys(note);

        nextbtn.click();
        reviews_details();

        postreq_btn.click();
        waitforElementToDisappear(spinner);
        waitforWebElementToAppear(success_card);


    }
    public void reviews_details()
    {
      for(int i=0;i< review_details.size();i++)
      {
          System.out.println(review_details.get(i).getText());
      }
    }








}
