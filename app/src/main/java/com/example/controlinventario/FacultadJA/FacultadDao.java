package com.example.controlinventario.FacultadJA;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.controlinventario.Autor.AutorEntity;
import com.example.controlinventario.Commons.GenericDAO;

import java.util.List;
@Dao
public interface FacultadDao extends GenericDAO<FacultadEntity> {
    @Query("Select * from Facultad where IDFACULTAD = :idFacultad")
    FacultadEntity findByIdFacultad(Long idFacultad);

    @Query("Select * from Facultad")
    List<FacultadEntity> findAll();
}
