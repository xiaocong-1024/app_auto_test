package com.kingsoft.day04.testcases;

import com.kingsoft.day04.common.BaseTest;
import com.kingsoft.day04.pages.IndexPage;
import com.kingsoft.day04.pages.LoginPage;
import com.kingsoft.day04.pages.MylemonPage;
import com.kingsoft.day04.pages.tiku.DifficultLevelPage;
import com.kingsoft.day04.pages.tiku.SubjectDetailPage;
import com.kingsoft.day04.pages.tiku.SubjectListPage;
import com.kingsoft.day04.pages.tiku.TikuPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author xiaocong
 * @date 2021/5/30 0030 - 14:18
 * desc:测试题库
 * case1：测试题库里的答案展示
 *测试题库页面-->[随机选择一个题库领域]-->【随机选择一个难度等级】-->【套题列表->随机选择一套题】-->【随机选择一道题】-->【测试答案展示】
 * case2：测试题库里的收藏功能
 * 测试题库页面-->[随机选择一个题库领域]-->【随机选择一个难度等级】-->【套题列表->随机选择一套题】-->【随机选择一道题】-->[点击收藏按钮]-->【测试是否收藏成功】
 * case3:取消收藏【同case2】
 */
public class TikuTest extends BaseTest{

    @BeforeClass
    public void setUpClass() throws InterruptedException {
        Thread.sleep(5000);
        ////(2)完成登录
        IndexPage indexPage = new IndexPage(getDriver());
        indexPage.enterMylemonPage();

        MylemonPage mylemonPage = new MylemonPage(getDriver());
        mylemonPage.enterLoginPage();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login("15202997202", "997202");
    }

    /*
        答案展示功能
     */
    @Test(priority = 2)
    public void tikuAnswerShow() throws InterruptedException {
        //(1)进入题库页面
        IndexPage indexPage = new IndexPage(getDriver());
        indexPage.enterTikuPage();
        Thread.sleep(3000);

        //(2)随机选择一个题库领域
        TikuPage tikuPage = new TikuPage(getDriver());
        tikuPage.randomSelectTikuFiled();
        Thread.sleep(2000);


        //(3)随机选择一个难度级别
        DifficultLevelPage difficultLevelPage = new DifficultLevelPage(getDriver());
        difficultLevelPage.randomSelectDifficultLevel();
        Thread.sleep(2000);

        //(4)随机选取一套题
        SubjectListPage subjectListPage = new SubjectListPage(getDriver());
        subjectListPage.randomSelectSubjectTitle();
        Thread.sleep(2000);
        //左滑(随机选一道题)
        for(int i=0;i<2;i++){
            swipeLeft(500);
            Thread.sleep(800);
        }

        //(5)点击答案
        SubjectDetailPage subjectDetailPage = new SubjectDetailPage(getDriver());
        subjectDetailPage.clickAnswerSwitch();

        //断言[判断答案文本元素是否显示]
        Assert.assertTrue(subjectDetailPage.isDisplayedAnswerText());
    }

    /*
        题目收藏功能
     */
    @Test(priority = 1)
    public void testCollectSubject() throws InterruptedException {
        MylemonPage mylemonPage = new MylemonPage(getDriver());
        int beforeCollectCount = mylemonPage.getCollectCount();

        //(1)进入题库页面
        IndexPage indexPage = new IndexPage(getDriver());
        indexPage.enterTikuPage();
        Thread.sleep(2000);

        //(2)随机选择一个题库领域
        TikuPage tikuPage = new TikuPage(getDriver());
        tikuPage.randomSelectTikuFiled();
        Thread.sleep(2000);
        //(3)随机选择一个难度级别
        DifficultLevelPage difficultLevelPage = new DifficultLevelPage(getDriver());
        difficultLevelPage.randomSelectDifficultLevel();
        Thread.sleep(2000);

        //(4)随机选取一套题
        SubjectListPage subjectListPage = new SubjectListPage(getDriver());
        subjectListPage.randomSelectSubjectTitle();
        Thread.sleep(2000);

        //左滑(随机选一道题)
        for(int i=0;i<3;i++){
            swipeLeft(500);
            Thread.sleep(800);
        }

        //(5)点击收藏按钮
        SubjectDetailPage subjectDetailPage = new SubjectDetailPage(getDriver());
        subjectDetailPage.clickCollect();

        //断言方法1：toast元素【收藏成功】
        Assert.assertEquals(getToastText("收藏成功"), "收藏成功");

        //断言方式2：[我的柠檬]页面-->收藏记录数+1
        backToIndexPage();
        indexPage.enterMylemonPage();
        mylemonPage = new MylemonPage(getDriver());
        int afterCollectCount = mylemonPage.getCollectCount();
        Assert.assertEquals(afterCollectCount, beforeCollectCount+1);
    }

    //@Test
    //public void testSelectAppointElement() throws InterruptedException {
    //    //(1)进入题库页面
    //    IndexPage indexPage = new IndexPage(getDriver());
    //    indexPage.enterTikuPage();
    //    Thread.sleep(2000);
    //
    //    //(2)滑动列表
    //    //循环滑动--->什么时候停止？
    //    //停止条件a:找到特定元素，break
    //    //停止条件b:已经滑动到页面最底部，break
    //
    //    String findText = "逻辑思维题aaa";
    //
    //    while(true){
    //        String beforePageSource = getDriver().getPageSource();
    //        if(beforePageSource.contains(findText)){
    //            //当当前页面源代码中包含查找元素关键字-->再去找元素
    //            getDriver().findElement(MobileBy.xpath("//*[@text='"+findText+"']")).click();
    //            break;
    //        }
    //        swipeUp(1000);
    //        String afterPageSource = getDriver().getPageSource();
    //        if(afterPageSource.equals(beforePageSource)){
    //            System.out.println("已经滑动到最底部了，没有找到该元素");
    //            break;
    //        }
    //    }
    //
    //}

    @AfterClass
    public void tearDownClass(){
        //当该测试类的所有测试方法都执行完成后，回到首页
        backToIndexPage();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
