package com.example.orbital.Model;

public class ContactModel {
    String contact_name;
    int contact_id;
    int image;

    public ContactModel(String contact_name, int contact_id, int image) {
        this.contact_name = contact_name;
        this.contact_id = contact_id;
        this.image = image;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public int getContact_id() {
        return contact_id;
    }

    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
