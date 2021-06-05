package com.kingsoft.day04.listener;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;


/**
 * @author xiaocong
 * @date 2021/6/1 0001 - 16:01
 * desc:
 * 测试用例失败重试机制
 * IRetryAnalyzer接口的作用：
 * 前提条件【在测试用例的@Test注解里面添加参数retryAnalyzer】
 * 用例失败触发retry方法，由该方法的返回值决定是否重试以及重试次数
 */
public class RetryTest implements IRetryAnalyzer {
    //重试次数
    int retryCount = 2;
    //当前的重试次数
    int currentCount = 0;
    @Override
    public boolean retry(ITestResult iTestResult) {
        if(currentCount < retryCount){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            currentCount++;
            return true;
        }
        return false;
    }
}
