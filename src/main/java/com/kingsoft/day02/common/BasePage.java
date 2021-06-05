package com.kingsoft.day02.common;

import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author xiaocong
 * @date 2021/5/29 0029 - 14:57
 * desc:所有页面的共性操作
 * 在该类中加入日志记录
 * 如：显示等待元素
 */
public class BasePage {
    public AndroidDriver driver;
    public BasePage(AndroidDriver driver){
        this.driver = driver;
    }
    Logger logger = Logger.getLogger(BasePage.class);
    /*
        封装元素点击
     */
    public void click(By by){
        logger.info("点击元素【"+by+"】");
        waitElementToBeClickable(by).click();
    }

    /*
        封装往输入框输入数据
     */
    public void input(By by,String data){
        logger.info("往元素【"+by+"】输入数据【"+data+"】");
        waitElementVisibility(by).sendKeys(data);
    }

    /*
        获取元素的文本信息
     */
    public String getText(By by){
        String text = waitElementVisibility(by).getText();
        logger.info("获取元素【"+by+"】的文本值【"+text+"】");
        return text;
    }

    /*
        获取元素的属性
     */
    public String getAttribute(By by,String attributeName){
        String value = waitElementPresence(by).getAttribute(attributeName);
        logger.info("获取元素【"+by+"】的【"+attributeName+"】属性值===>【"+value+"】");
        return value;
    }

    /*
        判断元素是否显示
     */
    public boolean isDisplay(By by){
        boolean display = waitElementPresence(by).isDisplayed();
        logger.info("判断元素【"+by+"】是否显示==>【"+display+"】");
        return display;
    }

    /*
       封装在页面随机选取一个元素并点击(根据一种定位方式找到多个元素)
       @params:by-->根据该元素的定位方式找到一个或者多个元素并在其中随机选择一个并点击
     */
    public void randomSelectElementAndClick(By by){
        List<WebElement> subjectTitleList = driver.findElements(by);
        Random random = new Random();
        int index = random.nextInt(subjectTitleList.size());
        logger.info("随机点击一个元素【"+by+"】");
        subjectTitleList.get(index).click();
    }

    /*
        封装在页面随机选取一个元素并点击(已知的多个元素定位方式-->定位多个元素)
     */
    public void randomSelectElementAndClick(List<By> list){
        Random random = new Random();
        int index = random.nextInt(list.size());
        By by = list.get(index);
        logger.info("随机点击一个元素【"+by+"】");
        waitElementToBeClickable(by).click();
    }


    //显示等待(元素可被点击)
    public WebElement waitElementToBeClickable(By by){
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
