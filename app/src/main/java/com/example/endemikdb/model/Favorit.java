package com.example.endemikdb.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorit")
public class Favorit {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int endemikId;
    private String nama;
    private String namaLatin;
    private String tipe;
    private String foto;
    private String status;

    public Favorit(int endemikId, String nama, String namaLatin, String tipe, String foto, String status) {
        this.endemikId = endemikId;
        this.nama = nama;
        this.namaLatin = namaLatin;
        this.tipe = tipe;
        this.foto = foto;
        this.status = status;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getEndemikId() { return endemikId; }
    public void setEndemikId(int endemikId) { this.endemikId = endemikId; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getNamaLatin() { return namaLatin; }
    public void setNamaLatin(String namaLatin) { this.namaLatin = namaLatin; }

    public String getTipe() { return tipe; }
    public void setTipe(String tipe) { this.tipe = tipe; }

    public String getFoto() { return foto; }
    public void setFoto(String foto) { this.foto = foto; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}