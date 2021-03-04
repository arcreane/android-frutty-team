package com.example.mastermind;

public class Fruit {
    // variables membres
    private final String name;
    private final Boolean seed;
    private final Boolean pealable;
    private final String url;

    // Constructor
    public Fruit(String p_name, Boolean p_seed, Boolean p_pealable, String p_url) {
        name = p_name;
        seed = p_seed;
        pealable = p_pealable;
        url = p_url;
    }

    // Getter
    public String getName() {
        return name;
    }

    public Boolean getSeed() {
        return seed;
    }

    public Boolean getPealable() {
        return pealable;
    }

    public String getUrl() {
        return url;
    }
}
