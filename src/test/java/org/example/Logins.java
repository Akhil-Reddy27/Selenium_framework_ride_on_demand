package org.example;

import Utils.baseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.Registrations.Admin_registration;
import pageObjects.logins.Admin_login;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Logins extends baseTest {

    public WebDriver driver;
    public Admin_login al;


    @Test(dataProvider = "admin_login_dataprovider",groups = {"login_users"})
    public void admin_Logins(HashMap<String,String> input) throws IOException {
        al = Admin_login_page();
        al.login_application(input.get("email"),input.get("password"));
        al.login_into();
    }

    @Test(dataProvider = "vendor_login_dataprovider",groups = {"login_users"})
    public void Vendor_Logins(HashMap<String,String> input) throws IOException { vl = Vendor_login_page();
        vl.login_application(input.get("email"),input.get("password"));
        vl.login_into();

    }

    @Test(dataProvider = "customer_login_dataprovider",groups = {"login_users"})
    public void Customer_Logins(HashMap<String,String> input) throws IOException {
        cl = Customer_login_page();
        cl.login_application(input.get("email"),input.get("password"));
        cl.login_into();
    }

    @Test(groups ={"error_test"},invocationCount = 5)
    public void Invalid_Vendor_login() throws IOException {
        vl = Vendor_login_page();
        vl.login_application("akhil12345456378e@gamil.com","akhil78900");

        vl.Error_login_info(vl.log_proc);
        Assert.assertEquals(vl.error_validation(),"Invalid email or password");

    }
    @Test(groups ={"error_test"})
    public void Invalid_Admin_login() throws IOException {
        al = Admin_login_page();
        al.login_application("akhil12345456378e@gamil.com","akhil78900");
        al.Error_login_info(al.log_proc);
        Assert.assertEquals(al.error_validation(),"Invalid email or password");
    }

    @Test(groups ={"error_test"})
    public void Invalid_Customer_login() throws IOException {
        cl = Customer_login_page();
        cl.login_application("akhil12345456378e@gamil.com","akhil78900");
        cl.Error_login_info(cl.log_proc);
        Assert.assertEquals(cl.error_validation(),"Invalid email or password");
    }

    @Test(groups = {"login_with_no_email_validation"})
    public void noemail_customer_reg() throws IOException {
        cl = Customer_login_page();
        cl.login_application(" ","12345678");
        cl.login();
        Assert.assertEquals(cl.error_validation(),"Enter a valid email address to continue.");
Assert.assertTrue(false);
    }


    @Test(groups = {"login_with_no_email_validation"})
    public void noemail_admin_reg() throws IOException {
        al = Admin_login_page();
        al.login_application(" ","akhil78900");
        al.login();
        Assert.assertEquals(al.error_validation(),"Enter a valid email address to continue.");
        Assert.assertTrue(false);
    }

    @Test(groups = {"login_with_no_email_validation"})
    public void noemail_vendor_reg() throws IOException {
        vl = Vendor_login_page();
        vl.login_application(" ","akhil78900");

       vl.login();
        Assert.assertEquals(vl.error_validation(),"Enter a valid email address to continue.");

    }


    @DataProvider
    public Object[][] admin_login_dataprovider() throws IOException {
        List<HashMap<String,String>> data = getJsondataToMap(System.getProperty("user.dir")+"//src//test//java//Data//login_users_data//Admindata.json");
        Object[][] obj = new Object[data.size()][1];
        for(int i=0;i<data.size();i++)
        {
            obj[i][0] = data.get(i);
        }
        return  obj;
    }

    @DataProvider
    public Object[][] customer_login_dataprovider() throws IOException {
        List<HashMap<String,String>> data = getJsondataToMap(System.getProperty("user.dir")+"//src//test//java//Data//login_users_data//Customerdata.json");

        Object[][] obj = new Object[data.size()][1];
        for(int i=0;i<data.size();i++)
        {
            obj[i][0] = data.get(i);
        }
        return  obj;
    }

    @DataProvider
    public Object[][] vendor_login_dataprovider() throws IOException {
        List<HashMap<String,String>> data = getJsondataToMap(System.getProperty("user.dir")+"//src//test//java//Data//login_users_data//Vendordata.json");
        Object[][] obj = new Object[data.size()][1];
        for(int i=0;i<data.size();i++)
        {
            obj[i][0] = data.get(i);
        }
        return  obj;
    }


}
