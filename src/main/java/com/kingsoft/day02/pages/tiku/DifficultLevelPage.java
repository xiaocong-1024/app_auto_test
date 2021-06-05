package com.kingsoft.day02.pages.tiku;

import com.kingsoft.day02.common.BasePage;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author xiaocong
 * @date 2021/5/30 0030 - 15:21
 * desc:
 * 难度级别页面
 */
public class DifficultLevelPage extends BasePage {
    public DifficultLevelPage(AndroidDriver driver){
        super(driver);
    }

    //初级按钮
    By firstLevelButtonBy = MobileBy.id("com.lemon.lemonban:id/first_level");
    //中级按钮
    By secondLevelButtonBy = MobileBy.id("com.lemon.lemonban:id/second_level");
    //高级按钮
    By thirdLevelButtonBy = MobileBy.id("com.lemon.lemonban:id/third_level");

    //随机选取一个难度级别
    public void randomSelectDifficultLevel(){
        List<By> difficultLevelList = new ArrayList<>();
        difficultLevelList.add(firstLevelButtonBy);
        difficultLevelList.add(secondLevelButtonBy);
        difficultLevelList.add(thirdLevelButtonBy);
        randomSelectElementAndClick(difficultLevelList);
    }

}
