package com.kingsoft.day04.pages.tiku;

import com.kingsoft.day04.common.BasePage;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

/**
 * @author xiaocong
 * @date 2021/5/30 0030 - 15:32
 * desc:套题列表页面
 */
public class SubjectListPage extends BasePage {
    public SubjectListPage(AndroidDriver driver){
        super(driver);
    }

    //套题
    By subjectTitleBy = MobileBy.id("com.lemon.lemonban:id/suit_subject_title");

    //随机选取 套题列表中任意一套题
    public void randomSelectSubjectTitle(){
        randomSelectElementAndClick(subjectTitleBy);
    }
}
