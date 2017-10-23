package com.katsman.solanteq.test.bean;

import com.katsman.solanteq.aspect.Log;

/**
 * @author Alexey Katsman
 * @since 23.10.17
 */

public class LoggedMethodsTestBean {

    @Log
    public void testMethodOne() {

    }

    @Log
    public int testMethodTwo() {
        return -5;
    }

    public void testMethodThree() {

    }
}
