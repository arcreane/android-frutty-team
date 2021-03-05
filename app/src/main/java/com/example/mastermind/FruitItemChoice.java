package com.example.mastermind;

public class FruitItemChoice{

    private String mFruitName;
    private int mFruitImage;
    public FruitItemChoice(String name, int image) {
        mFruitName = name;
        mFruitImage = image;
    }
    public String getFruitName() {
        return mFruitName;
    }
    public int getFruitImage() {
        return mFruitImage;
    }
}