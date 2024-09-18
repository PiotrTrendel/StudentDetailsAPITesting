package org.restassured.base.impl;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.restassured.base.IBaseResponse;
import org.utils.ConfigManager;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseResponseImpl implements IBaseResponse {

    public Response sendRequest(String method, String endpoint, Map<String, String> headers,
                                Map<String, String> queryParams, Map<String, String> pathParams, JSONObject payload) {

        RequestSpecification request = given().contentType(ContentType.JSON).accept(ContentType.JSON);

        if(headers != null) {
            request.headers(headers);
        }
        if(queryParams != null) {
            request.queryParams(queryParams);
        }
        if(pathParams != null) {
            request.pathParams(pathParams);
        }
        if(payload != null) {
            request.body(payload);
        }

        Response response;
        switch (method.toUpperCase()) {
            case "GET":
                response = request.get(endpoint);
                break;
            case "POST":
                response = request.post(endpoint);
                break;
            case "PUT":
                response = request.put(endpoint);
                break;
            case "DELETE":
                response = request.delete(endpoint);
                break;
            default:
                throw new IllegalArgumentException("Unknown HTTP method: " + method);
        }
        return response;
    }

}
