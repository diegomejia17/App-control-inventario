package com.example.controlinventario.Editorial;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.controlinventario.Commons.GenericDAO;
@Dao
public interface EditorialDao extends GenericDAO<EditorialEntity> {

    @Query("SELECT * FROM editorial e WHERE e.IDEDITORIAL = :id")
    EditorialEntity findByIdEditorial(Long id);
}
