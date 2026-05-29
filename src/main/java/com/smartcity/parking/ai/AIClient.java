package com.smartcity.parking.ai;

import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class AIClient {

    public InputStream stream(
            String question,
            String sessionId
    ) throws Exception {

        URL url =
                new URL("http://ai-service:8000/rag/stream");

        HttpURLConnection connection =
                (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");

        connection.setRequestProperty(
                "Content-Type",
                "application/json"
        );

        connection.setDoOutput(true);

        connection.setChunkedStreamingMode(0);

        String body = String.format("""
            {
              "question": "%s",
              "session_id": "%s"
            }
            """, question, sessionId);

        connection.getOutputStream()
                .write(body.getBytes());

        connection.getOutputStream().flush();

        return connection.getInputStream();
    }
}