package com.example.rdvmanager;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class RDV implements Parcelable {

    private int id;
    private String title;
    private String date;
    private String person;
    private String phone;

    public RDV(String _title, String _date, String _person, String _phone) {
        title = _title;
        date = _date;
        person = _person;
        phone = _phone;
    }

    protected RDV(Parcel in) {
        id = in.readInt();
        title = in.readString();
        date = in.readString();
        person = in.readString();
        phone = in.readString();
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
        parcel.writeString(person);
        parcel.writeString(phone);
    }
}
