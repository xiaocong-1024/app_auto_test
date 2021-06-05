package com.lemon.test.day04.specialElementLocated;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaocong
 * @date 2021/5/23 0023 - 21:01
 * 特殊场景：[使用手机真机进行测试]
 * 无法使用工具(appium工具/UIautomatorViewer工具---->这两个工具都是借助adb命令截图的)定位元素
 * //bilibili app
 * package:tv.danmaku.bili
 * launchable-activity:tv.danmaku.bili.ui.splash.SplashActivity
 */
public class elementNotLocatedWithTool {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        AndroidDriver driver = openApp();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //同意并继续【resource-id:tv.danmaku.bili:id/agree】
        driver.findElement(MobileBy.id("tv.danmaku.bili:id/agree")).click();
        //点击登录按钮
        // resource-id:tv.danmaku.bili:id/avatar_layout
        //  //*[@resource-id='tv.danmaku.bili:id/avatar_layout']//android.widget.ImageView
        driver.findElement(MobileBy.id("tv.danmaku.bili:id/avatar_layout")).click();

        //点击其他方式登录
        //resource-id:tv.danmaku.bili:id/btn_change_account
        driver.findElement(MobileBy.id("tv.danmaku.bili:id/btn_change_account")).click();
        //System.out.println(driver.getPageSource());

        //【请输入手机号码】
        //resource-id:tv.danmaku.bili:id/et_phone_number
        driver.findElement(MobileBy.id("tv.danmaku.bili:id/et_phone_number")).sendKeys("15202997202");
        //【请输入验证码】
        //resource-id="tv.danmaku.bili:id/et_capture"
        driver.findElement(MobileBy.id("tv.danmaku.bili:id/et_capture")).sendKeys("123456");
        Thread.sleep(2000);

    }

    //打开bilibili app
    public static AndroidDriver openApp() throws InterruptedException, MalformedURLException {
        //1.打开被测app
        //step1.打开被测app
        //desiredCapabilities--->所需功能
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        //往所需功能里面传入三个参数：
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appPackage", "tv.danmaku.bili");
        desiredCapabilities.setCapability("appActivity", "tv.danmaku.bili.ui.splash.SplashActivity");

        //appium服务的接口地址
        URL remoteUrl = new URL("http://localhost:4723/wd/hub");
        //根据已有的信息(appim服务接口地址、app相关的三个参数)和设备(模拟器)建立通信连接，打开设备中对应的测试app
        AndroidDriver driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        Thread.sleep(5000);
        return driver;
    }

}
