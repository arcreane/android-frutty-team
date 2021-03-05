package com.example.mastermind;

public class Gamer {
    private int id;
    private String login;
    private int score;

    public Gamer(int p_id, String p_login, int p_score ){
        id = p_id;
        login = p_login;
        score = p_score;

    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public int getScore() {
        return score;
    }
}
