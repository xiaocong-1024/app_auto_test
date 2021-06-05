package com.lemon.test.day04.specialElementLocated;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaocong
 * @date 2021/5/23 0023 - 20:10
 * 特殊场景元素定位：--->toast元素定位(消息提示框)，永远不会获得焦点，无法被点击
 * 定位方式：模糊匹配
 * //*[contains(@text,'tips')]
 * 定位的两种方式
 * （1）隐式等待
 * (2)显示等待（等待toast元素只能使用等待元素在当前页面存在的条件）
 */
public class toastElementTest {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        AndroidDriver driver = openApp();
        //第一种方式：隐式等待
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //第二种方式：显示等待
        driver.findElement(MobileBy.id("com.lemon.lemonban:id/navigation_tiku")).click();
        Thread.sleep(2000);

        //[resource-id='com.lemon.lemonban:id/button_go_login']
        driver.findElement(MobileBy.id("com.lemon.lemonban:id/button_go_login")).click();
        Thread.sleep(2000);

        driver.findElement(MobileBy.id("com.lemon.lemonban:id/et_mobile")).sendKeys("15202997202");
        driver.findElement(MobileBy.id("com.lemon.lemonban:id/et_password")).sendKeys("123456");
        driver.findElement(MobileBy.id("com.lemon.lemonban:id/btn_login")).click();
        //定位【手机号码或密码不能为空】
        //显示等待(等待toast元素只能用元素存在的条件)
        WebDriverWait wait = new WebDriverWait(driver,5);
        WebElement totastElement = wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//*[contains(@text,'错误的')]")));
        //异常：TimeoutException
        //WebElement totastElement = wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.xpath("//*[contains(@text,'错误的')]")));
        //WebElement totastElement = driver.findElement(MobileBy.xpath("//*[contains(@text,'错误的')]"));
        System.out.println(totastElement.getText());
        Thread.sleep(3000);

        driver.quit();
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
        Thread.sleep(9000);
        return driver;
    }
}
