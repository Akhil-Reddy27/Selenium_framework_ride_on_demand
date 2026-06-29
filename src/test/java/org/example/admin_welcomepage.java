package org.example;

import Utils.baseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Welcomepages.Admins.admin_profile;
import pageObjects.Welcomepages.Admins.awel;
import pageObjects.logins.Admin_login;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class admin_welcomepage extends baseTest {


    public WebDriver driver;
    public Admin_login al;
    public awel awp;
    public admin_profile aprofile;


    public void newlogin() throws IOException {
        al = Admin_login_page();
        al.login_application("akhilreddya7@gmail.com","akhil7890");
        al.login_into();

    }



    //@Test(groups = "admin_users")
    public void signout() throws InterruptedException, FileNotFoundException {

        awp = admin_welcomepage();
        Thread.sleep(2000);
        Assert.assertEquals(awp.welcome_mesg(),"Admin Dashboard");
        awp.profile_btn();
        aprofile = admin_profile_btn();

      for(int i=0;i<aprofile.getAccountDetails().size();i++)
      {
          System.out.println(aprofile.getAccountDetails().get(i).getText());
      }
      Thread.sleep(2000);
      aprofile.signout();
    }

    @Test(groups = "admin_users")
    public void active_requirement_details() throws InterruptedException, IOException {
        newlogin();
        awp = admin_welcomepage();

        Assert.assertEquals(awp.welcome_mesg(),"Admin Dashboard");
        //backend data fetching time taking , so using thread.sleep
        Thread.sleep(2900);
        List<WebElement> abc = awp.list_of_details();

        for(int i=0;i<abc.size();i++)
        {
            System.out.println(abc.get(i).getText());
        }

         approve_vendor("Pxpdm");
        reject_vendor("kOZxk");
        signout();

    }
// @Test()
    public void approve_vendor(String shopname) throws InterruptedException, FileNotFoundException {
       // newlogin();
        awp = admin_welcomepage();
        int before_approval = awp.vendor_names().size();
        System.out.println("Total no of requests: "+before_approval);
        System.out.println();
        int k=-1;

        for(int i=0;i<awp.vendor_names().size();i++)
        {
            System.out.println(awp.vendor_names().get(i).getText());
            System.out.println(awp.vendor_details().get(i).getText());

            if(awp.vendor_names().get(i).getText().equalsIgnoreCase(shopname))
            {
                //awp.approver_btn().get(i).click();
                k=i;

                break;
            }
        }
        if(k==-1)
        {
            Assert.fail("Shop name not found: "+shopname);
        }

        WebElement vendor = awp.vendor_names().get(k);

        awp.approver_btn().get(k).click();

        awp.waitforElementToDisappear(vendor);

        Thread.sleep(2900);

        int after_approval = awp.vendor_names().size();

        System.out.println("Total no of requests after approval is : "+after_approval);

        Assert.assertEquals(after_approval,before_approval-1,"Vendor request not yet approved");

        System.out.println("Vendor approved successfully: "+shopname);

    }

//@Test()
    public void reject_vendor(String shopname) throws InterruptedException, FileNotFoundException {
       // newlogin();
        awp = admin_welcomepage();
        int before_rej = awp.vendor_names().size();
        System.out.println("Total no of requests: "+before_rej);
        System.out.println();
        int k=-1;

        for(int i=0;i<awp.vendor_names().size();i++)
        {
            System.out.println(awp.vendor_names().get(i).getText());
            System.out.println(awp.vendor_details().get(i).getText());

            if(awp.vendor_names().get(i).getText().equalsIgnoreCase(shopname))
            {
                //awp.approver_btn().get(i).click();
                k=i;

                break;
            }
        }
        if(k==-1)
        {
            Assert.fail("Shop name not found: "+shopname);
        }

        WebElement vendor = awp.vendor_names().get(k);

        awp.Reject_btn().get(k).click();

        awp.waitforElementToDisappear(vendor);

        Thread.sleep(2000);

        int after_reject = awp.vendor_names().size();

        System.out.println("Total no of requests after reject is : "+after_reject);

        Assert.assertEquals(after_reject,before_rej-1,"Vendor request not approved,check your details");

        System.out.println("Vendor rejected successfully: "+shopname);

    }

    @Test(groups={"admin_dashboard_result","admin_users"})
    public void day_results() throws InterruptedException, IOException {
        newlogin();
        awp = admin_welcomepage();
        Thread.sleep(3000);
        awp.Day_btn();

        System.out.println("Day details: ");
        for(int i=0;i<awp.Total_details().size();i++)
        {
            System.out.println(awp.Total_details().get(i).getText());
        }

    }

    @Test(groups={"admin_dashboard_result", "admin_users"})
    public void month_results() throws InterruptedException, IOException {
        newlogin();
        awp = admin_welcomepage();
        Thread.sleep(3000);
        awp.Month_btn();

        System.out.println("Monthly details: ");
        for(int i=0;i<awp.Total_details().size();i++)
        {
            System.out.println(awp.Total_details().get(i).getText());
        }

    }

    @Test(groups={"admin_dashboard_result","admin_users"})
    public void year_results() throws InterruptedException, IOException {
        newlogin();
        awp = admin_welcomepage();
        Thread.sleep(3000);
        awp.Year_btn();

        System.out.println("Yearly details: ");

        for(int i=0;i<awp.Total_details().size();i++)
        {
            System.out.println(awp.Total_details().get(i).getText());
        }

    }



}
