package com.kingsoft.day04.pages.tiku;

import com.kingsoft.day04.common.BasePage;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

/**
 * @author xiaocong
 * @date 2021/5/30 0030 - 14:47
 * desc:题库页面
 */
public class TikuPage extends BasePage{
    public TikuPage(AndroidDriver driver){
        super(driver);
    }

    //题库领域
    By tikuFiledBy = MobileBy.id("com.lemon.lemonban:id/fragment_category_type");

    //随机选择一个题库领域
    public void randomSelectTikuFiled(){
        randomSelectElementAndClick(tikuFiledBy);
    }

}
