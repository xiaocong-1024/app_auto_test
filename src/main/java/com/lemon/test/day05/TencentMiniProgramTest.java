package com.lemon.test.day05;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Set;

/**
 * @author xiaocong
 * @date 2021/5/28 0028 - 8:27
 * desc:
 * 小程序自动化测试
 */
public class TencentMiniProgramTest {
    public static AndroidDriver driver;
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        //打开微信
        driver = openApp();
        //向下滑动
        swipeDown(600);
        //点击小程序(柠檬班软件测试)
        waitElementToBeClickable(MobileBy.AndroidUIAutomator("new UiSelector().text(\"柠檬班软件测试\")")).click();
        //[NATIVE_APP, WEBVIEW_com.tencent.mm:appbrand0, WEBVIEW_com.tencent.mm:toolsmp]
        Thread.sleep(6000);
        System.out.println(driver.getContextHandles());
        //需要切换context(小程序的context会和小程序程序名绑定)
        driver.context("WEBVIEW_com.tencent.mm:appbrand0");
        System.out.println("context切换完成");
        //需要做webview和chromedriver的版本匹配
        //需要切换窗口【因为打开小程序的时候会连带着打开三个窗口，所以需要进行窗口切换】
        Set<String> handles = driver.getWindowHandles();
        for(String handle:handles){
            if(driver.getTitle().equals("腾讯课堂柠檬班软件测试")){
                break;
            }
            driver.switchTo().window(handle);
        }
        //点击【课程】
        //a[contains(text(),'课程')]
        waitElementToBeClickable(By.xpath("//a[contains(text(),'课程')]")).click();
        Thread.sleep(3000);
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

    //显示等待(元素可被点击)
    public static WebElement waitElementToBeClickable(By by){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
        return element;
    }

    //显示等待(元素可见)
    public static WebElement waitElementVisibility(By by){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return element;
    }


    //package: name='com.tencent.mm'
    //launchable-activity: name='com.tencent.mm.ui.LauncherUI'
    //打开被测app-->微信
    public static AndroidDriver openApp() throws InterruptedException, MalformedURLException {
        //desiredCapabilities--->所需功能
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        //往所需功能里面传入三个参数：
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appPackage", "com.tencent.mm");
        desiredCapabilities.setCapability("appActivity", "com.tencent.mm.ui.LauncherUI");
        //重要：切换context失败时需要加该参数告知程序chromedriver.exe的位置
        desiredCapabilities.setCapability("chromedriverExecutable", "C:\\Program Files\\Appium\\resources\\app\\node_modules\\appium\\node_modules\\appium-chromedriver\\chromedriver\\win\\chromedriver.exe");

        //C:\Program Files\Appium\resources\app\node_modules\appium\node_modules\appium-chromedriver\chromedriver\win\chromedriver.exe
        //一定要加这个参数：不加会清除微信里面的所有数据
        desiredCapabilities.setCapability("noReset", true);

        //小程序测试需要添加的配置：
        // 支持X5内核应用自动化配置
        desiredCapabilities.setCapability("recreateChromeDriverSessions", true);
        // ChromeOptions使用来定制启动选项，因为在appium中切换context识别webview的时候,
        // 把com.tencent.mm:toolsmp的webview识别成com.tencent.mm的webview.
        // 所以为了避免这个问题，加上androidProcess: com.tencent.mm:toolsmp
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("androidProcess", "com.tencent.mm:appbrand0");
        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
        // 初始化会默认将chrome浏览器打开，需要将Browser置为空
        desiredCapabilities.setBrowserName("");
        //appium服务的接口地址
        URL remoteUrl = new URL("http://localhost:4723/wd/hub");

        //System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Appium\\resources\\app\\node_modules\\appium\\node_modules\\appium-chromedriver\\chromedriver\\win");
        //根据已有的信息(appim服务接口地址、app相关的三个参数)和设备(模拟器)建立通信连接，打开设备中对应的测试app
        AndroidDriver driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        Thread.sleep(7000);
        return driver;
    }

}
