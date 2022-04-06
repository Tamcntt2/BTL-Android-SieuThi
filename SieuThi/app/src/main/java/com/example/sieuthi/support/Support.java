package com.example.sieuthi.support;

public class Support {

    int hinh;
    String tieude;
    String noidung;

    public Support(int hinh, String tieude, String noidung) {
        this.hinh = hinh;
        this.tieude = tieude;
        this.noidung = noidung;
    }

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }
}
