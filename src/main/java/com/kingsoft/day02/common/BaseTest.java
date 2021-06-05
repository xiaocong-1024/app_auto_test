package com.kingsoft.day02.common;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

/**
 * @author xiaocong
 * @date 2021/5/29 0029 - 15:35
 * desc：所有测试类的共性操作
 *
 */
public class BaseTest {
    public static AndroidDriver driver;
    //确保在所有测试类执行之前打开app
    Logger logger = Logger.getLogger(BaseTest.class);
    @BeforeTest
    public void setUpTest(){
        //(1)打开被测app
        logger.info("====================打开测试app=========================");
        openApp();
    }

    //打开测试app
    public void openApp(){
        //打开测试app
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appPackage", "com.lemon.lemonban");
        desiredCapabilities.setCapability("appActivity", "com.lemon.lemonban.activity.WelcomeActivity");
        //不清除数据
        //desiredCapabilities.setCapability("noReset", true);
        //appium服务的接口地址
        URL remoteUrl = null;
        try {
            remoteUrl = new URL("http://localhost:4723/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        //根据已有的信息(appim服务接口地址、app相关的三个参数)和设备(模拟器)建立通信连接，打开设备中对应的测试app
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    //单手势操作(上下左右滑动)
    //向下滑通用方法：
    //参数swipeTime：滑动时间
    public  void swipeDown(int swipeTime){
        logger.info("向下滑动屏幕");
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
    public  void swipeUp(int swipeTime){
        //(3)向下滑动(TouchAction类)
        logger.info("向上滑动屏幕");
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
    public  void swipeLeft(int swipeTime){
        //(3)向下滑动(TouchAction类)
        logger.info("向左滑动屏幕");
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

    public  void swipeRight(int swipeTime){
        //(3)向下滑动(TouchAction类)
        logger.info("向右滑动屏幕");
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

    //多手势操作(放大缩小操作)
    public void multiTouchSwipe(int time){
        logger.info("放大屏幕");
        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();

        TouchAction touchAction1 = new TouchAction(driver);
        PointOption pointOptionA = PointOption.point(width/5,height/5);
        PointOption pointOptionB = PointOption.point(width*2/5,height*2/5);

        WaitOptions waitOptions = new WaitOptions();
        waitOptions.withDuration(Duration.ofMillis(time));
        //声明第一个手指的操作
        touchAction1.press(pointOptionB).waitAction(waitOptions).moveTo(pointOptionA).waitAction(waitOptions).release();

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
    }


    //进入首页
    public void backToIndexPage(){
        logger.info("=========返回首页============");
        //整个测试类中的所有用例全部执行结束之后，再返回首页
        // .activity.MainActivity
        Activity activity = new Activity("com.lemon.lemonban", ".activity.MainActivity");
        //Activity activity = new Activity("com.lemon.lemonban", ".activity.WelcomeActivity");
        driver.startActivity(activity);
    }

    //获取toast元素文本信息
    //partialText--->部分或者完整的toast元素提示信息
    public String getToastText(String partialText){
        WebElement element = driver.findElement(MobileBy.xpath("//*[contains(@text,'"+partialText+"')]"));
        String toastTips = element.getText();
        logger.info("获取toast元素信息【"+toastTips+"】");
        return toastTips;
    }

    //页面滑动-->滑动到指定元素(text文本值)位置处
    public void swipeToAppointElement(String elementText){
        logger.info("不断滑动页面，直到文本为【"+elementText+"】的元素/获取滑动到页面底部");
        //(2)滑动列表
        //循环滑动--->什么时候停止？
        //停止条件a:找到特定元素，break
        //停止条件b:已经滑动到页面最底部，break
        while(true){
            String beforePageSource = driver.getPageSource();
            if(beforePageSource.contains(elementText)){
                //当当前页面源代码中包含查找元素关键字-->再去找元素
                driver.findElement(MobileBy.xpath("//*[@text='"+elementText+"']")).click();
                break;
            }
            swipeUp(1000);
            String afterPageSource = driver.getPageSource();
            if(afterPageSource.equals(beforePageSource)){
                System.out.println("已经滑动到最底部了，没有找到该元素");
                break;
            }
        }


    }
    //确保在所有的测试类之后之后关闭app
    @AfterTest
    public void tearDownTest(){
        logger.info("====================关闭测试app=========================");
        //(2)关闭测试app
        driver.quit();
    }

}
