package core;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.htmlunit.*;
import org.testng.*;
import org.testng.annotations.*;
import java.lang.reflect.Method;
import org.testng.asserts.SoftAssert;

public class ElementsValidationTest2 implements ITest {
	static String in_browser = "Firefox"; // "HtmlUnit" "Firefox" "Chrome"  "Safari"  "IE"  "Edge"
    static WebDriver driver;
    static final String baseUrl = "http://alex.academy/exercises/signup/v1/";
    static String urlOld="1";

    //String csvFile = "./src/resources/test_data/csv/bat/ev.properties";
    //String csvFile = "./src/resources/test_data/csv/bat/elements_validation_chrome.csv";
    String csvFile = "./src/resources/test_data/csv/bat/ev_30_FF.csv";
    SoftAssert s_assert = new SoftAssert();
    
       private String test_name = "";
       public String getTestName() {return test_name;}
       private void setTestName(String a) {test_name = a;}
       
       @BeforeMethod(alwaysRun = true)
       public void bm(Method method, Object[] parameters) {
              setTestName(method.getName());
              Override a = method.getAnnotation(Override.class);
              String testCaseId = (String) parameters[a.id()];
              setTestName(testCaseId);}
       
       @DataProvider(name = "dp")
       public Iterator<String[]> a2d() throws InterruptedException, IOException {
              String cvsLine = "";
              String[] a = null;
              ArrayList<String[]> al = new ArrayList<>();
              BufferedReader br = new BufferedReader(new FileReader(csvFile));
              while ((cvsLine = br.readLine()) != null) {
            	     //System.out.println("cvsLine: "+cvsLine);
                     a = cvsLine.split(";");
                     //System.out.println("line: "+a);
                     al.add(a);}
              br.close();
              return al.iterator();}
       
       @Override
       @Test(dataProvider = "dp")
       public void test(String tc_id, String url, String element_id, String element_size, String element_location) throws IOException {
System.out.println(tc_id);
              if (!url.equals(urlOld)) {getDriver(url); urlOld=url;System.out.println("urlOld="+urlOld+" url="+url);} 
/*            s_assert.assertEquals(isPresent(element_id, driver), equalTo(true));
              s_assert.assertEquals(size(element_id, driver), equalTo(element_size));
              s_assert.assertEquals(location(element_id, driver), equalTo(element_location));}}*/
            	  System.out.println(tc_id);

            	                //getDriver(url);
            	                assertThat(isPresent(element_id, driver), equalTo(true));
            	                assertThat(size(element_id, driver), equalTo(element_size));
            	                assertThat(location(element_id, driver), equalTo(element_location));}

       @AfterTest
       public void am() {driver.quit();}

       public static void getDriver(String url) throws IOException {
    	   Browsers.setWebDriver(in_browser);
    	   driver = Browsers.driver;
    	   driver.get(baseUrl + url);}

       public static boolean isPresent(String element_id, WebDriver wd) {
              driver = wd;
              if (driver.findElements(By.id(element_id)).size() > 0) {return true;}
             else {return false;}}
       
       public static String size(String element_id, WebDriver wd) {
              driver = wd;
              String n = null;
              if (!driver.findElements(By.id(element_id)).isEmpty()) {
                   String s = driver.findElement(By.id(element_id)).getSize().toString(); return s;}
             else {return n;}}
       
       public static String location(String element_id, WebDriver wd) {
              driver = wd;
              String n = null;
              if (!driver.findElements(By.id(element_id)).isEmpty()) {
                  String l = driver.findElement(By.id(element_id)).getLocation().toString(); return l;}
             else {return n;}}
}

