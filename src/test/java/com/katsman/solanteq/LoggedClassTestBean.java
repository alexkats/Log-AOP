package com.katsman.solanteq;

import com.katsman.solanteq.aspect.Log;

/**
 * @author Alexey Katsman
 * @since 29.10.17
 */

@Log
@SuppressWarnings("WeakerAccess")
public class LoggedClassTestBean {

    public void testSleepMethod() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {

        }
    }

    public void testVoidMethod() {

    }

    void testDefaultAccessMethod() {

    }

    protected void testProtectedMethod() {

    }
}
