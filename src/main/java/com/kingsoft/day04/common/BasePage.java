package com.kingsoft.day04.common;

import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        try{
            waitElementToBeClickable(by).click();
            //如果点击成功了，输入点击元素的日志
            logger.info("点击元素【"+by+"】");
        }catch(Exception e){
            //如果点击失败了
            // 捕获异常
            // 打印异常信息,并且抛出异常
            logger.error("点击元素【"+by+"】失败了!");
            throw e;
        }
    }

    /*
        封装往输入框输入数据
     */
    public void input(By by,String data){
        try{
            waitElementVisibility(by).sendKeys(data);
            logger.info("往元素【"+by+"】输入数据【"+data+"】");
        }catch(Exception e){
            logger.error("往元素【"+by+"】输入数据【"+data+"】====>失败！");
            throw e;
        }
    }

    /*
        获取元素的文本信息
     */
    public String getText(By by){
        try{
            String text = waitElementVisibility(by).getText();
            logger.info("获取元素【"+by+"】的文本值【"+text+"】");
            //如果获取成功--->返回结果
            return text;
        }catch(Exception e){
            logger.error("获取元素【"+by+"】的文本值====>失败!");
            throw e;
            // 如果获取失败-->捕获异常--->抛出异常
        }
    }

    /*
        获取元素的属性
     */
    public String getAttribute(By by,String attributeName){
        try{
            String value = waitElementPresence(by).getAttribute(attributeName);
            logger.info("获取元素【"+by+"】的【"+attributeName+"】属性值===>【"+value+"】");
            return value;
        }catch(Exception e){
            logger.error("获取元素【"+by+"】的【"+attributeName+"】属性值===>失败！");
            throw e;
        }
    }

    /*
        判断元素是否显示
     */
    public boolean isDisplay(By by){
        try{
            boolean display = waitElementPresence(by).isDisplayed();
            logger.info("判断元素【"+by+"】是否显示==>【"+display+"】");
            return display;
        }catch(Exception e){
            logger.error("判断元素【"+by+"】是否显示==>失败！！");
            throw e;
        }
    }

    /*
       封装在页面随机选取一个元素并点击(根据一种定位方式找到多个元素)
       @params:by-->根据该元素的定位方式找到一个或者多个元素并在其中随机选择一个并点击
     */
    public void randomSelectElementAndClick(By by){
        try{
            List<WebElement> subjectTitleList = driver.findElements(by);
            Random random = new Random();
            int index = random.nextInt(subjectTitleList.size());
            subjectTitleList.get(index).click();
            logger.info("随机点击一个元素【"+by+"】");
        }catch(Exception e){
            logger.error("随机点击一个元素【"+by+"】===》失败！！！");
            throw e;
        }
    }

    /*
        封装在页面随机选取一个元素并点击(已知的多个元素定位方式-->定位多个元素)
     */
    public void randomSelectElementAndClick(List<By> list){
        Random random = new Random();
        int index = random.nextInt(list.size());
        By by = list.get(index);
        try{
            waitElementToBeClickable(by).click();
            logger.info("随机点击一个元素【"+by+"】");
        }catch(Exception e){
            logger.error("随机点击一个元素【"+by+"】====>失败！！！");
            throw e;
        }
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
