package com.example.controlinventario.Materia;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.controlinventario.Autor.AutorEntity;
import com.example.controlinventario.Commons.GenericDAO;

import java.util.List;

@Dao
public interface MateriaDao extends GenericDAO<MateriaEntity> {
    @Query("Select * from Materia where IDMATERIA = :idAutor")
    MateriaEntity findByIdMateria(Long idAutor);

    @Query("Select * from Materia")
    List<MateriaEntity> findAll();
}
