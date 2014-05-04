package com.blog.util;

import com.blog.test.BlogTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Ryan
 * Date: 13-12-11
 * Time: 下午9:17
 * To change this template use File | Settings | File Templates.
 */
public class ScreenShotListener extends TestListenerAdapter {
    public static WebDriver driver = BlogTest.getDriver();

    @Override
    public void onTestFailure(ITestResult tr) {
        screenShot("assert-fail");
    }

    // 截图函数
    public static void screenShot(String desc) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
        String dateString = formatter.format(currentTime);
        File scrFile = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);
        try {
            desc = desc.trim().equals("") ? "" : "-" + desc.trim();
            File screenShot = new File(dateString + "-" + desc + ".jpg");
            String filename = "/Users/Ryan/IdeaProjects/WordPress/captureScreen/"
                    + screenShot;
            FileUtils.copyFile(scrFile, new File(filename));
            Reporter.log("<a href='" + filename + "'><img src='" + filename
                    + "' height='100' width='100'/></a>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
