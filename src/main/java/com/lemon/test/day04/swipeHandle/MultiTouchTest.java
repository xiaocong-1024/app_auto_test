package com.lemon.test.day04.swipeHandle;

import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

/**
 * @author xiaocong
 * @date 2021/5/24 0024 - 14:42
 * 多点触摸[放大/缩小]
 * 如：百度地图
 */
public class MultiTouchTest {
    public static AndroidDriver driver;
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        //(1)打开百度地图app
        driver = openApp();
        //(2)放大功能
        //创建两个TouchAction对象
        //获取屏幕的宽度和高度
        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();
        //第一个手指[从B-->A]
        //[A B]
        //A[width/5,height/5]
        //B[width*2/5,height*2/5]
        TouchAction touchAction1 = new TouchAction(driver);
        PointOption pointOptionA = PointOption.point(width/5,height/5);
        PointOption pointOptionB = PointOption.point(width*2/5,height*2/5);

        //控制滑动的时间
        //特别注意：WaitAction在单手势滑动中去设置：表示从起始点到终止点的滑动时间
        //WaitAction在多手势滑动中去设置，表示在起始点(终止点)等待多久,多手势滑动不能控制滑动过程的快慢
        WaitOptions waitOptions = new WaitOptions();
        waitOptions.withDuration(Duration.ofMillis(800));
        //声明第一个手指的操作
        touchAction1.press(pointOptionB).waitAction(waitOptions).moveTo(pointOptionA).waitAction(waitOptions).release();
        //第二个手指[从C-->D]
        //C[width*3/5,height*3/5]
        //D[width*4/5,height*4/5]
        TouchAction touchAction2 = new TouchAction(driver);
        PointOption pointOptionC = PointOption.point(width*3/5,height*3/5);
        PointOption pointOptionD = PointOption.point(width*4/5,height*4/5);
        //声明第二个手指的操作
        touchAction2.press(pointOptionC).waitAction(waitOptions).moveTo(pointOptionD).waitAction(waitOptions).release();

        //创建MutiTouchAction对象(让多个手指的动作同时生效[可以添加多个手指的操作])
        MultiTouchAction multiTouchAction = new MultiTouchAction(driver);
        multiTouchAction.add(touchAction1);
        multiTouchAction.add(touchAction2);

        multiTouchAction.perform();
        Thread.sleep(3000);
    }

    //package: name='com.baidu.BaiduMap'
    //launchable-activity: name='com.baidu.baidumaps.WelcomeScreen'
    //打开百度地图app
    public static AndroidDriver openApp() throws InterruptedException, MalformedURLException {
        //1.打开被测app
        //step1.打开被测app
        //desiredCapabilities--->所需功能
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        //往所需功能里面传入三个参数：
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appPackage", "com.baidu.BaiduMap");
        desiredCapabilities.setCapability("appActivity", "com.baidu.baidumaps.WelcomeScreen");
        //不清除数据
        desiredCapabilities.setCapability("noReset", true);

        //appium服务的接口地址
        URL remoteUrl = new URL("http://localhost:4723/wd/hub");
        //根据已有的信息(appim服务接口地址、app相关的三个参数)和设备(模拟器)建立通信连接，打开设备中对应的测试app
        AndroidDriver driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        Thread.sleep(7000);
        return driver;
    }
}
