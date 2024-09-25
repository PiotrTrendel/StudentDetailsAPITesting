package org.listeners;

import org.testng.*;

import static org.listeners.ConditionVerifier.isConditionOneMet;

public class MyListener implements IInvokedMethodListener {

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
        IInvokedMethodListener.super.beforeInvocation(method, testResult, context);
    /**
     * tests performed only for users without middle name
     */
        if (isConditionOneMet(method, context)) {
            throw new SkipException("skipped");
        }
    }


    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
        IInvokedMethodListener.super.afterInvocation(method, testResult, context);
    }
}
