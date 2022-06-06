package SeleniumTasks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Driver;

import java.util.ArrayList;
import java.util.List;

public class Task2 {
/*
Task 2:
        1. Navigate to: https://moneygaming.qa.gameaccount.com/
        2. Click the JOIN NOW button to open the registration page
        3. Select a title value from the dropdown
        4. Enter your first name and surname in the form
        5. Check the tick box with text 'I accept the Terms and Conditions and certify that I am over
        the age of 18.'
        6. Submit the form by clicking the JOIN NOW button
        7. Validate that a validation message with text ‘ This field is required’ appears under the date of
        birth box

 */
    // text---->xpath
    // text---- no css
    @Test
    public void Test2() {
      WebDriver driver= Driver.getDriver();
      driver.get("https://moneygaming.qa.gameaccount.com/");
     // driver.findElement(By.xpath("//a[text()='Join Now!']")).click();
        WebElement joinNow=driver.findElement(By.cssSelector(".newUser.green"));
       // WebElement joinNow=driver.findElement(By.xpath("//a[text()='Join Now!']"));
        joinNow.click();
        Select sel= new Select(driver.findElement(By.id("title")));
         List<WebElement> titleOptions=sel.getOptions();
        ArrayList<String> titleOptionsallTogether= new ArrayList<>();
        for (WebElement element: titleOptions)
        {
            titleOptionsallTogether.add(element.getText());
            System.out.println(element.getText());
        }
        System.out.println(titleOptions.size());
        System.out.println(titleOptionsallTogether);
       // sel.selectByVisibleText("Mr"); (Dropdowndaki textin adina gore seciyor)
         sel.selectByIndex(1);  // ( Dropdowndaki elemani index numarasina gore seciyor )
         driver.findElement(By.id("forename")).clear();
         driver.findElement(By.id("forename")).sendKeys("AliVeli");
         driver.findElement(By.xpath("//input[@name='map(lastName)']")).clear();
         driver.findElement(By.xpath("//input[@name='map(lastName)']")).sendKeys("AHMEDI");
         driver.findElement(By.id("checkbox")).click();
         driver.findElement(By.id("form")).click();
         String actualText= driver.findElement(By.xpath("//label[@for='dob']")).getText();
         String expectedText="This field is required" ;
         Assert.assertEquals(actualText,expectedText);
       // Assert.assertEquals(driver.findElement(By.xpath("//label[@for='dob']")).getText(),"This field is required");
    }
}
