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
    LibroEntity findById(Long id);

    @Query("Select l.IDMATERIA from Libro l  order by l.IDMATERIA desc limit 1")
    Long getLastId();
}
