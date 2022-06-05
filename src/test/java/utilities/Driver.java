package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class Driver {

    private Driver() {
    }

    private static WebDriver driver;

    public static WebDriver getDriver() {

        System.out.println("GetDriver Methoduna girdik!");
        if (driver == null) {
            System.out.println("if kosulu Dogru, driver = null");

            String browserType = ConfigurationReader.getProperty("browser");

            if (browserType.equals("chrome")) {
                System.out.println("Browser turu chrome oldu!");

                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                driver.manage().window().maximize();

            } else if (browserType.equals("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                driver.manage().window().maximize();
            }

        }
        return driver;
    }

    public static void closeDriver(){

        System.out.println("Close driver!!");
        driver.close();
        driver=null;

    }

}