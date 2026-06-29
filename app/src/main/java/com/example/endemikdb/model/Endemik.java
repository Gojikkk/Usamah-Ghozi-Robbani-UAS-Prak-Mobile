package com.example.endemikdb.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "endemik")
public class Endemik {

    @PrimaryKey(autoGenerate = true)
    private int localId;

    @SerializedName("id")
    private String remoteId;

    private String tipe;
    private String nama;

    @SerializedName("nama_latin")
    private String namaLatin;

    private String famili;
    private String genus;
    private String deskripsi;
    private String asal;
    private String sebaran;
    private String foto;

    @SerializedName("sumber_foto")
    private String sumberFoto;

    private String vidio;

    @SerializedName("sumber_vidio")
    private String sumberVidio;

    private String status;
    private boolean isFavorit;

    // Constructor for Room and Retrofit
    public Endemik() {
    }

    // Getters & Setters
    public int getLocalId() { return localId; }
    public void setLocalId(int localId) { this.localId = localId; }

    public String getRemoteId() { return remoteId; }
    public void setRemoteId(String remoteId) { this.remoteId = remoteId; }

    public String getTipe() { return tipe; }
    public void setTipe(String tipe) { this.tipe = tipe; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getNamaLatin() { return namaLatin; }
    public void setNamaLatin(String namaLatin) { this.namaLatin = namaLatin; }

    public String getFamili() { return famili; }
    public void setFamili(String famili) { this.famili = famili; }

    public String getGenus() { return genus; }
    public void setGenus(String genus) { this.genus = genus; }

    public String getDeskripsi() { return deskripsi; }
    public void setDeskripsi(String deskripsi) { this.deskripsi = deskripsi; }

    public String getAsal() { return asal; }
    public void setAsal(String asal) { this.asal = asal; }

    public String getSebaran() { return sebaran; }
    public void setSebaran(String sebaran) { this.sebaran = sebaran; }

    public String getFoto() { return foto; }
    public void setFoto(String foto) { this.foto = foto; }

    public String getSumberFoto() { return sumberFoto; }
    public void setSumberFoto(String sumberFoto) { this.sumberFoto = sumberFoto; }

    public String getVidio() { return vidio; }
    public void setVidio(String vidio) { this.vidio = vidio; }

    public String getSumberVidio() { return sumberVidio; }
    public void setSumberVidio(String sumberVidio) { this.sumberVidio = sumberVidio; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public boolean isFavorit() { return isFavorit; }
    public void setFavorit(boolean favorit) { isFavorit = favorit; }

    // Compatibility method for existing code that uses .getId()
    public int getId() { return localId; }
    public void setId(int id) { this.localId = id; }
}
