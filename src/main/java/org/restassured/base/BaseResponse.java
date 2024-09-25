package org.restassured.base;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseResponse {

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

        request.log().uri().and().log().method();

        Response response;

        switch (method.toUpperCase()) {
            case "GET":
                response = request.when().get(endpoint);
                break;
            case "POST":
                response = request.when().post(endpoint);
                break;
            case "PUT":
                response = request.when().put(endpoint);
                break;
            case "DELETE":
                response = request.when().delete(endpoint);
                break;
            default:
                throw new IllegalArgumentException("Unknown HTTP method: " + method);
        }

        response.then().log().status();
        System.out.println();

        return response;
    }

}
