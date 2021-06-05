package com.lemon.test.day03;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author xiaocong
 * @date 2021/5/22 0022 - 19:44
 * desc:
 * Android常用 API
 */
public class AndroidApiTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        //1).打开被测app
        AndroidDriver driver = openApp();
        //2).登录被测app
        //loginApp(driver);

         //1.currentActivity() --->获取当前页面的名字(类名)【在前台运行】
        //System.out.println(driver.currentActivity());
        //【页面-->app首页】.activity.MainActivity
        //adb shell dumpsys activity | find "mFocusedActivity"运行结果：
        // mFocusedActivity: ActivityRecord{2fa30ff1 u0 com.lemon.lemonban/.activity.MainActivity t6}

        //2.getPageSource() ---->获取页面源代码(结构)
        //System.out.println(driver.getPageSource());

        //3.信息类获取API
        //获取设备时间信息
        //2021-05-22T20:13:49+08:00
        //System.out.println(driver.getDeviceTime());
        //获取设备DPI(屏幕密度)-->值越大越好
        //240
        //System.out.println(driver.getDisplayDensity());
        //获取android底层自动化引擎的名字
        //null
        //System.out.println(driver.getAutomationName());
        //获取设备的屏幕方向
        //横屏--->LANDSCAPE
        //竖屏---->PORTRAIT
        //PORTRAIT
        //System.out.println(driver.getOrientation());

        //4.pressKey(keyEvent) --->按键的响应事件
        //点击【题库】
        //1)点击【题库】 等待3s
        //driver.findElement(MobileBy.id("com.lemon.lemonban:id/navigation_tiku")).click();
        //Thread.sleep(2000);
        ////2).点击【去登录】等待3s
        ////[resource-id='com.lemon.lemonban:id/button_go_login']
        //driver.findElement(MobileBy.id("com.lemon.lemonban:id/button_go_login")).click();
        //Thread.sleep(3000);

        //driver.findElement(MobileBy.id("com.lemon.lemonban:id/et_mobile")).sendKeys("15202997202");

        //按【返回键】
        //KeyEvent keyEvent = new KeyEvent();
        //keyEvent.withKey(AndroidKey.BACK);
        //driver.pressKey(keyEvent);
        //Thread.sleep(2000);
        //driver.pressKey(keyEvent);
        //
        //KeyEvent keyEvent1 = new KeyEvent();
        ////调大音量
        //keyEvent1.withKey(AndroidKey.VOLUME_UP);
        //driver.pressKey(keyEvent1);
        //Thread.sleep(5000);

        //5.getScreenshotAs(返回的截图类型)
        //File srcFile = driver.getScreenshotAs(OutputType.FILE);
        //File destFile = new File("D:\\test\\screenshot.png");
        //FileUtils.copyFile(srcFile, destFile);


        //坐标点击 --->(TouchAction类  触摸对象)
        //【题库】359 1233
        //元素的bound属性值可以算出该元素的坐标中心点
        //TouchAction touchAction = new TouchAction(driver);
        //PointOption pointOption = PointOption.point(359,1233);
        //press()--->按压手指   release()--->释放手指   perform()--->让动作生效
        //touchAction.press(pointOption).release().perform();
        Thread.sleep(3000);

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

    public static void loginApp(AndroidDriver driver) throws InterruptedException {
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
    }



}
