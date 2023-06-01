package com.example.controlinventario.Secretaria;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.controlinventario.Commons.GenericDAO;

import java.util.List;
@Dao
public interface SecretariaDao extends GenericDAO<SecretariaEntity> {
    @Query("Select * from Secretaria where IDSECRETARIA = :idSecretaria")
    SecretariaEntity findByIdSecretaria(Long idSecretaria);

    @Query("Select * from Secretaria")
    List<SecretariaEntity> findAll();
}
