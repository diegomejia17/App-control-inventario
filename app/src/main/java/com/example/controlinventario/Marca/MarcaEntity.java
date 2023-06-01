package com.example.controlinventario.Marca;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "Marca")
public class MarcaEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "IDMARCA")
    private Long idMarca;

    @ColumnInfo(name = "NOMBREMARCA")
    private String nombre;

    @ColumnInfo(name = "FECHACREACIONMARCA")
    private Date fechaCreacion;

    @ColumnInfo(name = "DESCRIPCIONMARCA")
    private String descripcion;

    public MarcaEntity() {
    }

    public MarcaEntity(Long idMarca, String nombre, Date fechaCreacion, String descripcion) {
        this.idMarca = idMarca;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.descripcion = descripcion;
    }

    public MarcaEntity(String nombre, Date fechaCreacion, String descripcion) {
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.descripcion = descripcion;
    }

    public Long getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Long idMarca) {
        this.idMarca = idMarca;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
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
}
