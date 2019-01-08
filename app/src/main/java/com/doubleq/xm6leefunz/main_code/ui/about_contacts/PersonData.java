package com.doubleq.xm6leefunz.main_code.ui.about_contacts;

import java.io.Serializable;

public class PersonData implements Serializable {

    String name;
    String qrCode;
    String headImg;
    String tital;
    String scanTital;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getTital() {
        return tital;
    }

    public void setTital(String tital) {
        this.tital = tital;
    }

    public String getScanTital() {
        return scanTital;
    }

    public void setScanTital(String scanTital) {
        this.scanTital = scanTital;
    }
}
