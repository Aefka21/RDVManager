package com.example.rdvmanager;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class RDV implements Parcelable {

    private int id;
    private String title;
    private String person;
    private String phone;

    public RDV(String _title, String _person, String _phone) {
        title = _title;
        person = _person;
        phone = _phone;
    }

    public RDV(int _id, String _title, String _person, String _phone) {
        id = _id;
        title = _title;
        person = _person;
        phone = _phone;
    }

    protected RDV(Parcel in) {
        id = in.readInt();
        title = in.readString();
        person = in.readString();
        phone = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
        parcel.writeString(person);
        parcel.writeString(phone);
    }
}
