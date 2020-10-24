package com.agileengine.net;

import com.agileengine.service.DBService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import java.io.IOException;
import java.sql.SQLException;

public class HttpClient {

    private final ObjectMapper mapper = new ObjectMapper();
    private final OkHttpClient client = new OkHttpClient();
    private final String URL = "http://interview.agileengine.com";
    private final String APIKEY = "23567b218376f79d9415";
    private static HttpClient httpClient;
    private static DBService dbService = DBService.getInstance();

    private HttpClient() {}

    public static HttpClient getInstance() {
        if (httpClient == null) {
            synchronized (HttpClient.class) {
                if (httpClient == null) {
                    httpClient = new HttpClient();
                    try {
                        dbService.createTable();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return httpClient;
    }

    public void callForData() throws IOException {

        String autorizationResponse = autorization();
        String token = autorizationResponse.substring(22, 62);

        Request request2 = new Request.Builder()
                .url(URL + "/images")
                .addHeader("Authorization", "Bearer " + token)
                .build();
        Response response2 = client.newCall(request2).execute();

        ResponseBody body2 = response2.body();

        String jsonResponce = body2.string();

        System.out.println(jsonResponce);

        if (jsonResponce != null) {
            JsonNode jsonNode = mapper.readTree(jsonResponce);
            // here json node must converting to ImageDataEntity and save to db but i have no time for it
        }
    }

    private String autorization() throws IOException {

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String json = "{ \"apiKey\": \"" + APIKEY + "\" }";

        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(URL + "/auth")
                .post(body)
                .build();
        Response response = client.newCall(request).execute();

        return response.body().string();
    }
}

