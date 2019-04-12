package com.dwikailham.beritaharian.models;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("ALL")
//public class Posting {
public class Posting implements Serializable {
    public String id, judul, perusahaan, persyaratan, tanggal, gambar;
    public int gaji;
    public Date date;

    public Posting(String id ,String title, String content, String persyaratan, String tanggal, String gambar) {
        this.id = id;
        this.judul = title;
        this.perusahaan = content;
        this.persyaratan = persyaratan;
//        this.gaji = gaji;
        this.tanggal = tanggal;
        this. gambar = gambar;
    }

    public String getJudul() {
        return judul;
    }

    @SuppressWarnings("unused")
    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPerusahaan() {
        return perusahaan;
    }

    @SuppressWarnings("unused")
    public void setPerusahaan(String perusahaan) {
        this.perusahaan = perusahaan;
    }

    @SuppressWarnings("unused")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPersyaratan() {
        return persyaratan;
    }

    public void setPersyaratan(String persyaratan) {
        this.persyaratan = persyaratan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public int getGaji() {
        return gaji;
    }

    public void setGaji(int gaji) {
        this.gaji = gaji;
    }

    public String getFormattedDate(){
        //Tampilan tanggal yang dicetak : 01 Mar 2019
        DateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
        return formatter.format(date);
    }
}
