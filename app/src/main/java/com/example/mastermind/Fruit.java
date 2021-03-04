package com.example.mastermind;

public class Fruit {
    // variables membres
    private final String name;
    private final Boolean seed;
    private final Boolean pealable;
    private final int id;

    // Constructor
    public Fruit(String p_name, Boolean p_seed, Boolean p_pealable, int p_id) {
        name = p_name;
        seed = p_seed;
        pealable = p_pealable;
        id = p_id;
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

    public int getDrawableId() {
        return id;
    }
}
