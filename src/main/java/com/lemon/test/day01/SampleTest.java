package com.lemon.test.day01;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;


import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SampleTest {

  private AndroidDriver driver;

  //执行之前需:1打开夜神模拟器  2.启动appium服务
  @BeforeTest
  public void setUp() throws MalformedURLException {
    //desiredCapabilities--->所需功能
    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
    //往所需功能里面传入三个参数：
    desiredCapabilities.setCapability("platformName", "Android");
    desiredCapabilities.setCapability("appPackage", "com.lemon.lemonban");
    desiredCapabilities.setCapability("appActivity", "com.lemon.lemonban.activity.WelcomeActivity");

    //appium服务的接口地址
    URL remoteUrl = new URL("http://localhost:4723/wd/hub");
    //根据已有的信息(appim服务接口地址、app相关的三个参数)和设备(模拟器)建立通信连接，打开设备中对应的测试app
    driver = new AndroidDriver(remoteUrl, desiredCapabilities);
  }

  @Test
  public void sampleTest() throws InterruptedException {
    Thread.sleep(10000);
    MobileElement el1 = (MobileElement) driver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"题库\"]/android.widget.ImageView");
    el1.click();
    Thread.sleep(2000);
    MobileElement el2 = (MobileElement) driver.findElementById("com.lemon.lemonban:id/button_go_login");
    el2.click();
    Thread.sleep(2000);
    MobileElement el3 = (MobileElement) driver.findElementById("com.lemon.lemonban:id/et_mobile");
    el3.sendKeys("15202997202");
    MobileElement el4 = (MobileElement) driver.findElementById("com.lemon.lemonban:id/et_password");
    el4.sendKeys("997202");
    MobileElement el5 = (MobileElement) driver.findElementById("com.lemon.lemonban:id/btn_login");
    el5.click();
    Thread.sleep(3000);
  }

  @AfterTest
  public void tearDown() {
    driver.quit();
  }
}
