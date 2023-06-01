package com.example.controlinventario.Marca;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.controlinventario.Commons.GenericDAO;

import java.util.List;

@Dao
public interface MarcaDao extends GenericDAO<MarcaEntity> {
	@Query("SELECT * FROM Marca WHERE IDMARCA = :idMarca")
	MarcaEntity findByIdMarca(Long idMarca);

	@Query("SELECT * FROM Marca")
	List<MarcaEntity> findAll();


}
