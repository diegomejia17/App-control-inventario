package com.example.controlinventario.Descargo;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.controlinventario.Commons.GenericDAO;

import java.util.List;

@Dao
public interface DescargoDao extends GenericDAO<DescargoEntity> {
    @Query("SELECT * FROM Descargo WHERE IDDESCARGO = :idDescargo")
    DescargoEntity findByIdDescargo(Long idDescargo);

    @Query("SELECT * FROM Descargo")
    List<DescargoEntity> findAll();


}
