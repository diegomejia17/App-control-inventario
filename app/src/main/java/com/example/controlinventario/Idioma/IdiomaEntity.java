package com.example.controlinventario.Idioma;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Idioma")
public class IdiomaEntity implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "IDIDIOMA")
    private Long id;
    @ColumnInfo(name = "DESCRIPCIONIDIOMA")
    private String Descricion;
    @ColumnInfo(name = "FECHACREACIONIDIOMA")
    private String FechaCreacion;

    public IdiomaEntity(Long id, String descricion, String fechaCreacion) {
        this.id = id;
        Descricion = descricion;
        FechaCreacion = fechaCreacion;
    }

    public IdiomaEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricion() {
        return Descricion;
    }

    public void setDescricion(String descricion) {
        Descricion = descricion;
    }

    public String getFechaCreacion() {
        return FechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        FechaCreacion = fechaCreacion;
    }
}
