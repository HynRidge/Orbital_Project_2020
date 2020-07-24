package com.example.orbital.Model;

public class ContactModel {
    public int getContact_id() {
        return contact_id;
    }

    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
    }

    int contact_id;
    String contact_name;
    int image;

    public ContactModel(String contact_name, int image,int contact_id) {
        this.contact_name = contact_name;
        this.image = image;
        this.contact_id = contact_id;
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
