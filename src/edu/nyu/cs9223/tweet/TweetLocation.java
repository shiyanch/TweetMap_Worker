package edu.nyu.cs9223.tweet;

public class TweetLocation {
    private final double lat;
    private final double lon;

    public TweetLocation(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
}
