package com.example.controlinventario.Materia;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "Materia")
public class MateriaEntity implements Serializable {

    /*IDMATERIA            int not null,
   FECHACREACIONMATERIA datetime not null,
   DESCRIPCIONMATERIA   varchar(100) not null,*/
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "IDMATERIA")
    private Long idMateria;
    @ColumnInfo(name = "NOMBREMATERIA")
    private String nombre;

    @ColumnInfo(name = "FECHACREACIONMATERIA")

    private Date fechaCreacionMateria;

    @ColumnInfo(name = "DESCRIPCIONMATERIA")

    private String descripcionMateria;


    public MateriaEntity() {
    }

    public MateriaEntity(Long idMateria, String nombre, Date fechaCreacionMateria, String descripcionMateria) {
        this.idMateria = idMateria;
        this.nombre = nombre;
        this.fechaCreacionMateria = fechaCreacionMateria;
        this.descripcionMateria = descripcionMateria;
    }

    public MateriaEntity(Long idMateria, Date fechaCreacionMateria, String descripcionMateria) {
        this.idMateria = idMateria;
        this.fechaCreacionMateria = fechaCreacionMateria;
        this.descripcionMateria = descripcionMateria;
    }

    public Long getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Long idMateria) {
        this.idMateria = idMateria;
    }


    public Date getFechaCreacionMateria() {
        return fechaCreacionMateria;
    }

    public void setFechaCreacionMateria( Date fechaCreacionMateria) {
        this.fechaCreacionMateria = fechaCreacionMateria;
    }


    public String getDescripcionMateria() {
        return descripcionMateria;
    }

    public void setDescripcionMateria( String descripcionMateria) {
        this.descripcionMateria = descripcionMateria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
