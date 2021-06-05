package com.kingsoft.day02.pages;

import com.kingsoft.day02.common.BasePage;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

/**
 * @author xiaocong
 * @date 2021/5/29 0029 - 15:02
 * desc:登录页面
 * [元素定位信息]---》定位方式+定位表达式
 * [元素操作]
 */
public class LoginPage extends BasePage {
    public LoginPage(AndroidDriver driver){
        super(driver);
    }
    //手机号输入框
    By mobilephoneInputBy = MobileBy.id("com.lemon.lemonban:id/et_mobile");
    //密码输入框
    By passwordInputBy = MobileBy.id("com.lemon.lemonban:id/et_password");
    //登录按钮
    By loginButtonBy = MobileBy.id("com.lemon.lemonban:id/btn_login");

    //登录
    public void login(String mobilephone,String password){
        input(mobilephoneInputBy, mobilephone);
        input(passwordInputBy,password);
        click(loginButtonBy);
    }
}
