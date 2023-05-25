package com.example.controlinventario.Libro;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.controlinventario.Commons.GenericDAO;

import java.util.List;

@Dao
public interface LibroDao extends GenericDAO<LibroEntity> {
    @Query("SELECT * FROM Libro")
    List<LibroEntity> getAll();

    @Query("SELECT * FROM Libro WHERE idLibro = :id")
    LibroEntity findById(int id);
}
