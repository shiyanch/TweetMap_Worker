package edu.nyu.cs9223.tweet;

import edu.nyu.cs9223.sentiment.Sentiment;

public class TweetWithSentiment {
    private final Sentiment sentiment;
    private final long id;
    private final String text;
    private final String username;
    private final String date;
    private final TweetLocation location;

    private TweetWithSentiment(long id, String username, String text,
                               String date, double[] location, String label, double neg, double neutral, double pos) {
        this.id = id;
        this.username = username;
        this.text = text;
        this.date = date;
        this.sentiment = new Sentiment(label, neg, neutral, pos);
        this.location = new TweetLocation(location[0], location[1]);
    }

    public TweetWithSentiment(Tweet tweet, Sentiment sentiment) {
        this(tweet.getId(), tweet.getUsername(), tweet.getText(), tweet.getDate(), tweet.getLocation(),
                sentiment.getLabel(), sentiment.getProbability().getNeg(), sentiment.getProbability().getNeutral(), sentiment.getProbability().getPos());
    }
}
