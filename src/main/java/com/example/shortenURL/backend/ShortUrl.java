package com.example.shortenURL.backend;

public class ShortUrl {

    private int id;
    private String shortenURL;
    private String longURL;

    public ShortUrl(int id, String shortenURL, String longURL) {
        this.id = id;
        this.shortenURL = shortenURL;
        this.longURL = longURL;
    }
    public ShortUrl() {
        this.id = 0;
        this.shortenURL = "";
        this.longURL = "";
    }

    public int getId() {
        return id;
    }

    public String getShortenURL() {
        return shortenURL;
    }

    public String getLongURL() {
        return longURL;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setShortenURL(String shortenURL) {
        this.shortenURL = shortenURL;
    }

    public void setLongURL(String longURL) {
        this.longURL = longURL;
    }
}