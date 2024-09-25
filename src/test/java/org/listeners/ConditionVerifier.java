package org.listeners;

import org.testng.IInvokedMethod;
import org.testng.ITestContext;
import org.utils.ConfigManager;

import static org.restassured.enums.TestContext.MIDDLE_NAME;
import static org.restassured.enums.TestContext.getAttrName;

public class ConditionVerifier {

    public static boolean isConditionOneMet(IInvokedMethod method, ITestContext context) {
        String methodName = method.getTestMethod().getConstructorOrMethod().getMethod().getName();
        String midName = (String) context.getAttribute(getAttrName(MIDDLE_NAME));

        if (midName != "" && methodName.equals(ConfigManager.getConfigProperty("skip.middle-name-not-empty"))) {
            return true;
        }
        else {
            return false;
        }
    }
}
