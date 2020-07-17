package com.example.orbital.Model;

public class Message {
        private int senderID;
        private int receiverID;
        private int roomID;
        private String message;

    public Message(int senderID, int receiverID, int roomID, String message) {
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.roomID = roomID;
        this.message = message;
    }

    public int getSenderID() {
        return senderID;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }

    public int getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(int receiverID) {
        this.receiverID = receiverID;
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
}
