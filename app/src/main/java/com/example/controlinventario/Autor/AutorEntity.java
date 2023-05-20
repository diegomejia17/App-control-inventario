package com.example.controlinventario.Autor;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "Autor")
public class AutorEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "IDAUTOR")
    private Long idAutor;

    @ColumnInfo(name = "FECHACREACIONAUTOR")
    private Date fechaCreacion;

    @ColumnInfo(name = "NOMBREAUTOR")
    private String nombre;

    @ColumnInfo(name = "APELLIDOAUTOR")
    private String apellido;


    public AutorEntity() {
    }

    public AutorEntity( Date fechaCreacionAutor, String nombreAutor, String apellidoAutor) {

        this.fechaCreacion = fechaCreacionAutor;
        this.nombre = nombreAutor;
        this.apellido = apellidoAutor;
    }
    public AutorEntity(Long idAutor, Date fechaCreacionAutor, String nombreAutor, String apellidoAutor) {
        this.idAutor = idAutor;
        this.fechaCreacion = fechaCreacionAutor;
        this.nombre = nombreAutor;
        this.apellido = apellidoAutor;
    }


    public Long getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(Long idAutor) {
        this.idAutor = idAutor;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacionAutor) {
        this.fechaCreacion = fechaCreacionAutor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombreAutor) {
        this.nombre = nombreAutor;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellidoAutor) {
        this.apellido = apellidoAutor;
    }
}
