package com.example.rent.myapplication.toDoPackage;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by RENT on 2017-02-23.
 */

public class ListItem implements Parcelable{

    protected ListItem(Parcel in) {
        text = in.readString();
        checked = in.readByte() != 0;
    }

    public static final Creator<ListItem> CREATOR = new Creator<ListItem>() {
        @Override
        public ListItem createFromParcel(Parcel in) {
            return new ListItem(in);
        }

        @Override
        public ListItem[] newArray(int size) {
            return new ListItem[size];
        }
    };

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    String text;
    boolean checked;

    public ListItem(String text){
        this.text = text;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(text);
        dest.writeByte((byte) (checked ? 1 : 0));
    }
}
