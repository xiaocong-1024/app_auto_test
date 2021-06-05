package com.kingsoft.day02.testcases;

import com.kingsoft.day02.common.BaseTest;
import com.kingsoft.day02.pages.IndexPage;
import com.kingsoft.day02.pages.LoginPage;
import com.kingsoft.day02.pages.MylemonPage;
import com.kingsoft.day02.pages.SettingsPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * @author xiaocong
 * @date 2021/5/28 0028 - 22:12
 */
public class LoginTest extends BaseTest {

    @BeforeClass
    public void setUpClass(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //(2)进入登录页面
        IndexPage indexPage = new IndexPage(driver);
        indexPage.enterMylemonPage();

        MylemonPage mylemonPage = new MylemonPage(driver);
        mylemonPage.enterLoginPage();
    }


    //执行成功--->个人信息(如果执行成功，需要退出登录操作)
    //执行失败--->登录页面
    @Test(priority = 1)
    public void loginSuccess() throws InterruptedException {
        //登录页面
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("15202997202", "997202");

        Thread.sleep(4000);
        // 断言
        //断言1：页面发生改变  currentActivity()
        String actualActivityName = driver.currentActivity();
        String expectedActivityName = ".activity.MainActivity";
        Assert.assertEquals(actualActivityName,expectedActivityName);

        //断言2：页面信息 --->昵称：小葱
        //定位昵称元素
        IndexPage indexPage = new IndexPage(driver);
        indexPage.enterMylemonPage();

        MylemonPage mylemonPage = new MylemonPage(driver);
        String actualNickName = mylemonPage.getNickNameText();
        String expectedNickName = "小葱";
        Assert.assertEquals(actualNickName, expectedNickName);
        //当登录成功时--->才会执行退出登录
        //进入设置页面
        mylemonPage.enterSettingsPage();
        //退出登录
        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.quitLogin();
    }

    @DataProvider
    public Object[][] getLoginAbnormalDatas(){
        Object[][] datas = {
                {"","","手机号码或密码不能为空"},
                {"15202997202","","手机号码或密码不能为空"},
                {"","123456","手机号码或密码不能为空"},
                {"15202997201","997202","错误的账号信息"},
                {"1520299720","997202","手机号码格式不正确"},
                {"15202997202","123456","错误的账号信息"},
        };
        return datas;
    }

    //不管执行成功还是失败，最后都在登录页面
    @Test(dataProvider="getLoginAbnormalDatas")
    public void loginFailure(String mobilephone,String password,String expectedTips){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(mobilephone, password);
        //隐式等待
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //toast元素定位(toast元素不算真正属于页面，只是一个系统弹窗)-->提取到父类(BaseTest)中
        Assert.assertEquals(getToastText(expectedTips), expectedTips);
    }

    @AfterClass
    public void tearDownClass() throws InterruptedException {
        //(1)返回首页
        backToIndexPage();
        Thread.sleep(6000);
    }

}
