package com.example.controlinventario.CategoriaLibro;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.controlinventario.Commons.GenericDAO;

import java.util.List;

@Dao
public interface CategoriaLibroDao extends GenericDAO<CategoriaLibroEntity> {

    @Query("SELECT * FROM CATEGORIALIBRO c WHERE c.IDCATEGORIALIBRO = :id")
    CategoriaLibroEntity findByIdCategoriaLibro(Long id);

    @Query("SELECT * FROM CATEGORIALIBRO ")
    List<CategoriaLibroEntity> findAllCategoriaLibro();
}
