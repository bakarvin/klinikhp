package com.bakarvin.klinikhp.model;

public class Pasien {

    String no_ktp;
    String nama_pasien;
    String alamat_pasien;
    String jenkel;
    String umur;
    String status;
    String no_hp;
    String nama_ibu;
    String nama_pasangan;

    public Pasien() {
    }

    public Pasien(String no_ktp, String nama_pasien, String alamat_pasien, String jenkel, String umur, String status, String no_hp, String nama_ibu, String nama_pasangan) {
        this.no_ktp = no_ktp;
        this.nama_pasien = nama_pasien;
        this.alamat_pasien = alamat_pasien;
        this.jenkel = jenkel;
        this.umur = umur;
        this.status = status;
        this.no_hp = no_hp;
        this.nama_ibu = nama_ibu;
        this.nama_pasangan = nama_pasangan;
    }

    public String getNo_ktp() {
        return no_ktp;
    }

    public void setNo_ktp(String no_ktp) {
        this.no_ktp = no_ktp;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getNama_pasien() {
        return nama_pasien;
    }

    public void setNama_pasien(String nama_pasien) {
        this.nama_pasien = nama_pasien;
    }

    public String getAlamat_pasien() {
        return alamat_pasien;
    }

    public void setAlamat_pasien(String alamat_pasien) {
        this.alamat_pasien = alamat_pasien;
    }

    public String getJenkel() {
        return jenkel;
    }

    public void setJenkel(String jenkel) {
        this.jenkel = jenkel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNama_ibu() {
        return nama_ibu;
    }

    public void setNama_ibu(String nama_ibu) {
        this.nama_ibu = nama_ibu;
    }

    public String getNama_pasangan() {
        return nama_pasangan;
    }

    public void setNama_pasangan(String nama_pasangan) {
        this.nama_pasangan = nama_pasangan;
    }
}
