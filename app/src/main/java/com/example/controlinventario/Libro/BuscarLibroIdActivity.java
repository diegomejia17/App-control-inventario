package com.example.controlinventario.Libro;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.controlinventario.AppDatabase;
import com.example.controlinventario.Materia.MateriaEntity;
import com.example.controlinventario.R;

import java.util.Optional;

public class BuscarLibroIdActivity extends AppCompatActivity {

    private ActionBar actionBar;
    private AppDatabase db;
    private EditText id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_libro_id);

        db = AppDatabase.getDatabase(getApplicationContext());
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        this.id = findViewById(R.id.buscarLibro);
        actionBar.setTitle("Buscar libro");
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }


    private Optional<LibroEntity> findById(Long id) {
        Optional<LibroEntity> libroEntity = Optional.ofNullable(db.libroDao().findById(id));
        return libroEntity;
    }

    public void consultarLibro(View view) {
        Long id = Long.parseLong(this.id.getText().toString());
        Optional<LibroEntity> libroEntity = findById(id);
        if (libroEntity.isPresent()) {
            try {
                LibroEntity libro = libroEntity.get();
                Class<?> clase = Class.forName("com.example.controlinventario.Libro.LibroActivity");
                Intent intent = new Intent(this, clase);
                //autorEntity
                intent.putExtra("libro", libro);
                intent.putExtra("isEditMode", true);
                startActivity(intent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "A ocurrido un error vuelva a intentar", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        Toast.makeText(this, "No existe el libro", Toast.LENGTH_SHORT).show();

    }


}