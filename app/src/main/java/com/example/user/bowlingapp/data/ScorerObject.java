package com.example.user.bowlingapp.data;

import java.util.ArrayList;

/**
 * Created by User on 1/14/2018.
 */

public class ScorerObject {

    ArrayList<Integer> frames = new ArrayList<>();
    ArrayList<Integer> complteFrames = new ArrayList<>();
    int cumulativeScor;
    boolean isGameOver;
    private String mPlayerName;

    public ScorerObject() {

    }

    public String getPlayerName() {
        return mPlayerName;
    }

    public void setPlayerName(String name) {
        mPlayerName = name;
    }

    public int getFrameNumber(int index) {
        return frames.get(index);
    }

    public void setFrameNumber(ArrayList<Integer> frame) {
        frames = frame;
    }

    public int getScoreSoFar() {
        return cumulativeScor;
    }

    public void setScoreSoFar(int score) {
        cumulativeScor = score;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public ArrayList<Integer> roll() {
        return complteFrames;
    }
}
