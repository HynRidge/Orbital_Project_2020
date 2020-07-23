package com.example.orbital.Model;

public class ContactModel {
    String contact_name;
    int image;

    public ContactModel(String contact_name, int image) {
        this.contact_name = contact_name;
        this.image = image;
    }

    public ContactModel(){}

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
