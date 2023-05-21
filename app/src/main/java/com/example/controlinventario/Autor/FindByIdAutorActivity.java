package com.example.controlinventario.Autor;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.controlinventario.AppDatabase;
import com.example.controlinventario.R;

import java.util.Optional;

public class FindByIdAutorActivity extends AppCompatActivity {

    private ActionBar actionBar;
    private AppDatabase db;
    EditText id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_by_id);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").allowMainThreadQueries().build();
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        this.id = findViewById(R.id.buscar);
        actionBar.setTitle("Buscar Autor");
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    Optional<AutorEntity> findById(Long id) {
        Optional<AutorEntity> autorEntity = Optional.ofNullable(db.autorDao().findByIdAutor(id));
        return autorEntity;
    }

    public void consultarAutor(View v) {
        Long id = Long.parseLong(this.id.getText().toString());
        Optional<AutorEntity> autorEntity = findById(id);
        if (autorEntity.isPresent()) {
            try {
                AutorEntity autor = autorEntity.get();
                Class<?> clase = Class.forName("com.example.controlinventario.Autor.CreateAutorActivity");
                Intent intent = new Intent(this, clase);
                //autorEntity
                intent.putExtra("autorEntity", autor);
                intent.putExtra("isEditMode", true);
                startActivity(intent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return;
        }
        Toast.makeText(this, "No existe el autor", Toast.LENGTH_SHORT).show();

    }
}