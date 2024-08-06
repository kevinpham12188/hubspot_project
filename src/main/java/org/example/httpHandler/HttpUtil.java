package org.example.httpHandler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.example.requestBuilder.Transform;
import org.example.schemaClass.Country;
import org.example.schemaClass.Partner;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpUtil {
    private static final String API_URL = "https://candidate.hubteam.com/candidateTest/v3/problem/dataset";
    private static final String USER_KEY = "8bfc2c39c8902b977f7123841f44";
    private static final String POST_API_URL = "https://candidate.hubteam.com/candidateTest/v3/problem/result?userKey=" + USER_KEY;

    public List<Partner> getPartners() throws IOException {
        List<Partner> partners = new ArrayList<>();
        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(API_URL + "?userKey=" + USER_KEY);
            try(CloseableHttpResponse response = httpClient.execute(httpGet)) {
                HttpEntity entity = response.getEntity();
                if(entity != null) {
//                    convert the entity to a JSON string
                    String jsonString = EntityUtils.toString(entity);

//                    parse the JSON string to a Map
                    ObjectMapper objectMapper = new ObjectMapper();
                    Map<String, List<Partner>> responseMap = objectMapper.readValue(jsonString, new TypeReference<Map<String, List<Partner>>>() {});

                    partners = responseMap.get("partners");

                }
            }
        }
        return partners;
    }

    public List<Country> getCountries() throws IOException {
        List<Partner> partners = getPartners();
//        Should I call the getPartners here
//        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {
//            HttpGet httpGet = new HttpGet(API_URL + "?userKey=" + USER_KEY);
//            try(CloseableHttpResponse response = httpClient.execute(httpGet)) {
//                HttpEntity entity = response.getEntity();
//                if(entity != null) {
//                    String jsonString = EntityUtils.toString(entity);
//                    ObjectMapper objectMapper = new ObjectMapper();
//                    Map<String, List<Partner>> responseMap = objectMapper.readValue(jsonString, new TypeReference<Map<String, List<Partner>>>() {});
//                    partners = responseMap.get("partners");
//                }
//            }
//        }
        Transform transform = new Transform();
        return transform.transformPartnerToCountry(partners);
    }

    public void postCountries(List<Country> countries) throws IOException {
//        Serialization of Country list to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = objectMapper.writeValueAsString(Map.of("countries", countries));
        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(POST_API_URL);
            httpPost.setEntity(new StringEntity(jsonPayload, StandardCharsets.UTF_8));
            httpPost.setHeader("Content-Type", "application/json");
            try(CloseableHttpResponse response = httpClient.execute(httpPost)) {
                int statusCode = response.getStatusLine().getStatusCode();
                System.out.println("Response status code: " + statusCode);
                System.out.println(response.getEntity().toString());
            }
        }
    }
}
