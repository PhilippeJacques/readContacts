package com.example.readcontacts;

public class Contact {
    private String Niame;
    private String Number;

    public Contact(String niame, String number) {
        Niame = niame;
        Number = number;
    }

    public String getNiame() {
        return Niame;
    }

    public void setNiame(String niame) {
        Niame = niame;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }
}
