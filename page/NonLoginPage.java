package com.blog.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Ryan
 * Date: 13-12-11
 * Time: 下午8:45
 * To change this template use File | Settings | File Templates.
 */
public class NonLoginPage {
    private WebDriver driver;

    //    @FindBy(xpath = "//html/body/div/div/div/aside[6]/ul/li/a")
    @FindBy(css = "html body.home div#page.hfeed div#main div#secondary.widget-area aside#meta-2.widget ul li a")
    @CacheLookup
    private WebElement login;

    @FindBy(id = "s")
    @CacheLookup
    private WebElement search_input_box;

    public NonLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public LoginPage go() {
        login.click();
        return new LoginPage(driver);
    }

    public SearchPage search(String content) {
        search_input_box.sendKeys(content);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.RETURN).perform();
        return new SearchPage(driver);
    }
}
