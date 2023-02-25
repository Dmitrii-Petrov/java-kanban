package managers;


import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class KVTaskClient {
    URL url;
    String apiToken;

    public KVTaskClient(String url) throws IOException, InterruptedException {
        this.url =new URL(url);
        HttpClient client = HttpClient.newHttpClient();
        URI uri = URI.create(url + "register");
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder();
        HttpRequest request = requestBuilder
                .GET()
                .uri(uri)
                .build();

        HttpResponse.BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();

        HttpResponse<String> response = client.send(request, handler);
        this.apiToken = response.body();
    }

    public void put(String key, String json) throws IOException, InterruptedException {
        //должен сохранять состояние менеджера задач через запрос POST /save/<ключ>?API_TOKEN=.

        HttpClient client = HttpClient.newHttpClient();
        URI uri = URI.create(url + "save/"+key+"?API_TOKEN=" + apiToken) ;

        final HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofString(json);

        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder();
        HttpRequest request = requestBuilder
                .POST(body)
                .uri(uri)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public String load(String key) throws IOException, InterruptedException {
        //должен возвращать состояние менеджера задач через запрос GET /load/<ключ>?API_TOKEN=
        HttpClient client = HttpClient.newHttpClient();
        URI uri = URI.create(url + "load/"+key+"?API_TOKEN=" + apiToken) ;

        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder();
        HttpRequest request = requestBuilder
                .GET()
                .uri(uri)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
