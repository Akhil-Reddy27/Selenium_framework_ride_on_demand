package Utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pageObjects.Registrations.Admin_registration;
import pageObjects.Registrations.Vendor_registration;
import pageObjects.Registrations.customer_registration;
import pageObjects.Welcomepages.Admins.admin_profile;
import pageObjects.Welcomepages.Admins.awel;
import pageObjects.Welcomepages.Customers.cwelpage;
import pageObjects.Welcomepages.Customers.post_requirementspage;
import pageObjects.Welcomepages.Customers.profile_page;
import pageObjects.Welcomepages.Vendor.vendor_profile;
import pageObjects.Welcomepages.Vendor.vwel;
import pageObjects.logins.Admin_login;
import pageObjects.logins.Customer_login;
import pageObjects.logins.Vendor_login;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class baseTest {

    public WebDriver driver;
    public customer_registration cr;
    public Admin_registration ar;
    public Vendor_registration vr;
  public Admin_login al;
  public Vendor_login vl;
  public Customer_login cl;
  public cwelpage cwp;
  public profile_page pag;
  public post_requirementspage prp;

  public awel awp;

  public admin_profile aprofile;

  public vwel vwp;
  public vendor_profile vprofile;

    public WebDriver initialize() throws IOException {

        Properties props = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//resources//GlobalData.properties");
        props.load(fis);
        //we are using to read data from mvn terminal if its null we read the value from our globaldata properties file
        String browsername = System.getProperty("browser")!=null?System.getProperty("browser"):props.getProperty("browser");

        if(browsername.contains("chrome")){
            ChromeOptions chromeOptions = new ChromeOptions();
            if(browsername.contains("headless")) {
                chromeOptions.addArguments("--headless=new");
                chromeOptions.addArguments("--window-size=1920,1080");
            }
            driver = new ChromeDriver(chromeOptions);

        }
        else if(browsername.equalsIgnoreCase("firefox")){
            driver = new FirefoxDriver();
        }
        else if(browsername.equalsIgnoreCase("safari"))
        {
            driver = new SafariDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }


    @BeforeMethod(alwaysRun = true)
    public void Set_up(org.testng.ITestContext context) throws IOException // Add context parameter here
    {
        driver = initialize();
        // Save the driver instance into TestNG's context attributes so the listener can easily pull it
        context.setAttribute("WebDriver", driver);
    }


    public void refresh_the_page()
    {
        driver.navigate().refresh();
    }


    public customer_registration customer_landing_page()
    {

        cr = new customer_registration(driver);
        cr.gotoPage();
    return cr;
    }

    public void scrollToAndClickButton(List<WebElement> Rdetails_btn,int index) {
        WebElement targetButton = Rdetails_btn.get(index);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", targetButton);
        targetButton.click();
    }

    public void scrollTotheview(List<WebElement> Rdetails_btn,int index) {
        WebElement targetButton = Rdetails_btn.get(index);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", targetButton);
    }
    public void navigateBackToWelcomePage() {
        driver.navigate().back();
    }

    public void accept_alerts()
    {
        driver.switchTo().alert().accept();
    }



    public Admin_registration admin_landing_page()
    {

        ar = new Admin_registration(driver);
        ar.gotoPage();
        return ar;
    }

    public Vendor_registration vendor_landing_page()
    {

        vr = new Vendor_registration(driver);
        vr.gotoPage();
        return vr;
    }


    public Admin_login Admin_login_page()
    {
        al= new Admin_login(driver);
        al.gotoPage();
        return al;
    }
    public Customer_login Customer_login_page()
    {
        cl = new Customer_login(driver);
        cl.gotoPage();
        return cl;
    }

    public Vendor_login Vendor_login_page()
    {
        vl = new Vendor_login(driver);
        vl.gotoPage();
        return vl;
    }

    public cwelpage customer_welcomepage()
    {
        cwp = new cwelpage(driver);
        return cwp;
    }

    public awel admin_welcomepage()
    {
        awp = new awel(driver);
        return awp;
    }

    public vwel vendor_welcomepage()
    {
        vwp = new vwel(driver);
        return vwp;
    }

    public vendor_profile vendor_profile_btn()
    {
        vprofile = new vendor_profile(driver);
        return vprofile;
    }


    public admin_profile admin_profile_btn()
    {
        aprofile = new admin_profile(driver);
        return aprofile;
    }


    public profile_page profile_btn()
    {
        pag = new profile_page(driver);
        return pag;

    }


    public post_requirementspage post_req()
    {
        prp = new post_requirementspage(driver);
        return prp;

    }

   @AfterMethod(alwaysRun = true)
    public void teardown()
    {
        driver.quit();
    }

    public List<HashMap<String, String>> getJsondataToMap(String filepath) throws IOException {
        String jsoncontent = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String,String>> data = mapper.readValue(jsoncontent, new TypeReference<List<HashMap<String,String>>>(){});
        return data;
    }


    public String  getScreenshot(String testcasename,WebDriver driver) throws IOException {
        TakesScreenshot ts = ((TakesScreenshot) driver);
        File source = ts.getScreenshotAs(OutputType.FILE);
        File ds = new File(System.getProperty("user.dir")+"//reports//" +testcasename+".png");
        FileUtils.copyFile(source,ds);
        return testcasename + ".png";
    }




}
