package edu.nyu.cs9223.sentiment;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SentimentService {
    private static final String SENTIMENT_URL = "http://text-processing.com/api/sentiment/";

    public static String getSentiment(String text) {
        String body = "text="+text;
        return postWithBody(body);
    }

    private static String postWithBody(String body) {
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(SENTIMENT_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            connection.setRequestProperty("Accept-Encoding", "UTF-8");
            connection.setRequestProperty("Content-Type", "application/json");

            OutputStream os = connection.getOutputStream();
            os.write(body.getBytes("UTF-8"));
            os.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String temp;
            while((temp = reader.readLine()) != null) {
                sb.append(temp).append(" ");
            }
            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return sb.toString();
    }
}