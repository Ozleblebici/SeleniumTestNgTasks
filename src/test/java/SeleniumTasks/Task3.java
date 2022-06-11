package SeleniumTasks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utilities.Driver;

import java.util.ArrayList;
import java.util.List;
   /* Task 3
        Application under test : http://zero.webappsecurity.com/
        Login credentials:
        Username = “username”
        Password = “password”
        Login Feature
        Only authorized users should be able to login to the application. When user logs in
        with valid credentials, Account summary page should be displayed.

        Users with wrong username or wrong password should not be able to login. Users
        with blank username or password should also not be able to login. When user tries
        to login with invalid information, error message “Login and/or password are wrong.”
        should be displayed.


        Account summary Feature
        Account summary page should have the title Zero – Account summary. Account
        summary page should have to following account types: Cash Accounts, Investment
        Accounts, Credit Accounts, Loan Accounts.
        Credit Accounts table must have columns
        Account, Credit Card and Balance.
        Account Activity Feature
        Account Activity page should have the title Zero – Account activity.
        In the Account drop down default option should be Savings. Account drop down
        should have the following options: Savings, Checking, Loan, Credit Card, Brokerage.
        Transactions table should have column names Date, Description, Deposit,
        Withdrawal.
        NOTE: After you log in add extra step to refresh OR back method with Selenium to overcome security alert message.
        In all test cases do the verifications with TestNG
  */

public class Task3 {
    @BeforeTest
    public void BeforeEachTest(){
        WebDriver driver = Driver.getDriver();
        driver.get("http://zero.webappsecurity.com/");
        driver.findElement(By.id("signin_button")).click();
    }

    @Test
    public void TaskPositiveTestWithValidCredentials(){
        WebDriver driver = Driver.getDriver();
        driver.findElement(By.id("user_login")).sendKeys("username");
        driver.findElement(By.id("user_password")).sendKeys("password");
        driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
        driver.get("http://zero.webappsecurity.com/");
        driver.findElement(By.xpath("//strong[contains(text(),'Online Banking')]")).click();
        driver.findElement(By.id("account_summary_link")).click();
  // Diffrent verify
        String expectedTitle="Zero – Account Summary";
        String actualTitle=driver.getTitle();
       // Assert.assertEquals(actualTitle,expectedTitle);
         List<WebElement> listofAccountSummary=driver.findElements(By.xpath("//div/h2"));
          List<String> ActuallistofAccountSummaryAsText= new ArrayList<>();
        for (WebElement element: listofAccountSummary ) {
                element.getText();
            ActuallistofAccountSummaryAsText.add(element.getText());
            System.out.println(element.getText());
        }
        System.out.println(ActuallistofAccountSummaryAsText);

    }
    @Test
    public void TaskNegativeTestWithInValidCredentials(){
        WebDriver driver = Driver.getDriver();
        driver.findElement(By.id("user_login")).sendKeys("usernamertert");
        driver.findElement(By.id("user_password")).sendKeys("passwordrtret");
        driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
        String actualmessageNotLogin=driver.findElement(By.xpath("//div[contains(text(),'Login and/or')]")).getText();
        String expectedMessage="Login and/or password are wrong.";
        Assert.assertEquals(actualmessageNotLogin,expectedMessage);
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Login and/or')]")).isDisplayed(),"The message is not displayed");
    }
}
