package com.bakarvin.klinikhp.model;

public class JadwalDokter {
    String id_jadwal;
    String ktp_dokter;
    String keterangan_jadwal;
    String hari_jadwal;
    String nama_dokter;
    String poli_dokter;

    public JadwalDokter(String id_jadwal, String ktp_dokter, String keterangan_jadwal, String hari_jadwal, String nama_dokter, String poli_dokter) {
        this.id_jadwal = id_jadwal;
        this.ktp_dokter = ktp_dokter;
        this.keterangan_jadwal = keterangan_jadwal;
        this.hari_jadwal = hari_jadwal;
        this.nama_dokter = nama_dokter;
        this.poli_dokter = poli_dokter;
    }

    public JadwalDokter() {
    }

    public String getNama_dokter() {
        return nama_dokter;
    }

    public void setNama_dokter(String nama_dokter) {
        this.nama_dokter = nama_dokter;
    }

    public String getPoli_dokter() {
        return poli_dokter;
    }

    public void setPoli_dokter(String poli_dokter) {
        this.poli_dokter = poli_dokter;
    }

    public String getId_jadwal() {
        return id_jadwal;
    }

    public void setId_jadwal(String id_jadwal) {
        this.id_jadwal = id_jadwal;
    }

    public String getKtp_dokter() {
        return ktp_dokter;
    }

    public void setKtp_dokter(String ktp_dokter) {
        this.ktp_dokter = ktp_dokter;
    }

    public String getKeterangan_jadwal() {
        return keterangan_jadwal;
    }

    public void setKeterangan_jadwal(String keterangan_jadwal) {
        this.keterangan_jadwal = keterangan_jadwal;
    }

    public String getHari_jadwal() {
        return hari_jadwal;
    }

    public void setHari_jadwal(String hari_jadwal) {
        this.hari_jadwal = hari_jadwal;
    }
}
