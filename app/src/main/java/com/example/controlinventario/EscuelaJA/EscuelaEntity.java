package com.example.controlinventario.EscuelaJA;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.controlinventario.FacultadJA.FacultadEntity;
import com.example.controlinventario.Materia.MateriaEntity;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "escuela",
        foreignKeys = {
                @ForeignKey(
                        entity = FacultadEntity.class,
                        parentColumns = "IDFACULTAD",
                        childColumns = "IDFACULTAD",
                        onDelete = ForeignKey.CASCADE)})
public class EscuelaEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "IDESCUELA")
    private Long idMateria;

    @ColumnInfo(name = "FECHACREACIONESCUELA")
    private Date fechaCreacion;

    @ColumnInfo(name = "NOMBREESCUELA")
    private String nombre;
    @ColumnInfo(name = "IDFACULTAD")
    private Long idFacultad;

    public Long getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Long idMateria) {
        this.idMateria = idMateria;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getIdFacultad() {
        return idFacultad;
    }

    public void setIdFacultad(Long idFacultad) {
        this.idFacultad = idFacultad;
    }

    public EscuelaEntity(Long idMateria, Date fechaCreacion, String nombre, Long idFacultad) {
        this.idMateria = idMateria;
        this.fechaCreacion = fechaCreacion;
        this.nombre = nombre;
        this.idFacultad = idFacultad;
    }
    public EscuelaEntity( Date fechaCreacion, String nombre, Long idFacultad) {

        this.fechaCreacion = fechaCreacion;
        this.nombre = nombre;
        this.idFacultad = idFacultad;
    }
    public EscuelaEntity(Long idMateria) {
        this.idMateria = idMateria;
    }
    public EscuelaEntity() {

    }
}
