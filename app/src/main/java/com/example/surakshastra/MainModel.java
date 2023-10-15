package com.example.surakshastra;

public class MainModel {
    String number;
    String status, url;

    MainModel(){

    }
    public MainModel(String number, String status, String url) {
        this.number = number;
        this.status = status;
        this.url = url;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
