package factory;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();


    public WebDriver initDriver(String browser, int implicitWait) {
        boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        if (browser.equalsIgnoreCase("chrome")) {
            tlDriver.set(new ChromeDriver());
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--incognito");
            if (isHeadless) {
                chromeOptions.addArguments("--headless=new");
                chromeOptions.addArguments("--window-size=1920,1080");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
            }
        } else if (browser.equalsIgnoreCase("firefox")) {
            tlDriver.set(new FirefoxDriver());
        } else if (browser.equalsIgnoreCase("edge")) {
            tlDriver.set(new EdgeDriver());
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        return getDriver();
    }

    public static synchronized WebDriver getDriver() {

        return tlDriver.get();
    }
}
