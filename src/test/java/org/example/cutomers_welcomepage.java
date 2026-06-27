package org.example;

import Utils.baseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Welcomepages.Customers.cwelpage;
import pageObjects.Welcomepages.Customers.post_requirementspage;
import pageObjects.Welcomepages.Customers.profile_page;
import pageObjects.logins.Customer_login;

import java.util.List;

public class cutomers_welcomepage extends baseTest {
    public WebDriver driver;
    public Customer_login cl;
    public cwelpage cwp;
    public profile_page pag;
    public post_requirementspage prp;


    //@Test()
    public void profile_details_logout() throws InterruptedException {
//        cl = Customer_login_page();
//        cl.login_application("akhilfire@gmail.com","akhil7890");
//        cl.login_into();
//        Thread.sleep(3000);

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

   // @Test()
    public void create_postrequirement() throws InterruptedException {
        cl = Customer_login_page();
        cl.login_application("akhilfire@gmail.com","akhil7890");
        cl.login_into();
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

    @Test()
    public void printing_details_for_active_requests() throws InterruptedException {
        cl = Customer_login_page();
        cl.login_application("akhilfire@gmail.com","akhil7890");
        cl.login_into();
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



    public void checking_for_offers_for_active_requests( double maxUserBudgetPerDay) throws InterruptedException {


//        cl = Customer_login_page();
//        cl.login_application("akhilfire@gmail.com", "akhil7890");
//        cl.login_into();
//        Thread.sleep(3000); // Wait for dashboard to load

        cwp = customer_welcomepage();
        int totalRequests = cwp.Rdetails_btn().size();
        System.out.println("Total requirements found: " + totalRequests);
        int CountNoOffers = 0;

        for (int i = 0; i < totalRequests; i++) {
            System.out.println("\n--- Checking Requirement #" + (i + 1) + " ---");
            System.out.println("Target Criteria: Max budget of Rs " + maxUserBudgetPerDay + "/day");

           //Protects from IndexOutOfBoundsException if status list size doesn't match total rows
            String currentStatusText = "";
            try {
                currentStatusText = cwp.App_text_present().get(i).getText().trim();
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                // If row position doesn't exist in status tracking array, it's open/unaccepted
                currentStatusText = "Open/Pending";
            }
            System.out.println("Requirement Row Status: " + currentStatusText);

            // If already accepted, log it and instantly continue loop
            if (currentStatusText.contains("Accepted") || currentStatusText.equals("Accepted →")) {
                System.out.println("Result: Already accepted. Skipping this requirement.");
                System.out.println("----------------------------------");
                continue;
            }

            // Click the open requirement row item details button safely
            scrollToAndClickButton(cwp.Rdetails_btn(), i);
            Thread.sleep(2500); // Give details view time to load

            // Check if the "No offers" element is present on screen
            boolean hasNoOffersMessage = cwp.mssg_req_active().size() > 0;

            if (hasNoOffersMessage) {
                System.out.println("Result: No offers available for this requirement.");
                CountNoOffers++;
            } else {
                int offerCardCount = cwp.Toffercards().size();
                System.out.println("Found " + offerCardCount + " offer card(s).");

                int chosenOfferIndex = -1;
                double lowestValidCost = Double.MAX_VALUE;
                for (int j = 0; j < offerCardCount; j++) {
                    String priceText = cwp.Price_details().get(j).getText();
                    System.out.println("  -> Card #" + (j + 1) + " Raw Price Text: " + priceText);
                    System.out.println("     Vendor: " + cwp.Offer_details().get(j).getText());

                    try {
                        String cleanPrice = priceText.replaceAll("[^0-9.]", "");
                        double currentCost = Double.parseDouble(cleanPrice);

                        // BUDGET FILTERING CONDITION: Must be <= user max budget AND cheaper than previously found valid matches
                        if (currentCost <= maxUserBudgetPerDay && currentCost < lowestValidCost) {
                            lowestValidCost = currentCost;
                            chosenOfferIndex = j;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("⚠️ Could not parse price text to number: " + priceText);
                    }
                }

                // Accept the single matching card if a valid offer within your budget was identified
                if (chosenOfferIndex != -1) {
                    System.out.println("🎯 Match Found Within Budget! Card #" + (chosenOfferIndex + 1) + " at: Rs " + lowestValidCost + "/day");
                    System.out.println("Action: Clicking 'Accept' button.");

                    scrollToAndClickButton(cwp.Accept_btn_offer(), chosenOfferIndex);
                    Thread.sleep(5000); // Wait for acceptance action to process
                } else {
                    System.out.println("❌ No offers for this requirement met your criteria of being <= Rs " + maxUserBudgetPerDay + "/day.");
                }
            }

            // Navigate back to main dashboard list
            navigateBackToWelcomePage();
            Thread.sleep(6000);
            System.out.println("----------------------------------");
        } // End of outer For loop

      profile_details_logout();

    }
}
