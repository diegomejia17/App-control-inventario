package com.example.controlinventario.Docente;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.controlinventario.Autor.AutorEntity;
import com.example.controlinventario.Commons.GenericDAO;
import com.example.controlinventario.Docente.DocenteEntity;

import java.util.List;
@Dao
public interface DocenteDao extends GenericDAO<DocenteEntity> {
    @Query("Select * from Docente where IDDOCENTE = :idDocente")
    DocenteEntity findByIdDocente(Long idDocente);

    @Query("Select * from Docente")
    List<DocenteEntity> findAll();
}
