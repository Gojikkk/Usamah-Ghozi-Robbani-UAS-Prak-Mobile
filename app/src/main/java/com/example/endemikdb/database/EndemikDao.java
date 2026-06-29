package com.example.endemikdb.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.endemikdb.model.Endemik;

import java.util.List;

@Dao
public interface EndemikDao {

    @Insert
    void insertAll(List<Endemik> endemikList);

    @Insert
    void insert(Endemik endemik);

    @Update
    void update(Endemik endemik);

    @Query("SELECT * FROM endemik WHERE localId = :id LIMIT 1")
    androidx.lifecycle.LiveData<Endemik> getById(int id);

    @Query("SELECT * FROM endemik")
    List<Endemik> getAll();

    @Query("SELECT * FROM endemik WHERE nama LIKE '%' || :query || '%' OR namaLatin LIKE '%' || :query || '%'")
    List<Endemik> searchSync(String query);

    @Query("SELECT * FROM endemik WHERE tipe = 'Hewan'")
    androidx.lifecycle.LiveData<List<Endemik>> getAllHewan();

    @Query("SELECT * FROM endemik WHERE tipe = 'Tumbuhan'")
    androidx.lifecycle.LiveData<List<Endemik>> getAllTumbuhan();

    @Query("SELECT * FROM endemik WHERE isFavorit = 1")
    androidx.lifecycle.LiveData<List<Endemik>> getAllFavorit();

    @Query("SELECT * FROM endemik WHERE nama LIKE '%' || :query || '%' OR namaLatin LIKE '%' || :query || '%'")
    androidx.lifecycle.LiveData<List<Endemik>> search(String query);

    @Query("SELECT COUNT(*) FROM endemik")
    int getCount();

    @Query("UPDATE endemik SET isFavorit = :isFavorit WHERE localId = :id")
    void updateFavorit(int id, boolean isFavorit);

    @Query("SELECT * FROM endemik WHERE tipe = 'Hewan' AND (:region = 'Semua' OR asal LIKE '%' || :region || '%')")
    androidx.lifecycle.LiveData<List<Endemik>> getHewanByRegion(String region);

    @Query("SELECT * FROM endemik WHERE tipe = 'Tumbuhan' AND (:region = 'Semua' OR asal LIKE '%' || :region || '%')")
    androidx.lifecycle.LiveData<List<Endemik>> getTumbuhanByRegion(String region);
}
