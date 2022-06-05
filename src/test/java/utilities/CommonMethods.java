package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class CommonMethods {


    public static void selectDropdownOption(String id, String value) {
        try {
            WebElement selectE = Driver.getDriver().findElement(By.xpath("//select[@id='" + id + "']"));
            Select select = new Select(selectE);

            select.selectByValue(value);
        } catch (NoSuchElementException e) {
            Assert.fail("TEST FAIL! Element not found!!");
            e.printStackTrace();
        }
    }

    // span da text i olan button lar icin !!!
    public static void clickButton(String buttonText) {
        try {
            Driver.getDriver().findElement(By.xpath("//span[contains(text(),'" + buttonText + "')]/..")).click();
        } catch (NoSuchElementException e) {
            Assert.fail("TEST FAIL! Element not found!!");
            e.printStackTrace();
        }
    }


    public static void clickLink(String linkText) {
        try {
            Driver.getDriver().findElement(By.linkText(linkText)).click();
        } catch (NoSuchElementException e) {
            Assert.fail("TEST FAIL! Element not found!!");
            e.printStackTrace();
        }

    }

    public static void pause(int second){

        try {
            Thread.sleep(second*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
