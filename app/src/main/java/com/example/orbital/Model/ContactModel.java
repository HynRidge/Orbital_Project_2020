package com.example.orbital.Model;

public class ContactModel {
    String contact_name;
    int contact_id;
    String image_path;

    public ContactModel(String contact_name, int contact_id, String image_path) {
        this.contact_name = contact_name;
        this.contact_id = contact_id;
        this.image_path = image_path;
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

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }
}
