package com.kingsoft.day04.pages;

import com.kingsoft.day04.common.BasePage;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

/**
 * @author xiaocong
 * @date 2021/5/29 0029 - 14:50
 * desc:[我的柠檬首页]
 */
public class IndexPage extends BasePage {
    public IndexPage(AndroidDriver driver){
        super(driver);
    }
    //我的柠檬//content-desc:我的柠檬
    By mylemonBy = MobileBy.AccessibilityId("我的柠檬");

    //题库
    By tikuBy = MobileBy.AccessibilityId("题库");

    //进入我的柠檬页面
    public void enterMylemonPage(){
        click(mylemonBy);
    }

    //进入题库页面
    public void enterTikuPage(){
        click(tikuBy);
    }
}
