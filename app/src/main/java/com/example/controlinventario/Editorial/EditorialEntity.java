package com.example.controlinventario.Editorial;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "editorial")

public class EditorialEntity implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "IDEDITORIAL")
    private Long id;

    @ColumnInfo (name = "NOMBRE")
    private String nombre;
    @ColumnInfo (name = "DESCRIPCION")
    private String descripcion;
    @ColumnInfo (name = "FECHACREACION")
    private Date fechaCreacion;

    public EditorialEntity(Long id, String nombre, String descripcion, Date fechaCreacion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
    }

    public EditorialEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
