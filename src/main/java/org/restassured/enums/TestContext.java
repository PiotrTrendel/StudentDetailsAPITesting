package org.restassured.enums;

public enum Testvars {

    STUDENT_ID,
    MIDDLE_NAME;

    public static String getAttrName(Testvars var) {
        return var.name();
    }
}

