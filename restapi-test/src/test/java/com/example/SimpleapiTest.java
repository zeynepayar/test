import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class SimpleapiTest {

    private final HttpClient client = HttpClient.newHttpClient();

    // GET isteği örneği
    @Test
    public void testGetUser() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI("https://js