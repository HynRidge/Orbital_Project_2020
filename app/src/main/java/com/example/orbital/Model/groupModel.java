package com.example.orbital.Model;

public class groupModel {
    String groupName;
    int groupImage;

    public int getGroupImage() {
        return groupImage;
    }

    public void setGroupImage(int groupImage) {
        this.groupImage = groupImage;
    }

    public groupModel(String groupName, int groupImage) {
        this.groupName = groupName;
        this.groupImage = groupImage;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "groupModel{" +
                "groupName='" + groupName + '\'' +
                ", groupImage=" + groupImage +
                '}';
    }
}
