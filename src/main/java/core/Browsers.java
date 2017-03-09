package core;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public class Browsers {
	static WebDriver driver;
	private static final String mac="MAC"; // mac
	static final String win="WINDOWS"; // windows
	static final String os="os.name"; // OS name
	static final String driverPathEdge = "./src/main/resources/webdrivers/pc/MicrosoftWebDriver.exe";

	public static void setWebDriver(String browser) throws IOException {
        Logger logger = Logger.getLogger("");
        logger.setLevel(Level.OFF);
        String driverPath = ""; // project folder with browsers drivers

        
  if ("Firefox".equals(browser) && System.getProperty(os).toUpperCase().contains(mac)) 
/*	  Multiple markers at this line
		- When doing a String.toLowerCase()/toUpperCase() call, use a Locale
		- Useless parentheses.
		- Potential violation of Law of Demeter (method chain calls)
		- The String literal mac appears 6 times in this file; the first occurrence is 
		  on line 28
		- The String literal os appears 12 times in this file; the first occurrence is 
		 on line 28*/
        {driverPath = "./src/main/resources/webdrivers/mac/geckodriver.sh";}
else if (("Firefox".equals(browser)) && (System.getProperty(os).toUpperCase().contains(win))) 
        {driverPath = "./src/main/resources/webdrivers/pc/geckodriver.exe";}
else if (("Chrome".equals(browser)) && (System.getProperty(os).toUpperCase().contains(mac))) 
        {driverPath = "./src/main/resources/webdrivers/mac/chromedriver";}
else if (("Chrome".equals(browser)) && (System.getProperty(os).toUpperCase().contains(win))) 
        {driverPath = "./src/main/resources/webdrivers/pc/chromedriver.exe";}
else if (("Safari".equals(browser)) && (System.getProperty(os).toUpperCase().contains(mac)))
       {}
else if (("Safari".equals(browser)) && (System.getProperty(os).toUpperCase().contains(win))) 
       {throw new IllegalArgumentException("Safari is not available for Windows");}
else if (("IE".equals(browser)) && (System.getProperty(os).toUpperCase().contains(mac))) 
       {throw new IllegalArgumentException("Internet Explorer is not available for macOS");}
else if (("IE".equals(browser)) && (System.getProperty(os).toUpperCase().contains(win))) 
       {driverPath = "./src/main/resources/webdrivers/pc/IEDriverServer.exe";}
else if (("Edge".equals(browser)) && (System.getProperty(os).toUpperCase().contains(mac))) 
       {throw new IllegalArgumentException("Microsoft Edge is not available for macOS");}
else if (("Edge".equals(browser)) && (System.getProperty(os).toUpperCase().contains(win)))
       {driverPath = driverPathEdge;}
else if ("HtmlUnit".equals(browser)) //Use equals() to compare strings instead of '==' or '!='
									//Position literals first in String comparisons - 
									//that way if the String is null you won't get 
									//a NullPointerException, it'll just return false.
		  {}
else   {throw new IllegalArgumentException("Unknown OS");}

switch (browser) {

case "Firefox":
       System.setProperty("webdriver.gecko.driver", driverPath);
       driver = new FirefoxDriver();
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       driver.manage().window().maximize();
       break;

case "Chrome":
       System.setProperty("webdriver.chrome.driver", driverPath);
       System.setProperty("webdriver.chrome.silentOutput", "true");
       ChromeOptions option = new ChromeOptions();
       if (System.getProperty(os).toUpperCase().contains(mac)) 
       {option.addArguments("-start-fullscreen");}
       else if (System.getProperty(os).toUpperCase().contains(win)) 
       {option.addArguments("--start-maximized");}
       else {throw new IllegalArgumentException("Unknown OS");}
       driver = new ChromeDriver(option);
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       break;

case "Safari":
       SafariOptions options = new SafariOptions();
       options.setUseCleanSession(true);
       options.setPort(55555);
       driver = new SafariDriver(options);
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       driver.manage().window().maximize();
       break;

case "IE":
      DesiredCapabilities IEDesiredCapabilities = DesiredCapabilities.internetExplorer();
		  IEDesiredCapabilities.setCapability (InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		  IEDesiredCapabilities.setCapability (InternetExplorerDriver.INITIAL_BROWSER_URL, "");
       IEDesiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
       IEDesiredCapabilities.setJavascriptEnabled(true);
       IEDesiredCapabilities.setCapability("enablePersistentHover", false);

       System.setProperty("webdriver.ie.driver", driverPath);
       driver = new InternetExplorerDriver(IEDesiredCapabilities);
       driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
       driver.manage().window().maximize();
       break;

case "Edge":
       System.setProperty("webdriver.edge.driver", driverPath);
       driver = new EdgeDriver();
       driver.manage().timeouts().implicitlyWait(190, TimeUnit.SECONDS);
       driver.manage().window().maximize();
       break;

case "HtmlUnit":
       driver = new HtmlUnitDriver();
       ((HtmlUnitDriver) driver).setJavascriptEnabled(true);
       break;

default:
       throw new IllegalArgumentException("Unknown Browser");
        }
 }
	
}
