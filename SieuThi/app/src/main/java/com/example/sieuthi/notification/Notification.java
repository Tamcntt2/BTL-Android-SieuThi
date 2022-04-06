package com.example.sieuthi.notification;

import java.util.Timer;

public class Notification {

    String strND;
    String strNgay;
    String strGio;

    public Notification(String strND, String strNgay, String strGio) {
        this.strND = strND;
        this.strNgay = strNgay;
        this.strGio = strGio;
    }

    public String getStrNgay() {
        return strNgay;
    }

    public void setStrNgay(String strNgay) {
        this.strNgay = strNgay;
    }

    public String getStrGio() {
        return strGio;
    }

    public void setStrGio(String strGio) {
        this.strGio = strGio;
    }

    public String getStrND() {
        return strND;
    }

    public void setStrND(String strND) {
        this.strND = strND;
    }
}
