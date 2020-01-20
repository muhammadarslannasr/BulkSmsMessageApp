package com.example.bulksmsmarketingapp;


public class Word {

   public String number;
   public String city;

   public Word() {
    }

    public Word(String number, String city) {
        this.number = number;
        this.city = city;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
