package com.example.controlinventario.EscuelaJA;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.controlinventario.AppDatabase;
import com.example.controlinventario.FacultadJA.FacultadEntity;
import com.example.controlinventario.R;

import java.util.Optional;

public class FindByEscuelaActivity extends AppCompatActivity {


    private ActionBar actionBar;
    private AppDatabase db;
    EditText id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_by_escuela);
      //  AppDatabase.getDatabase(getApplicationContext());
        db = Room.databaseBuilder(getApplicationContext(),
               AppDatabase.class, "dbControlInventario").allowMainThreadQueries().build();
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        this.id = findViewById(R.id.buscar);
        actionBar.setTitle("Buscar Escuela");
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    Optional<EscuelaEntity> findById(Long id) {
        Optional<EscuelaEntity> escuelaEntity = Optional.ofNullable(db.escuelaDao().findByIdEscuela(id));
        return escuelaEntity;
    }

    public void consultar(View v) {
        Long id = Long.parseLong(this.id.getText().toString());
        Optional<EscuelaEntity> escuelaEntity = findById(id);

        if (escuelaEntity.isPresent()) {
            try {
                EscuelaEntity escuela = escuelaEntity.get();
                Class<?> clase = Class.forName("com.example.controlinventario.EscuelaJA.CreateEscuelaActivity");
                Intent intent = new Intent(this, clase);
                //autorEntity
                intent.putExtra("escuelaEntity", escuela);
                intent.putExtra("isEditMode", true);
                startActivity(intent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return;
        }
        Toast.makeText(this, "No existe el escuela", Toast.LENGTH_SHORT).show();
    }
}