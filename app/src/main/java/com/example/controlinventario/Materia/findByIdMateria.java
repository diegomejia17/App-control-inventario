package com.example.controlinventario.Materia;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.controlinventario.AppDatabase;
import com.example.controlinventario.Autor.AutorEntity;
import com.example.controlinventario.R;

import java.util.Optional;

public class findByIdMateria extends AppCompatActivity {

    private ActionBar actionBar;
    private AppDatabase db;
    EditText id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_by_id_materia);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "dbControlInventario").allowMainThreadQueries().build();
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        this.id = findViewById(R.id.buscarMateria);
        actionBar.setTitle("Buscar Materia");

    }
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    private Optional<MateriaEntity> findById(Long id) {
        Optional<MateriaEntity> materiaEntity = Optional.ofNullable(db.materiaDao().findByIdMateria(id));
        return materiaEntity;
    }
    public void consultarMateria(View view) {
        Long id = Long.parseLong(this.id.getText().toString());
        Optional<MateriaEntity> materiaEntity = findById(id);
        if (materiaEntity.isPresent()) {
            try {
                MateriaEntity materia = materiaEntity.get();
                Class<?> clase = Class.forName("com.example.controlinventario.Materia.CrudMateriaActivity");
                Intent intent = new Intent(this, clase);
                //autorEntity
                intent.putExtra("materia", materia);
                intent.putExtra("isEditMode", true);
                startActivity(intent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "A ocurrido un error vuelva a intentar", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        Toast.makeText(this, "No existe el autor", Toast.LENGTH_SHORT).show();

    }



}