package com.example.controlinventario.FacultadJA;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Entity(tableName = "Facultad")

public class FacultadEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "IDFACULTAD")
    private Long idFacultad;

    @ColumnInfo(name = "FECHACREACIONFACULTAD")
    private Date fechaCreacion;

    @ColumnInfo(name = "NOMBREFACULTAD")
    private String nombre;


    public Long getIdFacultad() {
        return idFacultad;
    }

    public void setIdFacultad(Long idFacultad) {
        this.idFacultad = idFacultad;
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

    public FacultadEntity(Long idFacultad, Date fechaCreacion, String nombre) {
        this.idFacultad = idFacultad;
        this.fechaCreacion = fechaCreacion;
        this.nombre = nombre;
    }
    public FacultadEntity(Long idFacultad) {
        this.idFacultad = idFacultad;
    }

    public FacultadEntity(Date fechaCreacion, String nombre) {
        this.fechaCreacion = fechaCreacion;
        this.nombre = nombre;
    }

    public FacultadEntity() {
    }
}
