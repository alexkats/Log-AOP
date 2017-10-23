package com.katsman.solanteq.test;

import com.katsman.solanteq.test.bean.LoggedClassTestBean;
import com.katsman.solanteq.test.bean.LoggedMethodsTestBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Alexey Katsman
 * @since 22.10.17
 */

public class TestRunner {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class);
        testLoggedClassTestBean(context);
        testLoggedMethodsTestBean(context);
    }

    private static void testLoggedClassTestBean(ApplicationContext context) {
        LoggedClassTestBean testBean = context.getBean(LoggedClassTestBean.class);
        testBean.testSleepMethod();
        testBean.testVoidMethod();
    }

    private static void testLoggedMethodsTestBean(ApplicationContext context) {
        LoggedMethodsTestBean testBean = context.getBean(LoggedMethodsTestBean.class);
        testBean.testMethodOne();
        testBean.testMethodTwo();
        testBean.testMethodThree();
    }
}
