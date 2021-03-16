package com.bakarvin.klinikhp.model;

public class Staff {
    String id_staff;
    String userName;
    String pass;
    String nama;
    String alamat;
    String no_hp;

    public Staff() {
    }

    public Staff(String id_staff, String userName, String pass, String nama, String alamat, String no_hp) {
        this.id_staff = id_staff;
        this.userName = userName;
        this.pass = pass;
        this.nama = nama;
        this.alamat = alamat;
        this.no_hp = no_hp;
    }

    public String getId_staff() {
        return id_staff;
    }

    public void setId_staff(String id_staff) {
        this.id_staff = id_staff;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public Staff(String userName, String pass) {
        this.userName = userName;
        this.pass = pass;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }


}
