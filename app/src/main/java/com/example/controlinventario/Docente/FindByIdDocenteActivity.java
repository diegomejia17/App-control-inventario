package com.example.controlinventario.Docente;

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

import com.example.controlinventario.Docente.DocenteEntity;
import com.example.controlinventario.R;

import java.util.Optional;

public class FindByIdDocenteActivity extends AppCompatActivity {

    private ActionBar actionBar;
    private AppDatabase db;
    EditText id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_by_id_docente);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "dbControlInventario").allowMainThreadQueries().build();
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        this.id = findViewById(R.id.buscarDocente);
        actionBar.setTitle("Buscar Docente");
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    Optional<DocenteEntity> findById(Long id) {
        Optional<DocenteEntity> docenteEntity = Optional.ofNullable(db.docenteDao().findByIdDocente(id));
        return docenteEntity;
    }

    public void consultarDocente(View v) {


        Long id = Long.parseLong(this.id.getText().toString());
        Optional<DocenteEntity> docenteEntity = findById(id);

        if (docenteEntity.isPresent()) {
            try {
                DocenteEntity docente = docenteEntity.get();
                Class<?> clase = Class.forName("com.example.controlinventario.Docente.CreateDocenteActivity");
                Intent intent = new Intent(this, clase);
                //DocenteEntity
                intent.putExtra("docenteEntity", docente);
                intent.putExtra("isEditMode", true);
                startActivity(intent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return;
        }
        Toast.makeText(this, "No existe el Docente", Toast.LENGTH_SHORT).show();

    }
}
