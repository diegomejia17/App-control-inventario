package com.example.controlinventario;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.controlinventario.Autor.AutorDao;
import com.example.controlinventario.Autor.AutorEntity;
import com.example.controlinventario.Commons.DateConverter;
import com.example.controlinventario.EscuelaJA.EscuelaDao;
import com.example.controlinventario.EscuelaJA.EscuelaEntity;
import com.example.controlinventario.FacultadJA.FacultadDao;
import com.example.controlinventario.FacultadJA.FacultadEntity;
import com.example.controlinventario.Libro.LibroEntity;
import com.example.controlinventario.Materia.MateriaEntity;

@Database(entities = {AutorEntity.class, LibroEntity.class, MateriaEntity.class, FacultadEntity.class, EscuelaEntity.class}, version = 1)
@TypeConverters({DateConverter.class})

public abstract class AppDatabase extends RoomDatabase {

    public abstract AutorDao autorDao();

    public abstract FacultadDao facultadDao();

    public abstract EscuelaDao escuelaDao();

    public static AppDatabase appDb;

    public static AppDatabase getDatabase(Context context) {
        // if(appDb == null){
        appDb = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "dbControlInventario").addCallback(DB_CALLBACK).fallbackToDestructiveMigration().build();
        appDb.autorDao().findAll();
        //  }
        return appDb;
    }

    private static RoomDatabase.Callback DB_CALLBACK = new RoomDatabase.Callback() {
        @Override
        public void onCreate(SupportSQLiteDatabase db) {
            super.onCreate(db);
            db.execSQL("INSERT INTO Autor ( FECHACREACIONAUTOR, NOMBREAUTOR, APELLIDOAUTOR) VALUES ( '2021-09-01', 'Juan', 'Perez')");
        }
    };


}
