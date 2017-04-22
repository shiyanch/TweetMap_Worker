package edu.nyu.cs9223.servlet;

import com.google.gson.Gson;
import edu.nyu.cs9223.aws.AWSNotificationSender;
import edu.nyu.cs9223.sentiment.Sentiment;
import edu.nyu.cs9223.sentiment.SentimentService;
import edu.nyu.cs9223.tweet.Tweet;
import edu.nyu.cs9223.tweet.TweetWithSentiment;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class WorkerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuffer sb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                sb.append(line);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (sb.length() != 0){
            processMsg(sb.toString());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private void processMsg(String json) {
        Tweet tweet = new Gson().fromJson(json, Tweet.class);
        String text = tweet.getText();
        String sentimentJson = SentimentService.getSentiment(text);
        Sentiment sentiment = new Gson().fromJson(sentimentJson, Sentiment.class);
        TweetWithSentiment tweetWithSentiment = new TweetWithSentiment(tweet, sentiment);
        AWSNotificationSender.publishToSNS(tweetWithSentiment);
    }
}
