package com.dhiyaulhaqza.rmenggalwaras.model;

import io.realm.RealmObject;

/**
 * Created by dhiyaulhaqza on 7/15/17.
 */

public class RumahSakit extends RealmObject {
    String pasien;
    String dokter;
    String keterangan;

    public String getPasien() {
        return pasien;
    }

    public void setPasien(String pasien) {
        this.pasien = pasien;
    }

    public String getDokter() {
        return dokter;
    }

    public void setDokter(String dokter) {
        this.dokter = dokter;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
