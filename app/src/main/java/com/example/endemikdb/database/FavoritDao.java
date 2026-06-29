package com.example.endemikdb.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.endemikdb.model.Favorit;

import java.util.List;

@Dao
public interface FavoritDao {

    @Insert
    void insert(Favorit favorit);

    @Delete
    void delete(Favorit favorit);

    @Query("SELECT * FROM favorit")
    androidx.lifecycle.LiveData<List<Favorit>> getAll();

    @Query("SELECT * FROM favorit WHERE endemikId = :endemikId LIMIT 1")
    Favorit getByEndemikId(int endemikId);

    @Query("DELETE FROM favorit WHERE endemikId = :endemikId")
    void deleteByEndemikId(int endemikId);

    @Query("SELECT COUNT(*) FROM favorit WHERE endemikId = :endemikId")
    int isFavorit(int endemikId);
}