package com.example.controlinventario.Secretaria;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Entity(tableName = "Secretaria")

public class SecretariaEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "IDSECRETARIA")
    private Long idSecretaria;

    @ColumnInfo(name = "FECHACREACIONSECRETARIA")
    private Date fechaCreacion;

    @ColumnInfo(name = "NOMBRESECRETARIA")
    private String nombre;


    public Long getIdSecretaria() {
        return idSecretaria;
    }

    public void setIdSecretaria(Long idSecretaria) {
        this.idSecretaria = idSecretaria;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public SecretariaEntity(Long idSecretaria, Date fechaCreacion, String nombre) {
        this.idSecretaria = idSecretaria;
        this.fechaCreacion = fechaCreacion;
        this.nombre = nombre;
    }
    public SecretariaEntity(Long idSecretaria) {
        this.idSecretaria = idSecretaria;
    }

    public SecretariaEntity(Date fechaCreacion, String nombre) {
        this.fechaCreacion = fechaCreacion;
        this.nombre = nombre;
    }

    public SecretariaEntity() {
    }
}
