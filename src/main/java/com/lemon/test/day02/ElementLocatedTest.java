package com.lemon.test.day02;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * @author xiaocong
 * @date 2021/5/22 0022 - 10:20
 */
public class ElementLocatedTest {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        //打开被测app
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


        //点击题库
        //1.id定位 --->通过resource-id属性
        //题库--> (com.lemon.lemonban:id/navigation_tiku)
        //driver.findElement(MobileBy.id("com.lemon.lemonban:id/navigation_tiku")).click();

        //2.text定位
        //text-->题库
        //MobileBy.AndroidUIAutomator("new Selector().text(文本值))
        //driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"题库\")")).click();
        //Thread.sleep(3000);

        //3.className定位（不推荐使用，页面一般会有很多相同的布局名和控件名）
        //List<WebElement> list = driver.findElements(MobileBy.className("android.widget.LinearLayout"));
        //System.out.println(list.size());

        //4.accessibility id定位
        //通过属性content-desc定位  题库
        //driver.findElementByAccessibilityId("题库").click();

        //5.xpath定位
        //相对定位(//*[@属性名=属性值])
        //driver.findElement(MobileBy.xpath("//*[@resource-id='com.lemon.lemonban:id/navigation_tiku']")).click();

        //轴定位
        //parent
        Thread.sleep(2000);
        //关闭app
        driver.quit();
    }
}
