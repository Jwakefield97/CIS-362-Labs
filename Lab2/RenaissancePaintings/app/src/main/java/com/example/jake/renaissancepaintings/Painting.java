package com.example.jake.renaissancepaintings;

public class Painting {
    private String mDescription;
    private int mId;

    public Painting(String description, int id){
        this.mDescription = description;
        this.mId = id;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }
}
