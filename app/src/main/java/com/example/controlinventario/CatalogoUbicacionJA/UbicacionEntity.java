package com.example.controlinventario.CatalogoUbicacionJA;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.controlinventario.EscuelaJA.EscuelaEntity;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "catalogo_ubicacion",
        foreignKeys = {
                @ForeignKey(
                        entity = EscuelaEntity.class,
                        parentColumns = "IDESCUELA",
                        childColumns = "IDESCUELA",
                        onDelete = ForeignKey.CASCADE)})
public class UbicacionEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "IDUBICACION")
    private Long idUbicacion;

    @ColumnInfo(name = "FECHACREACIONESCUELA")
    private Date fechaCreacion;

    @ColumnInfo(name = "NOMBREESCUELA")
    private String nombre;
    @ColumnInfo(name = "IDESCUELA")
    private Long idEscuela;

    public Long getIdUbicacion() {
        return idUbicacion;
    }


    public void setIdUbicacion(Long idUbicacion) {
        this.idUbicacion = idUbicacion;
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

    public Long getIdEscuela() {
        return idEscuela;
    }

    public void setIdEscuela(Long idEscuela) {
        this.idEscuela = idEscuela;
    }

    public UbicacionEntity(Long idUbicacion, Date fechaCreacion, String nombre, Long idEscuela) {
        this.idUbicacion = idUbicacion;
        this.fechaCreacion = fechaCreacion;
        this.nombre = nombre;
        this.idEscuela = idEscuela;
    }

    public UbicacionEntity( Date fechaCreacion, String nombre, Long idEscuela) {

        this.fechaCreacion = fechaCreacion;
        this.nombre = nombre;
        this.idEscuela = idEscuela;
    }
    public UbicacionEntity(Long idUbicacion) {
        this.idUbicacion = idUbicacion;

    }
    public UbicacionEntity() {


    }


}
