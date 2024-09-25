package org.restassured.actions;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.restassured.httpmethods.MethodTypesImpl;
import static org.utils.ConfigManager.*;

import java.util.HashMap;
import java.util.Map;

public class StudentDetailsActions extends MethodTypesImpl {

    public Response getAllStudentsDetails() {
        return sendGetRequest(getConfigProperty("students-details.endpoint"));
    }

    public Response getSingleStudentDetailsById(String id) {
        Map<String, String> idParam = new HashMap<>();
        idParam.put("id", id);
        return sendGetRequest(getConfigProperty("single-student-details.endpoint"), idParam);
    }

    public Response updateStudentDetails(String id, JSONObject payload) {
        Map<String, String> idParam = new HashMap<> ();
        idParam.put("id", id);
        return sendPutRequest(getConfigProperty("single-student-details.endpoint"), payload, idParam);
    }

    public Response addStudentDetails(JSONObject payload) {
        return sendPostRequest(getConfigProperty("students-details.endpoint"), payload);
    }

    public Response deleteStudentDetails(String id) {
        Map<String, String> idParam = new HashMap<> ();
        idParam.put("id", id);
        return sendDeleteRequest(getConfigProperty("single-student-details.endpoint"), idParam);
    }
}
