package org.restassured.enums;

public enum TestContext {

    STUDENT_ID,
    FIRST_NAME,
    MIDDLE_NAME;

    public static String getAttrName(TestContext var) {
        return var.name();
    }
}

