package org.example;

import Utils.baseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.Registrations.Admin_registration;
import pageObjects.Registrations.customer_registration;
import pageObjects.logins.Admin_login;
import pageObjects.logins.Customer_login;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class registartion extends baseTest {

public WebDriver driver;

 @Test(dataProvider = "Customer_registration_dataprovider",groups = {"reg_users"})
    public void Customer_Registrations(HashMap<String,String> input) throws IOException {
cr =customer_landing_page();
       cr.customerRegistrationprocess(input.get("name"),input.get("email"),input.get("password"),input.get("phone"),input.get("location"));
        cr.proceed_with_Cregistration();
    }


@Test(dataProvider = "Admin_registration_dataprovider",groups = {"reg_users"})
    public void Admin_Registration(HashMap<String,String> input) throws IOException {
        ar =admin_landing_page();
       ar.AdminRegistrationprocess(input.get("name"),input.get("email"),input.get("password"),input.get("phone"),input.get("location"));
       ar.proceed_with_Aregistration();

    }


    @Test(dataProvider = "Vendor_registration_dataprovider",groups = {"reg_users"})
    public void Vendor_Registration(HashMap<String,String> input) throws IOException {
        vr = vendor_landing_page();
        vr.VendorRegistrationprocess(input.get("name"),input.get("email"),input.get("password"),input.get("phone"),input.get("location"),input.get("companyName"));
        vr.proceed_with_Vregistration();
    }


    @Test(groups ={"error_test"},invocationCount = 1)
    public void duplicate_email_Vendor_regsitartion() throws IOException {
        vr = vendor_landing_page();
        vr.VendorRegistrationprocess("Akhil Reddy","akhil123456@gamil.com","akhil789000","9989884094","hyd","ab vechiles");
       vr.Error_login_info(vr.proceedbtn);
       // Assert.assertEquals(vr.error_validation(),"Email akhil123456@gamil.com is already registered. Please login.");
        //additional to fail test
        //Assert.assertTrue(false);


    }
    @Test(groups ={"error_test"})
    public void duplicate_email_Admin_regsitartion() throws IOException {
        ar = admin_landing_page();
       ar.AdminRegistrationprocess("Akhil Reddy","akhil123456@gamil.com","akhil789000","9989884094","hyd");
        ar.Error_login_info(ar.proceedbtn);
        Assert.assertEquals(ar.error_validation(),"Email akhil123456@gamil.com is already registered as VENDOR.");
        Assert.assertTrue(false);
    }

    @Test(groups ={"error_test"})
    public void duplicate_email_Customer_regsitartion() throws IOException {
        cr = customer_landing_page();
        cr.customerRegistrationprocess("Akhil Reddy","akhil123456@gamil.com","akhil789000","9989884094","hyd");
        cr.Error_login_info(cr.proceedbtn);
        Assert.assertEquals(cr.error_validation(),"Email akhil123456@gamil.com is already registered as VENDOR.");
        Assert.assertTrue(false);
    }

    @Test(groups ={"error_test"})
    public void noemail_customer_reg() throws IOException {
        cr = customer_landing_page();
        cr.customerRegistrationprocess("Akhil Reddy"," ","akhil789000","9989884094","hyd");
        cr.proceedbtn.click();
        Assert.assertEquals(cr.error_validation(),"Enter a valid email address to continue.");
        Assert.assertTrue(false);

    }

    @Test(groups ={"error_test"})
    public void noemail_admin_reg() throws IOException {
        ar = admin_landing_page();
        ar.AdminRegistrationprocess("Akhil Reddy"," ","akhil789000","9989884094","hyd");
        ar.proceedbtn.click();
        Assert.assertEquals(ar.error_validation(),"Enter a valid email address to continue.");
        Assert.assertTrue(false);
    }

    @Test(groups ={"error_test"})
    public void noemail_vendor_reg() throws IOException {
        vr = vendor_landing_page();
        vr.VendorRegistrationprocess("Akhil Reddy"," ","akhil789000","9989884094","hyd","ab vechiles");
        vr.proceedbtn.click();
        Assert.assertEquals(vr.error_validation(),"Enter a valid email address to continue.");
        Assert.assertTrue(false);
    }

    @Test(groups = {"error_test"})
    public void customer_invalid_email_missing_at_symbol() throws IOException {
        // Rule: "@" is a must for email formatting
        cr = customer_landing_page();
        cr.customerRegistrationprocess("Akhil Reddy", "akhilreddy.com", "akhil789000", "9989884094", "hyd");
        cr.proceedbtn.click();

        Assert.assertEquals(cr.error_validation(), "Enter a valid email address to continue.");
    }

    @Test(groups = {"error_test"})
    public void customer_invalid_email_domain_validation() throws IOException {
        // Rule: Domain must be structurally valid (e.g., missing top-level extension like .com/.in)
        cr = customer_landing_page();
        cr.customerRegistrationprocess("Akhil Reddy", "akhil@com.", "akhil789000", "9989884094", "hyd");
        cr.proceedbtn.click();

        Assert.assertEquals(cr.error_validation(), "Enter a valid email address to continue.");
        Assert.assertTrue(false);
    }

    @Test(groups = {"error_test"})
    public void customer_city_validation() throws IOException {

        cr = customer_landing_page();
        cr.customerRegistrationprocess("Akhil Reddy", "akhil1@gmail.com", "akhil789000", "9989884194", " ");
        cr.proceedbtn.click();
        Assert.assertEquals(cr.error_validation(), "Validation failed");
        Assert.assertTrue(false);
    }

    @Test(groups = {"error_test"})
    public void customer_invalid_password_length_less_than_eight() throws IOException {
        // Rule: Password must be >= 8 characters
        cr = customer_landing_page();
        cr.customerRegistrationprocess("Akhil Reddy", "akhil_valid@gmail.com", "1234567", "9989884094", "hyd");
        cr.proceedbtn.click();

        Assert.assertEquals(cr.error_validation(), "Password must be at least 8 characters.");
        Assert.assertTrue(false);
    }


    @Test(groups = {"error_test"})
    public void vendor_invalid_email_missing_at_symbol() throws IOException {
        // Rule: "@" is a must for email formatting
        vr = vendor_landing_page();
        vr.VendorRegistrationprocess("Akhil Reddy", "akhilreddy.com", "akhil789000", "9989884094", "hyd", "ab vehicles");
        vr.proceedbtn.click();

        Assert.assertEquals(vr.error_validation(), "Enter a valid email address to continue.");
        Assert.assertTrue(false);
    }


    @Test(groups = {"error_test"})
    public void vendor_city_validation() throws IOException {

        vr = vendor_landing_page();
        vr.VendorRegistrationprocess("Akhil Reddy", "akhil@gmail.com", "akhil789000", "9989884094", " ","ab");
        vr.proceedbtn.click();
        Assert.assertEquals(vr.error_validation(), "Validation failed");
        Assert.assertTrue(false);
    }



    @Test(groups = {"error_test"})
    public void vendor_invalid_email_domain_validation() throws IOException {
        // Rule: Domain must be structurally valid
        vr = vendor_landing_page();
        vr.VendorRegistrationprocess("Akhil Reddy", "akhil@com.", "akhil789000", "9989884094", "hyd", "ab vehicles");
        vr.proceedbtn.click();

        Assert.assertEquals(vr.error_validation(), "Enter a valid email address to continue.");
        Assert.assertTrue(false);
    }

    @Test(groups = {"error_test"})
    public void vendor_invalid_password_length_less_than_eight() throws IOException {
        // Rule: Password must be >= 8 characters
        vr = vendor_landing_page();
        vr.VendorRegistrationprocess("Akhil Reddy", "akhil_valid@gmail.com", "1234567", "9989884094", "hyd", "ab vehicles");
        vr.proceedbtn.click();

        Assert.assertEquals(vr.error_validation(), "Password must be at least 8 characters.");
        Assert.assertTrue(false);
    }



    @Test(groups = {"error_test"})
    public void admin_invalid_email_missing_at_symbol() throws IOException {
        // Rule: "@" is a must for email formatting
        ar = admin_landing_page();
        ar.AdminRegistrationprocess("Akhil Reddy", "akhilreddy.com", "akhil789000", "9989884094", "hyd");
        ar.proceedbtn.click();

        Assert.assertEquals(ar.error_validation(), "Enter a valid email address to continue.");
        Assert.assertTrue(false);
    }

    @Test(groups = {"error_test"})
    public void admin_city_validation() throws IOException {
        // Rule: Domain must be structurally valid (e.g., missing top-level extension like .com/.in)
        ar = admin_landing_page();
        ar.AdminRegistrationprocess("Akhil Reddy", "akhil@gmail.com", "akhil789000", "9989884094", " ");
        ar.proceedbtn.click();
        Assert.assertEquals(ar.error_validation(), "Validation failed");
        Assert.assertTrue(false);
    }

    @Test(groups = {"error_test"})
    public void admin_invalid_email_domain_validation() throws IOException {
        // Rule: Domain must be structurally valid
        ar = admin_landing_page();
        ar.AdminRegistrationprocess("Akhil Reddy", "akhil@com.", "akhil789000", "9989884094", "hyd");
        ar.proceedbtn.click();

        Assert.assertEquals(ar.error_validation(), "Enter a valid email address to continue.");
        Assert.assertTrue(false);
    }

    @Test(groups = {"error_test"})
    public void admin_invalid_password_length_less_than_eight() throws IOException {
        // Rule: Password must be >= 8 characters
        ar = admin_landing_page();
        ar.AdminRegistrationprocess("Akhil Reddy", "akhil_valid@gmail.com", "1234567", "9989884094", "hyd");
        ar.proceedbtn.click();

        Assert.assertEquals(ar.error_validation(), "Password must be at least 8 characters.");
    }



    @DataProvider
    public Object[][] Admin_registration_dataprovider() throws IOException {
        List<HashMap<String,String>> data = getJsondataToMap(System.getProperty("user.dir")+"//src//test//java//Data//registration_user_data//Admin_regdata.json");
        Object[][] obj = new Object[data.size()][1];
        for(int i=0;i<data.size();i++)
        {
            obj[i][0] = data.get(i);
        }
        return  obj;
    }

    @DataProvider
    public Object[][] Vendor_registration_dataprovider() throws IOException {
        List<HashMap<String,String>> data = getJsondataToMap(System.getProperty("user.dir")+"//src//test//java//Data//registration_user_data//Vendor_regdata.json");
        Object[][] obj = new Object[data.size()][1];
        for(int i=0;i<data.size();i++)
        {
            obj[i][0] = data.get(i);
        }
        return  obj;
    }

    @DataProvider
    public Object[][] Customer_registration_dataprovider() throws IOException {
        List<HashMap<String,String>> data = getJsondataToMap(System.getProperty("user.dir")+"//src//test//java//Data//registration_user_data//Customer_regdata.json");
        Object[][] obj = new Object[data.size()][1];
        for(int i=0;i<data.size();i++)
        {
            obj[i][0] = data.get(i);
        }
        return  obj;
    }






}
