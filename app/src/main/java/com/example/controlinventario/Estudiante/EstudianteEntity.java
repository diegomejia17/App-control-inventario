package com.example.controlinventario.Estudiante;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "Estudiante")

public class EstudianteEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "IDESTUDIANTE")
    private Long idEstudiante;

    @ColumnInfo(name = "FECHACREACIONESTUDIANTE")
    private Date fechaCreacion;

    @ColumnInfo(name = "NOMBREESTUDIANTE")
    private String nombre;


    public Long getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Long idEstudiante) {
        this.idEstudiante = idEstudiante;
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

    public EstudianteEntity(Long idEstudiante, Date fechaCreacion, String nombre) {
        this.idEstudiante = idEstudiante;
        this.fechaCreacion = fechaCreacion;
        this.nombre = nombre;
    }
    public EstudianteEntity(Long idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public EstudianteEntity(Date fechaCreacion, String nombre) {
        this.fechaCreacion = fechaCreacion;
        this.nombre = nombre;
    }

    public EstudianteEntity() {
    }
}
