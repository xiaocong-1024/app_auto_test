package com.lemon.test.day05;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author xiaocong
 * @date 2021/5/25 0025 - 21:57
 * desc:
 * 混合应用程序(hybrid自动化测试)
 */
public class HybridTest {
    public static AndroidDriver driver;
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        //打开被测app
        driver = openApp();
        System.out.println("进入柠檬社区之前："+driver.getContextHandles());
        //System.out.println(driver.getContext());
        //[NATIVE_APP]
        //点击柠檬社区
        //driver.findElement();
        waitElementToBeClickable(MobileBy.AndroidUIAutomator("new UiSelector().text(\"柠檬社区\")")).click();
        Thread.sleep(3000);
        System.out.println("进入柠檬社区之后："+driver.getContextHandles());
        System.out.println(driver.getContext());
        //NATIVE_APP
        //[NATIVE_APP, WEBVIEW_com.lemon.lemonban]
        //需要切换context
        driver.context("WEBVIEW_com.lemon.lemonban");
        System.out.println("切换了context后：" + driver.getContext());
        //WEBVIEW_com.lemon.lemonban

        //点击登录
        ////a[@href='http://testingpai.com/login' and @class='index-bottom__item']
        waitElementToBeClickable(By.xpath("//a[@href='http://testingpai.com/login' and @class='index-bottom__item']")).click();

        //手机号 id-->nameOrEmail
        waitElementVisibility(By.id("nameOrEmail")).sendKeys("15202997202");
        //密码 id-->userPassword
        waitElementVisibility(By.id("userPassword")).sendKeys("997202");
        //登录 id--->verifyLogin
        waitElementToBeClickable(By.id("verifyLogin")).click();
    }

    //显示等待(元素可被点击)
    public static WebElement waitElementToBeClickable(By by){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
        return element;
    }

    //显示等待(元素可见)
    public static WebElement waitElementVisibility(By by){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return element;
    }


    //打开被测app-->柠檬班
    public static AndroidDriver openApp() throws InterruptedException, MalformedURLException {
        //desiredCapabilities--->所需功能
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        //往所需功能里面传入三个参数：
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appPackage", "com.lemon.lemonban");
        desiredCapabilities.setCapability("appActivity", "com.lemon.lemonban.activity.WelcomeActivity");
        desiredCapabilities.setCapability("noReset", true);

        //appium服务的接口地址
        URL remoteUrl = new URL("http://localhost:4723/wd/hub");
        //根据已有的信息(appim服务接口地址、app相关的三个参数)和设备(模拟器)建立通信连接，打开设备中对应的测试app
        AndroidDriver driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        Thread.sleep(5000);
        return driver;
    }
}
