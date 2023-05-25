package com.example.controlinventario.Idioma;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "Idioma")
public class IdiomaEntity implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "IDIDIOMA")
    private Long id;
    @ColumnInfo(name = "NOMBREIDIOMA")
    private String nombre;
    @ColumnInfo(name = "DESCRIPCIONIDIOMA")
    private String descricion;
    @ColumnInfo(name = "FECHACREACIONIDIOMA")
    private Date fechaCreacion;

    public IdiomaEntity(Long id, String nombre, String descricion, Date fechaCreacion) {
        this.id = id;
        this.nombre = nombre;
        this.descricion = descricion;
        this.fechaCreacion = fechaCreacion;
    }

    public IdiomaEntity() {
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

    public String getDescricion() {
        return descricion;
    }

    public void setDescricion(String descricion) {
        this.descricion = descricion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
