package com.katsman.solanteq;

import com.katsman.solanteq.aspect.Log;

/**
 * @author Alexey Katsman
 * @since 29.10.17
 */

@SuppressWarnings("WeakerAccess")
public class LoggedMethodsTestBean {

    @Log
    public void testVoidMethod() {

    }

    @Log
    public int testIntMethod() {
        return -5;
    }

    public void testUnloggedMethod() {

    }
}
