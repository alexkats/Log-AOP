package com.katsman.solanteq.test.bean;

import com.katsman.solanteq.aspect.Log;

/**
 * @author Alexey Katsman
 * @since 23.10.17
 */

@Log
public class LoggedClassTestBean {

    public void testSleepMethod() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {

        }
    }

    public void testVoidMethod() {

    }

    private void testPrivateMethod() {

    }
}
