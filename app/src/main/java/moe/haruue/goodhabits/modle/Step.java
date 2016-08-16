package moe.haruue.goodhabits.modle;

/**
 * Created by simonla on 2016/8/13.
 * Have a good day.
 */

public class Step {
    private String mName;
    private String mSuggest;
    private int period;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getSuggest() {
        return mSuggest;
    }

    public void setSuggest(String suggest) {
        mSuggest = suggest;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }
}
