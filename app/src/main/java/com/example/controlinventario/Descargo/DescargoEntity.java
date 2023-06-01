package com.example.controlinventario.Descargo;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;


@Entity(tableName = "Descargo")
public class DescargoEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "IDDESCARGO")
    private Long idDescargo;

    @ColumnInfo(name = "IDMOVIENTO")
    private Long idMoviento;

    @ColumnInfo(name = "FECHACREACIONDESCARGO")
    private Date fechaCreacion;

    public DescargoEntity() {
    }

    public DescargoEntity(Long idMoviento, Date fechaCreacion) {
        this.idMoviento = idMoviento;
        this.fechaCreacion = fechaCreacion;
    }

    public Long getIdDescargo() {
        return idDescargo;
    }

    public void setIdDescargo(Long idDescargo) {
        this.idDescargo = idDescargo;
    }

    public Long getIdMoviento() {
        return idMoviento;
    }

    public void setIdMoviento(Long idMoviento) {
        this.idMoviento = idMoviento;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
