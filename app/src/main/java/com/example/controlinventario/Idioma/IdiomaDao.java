package com.example.controlinventario.Idioma;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.controlinventario.Commons.GenericDAO;

import java.util.List;
@Dao
public interface IdiomaDao extends GenericDAO<IdiomaEntity> {
    @Query("Select * from Idioma where IDIDIOMA = :idIdioma")
    IdiomaEntity findByIdIdioma(Long idIdioma);

    @Query("Select * from Idioma")
    List<IdiomaEntity> findAll();

}
