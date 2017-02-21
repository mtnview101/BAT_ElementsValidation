package core;

import java.io.IOException;
/**
 * Elements Validation
 *
 */
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.htmlunit.*;

public class EV {
	static String in_browser = "Firefox"; // "HtmlUnit" "Firefox" "Chrome"  "Safari"  "IE"  "Edge"
    static WebDriver driver;
    static final String url = "http://alex.academy/exercises/signup/v1/index.php";

       public static void main(String[] args) throws InterruptedException, IOException {
    	   Browsers.setWebDriver(in_browser);
    	   driver = Browsers.driver;
             
              driver.get(url);
              driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
              boolean quotesisPresent = driver.findElements(By.id("id_quotes")).size() > 0;
              System.out.println("quotes isPresent? " + quotesisPresent);
              
              Dimension quotesSize = driver.findElement(By.id("id_quotes")).getSize();
              System.out.println("quotes Size Width: " + quotesSize.getWidth());
              System.out.println("quotes Size Height: " + quotesSize.getHeight());
              System.out.println("quotes Size: " + quotesSize.toString());
              
              Point quoteLocation = driver.findElement(By.id("id_quotes")).getLocation();
              System.out.println("quotes Location X: " + quoteLocation.getX());
              System.out.println("quotes Location Y: " + quoteLocation.getY());
              System.out.println("quotes Location: " + quoteLocation.toString());
driver.quit();}}

