package com.lemon.test.day03;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaocong
 * @date 2021/5/22 0022 - 23:51
 * 元素的三大等待
 */
public class ElementWaitTest {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        AndroidDriver driver = openApp();
        //2.隐式等待
        //driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        ////点击题库
        //driver.findElement(MobileBy.id("com.lemon.lemonban:id/navigation_tiku")).click();
        ////点击去登录
        //driver.findElement(MobileBy.id("com.lemon.lemonban:id/button_go_login")).click();
        ////3).输入手机号
        ////手机号：[resource-id='com.lemon.lemonban:id/et_mobile']
        ////4)输入密码
        ////密码：[resource-id='com.lemon.lemonban:id/et_password']
        //driver.findElement(MobileBy.id("com.lemon.lemonban:id/et_mobile")).sendKeys("15202997202");
        //driver.findElement(MobileBy.id("com.lemon.lemonban:id/et_password")).sendKeys("997202");
        ////5).点击登录按钮
        ////登录[resource-id='com.lemon.lemonban:id/btn_login']
        //driver.findElement(MobileBy.id("com.lemon.lemonban:id/btn_login")).click();

        //3.显示等待
        WebDriverWait wait = new WebDriverWait(driver,10);
        //条件1：页面元素存在presenceOfElementLocated
        //条件2：页面元素存在且可见(输入框)visibilityOfElementLocated
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.id("com.lemon.lemonban:id/navigation_tiku"))).click();
        //条件3：页面元素可被点击 elementToBeClickable
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.id("com.lemon.lemonban:id/button_go_login"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.id("com.lemon.lemonban:id/et_mobile"))).sendKeys("15202997202");
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.id("com.lemon.lemonban:id/et_password"))).sendKeys("997202");

        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.id("com.lemon.lemonban:id/btn_login"))).click();

    }




    public static AndroidDriver openApp() throws InterruptedException, MalformedURLException {
        //1.打开被测app
        //step1.打开被测app
        //desiredCapabilities--->所需功能
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        //往所需功能里面传入三个参数：
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appPackage", "com.lemon.lemonban");
        desiredCapabilities.setCapability("appActivity", "com.lemon.lemonban.activity.WelcomeActivity");

        //appium服务的接口地址
        URL remoteUrl = new URL("http://localhost:4723/wd/hub");
        //根据已有的信息(appim服务接口地址、app相关的三个参数)和设备(模拟器)建立通信连接，打开设备中对应的测试app
        AndroidDriver driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        Thread.sleep(8000);
        return driver;
    }

}
