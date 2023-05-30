package com.example.controlinventario.EscuelaJA;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.controlinventario.Commons.GenericDAO;
import com.example.controlinventario.FacultadJA.FacultadEntity;

import java.util.List;
@Dao
public interface EscuelaDao extends GenericDAO<EscuelaEntity> {
    @Query("Select * from escuela where IDESCUELA = :idEscuela")
    EscuelaEntity findByIdEscuela(Long idEscuela);

    @Query("Select * from escuela")
    List<EscuelaEntity> findAll();
    @Query("Select COUNT(*) from catalogo_ubicacion where IDESCUELA = :idEscuela")
    int existeLlaveForane(String idEscuela);
}
