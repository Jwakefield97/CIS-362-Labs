package com.example.jake.burgercaloriecounter;

public class Burger {
    static final int BEEF = 100;
    static final int LAMB = 170;
    static final int OSTRICH = 150;
    static final int ASIAGO = 90;
    static final int CREME_FRAICHE = 120;
    static final int PROSCIUTTO = 115;

    private int mPattyCal;
    private int mCheeseCal;
    private int mProsciuttoCal;
    private int mSauceCal;

    public Burger(){
        mPattyCal = BEEF;
        mCheeseCal = ASIAGO;
        mProsciuttoCal = 0;
        mSauceCal = 0;
    }

    public int getTotalCalories() {
        return mPattyCal + mCheeseCal + mProsciuttoCal + mSauceCal;
    }

    public void setmPattyCal(int mPattyCal) {
        this.mPattyCal = mPattyCal;
    }

    public void setmCheeseCal(int mCheeseCal) {
        this.mCheeseCal = mCheeseCal;
    }

    public void setmSauceCal(int mSauceCal) {
        this.mSauceCal = mSauceCal;
    }

    public void setmProsciuttoCal(int mProsciuttoCal) {
        this.mProsciuttoCal = mProsciuttoCal;
    }

    public void clearProsciuttoCalories() {
        mProsciuttoCal = 0;
    }
}
