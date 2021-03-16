package com.bakarvin.klinikhp.model;

public class Dokter {
    String ktp_dokter;
    String nama_dokter;
    String alamat_dokter;
    String poli_dokter;
    String telp_dokter;
    String username_dokter;
    String password_dokter;

    public Dokter() {
    }

    public Dokter(String ktp_dokter, String nama_dokter, String alamat_dokter, String poli_dokter, String telp_dokter, String username_dokter, String password_dokter) {
        this.ktp_dokter = ktp_dokter;
        this.nama_dokter = nama_dokter;
        this.alamat_dokter = alamat_dokter;
        this.poli_dokter = poli_dokter;
        this.telp_dokter = telp_dokter;
        this.username_dokter = username_dokter;
        this.password_dokter = password_dokter;
    }

    public String getKtp_dokter() {
        return ktp_dokter;
    }

    public void setKtp_dokter(String ktp_dokter) {
        this.ktp_dokter = ktp_dokter;
    }

    public String getNama_dokter() {
        return nama_dokter;
    }

    public void setNama_dokter(String nama_dokter) {
        this.nama_dokter = nama_dokter;
    }

    public String getAlamat_dokter() {
        return alamat_dokter;
    }

    public void setAlamat_dokter(String alamat_dokter) {
        this.alamat_dokter = alamat_dokter;
    }

    public String getPoli_dokter() {
        return poli_dokter;
    }

    public void setPoli_dokter(String poli_dokter) {
        this.poli_dokter = poli_dokter;
    }

    public String getTelp_dokter() {
        return telp_dokter;
    }

    public void setTelp_dokter(String telp_dokter) {
        this.telp_dokter = telp_dokter;
    }

    public String getUsername_dokter() {
        return username_dokter;
    }

    public void setUsername_dokter(String username_dokter) {
        this.username_dokter = username_dokter;
    }

    public String getPassword_dokter() {
        return password_dokter;
    }

    public void setPassword_dokter(String password_dokter) {
        this.password_dokter = password_dokter;
    }
}
