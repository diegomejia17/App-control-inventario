package com.example.controlinventario.Autor;

import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Query;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(tableName = "Autor")
public class AutorEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "IDAUTOR")
    private Long idAutor;

    @ColumnInfo(name = "FECHACREACIONAUTOR")
    private Date fechaCreacionAutor;

    @ColumnInfo(name = "NOMBREAUTOR")
    private String nombreAutor;

    @ColumnInfo(name = "APELLIDOAUTOR")
    private String apellidoAutor;


    public AutorEntity() {
    }

    public AutorEntity(Long idAutor, Date fechaCreacionAutor, String nombreAutor, String apellidoAutor) {
        this.idAutor = idAutor;
        this.fechaCreacionAutor = fechaCreacionAutor;
        this.nombreAutor = nombreAutor;
        this.apellidoAutor = apellidoAutor;
    }

    public Long getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(Long idAutor) {
        this.idAutor = idAutor;
    }

    public Date getFechaCreacionAutor() {
        return fechaCreacionAutor;
    }

    public void setFechaCreacionAutor(Date fechaCreacionAutor) {
        this.fechaCreacionAutor = fechaCreacionAutor;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public String getApellidoAutor() {
        return apellidoAutor;
    }

    public void setApellidoAutor(String apellidoAutor) {
        this.apellidoAutor = apellidoAutor;
    }
}
