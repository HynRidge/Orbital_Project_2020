package com.example.orbital.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Message implements Parcelable {
        private int senderID;
        private int roomID;
        private String message;

    public Message(int senderID, int roomID, String message) {
        this.senderID = senderID;
        this.roomID = roomID;
        this.message = message;
    }

    protected Message(Parcel in) {
        senderID = in.readInt();
        roomID = in.readInt();
        message = in.readString();
    }

    public static final Creator<Message> CREATOR = new Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel in) {
            return new Message(in);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };

    public int getSenderID() {
        return senderID;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(senderID);
        dest.writeInt(roomID);
        dest.writeString(message);
    }
}
