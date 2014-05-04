package com.blog.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Ryan
 * Date: 13-12-11
 * Time: 下午9:07
 * To change this template use File | Settings | File Templates.
 */
public class AdminPage {
    private WebDriver driver;

    @FindBy(xpath = "//html/body/div/div[3]/div/div/ul[2]/li/a")
    @CacheLookup
    private WebElement howdy;

    @FindBy(xpath = "//html/body/div/div[2]/ul/li[12]/a/div[3]")
    @CacheLookup
    private WebElement settings;

    @FindBy(id = "default_role")
    @CacheLookup
    private WebElement default_role;

    @FindBy(xpath = "//html/body/div/div[3]/div[2]/div/div[4]/form/table/tbody/tr[9]/td/fieldset/label[2]/input")
    @CacheLookup
    private WebElement date;

    @FindBy(xpath = "//html/body/div/div[3]/div[2]/div/div[4]/form/table/tbody/tr[10]/td/fieldset/label[2]/input")
    @CacheLookup
    private WebElement time;

    @FindBy(id = "submit")
    @CacheLookup
    private WebElement change_submit;

    @FindBy(xpath = "//html/body/div/div[3]/div[2]/div/div[4]/div[2]/p/strong")
    @CacheLookup
    private WebElement save_message;

    @FindBy(xpath = "//html/body/div/div[2]/ul/li[4]/a/div[3]")
    @CacheLookup
    private WebElement media;

    @FindBy(xpath = "//html/body/div/div[3]/div[2]/div/div[4]/form/table/tbody/tr/td[2]/strong/a")
    @CacheLookup
    private WebElement image_title;

    @FindBy(xpath = "//html/body/div/div[3]/div[2]/div/div[4]/form/table/tbody/tr/td[2]/div/span[2]/a")
    @CacheLookup
    private WebElement bird_delete;

    @FindBy(xpath = "//html/body/div/div[2]/ul/li[8]/a/div[3]")
    @CacheLookup
    private WebElement appearance;

    @FindBy(xpath = "//html/body/div/div[2]/ul/li[8]/ul/li[7]/a")
    @CacheLookup
    private WebElement header;

    @FindBy(id = "choose-from-library-link")
    @CacheLookup
    private WebElement choose_image_btn;

    @FindBy(xpath = "//html/body/div[2]/div/div/div/div[4]/div/ul/li[2]/div/div")
    @CacheLookup
    private WebElement image;

    @FindBy(xpath = "//html/body/div[2]/div/div/div/div[5]/div/div[2]/a")
    @CacheLookup
    private WebElement set_header_btn;

    @FindBy(id = "submit")
    @CacheLookup
    private WebElement crop_and_publish_btn;

    public AdminPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void checkResult_login() {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        Actions action = new Actions(driver);
        action.moveToElement(howdy);
        Assert.assertEquals("Howdy, yuxin", howdy.getText());
        System.out.println(howdy.getText() + " Welcome to login!");
    }

    public void settings_general(String author) {
        settings.click();
        Select role = new Select(default_role);
        role.selectByValue(author);
        date.click();
        time.click();
        change_submit.click();
    }

    public void checkResult_setting_general() {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        Assert.assertEquals("Settings saved.", save_message.getText());
        System.out.println("Change settings complete!");
    }

    // 删除图片
    public void deleteMedia() {
        media.click();
        Actions action = new Actions(driver);
        action.moveToElement(image_title).perform();
        if (!image_title.getText().equals("愤怒的小鸟")) {
            System.out.println("Image is not angry birds, omit deleting!");
            return;
        }
        System.out.println("deleting image!");
        bird_delete.click();
        driver.switchTo().alert().accept();
    }

    public void setHeader() {
        Actions action = new Actions(driver);
        action.moveToElement(appearance).perform();
        header.click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        choose_image_btn.click();
        image.click();
        set_header_btn.click();
        crop_and_publish_btn.click();
    }
}