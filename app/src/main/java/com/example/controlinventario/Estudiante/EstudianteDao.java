package com.example.controlinventario.Estudiante;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.controlinventario.Autor.AutorEntity;
import com.example.controlinventario.Commons.GenericDAO;
import com.example.controlinventario.Estudiante.EstudianteEntity;

import java.util.List;
@Dao
public interface EstudianteDao extends GenericDAO<EstudianteEntity> {
    @Query("Select * from Estudiante where IDESTUDIANTE = :idEstudiante")
    EstudianteEntity findByIdEstudiante(Long idEstudiante);

    @Query("Select * from Estudiante")
    List<EstudianteEntity> findAll();
}