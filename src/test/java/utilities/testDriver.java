package utilities;

import org.openqa.selenium.WebDriver;

public class testDriver {


    public static void main(String[] args) {

        Driver.getDriver().get("https://www.amazon.com");

        Driver.closeDriver();

        Driver.getDriver().get("https://www.bbc.com");

        Driver.closeDriver();
    }
}
