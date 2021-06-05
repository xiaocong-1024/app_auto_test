package com.lemon.test.day03;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author xiaocong
 * @date 2021/5/22 0022 - 19:10
 * Appium常用API
 */
public class AppiumApiTest {
    public static void main(String[] args) throws InterruptedException, MalformedURLException {
        AndroidDriver driver = openApp();

        //WebElement webElement = driver.findElement(MobileBy.id("com.lemon.lemonban:id/navigation_tiku"));

        //获取class属性值的两种方法：
        //1.getAttribute("class")
        //2.getAttribute("className")
        //System.out.println("class属性1为：" + webElement.getAttribute("className"));
        ////class属性1为：android.widget.FrameLayout
        //System.out.println("class属性2为：" + webElement.getAttribute("class"));
        ////class属性2为：android.widget.FrameLayout

        //System.out.println("content-desc属性1为：" + webElement.getAttribute("name"));
        ////content-desc属性1为：(name返回text属性值)
        //System.out.println("content-desc属性2为：" + webElement.getAttribute("content-desc"));
        ////content-desc属性2为：题库

        //获取resource-id属性值的两种方法:
        //getAttribute("resource-id")
        //getAttribute("resourceId")
        //System.out.println("resource-id属性1为：" + webElement.getAttribute("resourceId"));
        ////resource-id属性1为：com.lemon.lemonban:id/navigation_tiku
        //System.out.println("resource-id属性2为：" + webElement.getAttribute("resource-id"));
        ////resource-id属性2为：com.lemon.lemonban:id/navigation_tiku

        WebElement element = driver.findElement(MobileBy.xpath("//*[@text='柠檬社区']"));
        //获取text属性值的方法有三种：
        //1.getAttribute("name")
        //2.getAttribute("text")
        //3.getText()
        //System.out.println("text属性1为：" + element.getAttribute("name"));
        ////text属性1为：柠檬社区
        //System.out.println("text属性2为：" + element.getAttribute("text"));
        ////text属性2为：柠檬社区
        //System.out.println("text属性3为：" + element.getText());
        //text属性3为：柠檬社区
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
        Thread.sleep(10000);
        return driver;
    }

}
