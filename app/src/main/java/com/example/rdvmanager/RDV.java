package com.example.rdvmanager;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class RDV implements Parcelable {

    private int id;
    private final String title;
    private final String date;
    private final String time;
    private final String person;
    private final String phone;
    private boolean state;

    public RDV(String _title, String _date, String _time, String _person, String _phone, boolean _state) {
        title = _title;
        date = _date;
        time = _time;
        person = _person;
        phone = _phone;
        state = _state;
    }

    public RDV(int _id, String _title, String _date, String _time, String _person, String _phone, boolean _state) {
        id = _id;
        title = _title;
        date = _date;
        time = _time;
        person = _person;
        phone = _phone;
        state = _state;
    }

    protected RDV(Parcel in) {
        id = in.readInt();
        title = in.readString();
        date = in.readString();
        time = in.readString();
        person = in.readString();
        phone = in.readString();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            state = in.readBoolean();
        }
    }

    public String getId() {
        return Integer.toString(id);
    }

    public String getPerson() {
        return person;
    }

    public String getTitle() {
        return title;
    }

    public String getPhone() {
        return phone;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public boolean getState() {
        return state;
    }

    public static final Creator<RDV> CREATOR = new Creator<RDV>() {
        @Override
        public RDV createFromParcel(Parcel in) {
            return new RDV(in);
        }

        @Override
        public RDV[] newArray(int size) {
            return new RDV[size];
        }
    };

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(date);
        parcel.writeString(time);
        parcel.writeString(person);
        parcel.writeString(phone);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            parcel.writeBoolean(state);
        }
    }
}
