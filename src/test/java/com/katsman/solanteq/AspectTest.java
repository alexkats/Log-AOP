package com.katsman.solanteq;

import ch.qos.logback.classic.spi.ILoggingEvent;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.util.List;

/**
 * @author Alexey Katsman
 * @since 27.10.17
 */

@SuppressWarnings("SpringJavaAutowiringInspection")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class AspectTest {

    @Autowired
    LoggedClassTestBean loggedClassTestBean;

    @Autowired
    LoggedMethodsTestBean loggedMethodsTestBean;

    static {
        System.setProperty("logback.configurationFile", System.getProperty("user.dir")
            + File.separator
            + "logback-test.xml");
    }

    private static final String ENTRY_MESSAGE_FORMAT = "Method {} has started";
    private static final String EXIT_MESSAGE_FORMAT = "Method {} has finished in {} seconds";

    @Before
    public void reset() {
        TestAppender.clear();
    }

    @Test
    public void testLoggedClassSleepMethod() {
        loggedClassTestBean.testSleepMethod();
        List<ILoggingEvent> events = TestAppender.getEvents();

        testLogWithMethodName("testSleepMethod");

        Assert.assertTrue(events.get(1).getArgumentArray().length > 1);
        Assert.assertThat(events.get(1).getArgumentArray()[1], CoreMatchers.instanceOf(Double.class));
        Assert.assertTrue((Double) events.get(1).getArgumentArray()[1] >= 1.0);
    }

    @Test
    public void testLoggedClassVoidMethod() {
        loggedClassTestBean.testVoidMethod();
        testLogWithMethodName("testVoidMethod");
    }

    @Test
    public void testLoggedClassDefaultAccessMethod() {
        loggedClassTestBean.testDefaultAccessMethod();
        Assert.assertEquals(0, TestAppender.getEvents().size());
    }

    @Test
    public void testLoggedClassProtectedMethod() {
        loggedClassTestBean.testProtectedMethod();
        Assert.assertEquals(0, TestAppender.getEvents().size());
    }

    @Test
    public void testLoggedMethodsMethodOne() {
        loggedMethodsTestBean.testVoidMethod();
        testLogWithMethodName("testVoidMethod");
    }

    @Test
    public void testLoggedMethodMethodTwo() {
        Assert.assertEquals(-5, loggedMethodsTestBean.testIntMethod());
        testLogWithMethodName("testIntMethod");
    }

    @Test
    public void testLoggedMethodsUnloggedMethod() {
        loggedMethodsTestBean.testUnloggedMethod();
        Assert.assertEquals(0, TestAppender.getEvents().size());
    }

    private void testLogWithMethodName(String methodName) {
        List<ILoggingEvent> events = TestAppender.getEvents();
        Assert.assertEquals(2, events.size());

        Assert.assertEquals(ENTRY_MESSAGE_FORMAT, events.get(0).getMessage());
        Assert.assertTrue(events.get(0).getArgumentArray().length > 0);
        Assert.assertEquals(methodName, events.get(0).getArgumentArray()[0]);

        Assert.assertEquals(EXIT_MESSAGE_FORMAT, events.get(1).getMessage());
        Assert.assertTrue(events.get(1).getArgumentArray().length > 0);
        Assert.assertEquals(methodName, events.get(1).getArgumentArray()[0]);
    }
}
