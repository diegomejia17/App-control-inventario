package com.example.controlinventario.Estudiante;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.controlinventario.AppDatabase;

import com.example.controlinventario.Estudiante.EstudianteEntity;
import com.example.controlinventario.R;

import java.util.Optional;

public class FindByIdEstudianteActivity extends AppCompatActivity {

    private ActionBar actionBar;
    private AppDatabase db;
    EditText id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_by_id_estudiante);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "dbControlInventario").allowMainThreadQueries().build();
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        this.id = findViewById(R.id.buscarEstudiante);
        actionBar.setTitle("Buscar Estudiante");
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    Optional<EstudianteEntity> findById(Long id) {
        Optional<EstudianteEntity> estudianteEntity = Optional.ofNullable(db.estudianteDao().findByIdEstudiante(id));
        return estudianteEntity;
    }

    public void consultarEstudiante(View v) {


        Long id = Long.parseLong(this.id.getText().toString());
        Optional<EstudianteEntity> estudianteEntity = findById(id);

        if (estudianteEntity.isPresent()) {
            try {
                EstudianteEntity estudiante = estudianteEntity.get();
                Class<?> clase = Class.forName("com.example.controlinventario.Estudiante.CreateEstudianteActivity");
                Intent intent = new Intent(this, clase);
                //EstudianteEntity
                intent.putExtra("estudianteEntity", estudiante);
                intent.putExtra("isEditMode", true);
                startActivity(intent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return;
        }
        Toast.makeText(this, "No existe el Estudiante", Toast.LENGTH_SHORT).show();

    }
}
