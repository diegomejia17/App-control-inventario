package com.example.controlinventario.Autor;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.controlinventario.Commons.GenericDAO;
import java.util.List;
@Dao
public interface AutorDao extends GenericDAO<AutorEntity> {
    @Query("Select * from Autor where IDAUTOR = :idAutor")
    AutorEntity findByIdAutor(Long idAutor);

    @Query("Select * from Autor")
    List<AutorEntity> findAll();

    @Transaction
    @Query("select autor.* from AUTORLIBRO inner join autor on autorlibro.idAutor = autor.idAutor where autorlibro.idLibro = :idLibro")
    public List<AutorEntity> getAutoresPorIdLibro(Long idLibro);
}
