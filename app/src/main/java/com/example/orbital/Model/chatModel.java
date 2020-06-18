package com.example.orbital.Model;

public class chatModel {
    String userName;
    int imagePath;
    String chatSnippet;

    public chatModel(String userName, int imagePath, String chatSnippet) {
        this.userName = userName;
        this.imagePath = imagePath;
        this.chatSnippet = chatSnippet;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getImagePath() {
        return imagePath;
    }

    public void setImagePath(int imagePath) {
        this.imagePath = imagePath;
    }

    public String getChatSnippet() {
        return chatSnippet;
    }

    public void setChatSnippet(String chatSnippet) {
        this.chatSnippet = chatSnippet;
    }

    @Override
    public String toString() {
        return "chatModel{" +
                "userName='" + userName + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", chatSnippet='" + chatSnippet + '\'' +
                '}';
    }
}
