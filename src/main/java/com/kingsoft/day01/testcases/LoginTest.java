package com.kingsoft.day01.testcases;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author xiaocong
 * @date 2021/5/28 0028 - 22:12
 */
public class LoginTest {
    AndroidDriver driver;

    @BeforeClass
    public void setUpClass() throws MalformedURLException, InterruptedException {
        //打开测试app
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appPackage", "com.lemon.lemonban");
        desiredCapabilities.setCapability("appActivity", "com.lemon.lemonban.activity.WelcomeActivity");
        //不清除数据
        //desiredCapabilities.setCapability("noReset", true);
        //appium服务的接口地址
        URL remoteUrl = new URL("http://localhost:4723/wd/hub");
        //根据已有的信息(appim服务接口地址、app相关的三个参数)和设备(模拟器)建立通信连接，打开设备中对应的测试app
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        Thread.sleep(5000);

        System.out.println("主页的类名：" + driver.currentActivity());

        //1.点击【我的柠檬】
        waitElementToBeClickable(MobileBy.AndroidUIAutomator("new UiSelector().text(\"我的柠檬\")")).click();

        Thread.sleep(3000);
        System.out.println("我的柠檬首页类名：" + driver.currentActivity());
        //2.【点击头像登录】resource-id:com.lemon.lemonban:id/fragment_my_lemon_avatar_title
        waitElementToBeClickable(MobileBy.id("com.lemon.lemonban:id/fragment_my_lemon_avatar_title")).click();
        //进入登录页面
        Thread.sleep(3000);
        System.out.println("登录页面的类名：" + driver.currentActivity());
    }


    //执行成功--->个人信息(如果执行成功，需要退出登录操作)
    //执行失败--->登录页面
    @Test(priority=2)
    public void loginSuccess() throws InterruptedException {
        //登录页面
        //输入手机号
        waitElementVisibility(MobileBy.id("com.lemon.lemonban:id/et_mobile")).sendKeys("15202997202");
        // 输入密码
        waitElementVisibility(MobileBy.id("com.lemon.lemonban:id/et_password")).sendKeys("997202");
        // 点击登录按钮
        waitElementToBeClickable(MobileBy.id("com.lemon.lemonban:id/btn_login")).click();
        Thread.sleep(4000);
        System.out.println("个人信息页面类名：" + driver.currentActivity());

        // 断言
        //断言1：页面发生改变  currentActivity()
        String actualActivityName = driver.currentActivity();
        String expectedActivityName = ".activity.MainActivity";
        Assert.assertEquals(actualActivityName,expectedActivityName);

        //断言2：页面信息 --->昵称：小葱
        //定位昵称元素
        WebElement element = waitElementToBeClickable(MobileBy.id("com.lemon.lemonban:id/fragment_my_lemon_avatar_title"));
        String actualNickName = element.getText();
        String expectedNickName = "小葱";
        Assert.assertEquals(actualNickName, expectedNickName);
    }

    //不管执行成功还是失败，最后都在登录页面
    @Test(priority=1)
    public void loginFailure(){
        //登录页面
        //输入手机号
        waitElementVisibility(MobileBy.id("com.lemon.lemonban:id/et_mobile")).sendKeys("15202997201");
        // 输入密码
        waitElementVisibility(MobileBy.id("com.lemon.lemonban:id/et_password")).sendKeys("997202");
        // 点击登录按钮
        waitElementToBeClickable(MobileBy.id("com.lemon.lemonban:id/btn_login")).click();

        //toast元素定位
        WebElement element = waitElementPresence(MobileBy.xpath("//*[contains(@text,'错误的账号信息')]"));

        String actualElementInfo = element.getText();
        String expectedElementInfo = "错误的账号信息";
        Assert.assertEquals(actualElementInfo, expectedElementInfo);

        //返回首页方法1：[通过按键操作]
        //KeyEvent keyEvent = new KeyEvent();
        //keyEvent.withKey(AndroidKey.BACK);
        //driver.pressKey(keyEvent);
        //返回首页方法2：[通过startActivity(new Activity(应用包名,要跳转页面的页面类名))方法]
        // .activity.MainActivity
        //Activity activity = new Activity("com.lemon.lemonban", ".activity.MainActivity");
        //driver.startActivity(activity);
    }


    @AfterClass
    public void tearDownClass(){
        //整个测试类中的所有用例全部执行结束之后，再返回首页
        // .activity.MainActivity
        Activity activity = new Activity("com.lemon.lemonban", ".activity.MainActivity");
        driver.startActivity(activity);
        //关闭测试app
        driver.quit();
    }

    //显示等待(元素可被点击)
    public  WebElement waitElementToBeClickable(By by){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
        return element;
    }

    //显示等待(元素可见)
    public  WebElement waitElementVisibility(By by){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return element;
    }
    //显示等待(等待元素存在)
    public  WebElement waitElementPresence(By by){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        return element;
    }

}
