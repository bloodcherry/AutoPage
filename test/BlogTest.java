package com.blog.test;

import com.blog.page.*;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Ryan
 * Date: 13-12-11
 * Time: 下午9:15
 * To change this template use File | Settings | File Templates.
 */
@Listeners({com.blog.util.ScreenShotListener.class})
public class BlogTest {
    private static WebDriver driver;
    private static final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";

    @Before
    public static WebDriver getDriver() {
        driver = new FirefoxDriver();
        System.setProperty(ESCAPE_PROPERTY, "false");
        return driver;
    }

    @Test
    @Parameters({"Uname", "Pword"})
    public void testLogin(String Uname, String Pword) {
        long startTime = System.currentTimeMillis();
        driver.get("http://localhost/wordpress/");
        NonLoginPage nonLoginPage = new NonLoginPage(driver);
        LoginPage loginPage = nonLoginPage.go();
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        AdminPage adminPage = loginPage.LoginAs(Uname, Pword);
        // Assertation
        adminPage.checkResult_login();
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total Page Load Time: " + totalTime + "milliseconds");
    }

    @Test
    @Parameters({"content"})
    public void testSearch(String content) {
        driver.get("http://localhost/wordpress/");
        // Use Navigation Timing API
        JavascriptExecutor js = (JavascriptExecutor) driver;
        long loadEventEnd = (Long) js.executeScript("return window.performance.timing.loadEventEnd;");
        long navigationStart = (Long) js.executeScript("return window.performance.timing.navigationStart;");
        System.out.println("Page Load Time is " + (loadEventEnd - navigationStart) / 1000 + " seconds.");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        NonLoginPage
                nonLoginPage = new NonLoginPage(driver);
        SearchPage searchPage = nonLoginPage.search(content);
        searchPage.checkResult();
    }

    @Test
    @Parameters({"default_role"})
    public void testChangeSettings(String default_role) {
        driver.get("http://localhost/wordpress/");
        NonLoginPage nonLoginPage = new NonLoginPage(driver);
        LoginPage loginPage = nonLoginPage.go();
        AdminPage adminPage = loginPage.LoginAs("yuxin", "zaq1,lp-");
        adminPage.settings_general(default_role);
        // Assertation
        adminPage.checkResult_setting_general();
    }

    @Test
    public void testDeleteMedia() {
        driver.get("http://localhost/wordpress/");
        NonLoginPage nonLoginPage = new NonLoginPage(driver);
        LoginPage loginPage = nonLoginPage.go();
        AdminPage adminPage = loginPage.LoginAs("yuxin", "zaq1,lp-");
        adminPage.deleteMedia();
    }

    @Test
    public void testSetHeader() {
        driver.get("http://localhost/wordpress/");
        NonLoginPage nonLoginPage = new NonLoginPage(driver);
        LoginPage loginPage = nonLoginPage.go();
        AdminPage adminPage = loginPage.LoginAs("yuxin", "zaq1,lp-");
        adminPage.setHeader();
        System.out.println("Set header complete!");
    }

    @Test
    public void testWindowsHandler() {
        driver.get("http://www.baidu.com");
        driver.get("http://www.google.com.hk");
        Set<String> handles = driver.getWindowHandles();
        String current = driver.getWindowHandle();
        for (String handle : handles) {
            if (handle.equals(current)) {
                driver.switchTo().window(handle).close();
            }
        }
    }

    public void tearDown() {
        driver.close();
    }
}
