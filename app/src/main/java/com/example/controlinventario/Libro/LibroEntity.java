package com.example.controlinventario.Libro;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.controlinventario.Materia.MateriaEntity;

import java.util.Date;

@Entity(tableName = "Libro",
        foreignKeys = {
                @ForeignKey(
                        entity = MateriaEntity.class,
                        parentColumns = "IDMATERIA",
                        childColumns = "IDMATERIA",
                        onDelete = ForeignKey.CASCADE)})

public class LibroEntity {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "IDLIBRO")
    private Long idLibro;

    @ColumnInfo(name = "FECHACREACIONLIBRO")
    private Date fechaCreacionLibro;

    @ColumnInfo(name = "DESCRIPCIONLIBRO")
    private String descripcionLibro;

    @ColumnInfo(name = "ISBNLIBRO")
    private Long isbnLibro;

    @ColumnInfo(name = "FECHAPUBLICACIONLIBRO")
    private Date fechaPublicacionLibro;

    @ColumnInfo(name = "EDICIONLIBRO")
    private Long edicionLibro;

    @ColumnInfo(name = "TOMOLIBRO")
    private Long tomoLibro;

    @ColumnInfo(name = "IDMATERIA")
    private Long idMateria;

    public LibroEntity() {
    }

    public LibroEntity(Long idLibro, Date fechaCreacionLibro, String descripcionLibro, Long isbnLibro, Date fechaPublicacionLibro, Long edicionLibro, Long tomoLibro, Long idMateria) {
        this.idLibro = idLibro;
        this.fechaCreacionLibro = fechaCreacionLibro;
        this.descripcionLibro = descripcionLibro;
        this.isbnLibro = isbnLibro;
        this.fechaPublicacionLibro = fechaPublicacionLibro;
        this.edicionLibro = edicionLibro;
        this.tomoLibro = tomoLibro;
        this.idMateria = idMateria;
    }

    public Long getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Long idLibro) {
        this.idLibro = idLibro;
    }

    public Date getFechaCreacionLibro() {
        return fechaCreacionLibro;
    }

    public void setFechaCreacionLibro(Date fechaCreacionLibro) {
        this.fechaCreacionLibro = fechaCreacionLibro;
    }

    public String getDescripcionLibro() {
        return descripcionLibro;
    }

    public void setDescripcionLibro(String descripcionLibro) {
        this.descripcionLibro = descripcionLibro;
    }

    public Long getIsbnLibro() {
        return isbnLibro;
    }

    public void setIsbnLibro(Long isbnLibro) {
        this.isbnLibro = isbnLibro;
    }

    public Date getFechaPublicacionLibro() {
        return fechaPublicacionLibro;
    }

    public void setFechaPublicacionLibro(Date fechaPublicacionLibro) {
        this.fechaPublicacionLibro = fechaPublicacionLibro;
    }

    public Long getEdicionLibro() {
        return edicionLibro;
    }

    public void setEdicionLibro(Long edicionLibro) {
        this.edicionLibro = edicionLibro;
    }

    public Long getTomoLibro() {
        return tomoLibro;
    }

    public void setTomoLibro(Long tomoLibro) {
        this.tomoLibro = tomoLibro;
    }

    public Long getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Long idMateria) {
        this.idMateria = idMateria;
    }
}
