package com.example.controlinventario.manytomanytables;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.controlinventario.Commons.GenericDAO;

import java.util.List;

@Dao
public interface LibroAutorDAO  extends GenericDAO<AutorLibroEntity> {
    @Transaction
    @Query("SELECT * FROM Libro")
    public List<LibroConAutores> getLibrosConAutores();
    @Transaction
    @Query("Select * from Libro where IDLIBRO = :idLibro")
    public LibroConAutores getLibroConAutores(Long idLibro);


}
