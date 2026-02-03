package leonparser.client;

import leonparser.exception.LeonClientException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class LeonClient {

    HttpClient client = HttpClient.newHttpClient();

    public String sendRequestGetJson(String url){

        HttpRequest request = null;
        try {
            request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();
        } catch (URISyntaxException e) {
            throw new LeonClientException("Invalid URL: " + url, e);
        }

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new LeonClientException("I/O error while calling: " + url, e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new LeonClientException("Request interrupted: " + url, e);
        }

        return response.body();

    }
}
