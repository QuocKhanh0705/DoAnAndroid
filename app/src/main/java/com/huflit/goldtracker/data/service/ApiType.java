package com.huflit.goldtracker.data.service;

public enum ApiType {

    GOLD("https://tygia.com"),
    BITCOIN("https://api.exchange.bitcoin.com"),
    CURRENCY("");

    private String baseUrl;

    public String getBaseUrl() {
        return baseUrl;
    }

    ApiType(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
