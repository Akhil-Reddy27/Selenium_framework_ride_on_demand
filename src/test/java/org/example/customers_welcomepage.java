package org.example;

import Utils.baseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Welcomepages.Customers.cwelpage;
import pageObjects.Welcomepages.Customers.post_requirementspage;
import pageObjects.Welcomepages.Customers.profile_page;
import pageObjects.logins.Customer_login;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class customers_welcomepage extends baseTest {
    public WebDriver driver;
    public Customer_login cl;
    public cwelpage cwp;
    public profile_page pag;
    public post_requirementspage prp;


    //@Test()

    public void login() throws IOException {
        cl = Customer_login_page();
    cl.login_application("akhilfire@gmail.com","akhil7890");
    cl.login_into();

    }
    public void profile_details_logout() throws InterruptedException, FileNotFoundException {
//
//        Thread.sleep(3000);

        //login();
        cwp = customer_welcomepage();
        System.out.println(cwp.getActiverequests());
        Assert.assertEquals(cwp.custome_name_greetings(),"Welcome, Akhil Reddy");
        cwp.clickOpen_profile();

        pag = profile_btn();

        List<WebElement> ab= pag.getAccountDetails();
        for(int i=0;i<ab.size();i++)
        {
            System.out.println(ab.get(i).getText());
        }
      Thread.sleep(3000);
        pag.signout();
    }

    @Test(groups = {"customer_users"})
    public void create_postrequirement() throws InterruptedException, IOException {
        login();
        Thread.sleep(3000);
        cwp = customer_welcomepage();
        cwp.clickpost_req();
        prp = post_req();
        prp.post_details("Bike","coimbatore","900","27-09-2026","29-09-2026","vechicle in neat and good condition");

        profile_details_logout();

//->  //div[@class='item clickable']/div[@class='item-main']  for list of data of requests
        //->   //div[@class='item clickable']/div[@class='item-action']   for button to click on requirements
    //->  //section/p[contains(text(),'No offers available for this requirement')]   for no offer found page

    }

    @Test(groups = {"customer_users"})
    public void printing_details_for_active_requests_and_accepting_offers_based_user_max_budget() throws InterruptedException, IOException {
        login();
        Thread.sleep(3000);
        cwp = customer_welcomepage();
       List<WebElement> ab = cwp.Allin_request_details();
       System.out.println(ab.size());
       for(int i=0;i<ab.size();i++)
       {
           System.out.println(ab.get(i).getText());
          System.out.println();
       }
       checking_for_offers_for_active_requests(400);


    }



    public void checking_for_offers_for_active_requests(double maxUserBudgetPerDay) throws InterruptedException, FileNotFoundException {

        cwp = customer_welcomepage();

        int totalRequests = cwp.Rdetails_btn().size();
        System.out.println("Total requirements found: " + totalRequests);

        int clickIndex = 0;

        for (int i = 0; i < totalRequests; i++) {

            System.out.println("\n--------------------------------------");
            System.out.println("Checking Requirement : " + (i + 1));

            String currentStatus = "";

            try {
                currentStatus = cwp.App_text_present().get(i).getText().trim();
            } catch (Exception e) {
                currentStatus = "";
            }

            System.out.println("Status : " + currentStatus);

            if (currentStatus.contains("Accepted")) {
                System.out.println("Already accepted. Skipping...");
                continue;
            }

            scrollToAndClickButton(cwp.Rdetails_btn(), clickIndex);
            clickIndex++;

            Thread.sleep(2000);

            if (cwp.mssg_req_active().size() > 0) {

                System.out.println("No offers available.");

                navigateBackToWelcomePage();
                Thread.sleep(3000);
                continue;
            }

            int offerCount = cwp.Toffercards().size();

            System.out.println("Offer Cards : " + offerCount);
            System.out.println("Prices      : " + cwp.Price_details().size());
            System.out.println("Vendors     : " + cwp.Offer_details().size());
            System.out.println("Accept Btns : " + cwp.Accept_btn_offer().size());

            double lowestPrice = Double.MAX_VALUE;
            int selectedIndex = -1;

            int loopSize = Math.min(
                    cwp.Price_details().size(),
                    cwp.Offer_details().size()
            );

            for (int j = 0; j < loopSize; j++) {

                String priceText = cwp.Price_details().get(j).getText();

                System.out.println("--------------------------------");
                System.out.println("Offer " + (j + 1));
                System.out.println("Vendor :");
                System.out.println(cwp.Offer_details().get(j).getText());
                System.out.println("Price : " + priceText);

                try {

                    String cleanPrice = priceText.replaceAll("[^0-9]", "");

                    double price = Double.parseDouble(cleanPrice);

                    if (price <= maxUserBudgetPerDay && price < lowestPrice) {

                        lowestPrice = price;
                        selectedIndex = j;
                    }

                } catch (Exception e) {

                    System.out.println("Unable to parse : " + priceText);
                }
            }

            if (selectedIndex == -1) {

                System.out.println("No offer found within budget.");

                navigateBackToWelcomePage();
                Thread.sleep(3000);
                continue;
            }

            System.out.println("Lowest Price = " + lowestPrice);

            List<WebElement> acceptButtons = cwp.Accept_btn_offer();

            if (acceptButtons.size() == 0) {

                System.out.println("No Accept button present.");

                navigateBackToWelcomePage();
                Thread.sleep(3000);
                continue;
            }

            if (selectedIndex >= acceptButtons.size()) {

                System.out.println("Selected index = " + selectedIndex);
                System.out.println("Accept button count = " + acceptButtons.size());

                selectedIndex = 0;
            }

            System.out.println("Clicking Accept Button...");

            scrollToAndClickButton(acceptButtons, selectedIndex);

            Thread.sleep(3000);

            navigateBackToWelcomePage();

            Thread.sleep(3000);
        }

        profile_details_logout();
    }
}
