package com.kingsoft.day04.listener;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author xiaocong
 * @date 2021/6/1 0001 - 16:10
 * desc：
 * 失败用例重试监听器
 * 实现接口IAnnotationTransformer(TestNG提供)
 * 实现方法：transform()
 * 作用：【前提条件：在testng.xml中配置该监听器】
 * 在测试用例执行的时候，动态改变/添加@Test注解的参数(添加参数retryAnalyzer)
 */
public class TestFailRetryListener implements IAnnotationTransformer {
    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        //获取@Test注解的参数retryAnalyzer
        IRetryAnalyzer retryAnalyzer = annotation.getRetryAnalyzer();
        //retryAnalyzer对象为空===>表示该测试用例没有设置该参数
        if(retryAnalyzer == null){
            //设置retryAnalyzer参数
            annotation.setRetryAnalyzer(RetryTest.class);
        }
    }
}
