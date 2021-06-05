package com.kingsoft.day03.pages;

import com.kingsoft.day03.common.BasePage;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

/**
 * @author xiaocong
 * @date 2021/5/29 0029 - 16:30
 * desc：设置页面
 */
public class SettingsPage extends BasePage {
    public SettingsPage(AndroidDriver driver){
        super(driver);
    }
    //退出登录按钮
    By quitLoginButtonBy = MobileBy.id("com.lemon.lemonban:id/logout_button");
    //确定按钮
    By confirmButtonBy = MobileBy.id("com.lemon.lemonban:id/tv_sure");
    //取消按钮
    By cancelButtonBy = MobileBy.id("com.lemon.lemonban:id/tv_cancel");
    //退出登录方法
    public void quitLogin(){
        click(quitLoginButtonBy);
        click(confirmButtonBy);
    }
}
