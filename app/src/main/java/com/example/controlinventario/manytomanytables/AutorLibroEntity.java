package com.example.controlinventario.manytomanytables;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "AUTORLIBRO", primaryKeys ={ "IDAUTOR", "IDLIBRO"})
public class AutorLibroEntity {
    @NonNull
    private Long IDAUTOR;
    @NonNull
    private Long IDLIBRO;

    public AutorLibroEntity(Long IDAUTOR, Long IDLIBRO) {
        this.IDAUTOR = IDAUTOR;
        this.IDLIBRO = IDLIBRO;
    }
    public AutorLibroEntity(){

    }

    @NonNull
    public Long getIDAUTOR() {
        return IDAUTOR;
    }

    public void setIDAUTOR(@NonNull Long IDAUTOR) {
        this.IDAUTOR = IDAUTOR;
    }

    @NonNull
    public Long getIDLIBRO() {
        return IDLIBRO;
    }

    public void setIDLIBRO(@NonNull Long IDLIBRO) {
        this.IDLIBRO = IDLIBRO;
    }
}
