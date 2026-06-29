package org.example;

import Utils.baseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Welcomepages.Vendor.vendor_profile;
import pageObjects.Welcomepages.Vendor.vwel;
import pageObjects.logins.Vendor_login;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class vendor_welcomepage extends baseTest {


    public vwel vwp;
    public vendor_profile vprofile;
    public Vendor_login vl;

    public void login() throws IOException {
        vl = Vendor_login_page();
        vl.login_application("akhil2004@gmail.com","akhil7890");
        vl.login_into();
    }

   //@Test(groups = {"vendor_users"})
    public void profile_details() throws InterruptedException, IOException {
        login();
        vwp = vendor_welcomepage();
        Thread.sleep(2000);
        Assert.assertEquals(vwp.Welcome_mssg(),"Vendor Dashboard");

        vwp.Profile_btn();
        vprofile = vendor_profile_btn();

        for(int i=0;i<vprofile.getAccountDetails().size();i++)
        {
            System.out.println(vprofile.getAccountDetails().get(i).getText());
        }
        Thread.sleep(2000);

        vprofile.signout();
    }

    @Test(groups = {"vendor_users"})
    public void get_details() throws InterruptedException, IOException {
        login();
        Thread.sleep(2000);
        vwp=vendor_welcomepage();
        for(int i=0;i<vwp.Details().size();i++)
        {
            System.out.println(vwp.Details().get(i).getText());
        }

    }

    @Test(groups = {"vendor_users"})
    public void live_requirement_details() throws InterruptedException, IOException {
        login();
        Thread.sleep(2000);
        vwp=vendor_welcomepage();
        List<WebElement> live_req = vwp.Live_requirements_req();

        for(int i=0;i<live_req.size();i++)
        {
            System.out.println(live_req.get(i).getText());
            System.out.println();
        }
//        refresh_the_page();
//        Thread.sleep(2000);

    }

    @Test(groups = {"vendor_users"})
    public void send_offer_to_request() throws InterruptedException, IOException {
        login();
        // inherited 'driver' instance directly from baseTest
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        vwp = vendor_welcomepage();
        int size = vwp.Live_requirements_req().size();
        System.out.println("Total Requirements: " + size);

        for (int i = 0; i < size; i++) {

            List<WebElement> liveReqs = vwp.Live_requirements_req();
            String currentText = liveReqs.get(i).getText();

            if (currentText.contains("Car")) {
                System.out.println("Processing Match at index " + i + ": [" + currentText.trim() + "]");
                List<WebElement> offer_btn = vwp.Send_offer_btn();
                scrollToAndClickButton(offer_btn, i);
                Assert.assertEquals(vwp.Offer_page().size(), 1, "Offer page did not open for index: " + i);

                vwp.Price("7000");
                vwp.Vehiclemodel("Swift");
                vwp.Vehicleno("TN07HV3428");
                vwp.Notes("first-aid kit and spare tyre included in the car");
                vwp.Offerpage_submit_btn();
                wait.until(ExpectedConditions.visibilityOfAllElements(vwp.Live_requirements_req()));

            } else {
                System.out.println("Skipping item at index " + i + " because it doesn't match 'Car'.");
            }
        }
    }

    @Test(groups = {"vendor_users"})
    public void cancelbtn_the_request_offer_after_filling() throws IOException {
        login();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        vwp = vendor_welcomepage();
        int size = vwp.Live_requirements_req().size();
        System.out.println("Total Requirements: " + size);

        for (int i = 0; i < size; i++) {

            List<WebElement> liveReqs = vwp.Live_requirements_req();
            String currentText = liveReqs.get(i).getText();

            if (currentText.contains("Car")) {
                System.out.println("Processing Match at index " + i + ": [" + currentText.trim() + "]");
                List<WebElement> offer_btn = vwp.Send_offer_btn();
                scrollToAndClickButton(offer_btn, i);
                Assert.assertEquals(vwp.Offer_page().size(), 1, "Offer page did not open for index: " + i);

                vwp.Price("7000");
                vwp.Vehiclemodel("Swift");
                vwp.Vehicleno("TN07HV3428");
                vwp.Notes("first-aid kit and spare tyre included in the car");
                vwp.Offerpage_cancel_btn();
                wait.until(ExpectedConditions.visibilityOfAllElements(vwp.Live_requirements_req()));

            } else {
                System.out.println("Skipping item at index " + i + " because it doesn't match 'Car'.");
            }
        }
    }


    @Test(groups = {"vendor_users"})
    public void clear_btn_for_for_all_requests() throws IOException {
        login();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        vwp = vendor_welcomepage();
        int size = vwp.Live_requirements_req().size();
        System.out.println("Total Requirements: " + size);
        int count =0;
        for (int i = 0; i < size; i++) {

            List<WebElement> liveReqs = vwp.Live_requirements_req();
            String currentText = liveReqs.get(i).getText();

            List<WebElement> offer_btn = vwp.Send_offer_btn();
            scrollToAndClickButton(offer_btn, i);
            Assert.assertEquals(vwp.Offer_page().size(), 1, "Offer page did not open for index: " + i);
             vwp.Offerpage_clear_btn();
             count++;
            wait.until(ExpectedConditions.visibilityOfAllElements(vwp.Live_requirements_req()));

        }
        System.out.println(count);

    }


    @Test(groups = {"vendor_users"})
    public void no_of_offers_you_offered() throws IOException {
        login();
        vwp = vendor_welcomepage();
       System.out.println("My offers given to customers is: "+vwp.My_offer_list().size());
        System.out.println();

        System.out.println("Offered Details");
        for(int i=0;i<vwp.My_offer_list().size();i++)
        {
            System.out.println(vwp.My_offer_list().get(i).getText());
            System.out.println();
        }

    }

    @Test(groups = {"vendor_my_offers_status","vendor_users"})
    public void pending_status_my_offers() throws IOException {
        login();
        int count=0;
        vwp = vendor_welcomepage();

        System.out.println("Pending Offer Details");
        for(int i=0;i<vwp.My_offer_list().size();i++)
        {
            scrollTotheview((List<WebElement>) vwp.My_offer_list(),i);
            if(vwp.My_offer_list().get(i).getText().contains("Pending")) {
                scrollTotheview((List<WebElement>) vwp.My_offer_list(),i);
                System.out.println(vwp.My_offer_list().get(i).getText());
                System.out.println();
                count++;
            }
        }
        if(count==0)
        {
            System.out.println("No offers are pending");
            Assert.fail("No offers are pending");
        }

    }


    @Test(groups = {"vendor_my_offers_status","vendor_users"})
    public void Accepted_status_my_offers() throws IOException {
        login();
        vwp = vendor_welcomepage();
int count =0;
        System.out.println("Accepted Offer Details");
        for(int i=0;i<vwp.My_offer_list().size();i++)
        {
            scrollTotheview((List<WebElement>) vwp.My_offer_list(),i);

            if(vwp.My_offer_list().get(i).getText().contains("Accepted")) {
                scrollTotheview((List<WebElement>) vwp.My_offer_list(),i);
                System.out.println(vwp.My_offer_list().get(i).getText());
                System.out.println();
                count++;
            }
        }
        if(count==0)
        {
            System.out.println("No offers are accepted");
            Assert.fail("No offers are accepted");
        }

    }

    @Test(groups = {"vendor_my_offers_status","vendor_users"})
    public void Rejected_status_my_offers() throws IOException {
        login();
        vwp = vendor_welcomepage();
        int count =0;
        System.out.println("Rejected Offer Details");
        for(int i=0;i<vwp.My_offer_list().size();i++)
        {
            scrollTotheview((List<WebElement>) vwp.My_offer_list(),i);
            if(vwp.My_offer_list().get(i).getText().contains("Rejected")) {
                scrollTotheview((List<WebElement>) vwp.My_offer_list(),i);
                System.out.println(vwp.My_offer_list().get(i).getText());
                System.out.println();
                count++;
            }
        }
        if(count==0)
        {
            System.out.println("No offers are rejected");
        }

    }

    @Test(groups = {"vendor_users"})
    public void withdraw_the_offer_for_all() throws InterruptedException, IOException {
        login();
        vwp = vendor_welcomepage();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        int totalOffers = vwp.Withdraw_btn().size();
        System.out.println("Total offers to withdraw: " + totalOffers);

        for (int i = 0; i < totalOffers; i++) {
            List<WebElement> wit_btn = vwp.Withdraw_btn();
            scrollToAndClickButton(wit_btn, i);
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
                Thread.sleep(1500);
        }
        System.out.println("All offers processed.");
    }


    @Test(groups = {"vendor_users"})
    public void withdrawing_a_particular_offer() throws InterruptedException, IOException {
        withdraw_a_particular_offer("Royal Enfield Classic");
    }

    public void withdraw_a_particular_offer(String bikename) throws InterruptedException, IOException {
        login();
        vwp = vendor_welcomepage();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        int totaloffers = vwp.My_offer_list().size();
      int index =0;
        for(int i=0;i<totaloffers;i++)
        {
            List<WebElement> my_li = vwp.My_offer_list();
            scrollTotheview(my_li, i);
            if(vwp.My_offer_list().get(i).getText().contains(bikename) && vwp.My_offer_list().get(i).getText().contains("Pending"))
            {
                index++;
            }
        }

        if(index==0)
        {
           Assert.fail("No offer present with that bike name: "+bikename);
        }
        else{
            vwp.Withdraw_btn().get(index-1).click();
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
            Thread.sleep(1500);
        }


    }
}
