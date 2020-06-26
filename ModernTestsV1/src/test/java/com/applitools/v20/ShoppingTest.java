package com.applitools.v20;

import com.applitools.eyes.*;
import com.applitools.eyes.selenium.*;
import com.applitools.eyes.selenium.fluent.*;
import com.applitools.eyes.visualgrid.model.DeviceName;
import com.applitools.eyes.visualgrid.model.ScreenOrientation;
import com.applitools.eyes.visualgrid.services.*;

import org.junit.*;
import org.junit.runners.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;

/**
 * ShoppingTest
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ShoppingTest
{
  private static Eyes EYES;
  private static WebDriver WEB_DRIVER;
  private static VisualGridRunner RUNNER;

  /**
   * task1
   */
  @Test public void task1()
  {
    // Navigate to the url we want to test
    WEB_DRIVER.get("https://demo.applitools.com/gridHackathonV1.html");

    //open eyes
    EYES.open(WEB_DRIVER, "AppliFashion", "Task 1");
    //Check whole window
    EYES.checkWindow("Cross-Device Elements Test");
    //close eyes
    EYES.closeAsync();
  }


  /**
   * task2
   *
   * @throws InterruptedException
   */
  @Test public void task2() throws InterruptedException
  {
    EYES.open(WEB_DRIVER, "AppliFashion", "Task 2");

    WEB_DRIVER.findElement(By.id("ti-filter")).click();

    Thread.sleep(3000);

    WebElement labelContainer = WEB_DRIVER.findElement(By.id("LABEL__containerc__104"));

//    Thread.sleep(1000);
    labelContainer.click();

    WEB_DRIVER.findElement(By.id("filterBtn")).click();

    //Check region
    EYES.check("Filter Results", Target.region(By.id("product_grid")));
    //close eyes
    EYES.closeAsync();
  }


  /**
   * task3
   *
   * @throws InterruptedException
   */
  @Test public void task3() throws InterruptedException
  {
    EYES.open(WEB_DRIVER, "AppliFashion", "Task 3");
    Thread.sleep(3000);
    WebElement productGrid = WEB_DRIVER.findElement(By.id("product_grid"));
    WebElement gridItem = productGrid.findElement(By.className("grid_item"));
    gridItem.click();
    //Check region
    EYES.checkWindow("Product Details test", true);
    //close eyes
    EYES.closeAsync();
  }


  /**
   * tearDown
   */
  @AfterClass public static void tearDown()
  {
    // Close the browser
    WEB_DRIVER.quit();

    TestResultsSummary allTestResults = RUNNER.getAllTestResults(false);
    System.out.println(allTestResults);
  }


  /**
   * doSetUp
   */
  @BeforeClass public static void doSetUp()
  {
    // Create a new chrome web driver
    WEB_DRIVER = new ChromeDriver();

    // Create a runner with concurrency of 1
    RUNNER = new VisualGridRunner(10);

    // Create Eyes object with the runner, meaning it'll be a Visual Grid eyes.
    EYES = new Eyes(RUNNER);

    // Initialize eyes Configuration
    Configuration config = new Configuration();

    // You can get your api key from the Applitools dashboard
    config.setApiKey("8AP7TpkRPS107AWIXxwQMpJhSJI6ljUXxc99rkMp15BY6c110");

    // create a new batch info instance and set it to the configuration
    config.setBatch(new BatchInfo("UFG Hackathon"));

    // Add browsers with different viewports
    config.addBrowser(1200, 700, BrowserType.CHROME);
    config.addBrowser(1200, 700, BrowserType.FIREFOX);
    config.addBrowser(1200, 700, BrowserType.EDGE_CHROMIUM);
    config.addBrowser(768, 700, BrowserType.CHROME);
    config.addBrowser(768, 700, BrowserType.FIREFOX);
    config.addBrowser(768, 700, BrowserType.EDGE_CHROMIUM);

    // Add mobile emulation devices in Portrait mode
    config.addDeviceEmulation(DeviceName.iPhone_X, ScreenOrientation.PORTRAIT);
    config.addDeviceEmulation(DeviceName.Pixel_2, ScreenOrientation.PORTRAIT);

    // Set the configuration object to eyes
    EYES.setConfiguration(config);
  }
}
