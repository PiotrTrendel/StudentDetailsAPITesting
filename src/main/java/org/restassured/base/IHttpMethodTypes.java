package org.restassured;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.restassured.base.IHttpResponse;

import java.util.Map;

public interface IHttpMethodTypes extends IHttpResponse {

    Response sendGetRequest(String endpoint);

    Response sendGetRequest(String endpoint, Map<String, String> pathParams);

    Response sendGetRequest(String endpoint, Map<String, String> pathParams, Map<String, String> queryParams);

    Response sendPostRequest(String endpoint, JSONObject payload);

    Response sendPutRequest(String endpoint, JSONObject payload);






}
