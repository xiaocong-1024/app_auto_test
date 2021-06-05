package com.kingsoft.day04.listener;

import com.kingsoft.day04.common.BaseTest;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;

/**
 * @author xiaocong
 * @date 2021/6/1 0001 - 0:03
 * desc:监听类(监听用例失败)
 * 实现IHookable接口(重写run方法)
 * 作用：在测试用例执行时，用run方法的内容动态替换掉@test方法里面的所有内容
 * 要让测试用例正常执行：
 * iHookCallBack.runTestMethod(iTestResult)
 */
public class TestFailListener implements IHookable {
    @Override
    public void run(IHookCallBack iHookCallBack, ITestResult iTestResult) {
        //让测试用例可以正常执行
        iHookCallBack.runTestMethod(iTestResult);
        //如果测试用例执行失败-->(捕获到异常)
        if(iTestResult.getThrowable() != null){
            //截图
            byte[] screenshot = BaseTest.getDriver().getScreenshotAs(OutputType.BYTES);
            getScreenshot(screenshot);
        }
    }

    //Attachment注解的特性：会将返回结果作为附件添加到Allure报表中(前提：该方法返回值类型为字节数组或者字符串)
    @Attachment(value="screenshot",type="img/png")
    public byte[] getScreenshot(byte[] screenshot){
        return screenshot;
    }

}
