package edu.nyu.cs9223.tweet;

import twitter4j.GeoLocation;

public class Tweet {
    private final long id;
    private final String text;
    private final String username;
    private final String date;
    private final double[] location;
    public Tweet(long id, String username, String text, String date, GeoLocation location) {
        if (location == null || username == null || text == null || date == null) {
            throw new IllegalArgumentException("Arguments invalid!");
        }
        this.id = id;
        this.username = username;
        this.text = text;
        this.date = date;
        this.location = new double[] {location.getLatitude(), location.getLongitude()};
    }

    public Tweet(long id, String username, String text, String date, double latitude, double longitude) {
        if (username == null || text == null || date == null) {
            throw new IllegalArgumentException("Arguments invalid!");
        }
        this.id = id;
        this.username = username;
        this.text = text;
        this.date = date;
        this.location = new double[] {latitude, longitude};
    }

    public Tweet(long id, String username, String text, String date, double[] location) {
        if (location == null || location.length < 2 || username == null || text == null || date == null) {
            throw new IllegalArgumentException("Arguments invalid!");
        }
        this.id = id;
        this.username = username;
        this.text = text;
        this.date = date;
        this.location = new double[] {location[0], location[1]};
    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getUsername() {
        return username;
    }

    public String getDate() {
        return date;
    }

    public double[] getLocation() {
        return location;
    }
}
