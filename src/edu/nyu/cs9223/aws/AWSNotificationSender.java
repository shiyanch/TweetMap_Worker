package edu.nyu.cs9223.aws;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.google.gson.Gson;
import edu.nyu.cs9223.tweet.TweetWithSentiment;

public class AWSNotificationSender {
    private static final AmazonSNSClient SNS_Client;
    private static final String ARN = "arn:aws:sns:us-west-2:999340566974:TweetWithSentiment";
    static {
        BasicAWSCredentials credentials = new BasicAWSCredentials("", "");
        SNS_Client = new AmazonSNSClient(credentials);
        SNS_Client.setRegion(Region.getRegion(Regions.US_WEST_2));
    }

    public static void publishToSNS(TweetWithSentiment tweet) {
        String json = new Gson().toJson(tweet);
        PublishRequest request = new PublishRequest(ARN, json);
        PublishResult result = SNS_Client.publish(request);
//        System.out.println(json);
        System.out.println("MessageId - " + result.getMessageId());
    }

    public static void subscribeToTopic() {
        SubscribeRequest request = new SubscribeRequest(ARN, "email", "shiyanch@gmail.com");
        SNS_Client.subscribe(request);
        System.out.println("Done");
    }
}
