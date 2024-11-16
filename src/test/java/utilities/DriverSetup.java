package utilities;

import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import java.time.Duration;

public class DriverSetup {
    private static final ThreadLocal<WebDriver> WEB_DRIVER_THREAD_LOCAL = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return WEB_DRIVER_THREAD_LOCAL.get();
    }
    public static void setDriver(WebDriver driver) {
        DriverSetup.WEB_DRIVER_THREAD_LOCAL.set(driver);
    }

    public static WebDriver getBrowser(String browserName){
        if (browserName.equalsIgnoreCase("Chrome"))
            return new ChromeDriver();
        else if (browserName.equalsIgnoreCase("Firefox"))
            return new FirefoxDriver();
        else if (browserName.equalsIgnoreCase("Edge"))
            return new EdgeDriver();
        else {
            throw  new RuntimeException("Browser is not currently available with the given name: " + browserName);
        }
    }

    public static void openABrowser(String browserName){
        WebDriver driver = getBrowser(browserName);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        setDriver(driver);
    }

    public static void closeBrowser(Scenario scenario){
        takeScreenshotOnFailed(scenario);
        getDriver().quit();
    }

    public static void takeScreenshotOnFailed(Scenario scenario){
        if (scenario.isFailed()){
            String name = scenario.getName().replaceAll(" ", "_");
            byte[] source = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(source, "image/png", name);
        }

    }
}
