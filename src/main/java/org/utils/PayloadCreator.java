package org.utils;

import org.json.simple.JSONObject;
import org.restassured.data.Student;

public class PayloadCreator {

    public static JSONObject createPayloadForUpdate(int id, String firstName, String middleName, String lastName, String dob, Student student) {
        JSONObject payload = new JSONObject();

        payload.put("id", id);

        if(firstName != null) {
            payload.put("first_name", firstName);
        } else {
            payload.put("first_name", student.getFirst_name());
        }
        if(middleName != null) {
            payload.put("middle_name", middleName);
        } else {
            payload.put("middle_name", student.getMiddle_name());
        }
        if(lastName != null) {
            payload.put("last_name", lastName);
        } else {
            payload.put("last_name", student.getLast_name());
        }
        if(dob != null) {
            payload.put("date_of_birth", dob);
        } else {
            payload.put("date_of_birth", student.getDate_of_birth());
        }

        return payload;
    }
}
