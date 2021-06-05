package com.lemon.test.day04.swipeHandle;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaocong
 * @date 2021/5/24 0024 - 11:02
 * desc:单手势操作--->多次连续滑动【如：九宫格解锁】
 */
public class swipeSequenceAndOnetime {
    public static AndroidDriver driver;
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        driver = openApp();
        //设置全局隐式等待：
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //(1)[246 712]  [564  712] [871 712]
        //[564 1050]
        ////[246 1331] [564  1331] [871 1331]
        //TouchAction touchAction = new TouchAction(driver);
        //PointOption point1 = PointOption.point(246,712);
        //PointOption point2 = PointOption.point(564,712);
        //PointOption point3 = PointOption.point(871,712);
        //
        //PointOption point4 = PointOption.point(564,1019);
        //
        //PointOption point5 = PointOption.point(246,1331);
        //PointOption point6 = PointOption.point(564,1331);
        //PointOption point7 = PointOption.point(871,1331);
        //
        //touchAction.press(point1).moveTo(point2).moveTo(point3).moveTo(point4)
        //                            .moveTo(point5).moveTo(point6).moveTo(point7).release().perform();

        //获取九宫格元素：resource-id:com.huawei.systemmanager:id/applock_input_pwd_pattern
        WebElement element = driver.findElement(MobileBy.id("com.huawei.systemmanager:id/applock_input_pwd_pattern"));
        //九宫格解锁
        nineGridsUnlocked(element);
        Thread.sleep(3000);
    }

    //九宫格解锁
    public static void nineGridsUnlocked(WebElement nineGridsElement){
        TouchAction touchAction = new TouchAction(driver);

        //获取九宫格元素的长和宽
        int width = nineGridsElement.getSize().getWidth();
        int height = nineGridsElement.getSize().getHeight();
        System.out.println("九宫格元素的长："+width +"  高："+height);

        //获取九宫格元素左上角的坐标
        //注意：用下面方法获取的值不对
        //int x0 = element.getLocation().getX();
        //int y0 = element.getLocation().getX();
        //System.out.println(element.getAttribute("bounds"));
        //String markPoint = "[72,534][1008,1470]";
        String markPoint = nineGridsElement.getAttribute("bounds");
        String[] splits = markPoint.split("]",2);
        String pointItem = splits[0];
        int x0 = Integer.valueOf(pointItem.substring(1).split(",")[0]);
        int y0 = Integer.valueOf(pointItem.substring(1).split(",")[1]);
        System.out.println("[x0="+x0+",y0="+y0);
        System.out.println("九宫格元素左上角的坐标:[x0=" + x0 + ",y0="+y0+"]");

        //[x0 + width/6,y0 + height/6]  [x0+width/2,y0+height/6] [x0+width*5/6,y0+height/6]
        //[x0+width/2, y0+height/2]
        //[x0+width/6,y0+height*5/6] [x0+width/2,y0+height*5/6] [x0+width*5/6,y0+height*5/6]
        int x1 = x0+width/6;
        int y1 = y0+height/6;
        PointOption point1 = PointOption.point(x1,y1);
        //System.out.println("x1="+x1 +",y="+y1);
        //
        int x2 = x0+width/2;
        int y2 = y0+height/6;
        PointOption point2 = PointOption.point(x2,y2);
        //System.out.println("x2="+x2 +",y="+y2);
        //
        int x3 = x0+width*5/6;
        int y3 = y0+height/6;
        PointOption point3 = PointOption.point(x3,y3);
        //System.out.println("x3="+x3 +",y="+y3);
        //
        int x4 = x0+width/2;
        int y4 = y0+height/2;
        PointOption point4 = PointOption.point(x4,y4);
        //System.out.println("x4="+x4 +",y="+y4);
        //
        int x5 = x0+width/6;
        int y5 = y0+height*5/6;
        PointOption point5 = PointOption.point(x5,y5);
        //System.out.println("x5="+x5 +",y="+y5);
        //
        int x6 = x0+width/2;
        int y6 = y0+height*5/6;
        PointOption point6 = PointOption.point(x6,y6);
        //System.out.println("x6="+x6 +",y="+y6);
        //
        int y7 = y0+height*5/6;
        int x7 = x0+width*5/6;
        PointOption point7 = PointOption.point(x7,y7);
        //System.out.println("x7="+x7 +",y="+y7);

        touchAction.press(point1).moveTo(point2).moveTo(point3)
                .moveTo(point4)
                .moveTo(point5).moveTo(point6).moveTo(point7)
                .release().perform();

    }


    //前程贷app
    //package: name='com.xxzb.fenwoo'
    //launchable-activity: name='com.xxzb.fenwoo.activity.addition.WelcomeActivity'

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
        //desiredCapabilities.setCapability("noReset", true);

        //appium服务的接口地址
        URL remoteUrl = new URL("http://localhost:4723/wd/hub");
        //根据已有的信息(appim服务接口地址、app相关的三个参数)和设备(模拟器)建立通信连接，打开设备中对应的测试app
        AndroidDriver driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        Thread.sleep(5000);
        return driver;
    }
}
