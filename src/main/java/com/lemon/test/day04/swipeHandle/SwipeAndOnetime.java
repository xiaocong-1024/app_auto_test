package com.lemon.test.day04.swipeHandle;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaocong
 * @date 2021/5/24 0024 - 9:56
 * desc:单手势操作--->滑动一次
 * [上滑、下滑、左滑、右滑]
 */
public class SwipeAndOnetime {
    public static AndroidDriver driver;

    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        //(1)打开测试app
        driver = openApp();
        //全局设置隐式等待
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //(2)点击题库
        //点击【题库】
        driver.findElement(MobileBy.id("com.lemon.lemonban:id/navigation_tiku")).click();

        //driver.findElement(MobileBy.id("com.lemon.lemonban:id/button_go_login")).click();
        //
        //driver.findElement(MobileBy.id("com.lemon.lemonban:id/et_mobile")).sendKeys("15202997202");
        //driver.findElement(MobileBy.id("com.lemon.lemonban:id/et_password")).sendKeys("997202");
        //driver.findElement(MobileBy.id("com.lemon.lemonban:id/btn_login")).click();

        Thread.sleep(3000);
        ////(3)向下滑动(TouchAction类)
        //TouchAction touchAction = new TouchAction(driver);
        ////把原始的坐标值转换为PointOption对象
        ////起始坐标(577 736)
        //PointOption startPoint = PointOption.point(577,736);
        ////终止点坐标(618 1494)
        //PointOption endPoint = PointOption.point(618,1494);
        ////控制滑动时间
        //WaitOptions waitOptions = new WaitOptions();
        //waitOptions.withDuration(Duration.ofMillis(2000));
        //touchAction.press(startPoint).waitAction(waitOptions).moveTo(endPoint).release().perform();
        //swipeDown(2000);
        //向左滑动
        swipeLeft(2000);
        Thread.sleep(3000);

        //返回键
        KeyEvent keyEvent = new KeyEvent();
        keyEvent.withKey(AndroidKey.BACK);
        driver.pressKey(keyEvent);

        Thread.sleep(2000);

        //向上滑动
        swipeUp(2000);
        Thread.sleep(3000);
        //向右滑动
        swipeRight(2000);
        Thread.sleep(2000);
    }

    //向下滑通用方法：
    //参数swipeTime：滑动时间
    public static void swipeDown(int swipeTime){
        //(3)向下滑动(TouchAction类)
        TouchAction touchAction = new TouchAction(driver);
        //把原始的坐标值转换为PointOption对象
        //获取手机屏幕的长度和高度
        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();
        //定义滑动的起始点和终止点
        //起始点：[width/2,height/4]
        //终止点：[width/2,height*3/4]
        PointOption startPoint = PointOption.point(width/2, height/4);
        PointOption endPoint = PointOption.point(width/2, height*3/4);
        //控制滑动时间
        WaitOptions waitOptions = new WaitOptions();
        waitOptions.withDuration(Duration.ofMillis(swipeTime));
        touchAction.press(startPoint).waitAction(waitOptions).moveTo(endPoint).release().perform();
    }

    //向上滑通用方法：
    //参数swipeTime：滑动时间
    public static void swipeUp(int swipeTime){
        //(3)向下滑动(TouchAction类)
        TouchAction touchAction = new TouchAction(driver);
        //把原始的坐标值转换为PointOption对象
        //获取手机屏幕的长度和高度
        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();
        //定义滑动的起始点和终止点
        //起始点：[width/2,height*3/4]
        //终止点：[width/2,height/4]
        PointOption startPoint = PointOption.point(width/2, height*3/4);
        PointOption endPoint = PointOption.point(width/2, height/4);
        //控制滑动时间
        WaitOptions waitOptions = new WaitOptions();
        waitOptions.withDuration(Duration.ofMillis(swipeTime));
        touchAction.press(startPoint).waitAction(waitOptions).moveTo(endPoint).release().perform();
    }




    //向左滑动通用方法：
    //参数swipeTime：滑动时间
    public static void swipeLeft(int swipeTime){
        //(3)向下滑动(TouchAction类)
        TouchAction touchAction = new TouchAction(driver);
        //把原始的坐标值转换为PointOption对象
        //获取手机屏幕的长度和高度
        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();
        //定义滑动的起始点和终止点
        //起始点：[width*3/4,height/2]
        //终止点：[width/4,height/2]
        PointOption startPoint = PointOption.point(width*3/4, height/2);
        PointOption endPoint = PointOption.point(width/4, height/2);
        //控制滑动时间
        WaitOptions waitOptions = new WaitOptions();
        waitOptions.withDuration(Duration.ofMillis(swipeTime));
        touchAction.press(startPoint).waitAction(waitOptions).moveTo(endPoint).release().perform();
    }

    //向右滑动通用方法：
    //参数swipeTime：滑动时间
    public static void swipeRight(int swipeTime){
        //(3)向下滑动(TouchAction类)
        TouchAction touchAction = new TouchAction(driver);
        //把原始的坐标值转换为PointOption对象
        //获取手机屏幕的长度和高度
        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();
        //定义滑动的起始点和终止点
        //起始点：[width/4,height/2]
        //终止点：[width*3/4,height/2]
        PointOption startPoint = PointOption.point(width/4, height/2);
        PointOption endPoint = PointOption.point(width*3/4, height/2);
        //控制滑动时间
        WaitOptions waitOptions = new WaitOptions();
        waitOptions.withDuration(Duration.ofMillis(swipeTime));
        touchAction.press(startPoint).waitAction(waitOptions).moveTo(endPoint).release().perform();
    }



    //打开柠檬班app
    public static AndroidDriver openApp() throws InterruptedException, MalformedURLException {
        //1.打开被测app
        //step1.打开被测app
        //desiredCapabilities--->所需功能
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        //往所需功能里面传入三个参数：
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appPackage", "com.lemon.lemonban");
        desiredCapabilities.setCapability("appActivity", "com.lemon.lemonban.activity.WelcomeActivity");
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
