package no.kristiania.pgr200.database;


import no.kristiania.pgr200.http.HttpResponse;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;


public class ResponseTest {

    @Test
    public void shouldReadLines() throws IOException {
        byte[] headerLines = "Content-length: 10".getBytes();

        InputStream input = new ByteArrayInputStream(headerLines);

        String expected = HttpResponse.readLine(input);
        assertEquals("Content-length: 10", expected);


    }
}
