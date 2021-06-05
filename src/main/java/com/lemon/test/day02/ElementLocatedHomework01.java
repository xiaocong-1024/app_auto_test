package com.lemon.test.day02;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author xiaocong
 * @date 2021/5/22 0022 - 11:19
 * desc:
 * homework01:
 * 登录柠檬班App，进入到题库页面，选择Java自动化点击->中级->Java自动化-中级-第二套
 */
public class ElementLocatedHomework01 {
    public static void main(String[] args) throws InterruptedException, MalformedURLException {
        //  打开被测app
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
        Thread.sleep(10000);

        //登录柠檬班app
        //1.点击【题库】 等待3s
        driver.findElement(MobileBy.id("com.lemon.lemonban:id/navigation_tiku")).click();
        Thread.sleep(2000);
        //2.点击【去登录】等待3s
        //[resource-id='com.lemon.lemonban:id/button_go_login']
        driver.findElement(MobileBy.id("com.lemon.lemonban:id/button_go_login")).click();
        Thread.sleep(2000);
        //3.输入手机号
        //手机号：[resource-id='com.lemon.lemonban:id/et_mobile']
        //4.输入密码
        //密码：[resource-id='com.lemon.lemonban:id/et_password']
        driver.findElement(MobileBy.id("com.lemon.lemonban:id/et_mobile")).sendKeys("15202997202");
        driver.findElement(MobileBy.id("com.lemon.lemonban:id/et_password")).sendKeys("997202");
        //5.点击登录按钮 等待5s
        //登录[resource-id='com.lemon.lemonban:id/btn_login']
        driver.findElement(MobileBy.id("com.lemon.lemonban:id/btn_login")).click();
        Thread.sleep(5000);
        //6.点击Java自动化  【text-->Java自动化】  等待3s
        driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Java自动化\")")).click();
        Thread.sleep(2000);
        //7.点击【中级】 [text-->中级]  等待3s
        driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"中级\")")).click();
        Thread.sleep(2000);
        //8.点击【Java自动化--中级--第2套】 [text--->Java自动化--中级--第2套]
        driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Java自动化--中级--第2套\")")).click();


        Thread.sleep(3000);
        driver.quit();
    }
}
