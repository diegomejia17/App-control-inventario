package com.example.controlinventario;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.controlinventario.Autor.AutorDao;
import com.example.controlinventario.Autor.AutorEntity;

import com.example.controlinventario.CatalogoUbicacionJA.UbicacionDao;
import com.example.controlinventario.CatalogoUbicacionJA.UbicacionEntity;

import com.example.controlinventario.CategoriaLibro.CategoriaLibroDao;
import com.example.controlinventario.CategoriaLibro.CategoriaLibroEntity;

import com.example.controlinventario.Commons.DateConverter;
import com.example.controlinventario.Editorial.EditorialDao;
import com.example.controlinventario.Editorial.EditorialEntity;
import com.example.controlinventario.EscuelaJA.EscuelaDao;
import com.example.controlinventario.EscuelaJA.EscuelaEntity;
import com.example.controlinventario.FacultadJA.FacultadDao;
import com.example.controlinventario.FacultadJA.FacultadEntity;

import com.example.controlinventario.Idioma.IdiomaDao;
import com.example.controlinventario.Idioma.IdiomaEntity;
import com.example.controlinventario.Libro.LibroDao;
import com.example.controlinventario.Libro.LibroEntity;
import com.example.controlinventario.Marca.MarcaDao;
import com.example.controlinventario.Marca.MarcaEntity;
import com.example.controlinventario.Materia.MateriaDao;
import com.example.controlinventario.Materia.MateriaEntity;
import com.example.controlinventario.manytomanytables.AutorLibroEntity;
import com.example.controlinventario.manytomanytables.LibroAutorDAO;
import com.example.controlinventario.Docente.DocenteDao;
import com.example.controlinventario.Docente.DocenteEntity;
import com.example.controlinventario.Secretaria.SecretariaDao;
import com.example.controlinventario.Secretaria.SecretariaEntity;
import com.example.controlinventario.Estudiante.EstudianteDao;
import com.example.controlinventario.Estudiante.EstudianteEntity;

@Database(entities = {
    EditorialEntity.class,
    AutorEntity.class,
    LibroEntity.class,
    MateriaEntity.class,
    FacultadEntity.class,
    EscuelaEntity.class,
    IdiomaEntity.class,
    CategoriaLibroEntity.class,
    AutorLibroEntity.class,
    UbicacionEntity.class,
    MarcaEntity.class,
    SecretariaEntity.class,
    DocenteEntity.class,
    EstudianteEntity.class
}, version = 2)

@TypeConverters({DateConverter.class})

public abstract class AppDatabase extends RoomDatabase {
    public abstract LibroDao libroDao();
    public abstract CategoriaLibroDao categoriaLibroDao();
    public abstract EditorialDao editorialDao();
    public abstract LibroAutorDAO autorLibroDao();

    public abstract AutorDao autorDao();
    public abstract MateriaDao materiaDao();

    public abstract FacultadDao facultadDao();

    public abstract EscuelaDao escuelaDao();

    public abstract UbicacionDao ubicacionDao();

    public abstract IdiomaDao idiomaDao();

    public abstract SecretariaDao secretariaDao();

    public abstract DocenteDao docenteDao();

    public abstract EstudianteDao estudianteDao();
    public abstract MarcaDao marcaDao();

    public static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(Context context) {

        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                context.getApplicationContext(),
                AppDatabase.class,
                "dbControlInventario"
            )
            .allowMainThreadQueries()
            .addCallback(DB_CALLBACK)
            .build();
        }
        return INSTANCE;

    }

    private static RoomDatabase.Callback DB_CALLBACK = new RoomDatabase.Callback() {
        @Override
        public void onCreate(SupportSQLiteDatabase db) {
            super.onCreate(db);
            // db.execSQL("INSERT INTO Autor ( FECHACREACIONAUTOR, NOMBREAUTOR, APELLIDOAUTOR) VALUES ( '2021-09-01', 'Juan', 'Perez')");

        }
    };


}
