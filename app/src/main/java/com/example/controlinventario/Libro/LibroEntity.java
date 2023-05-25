package com.example.controlinventario.Libro;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.controlinventario.Editorial.EditorialEntity;
import com.example.controlinventario.Materia.MateriaEntity;

import java.util.Date;

@Entity(tableName = "Libro",
        foreignKeys = {
                @ForeignKey(
                        entity = MateriaEntity.class,
                        parentColumns = "IDMATERIA",
                        childColumns = "IDMATERIA"),
                @ForeignKey(
                        entity = EditorialEntity.class,
                        parentColumns = "IDEDITORIAL",
                        childColumns = "IDEDITORIAL")
        })

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

    @ColumnInfo(name = "IDEDITORIAL")
    private Long idEditorial;

    public LibroEntity() {
    }

    public LibroEntity(Long idLibro, Date fechaCreacionLibro, String descripcionLibro, Long isbnLibro, Date fechaPublicacionLibro, Long edicionLibro, Long tomoLibro, Long idMateria, Long idEditorial) {
        this.idLibro = idLibro;
        this.fechaCreacionLibro = fechaCreacionLibro;
        this.descripcionLibro = descripcionLibro;
        this.isbnLibro = isbnLibro;
        this.fechaPublicacionLibro = fechaPublicacionLibro;
        this.edicionLibro = edicionLibro;
        this.tomoLibro = tomoLibro;
        this.idMateria = idMateria;
        this.idEditorial = idEditorial;
    }
}
