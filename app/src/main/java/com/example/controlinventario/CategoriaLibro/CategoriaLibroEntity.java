package com.example.controlinventario.CategoriaLibro;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "CATEGORIALIBRO")

public class CategoriaLibroEntity implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "IDCATEGORIALIBRO")
    private Long id;
    @ColumnInfo(name="DESCRIPCIONCATEGORIALIBRO")
    private String descripcion;
    @ColumnInfo(name = "NOMBRECATEGORIALIBRO")
    private String nombre;
    @ColumnInfo(name="FECHAINGRESOCATEGORIALIBRO")
    private Date fechaIngreso;

    public CategoriaLibroEntity(Long id, String descripcion, String nombre, Date fechaIngreso) {
        this.id = id;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.fechaIngreso = fechaIngreso;
    }

    public CategoriaLibroEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
}
