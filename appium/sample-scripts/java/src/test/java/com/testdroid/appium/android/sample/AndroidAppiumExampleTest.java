package com.testdroid.appium.android.sample;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;


import org.apache.commons.io.FileUtils;


import com.testdroid.appium.BaseAndroidTest;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.testdroid.appium.FileUploader;
import com.testdroid.appium.ServerUtil;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

public class AndroidAppiumExampleTest  extends BaseAndroidTest {

    public WebDriverWait wait;
    public ServerUtil serverutil;
    
    @BeforeClass
    public void setUp() throws Exception {
        setUpTest();
        serverutil = ServerUtil.getInstance();
        wait = new WebDriverWait(wd, 30);
    }
    @AfterClass
    public void tearDown()
    {
        quitAppiumSession();
    }


    @Test
    public void mainPageTest() throws IOException, InterruptedException {
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        
        //*********Enter the login details and login*********//
        WebElement userField = wd.findElement(By.id("appcenter_Login_login"));
        userField.click();
        userField.sendKeys(serverutil.getUsername());

        WebElement passwordField = wd.findElement(By.id("appcenter_Login_pw"));
        passwordField.click();
        passwordField.sendKeys(serverutil.getPassword());

        WebElement hostField = wd.findElement(By.id("appcenter_Login_server"));
        hostField.click();
        hostField.sendKeys(serverutil.getHost());

        WebElement portField = wd.findElement(By.id("appcenter_Login_port"));
        portField.click();
        portField.sendKeys(""+ serverutil.getPort() + "");

        WebElement contextField = wd.findElement(By.id("appcenter_Login_ctx"));
        contextField.click();
        contextField.sendKeys(serverutil.getMobContext());

        //takeScreenshot("Login details entered");
        wd.sendKeyEvent(AndroidKeyCode.ENTER);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //*********Catalog: Displaying the apps*********//
        WebElement catalogElement = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='Catalog']")));

        //takeScreenshot("Catalog Screen");
        if (catalogElement.isDisplayed()) {
            AssertJUnit.assertEquals(1, 1);
        } else {
            AssertJUnit.assertEquals(1, 0);//fail the case
        }
    }
}
