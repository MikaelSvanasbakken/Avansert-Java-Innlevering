package no.kristiania.pgr200.database;

import no.kristiania.pgr200.http.HttpRequest;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class RequestTest {

    @Test
    public void shouldCreatePath() {

        String baseUrl = "db/talks";

        Map<String, String> talkParameters = new HashMap<>();
        talkParameters.put("id", "1");
        talkParameters.put("title", "java 101");

        String expected = "db/talks?id=1&title=java+101";

        String actual = HttpRequest.createPath(baseUrl, talkParameters);

        assertEquals(expected, actual);
    }


}