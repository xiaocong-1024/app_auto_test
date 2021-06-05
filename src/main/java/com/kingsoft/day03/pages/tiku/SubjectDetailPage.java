package com.kingsoft.day03.pages.tiku;

import com.kingsoft.day03.common.BasePage;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

/**
 * @author xiaocong
 * @date 2021/5/30 0030 - 15:41
 * desc:题目[答案]展示页面
 */
public class SubjectDetailPage extends BasePage {
    public SubjectDetailPage(AndroidDriver driver){
        super(driver);
    }

    //答案按钮
    By answerButtonBy = MobileBy.id("com.lemon.lemonban:id/switch_button");
    //答案文本
    By answerTextBy = MobileBy.id("com.lemon.lemonban:id/tvBody");
    //收藏按钮
    By collectButtonBy = MobileBy.id("com.lemon.lemonban:id/action_favourite");

    //点击答案
    public void clickAnswerSwitch(){
        click(answerButtonBy);
    }

    //判断答案文本元素是否出现
    public boolean isDisplayedAnswerText(){
        return isDisplay(answerTextBy);
    }
    //点击收藏
    public void clickCollect(){
        click(collectButtonBy);
    }

}
