package com.taslima.contactlist;

/**
 * Created by Taslima on 3/31/2017.
 */

public class ContactModel {

    String name,number;

    public ContactModel() {
    }

    public ContactModel(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
