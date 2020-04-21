package com.example.readcontacts;

public class Contact implements Comparable< Contact> {
    private String Niame;
    private String Number;

    public Contact(String niame, String number)  {
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

    @Override
    public int compareTo(Contact o) {
        return this.getNiame().compareTo(o.getNiame());
    }
}
