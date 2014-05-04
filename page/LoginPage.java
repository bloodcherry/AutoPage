package com.blog.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created with IntelliJ IDEA.
 * User: Ryan
 * Date: 13-12-11
 * Time: 下午8:55
 * To change this template use File | Settings | File Templates.
 */
public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//*[@id='user_login']")
    @CacheLookup
    private WebElement username;

    @FindBy(xpath = "//*[@id='user_pass']")
    @CacheLookup
    private WebElement password;

    @FindBy(xpath = "//*[@id='wp-submit']")
    @CacheLookup
    private WebElement submit;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public AdminPage LoginAs(String Uname, String Pword) {
        username.sendKeys(Uname);
        password.sendKeys(Pword);
        submit.click();
        return new AdminPage(driver);
    }

    public Boolean isLoginPage() {
        wait = new WebDriverWait(driver, 10);
        Boolean isLoginPage = wait.until(ExpectedConditions.textToBePresentInElement(
                By.xpath("//html/body/div/p/a"), "Lost your password?"));
        return isLoginPage;
    }
}
