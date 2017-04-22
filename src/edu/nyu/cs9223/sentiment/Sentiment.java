package edu.nyu.cs9223.sentiment;

public class Sentiment {
    private final String label;
    private final Probability probability;

    public Sentiment(String label, Double neg, Double neutral, Double pos) {
        this.label = label;
        this.probability = new Probability(neg, neutral, pos);
    }

    public String getLabel() {
        return label;
    }

    public Probability getProbability() {
        return probability;
    }
}