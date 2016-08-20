package moe.haruue.goodhabits.model;

import java.util.ArrayList;

/**
 * Created by simonla on 2016/8/13.
 * Have a good day.
 */

public class Course {
    private String mName;
    private String mGuide;
    private int mVersion;
    private int mTotalDays;
    private ArrayList<Step> mSteps;

    public int getVersion() {
        return mVersion;
    }

    public void setVersion(int version) {
        mVersion = version;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getGuide() {
        return mGuide;
    }

    public void setGuide(String guide) {
        mGuide = guide;
    }

    public ArrayList<Step> getSteps() {
        return mSteps;
    }

    public void setSteps(ArrayList<Step> steps) {
        mSteps = steps;
    }

    public int getTotalDays() {
        int totalDays=0;
        for (Step s : mSteps) {
            totalDays=  s.getPeriod() + totalDays;
        }
        return totalDays;
    }
}
