package com.example.controlinventario.Docente;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Entity(tableName = "Docente")

public class DocenteEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "IDDOCENTE")
    private Long idDocente;

    @ColumnInfo(name = "FECHACREACIONDOCENTE")
    private Date fechaCreacion;

    @ColumnInfo(name = "NOMBREDOCENTE")
    private String nombre;


    public Long getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(Long idDocente) {
        this.idDocente = idDocente;
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

    public DocenteEntity(Long idDocente, Date fechaCreacion, String nombre) {
        this.idDocente = idDocente;
        this.fechaCreacion = fechaCreacion;
        this.nombre = nombre;
    }
    public DocenteEntity(Long idDocente) {
        this.idDocente = idDocente;
    }

    public DocenteEntity(Date fechaCreacion, String nombre) {
        this.fechaCreacion = fechaCreacion;
        this.nombre = nombre;
    }

    public DocenteEntity() {
    }
}
