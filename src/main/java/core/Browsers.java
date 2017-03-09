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
	static final String fireFox="Firefox"; // browser name
	static final String chrome="Chrome"; // browser name
	static final String safari="Safari"; // browser name
	static final String iExplorer="IE"; // browser name
	static final String edge="Edge"; // browser name
	static final String htmlUnit="HtmlUnit"; // browser name
	
	static final String driverPathEdge = "./src/main/resources/webdrivers/pc/MicrosoftWebDriver.exe";
	static final String driverPathChromeMAC = "./src/main/resources/webdrivers/mac/chromedriver";
	static final String driverPathChromeWIN = "./src/main/resources/webdrivers/pc/chromedriver.exe";
	static final String driverPathFFMAC = "./src/main/resources/webdrivers/mac/geckodriver.sh";
	static final String driverPathFFWIN = "./src/main/resources/webdrivers/pc/geckodriver.exe";	
	static final String driverPathIE = "./src/main/resources/webdrivers/pc/IEDriverServer.exe";
	
	
	static Logger log = Logger.getLogger(Browsers.class.getName());
	Browsers() {};

	public static void setWebDriver(String browser) throws IOException {
        Logger logger = Logger.getLogger("");
        logger.setLevel(Level.OFF);
        String driverPath = ""; // project folder with browsers drivers

        
     if (fireFox.equals(browser) && System.getProperty(os).toUpperCase().contains(mac)) 
        {driverPath = driverPathFFMAC;}
else if ((fireFox.equals(browser)) && (System.getProperty(os).toUpperCase().contains(win))) 
        {driverPath = driverPathFFWIN;}
else if ((chrome.equals(browser)) && (System.getProperty(os).toUpperCase().contains(mac))) 
        {driverPath = driverPathChromeMAC;}
else if ((chrome.equals(browser)) && (System.getProperty(os).toUpperCase().contains(win))) 
        {driverPath = driverPathChromeWIN;}
else if ((safari.equals(browser)) && (System.getProperty(os).toUpperCase().contains(mac)))
       {}
else if ((safari.equals(browser)) && (System.getProperty(os).toUpperCase().contains(win))) 
       {throw new IllegalArgumentException("Safari is not available for Windows");}
else if ((iExplorer.equals(browser)) && (System.getProperty(os).toUpperCase().contains(mac))) 
       {throw new IllegalArgumentException("Internet Explorer is not available for macOS");}
else if ((iExplorer.equals(browser)) && (System.getProperty(os).toUpperCase().contains(win))) 
       {driverPath = driverPathIE;}
else if ((edge.equals(browser)) && (System.getProperty(os).toUpperCase().contains(mac))) 
       {throw new IllegalArgumentException("Microsoft Edge is not available for macOS");}
else if ((edge.equals(browser)) && (System.getProperty(os).toUpperCase().contains(win)))
       {driverPath = driverPathEdge;}
else if (htmlUnit.equals(browser))
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
