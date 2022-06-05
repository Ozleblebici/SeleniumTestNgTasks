package SeleniumTasks;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.CommonMethods;
import utilities.Driver;

public class Task1 {

// 1.	Go to https://www.amazon.com
// 2.	Search for "hats for men" (Call from Configuration.properties file)
// 3.	Add the first hat appearing to Cart with quantity 2
// 4.	Open cart and assert that the total price and quantity are correct
// 5.	Reduce the quantity from 2 to 1 in Cart for the item selected in the step 3
// 6.	Assert that the total price and quantity has been correctly changed

    @Test
    public void testTask1() {

        WebDriver driver = Driver.getDriver();

        // 1.	Go to https://www.amazon.com
        driver.get("https://www.amazon.com");


        // 2.	Search for "hats for men" (Call from Configuration.properties file)
        WebElement searchBoxInput = driver.findElement(By.id("twotabsearchtextbox"));

            // 1 way to search
        searchBoxInput.sendKeys("hats for men" + Keys.ENTER);
            // 2 way to search
//        searchBoxInput.sendKeys("hats for men");
//        driver.findElement(By.id("nav-search-submit-button")).click();



        // 3.	Add the first hat appearing to Cart with quantity 2
        driver.findElement(By.xpath("(//div[@data-component-type='s-search-result']//img[@class='s-image'])[1]")).click();

        String expectedQuantity= "2";

        String expectedPrice= driver.findElement(By.xpath("//table[@class='a-lineitem a-align-top']//span")).getText();

        CommonMethods.selectDropdownOption("quantity", expectedQuantity);

        //driver.findElement(By.id("add-to-cart-button")).click();
        CommonMethods.clickButton("Add to Cart");


        // 4.	Open cart and assert that the total price and quantity are correct
        CommonMethods.clickLink("Go to Cart");

        //Verify (cart page !!)
         String actualQuantity= driver.findElement(By.className("a-dropdown-prompt")).getText();

        Assert.assertEquals(actualQuantity,expectedQuantity,"TEST FAIL! Quantities does not match!!!");

//        exp total price hesaplamasi:
//        expectedPrice -> $19.99
//        dolar isaretinden kurtul, onu ayrica kaydet!
//        string i double cevir
//        quantitiy ile carp ve expected totali hesapla!!

        String currency = expectedPrice.substring(0,1);
        expectedPrice=expectedPrice.replace( currency, "");

        double expectedPriceDob = Double.parseDouble(expectedPrice);

        String expectedTotalPrice = currency+ (expectedPriceDob * Double.parseDouble(expectedQuantity));


        String actualCurrency= driver.findElement(By.xpath("//span[@id='sc-subtotal-amount-buybox']//span[@class='a-price-symbol']")).getText();
        String actualWhole= driver.findElement(By.xpath("//span[@id='sc-subtotal-amount-buybox']//span[@class='a-price-whole']")).getText();
        String actualFraction= driver.findElement(By.xpath("//span[@id='sc-subtotal-amount-buybox']//span[@class='a-price-fraction']")).getText();

        String actualTotalPrice = actualCurrency+actualWhole+"."+actualFraction;

        Assert.assertEquals(actualTotalPrice,expectedTotalPrice,"TEST FAIL! Total price does not match!!!");



        Driver.closeDriver();
    }
}
