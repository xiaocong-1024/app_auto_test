package com.kingsoft.day02.pages;

import com.kingsoft.day02.common.BasePage;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

/**
 * @author xiaocong
 * @date 2021/5/30 0030 - 14:20
 * dedc:我的柠檬页面
 */
public class MylemonPage extends BasePage {
    public MylemonPage(AndroidDriver driver){
        super(driver);
    }
    //点击头像登录
    By portraitBy = MobileBy.id("com.lemon.lemonban:id/fragment_my_lemon_avatar_title");
    //昵称
    By nickNameBy = MobileBy.id("com.lemon.lemonban:id/fragment_my_lemon_avatar_title");
    //设置按钮
    By settingsButtonBy = MobileBy.xpath("//*[@resource-id='com.lemon.lemonban:id/mypage_titlebar']//android.widget.ImageButton");
    //收藏记录
    By collectRecordBy = MobileBy.id("com.lemon.lemonban:id/fragment_my_lemon_collection_count");

    //进入设置页面
    public void enterSettingsPage(){
        //点击设置按钮
        click(settingsButtonBy);
    }

    //返回昵称文本属性值
    public String getNickNameText(){
        return getText(nickNameBy);
    }

    //进入登录页面
    public void enterLoginPage(){
        click(portraitBy);
    }

    /*
        获取收藏记录数
     */
    public int getCollectCount(){
        String collectCount = getText(collectRecordBy);
        return Integer.valueOf(collectCount);
    }

}
