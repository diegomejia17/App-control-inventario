package com.example.controlinventario.Equipo;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.controlinventario.Commons.GenericDAO;
import com.example.controlinventario.Marca.MarcaEntity;

import java.util.List;

@Dao
public interface EquipoDao extends GenericDAO<EquipoEntity> {
	@Query("SELECT * FROM Equipo WHERE IDEQUIPO = :idEquipo")
	EquipoEntity findByIdEquipo(Long idEquipo);

	@Query("SELECT * FROM Equipo")
	List<EquipoEntity> findAll();
}
