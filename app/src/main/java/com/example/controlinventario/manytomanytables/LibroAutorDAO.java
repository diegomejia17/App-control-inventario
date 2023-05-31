package com.example.controlinventario.manytomanytables;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.controlinventario.Autor.AutorEntity;
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

    @Transaction
    //delete by idLibro
    @Query("DELETE FROM autorlibro  WHERE idLibro = :idLibro")
    public void deleteLibroConAutores(Long idLibro);

    //count by idLibro
    @Query("SELECT COUNT(*) FROM autorlibro a WHERE a.IDAUTOR  = :idLibro")
    public int countLibroConAutores(Long idLibro);



}
