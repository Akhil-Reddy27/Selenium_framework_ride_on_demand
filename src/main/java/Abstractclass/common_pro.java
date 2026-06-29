package Abstractclass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class common_pro {

    public WebDriver driver;
    public common_pro(WebDriver driver) throws FileNotFoundException {
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

    public void gotoPage() throws IOException {
        Properties props = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//resources//GlobalData.properties");
        props.load(fis);
        driver.get(props.getProperty("baseURL"));
    }

    public void Error_login_info(WebElement log_proc)
    {
        log_proc.click();
        WebElement btn = driver.findElement(By.cssSelector("span.btn-spinner"));
        waitforElementToDisappear(btn);

    }

}
