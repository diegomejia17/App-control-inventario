package com.example.controlinventario.CatalogoUbicacionJA;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.controlinventario.Commons.GenericDAO;
import com.example.controlinventario.EscuelaJA.EscuelaEntity;

import java.util.List;

@Dao
public interface UbicacionDao extends GenericDAO<UbicacionEntity> {

    @Query("Select * from catalogo_ubicacion where IDUBICACION = :idUbicacion")
    UbicacionEntity findByIdUbicacion(Long idUbicacion);

    @Query("Select * from catalogo_ubicacion")
    List<UbicacionEntity> findAll();
}
