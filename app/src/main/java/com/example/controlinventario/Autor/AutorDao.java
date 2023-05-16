package com.example.controlinventario.Autor;

import androidx.room.Dao;
import androidx.room.Query;
import com.example.controlinventario.Commons.GenericDAO;
import java.util.List;
@Dao
public interface AutorDao extends GenericDAO<AutorEntity> {
    @Query("Select * from Autor where IDAUTOR = :idAutor")
    AutorEntity findByIdAutor(Long idAutor);

    @Query("Select * from Autor")
    List<AutorEntity> findAll();
}
