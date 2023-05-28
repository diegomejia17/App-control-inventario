package com.example.controlinventario.manytomanytables;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.example.controlinventario.Libro.LibroEntity;

import java.util.List;

public class LibroConAutores {
    @Embedded public LibroEntity libro;
    @Relation(
            parentColumn = "IDLIBRO",
            entity = AutorLibroEntity.class,
            entityColumn = "IDAUTOR",

            associateBy = @Junction(
                    value = AutorLibroEntity.class,
                    parentColumn = "IDLIBRO",
                    entityColumn = "IDAUTOR"
            )
    )
    public List<AutorLibroEntity> autores;
}
