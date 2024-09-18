package org.restassured.base.impl;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.restassured.base.IHttpMethodTypes;

import java.util.Map;

public class MethodTypesImpl extends BaseResponseImpl implements IHttpMethodTypes {

    public Response sendGetRequest(String endpoint) {
        return sendRequest("GET", endpoint, null, null, null, null);
    }

    public Response sendGetRequest(String endpoint, Map<String, String> pathParams) {
        return sendRequest("GET", endpoint, null, null, pathParams, null);
    }

    public Response sendGetRequest(String endpoint, Map<String, String> pathParams, Map<String, String> queryParams) {
        return sendRequest("GET", endpoint, null, queryParams, pathParams, null);
    }

    public Response sendPostRequest(String endpoint, JSONObject payload) {
        return sendRequest("POST", endpoint, null, null, null, payload);
    }

    public Response sendPutRequest(String endpoint, JSONObject payload, Map<String, String> pathParams) {
        return sendRequest("PUT", endpoint, null, null, null, payload);
    }

    public Response sendDeleteRequest(String endpoint, Map<String, String> pathParams) {
        return sendRequest("DELETE", endpoint, null, null, pathParams, null);
    }

}
