package ma.tickets.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import ma.tickets.models.LoginRequest;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AuthServiceImpl {
    private static final String API_URL = "http://localhost:8080/api/v1/auth";

    public String authenticate(LoginRequest loginRequest) throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(API_URL + "/login");
            post.setHeader("Content-Type", "application/json");

            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> loginData = new HashMap<>();
            loginData.put("username", loginRequest.getUsername());
            loginData.put("password", loginRequest.getPassword());
            String jsonPayload = objectMapper.writeValueAsString(loginData);

            post.setEntity(new StringEntity(jsonPayload, ContentType.APPLICATION_JSON));

            try (CloseableHttpResponse response = httpClient.execute(post)) {
                int statusCode = response.getCode();
                if (statusCode == 200)
                    return Arrays.stream(response.getHeaders("Set-Cookie"))
                            .map(NameValuePair::getValue)
                            .filter(h -> h.startsWith("JSESSIONID"))
                            .map(h -> h.split(";")[0].split("=")[1])
                            .findFirst()
                            .orElse(null);
                else return null;
            }
        }

    }

    public String getCurrentUser(String sessionId) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(API_URL + "/me");
            request.setHeader("Accept", "application/json");

            if (sessionId != null && !sessionId.isEmpty())
                request.setHeader("Cookie", "JSESSIONID=" + sessionId);

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                int statusCode = response.getCode();
                if (statusCode == 200)
                    return EntityUtils.toString(response.getEntity());
                else
                    return "Error: " + statusCode;
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
