package com.lemon.test.day02;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author xiaocong
 * @date 2021/5/22 0022 - 11:43
 * desc:
 * homework02:
 *  * 登录柠檬班App，点击头像进入个人信息页面，要求：
 *  * 通过昵称找到昵称名元素
 *  * 通过姓名找到具体姓名元素
 *  * 通过生日找到具体生日日期元素
 */
public class ElementLocatedHomework02 {
    public static void main(String[] args) throws InterruptedException, MalformedURLException {
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

        //2.登录柠檬班app
        //1)点击【题库】 等待3s
        driver.findElement(MobileBy.id("com.lemon.lemonban:id/navigation_tiku")).click();
        Thread.sleep(2000);
        //2).点击【去登录】等待3s
        //[resource-id='com.lemon.lemonban:id/button_go_login']
        driver.findElement(MobileBy.id("com.lemon.lemonban:id/button_go_login")).click();
        Thread.sleep(2000);
        //3).输入手机号
        //手机号：[resource-id='com.lemon.lemonban:id/et_mobile']
        //4)输入密码
        //密码：[resource-id='com.lemon.lemonban:id/et_password']
        driver.findElement(MobileBy.id("com.lemon.lemonban:id/et_mobile")).sendKeys("15202997202");
        driver.findElement(MobileBy.id("com.lemon.lemonban:id/et_password")).sendKeys("997202");
        //5).点击登录按钮 等待5s
        //登录[resource-id='com.lemon.lemonban:id/btn_login']
        driver.findElement(MobileBy.id("com.lemon.lemonban:id/btn_login")).click();
        Thread.sleep(5000);

        //点击【我的柠檬】
        //[resource-id='com.lemon.lemonban:id/navigation_my']
        driver.findElement(MobileBy.id("com.lemon.lemonban:id/navigation_my")).click();
        Thread.sleep(3000);

        //点击我的图像
        //[resource-id='com.lemon.lemonban:id/fragment_my_lemon_avatar_layout']
        driver.findElement(MobileBy.id("com.lemon.lemonban:id/fragment_my_lemon_avatar_layout")).click();
        Thread.sleep(7000);

        //通过昵称找到昵称名元素
        //driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"\")"))
        WebElement element01 = driver.findElement(MobileBy.xpath("//*[@text='昵称']/following-sibling::android.widget.TextView"));
        System.out.println(element01.getText());

        //通过姓名找到具体姓名元素
        WebElement element02 = driver.findElement(MobileBy.xpath("//*[@text='姓名']/following-sibling::android.widget.TextView"));
        System.out.println(element02.getText());
        //通过生日找到具体生日日期元素
        WebElement element03 = driver.findElement(MobileBy.xpath("//*[@text='生日']/following-sibling::android.widget.TextView"));
        System.out.println(element03.getText());

    }

}
