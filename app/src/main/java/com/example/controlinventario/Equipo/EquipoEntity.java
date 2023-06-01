package com.example.controlinventario.Equipo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "Equipo")
public class EquipoEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "IDEQUIPO")
    private Long idEquipo;

    @ColumnInfo(name = "IDMARCA")
    private Long idMarca;

    @ColumnInfo(name = "DESCRIPCIONEQUIPO")
    private String descripcion;

    @ColumnInfo(name = "NUMEROSERIEEQUIPO")
    private String numeroSerie;

    @ColumnInfo(name = "UBICACIONEQUIPO")
    private String ubicacion;

    @ColumnInfo(name = "FECHACREACIONEQUIPO")
    private Date fechaCreacion;

    public EquipoEntity() {
    }

    public EquipoEntity(Long idMarca, String descripcion, String numeroSerie, String ubicacion, Date fechaCreacion) {
        this.idMarca = idMarca;
        this.descripcion = descripcion;
        this.numeroSerie = numeroSerie;
        this.ubicacion = ubicacion;
        this.fechaCreacion = fechaCreacion;
    }

    public Long getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Long idEquipo) {
        this.idEquipo = idEquipo;
    }

    public Long getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Long idMarca) {
        this.idMarca = idMarca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
