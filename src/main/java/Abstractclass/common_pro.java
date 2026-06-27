package Abstractclass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class common_pro {

    public WebDriver driver;

    public common_pro(WebDriver driver)
    {
        this.driver = driver;
    }


    public void waitforWebElementToAppear(WebElement findBy)
    {
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(20));
        w.until(ExpectedConditions.visibilityOf(findBy));
    }


    public void waitforElementToDisappear(WebElement e)
    {
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(20));
        w.until(ExpectedConditions.invisibilityOf(e));
    }

    public void gotoPage()
    {
        driver.get("https://frontend-johnprakashbalireddys-projects.vercel.app/");
    }

    public void Error_login_info(WebElement log_proc)
    {
        log_proc.click();
        WebElement btn = driver.findElement(By.cssSelector("span.btn-spinner"));
        waitforElementToDisappear(btn);

    }

}
