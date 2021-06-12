package com.example.petquotes.models;

import com.example.petquotes.app.MyApplication;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Appointment extends RealmObject {

    @PrimaryKey
    private int id;
    @Required
    private String namePet;
    @Required
    private String nameOwner;
    @Required
    private String date;
    @Required
    private String time;
    @Required
    private String symptoms;

    public Appointment() {
        this.id = MyApplication.AppointmentID.incrementAndGet();
    }

    public Appointment(String namePet, String nameOwner, String date, String time, String symptoms) {
        this.id = MyApplication.AppointmentID.incrementAndGet();
        this.namePet = namePet;
        this.nameOwner = nameOwner;
        this.date = date;
        this.time = time;
        this.symptoms = symptoms;
    }

    public int getId() {
        return id;
    }

    public String getNamePet() {
        return namePet;
    }

    public void setNamePet(String namePet) {
        this.namePet = namePet;
    }

    public String getNameOwner() {
        return nameOwner;
    }

    public void setNameOwner(String nameOwner) {
        this.nameOwner = nameOwner;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }
}
