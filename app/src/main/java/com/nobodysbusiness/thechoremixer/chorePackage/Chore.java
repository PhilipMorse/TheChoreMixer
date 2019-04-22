package com.nobodysbusiness.thechoremixer.chorePackage;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "chore")
public class Chore {

    private String name, tag1, tag2, tag3, duration, date, expiryDate;
    private Boolean status;
    @PrimaryKey(autoGenerate = true)
    private Integer key;

    public Chore(String name, String tag1, String tag2, String tag3, String duration, String date, String expiryDate, Boolean status) {
        this.name = name;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
        this.duration = duration;
        this.date = date;
        this.expiryDate = expiryDate;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    public String getTag3() {
        return tag3;
    }

    public void setTag3(String tag3) {
        this.tag3 = tag3;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }


    @NonNull
    public Integer getKey() {
        return key;
    }

    @NonNull
    public void setKey(Integer key) {
        this.key = key;
    }

}
