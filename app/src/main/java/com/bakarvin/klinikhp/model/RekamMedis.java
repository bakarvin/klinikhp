package com.bakarvin.klinikhp.model;

import java.util.Date;

public class RekamMedis {
    String id_dokter;
    String nama_dokter;
    String id_pasien;
    String nama_pasien;
    String id_Medis;
    String anastesa;
    String diagnosa;
    String terapi;
    String resep;
    String tgl_medis;

    public RekamMedis() {
    }

    public RekamMedis(String id_dokter, String nama_dokter, String id_pasien, String nama_pasien, String id_Medis, String anastesa, String diagnosa, String terapi, String resep, String tgl_medis) {
        this.id_dokter = id_dokter;
        this.nama_dokter = nama_dokter;
        this.id_pasien = id_pasien;
        this.nama_pasien = nama_pasien;
        this.id_Medis = id_Medis;
        this.anastesa = anastesa;
        this.diagnosa = diagnosa;
        this.terapi = terapi;
        this.resep = resep;
        this.tgl_medis = tgl_medis;
    }

    public String getNama_dokter() {
        return nama_dokter;
    }

    public void setNama_dokter(String nama_dokter) {
        this.nama_dokter = nama_dokter;
    }

    public String getId_dokter() {
        return id_dokter;
    }

    public void setId_dokter(String id_dokter) {
        this.id_dokter = id_dokter;
    }

    public String getId_pasien() {
        return id_pasien;
    }

    public void setId_pasien(String id_pasien) {
        this.id_pasien = id_pasien;
    }

    public String getNama_pasien() {
        return nama_pasien;
    }

    public void setNama_pasien(String nama_pasien) {
        this.nama_pasien = nama_pasien;
    }

    public String getId_Medis() {
        return id_Medis;
    }

    public void setId_Medis(String id_Medis) {
        this.id_Medis = id_Medis;
    }

    public String getAnastesa() {
        return anastesa;
    }

    public void setAnastesa(String anastesa) {
        this.anastesa = anastesa;
    }

    public String getDiagnosa() {
        return diagnosa;
    }

    public void setDiagnosa(String diagnosa) {
        this.diagnosa = diagnosa;
    }

    public String getTerapi() {
        return terapi;
    }

    public void setTerapi(String terapi) {
        this.terapi = terapi;
    }

    public String getResep() {
        return resep;
    }

    public void setResep(String resep) {
        this.resep = resep;
    }

    public String getTgl_medis() {
        return tgl_medis;
    }

    public void setTgl_medis(String tgl_medis) {
        this.tgl_medis = tgl_medis;
    }
}
