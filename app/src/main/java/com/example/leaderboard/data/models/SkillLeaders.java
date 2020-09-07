package com.example.leaderboard.data.models;

import com.google.gson.annotations.SerializedName;

public class SkillLeaders {

    @SerializedName("name")
    private String name;
    @SerializedName("score")
    private String score;
    @SerializedName("country")
    private String country;
    @SerializedName("badgeUrl")
    private String badgeUrl;

    public SkillLeaders(String name, String score, String country, String badgeUrl) {
        this.name = name;
        this.score = score;
        this.country = country;
        this.badgeUrl = badgeUrl;
    }

    public String getName() {
        return name;
    }

    public String getScore() {
        return score;
    }

    public String getCountry() {
        return country;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }
}

