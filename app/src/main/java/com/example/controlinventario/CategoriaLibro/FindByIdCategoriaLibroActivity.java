package com.example.controlinventario.CategoriaLibro;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.controlinventario.AppDatabase;
import com.example.controlinventario.Editorial.EditorialEntity;
import com.example.controlinventario.R;

import java.util.Optional;

public class FindByIdCategoriaLibroActivity extends AppCompatActivity {

    private ActionBar actionBar;
    private AppDatabase db;
    private EditText id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_by_id_categoria_libro);


        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "dbControlInventario").allowMainThreadQueries().build();
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        this.id = findViewById(R.id.buscarCategoria);
        actionBar.setTitle("Buscar categoria de libro");

    }
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
    private Optional<CategoriaLibroEntity> findById(Long id) {
        Optional<CategoriaLibroEntity> categoriaLibroEntity = Optional.ofNullable(db.categoriaLibroDao().findByIdCategoriaLibro(id));
        return categoriaLibroEntity;
    }
    public void consultarCategoria(View view) {
        Long id = Long.parseLong(this.id.getText().toString());
        Optional<CategoriaLibroEntity> categoriaLibroEntity = findById(id);
        if (categoriaLibroEntity.isPresent()) {
            try {
                CategoriaLibroEntity categoriaLibro = categoriaLibroEntity.get();
                Class<?> clase = Class.forName("com.example.controlinventario.CategoriaLibro.CrudCategoriaLibroActivity");
                Intent intent = new Intent(this, clase);
                //autorEntity
                intent.putExtra("categoriaLibro", categoriaLibro);
                intent.putExtra("isEditMode", true);
                startActivity(intent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "A ocurrido un error vuelva a intentar", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        Toast.makeText(this, "No existe el la categoria", Toast.LENGTH_SHORT).show();

    }
}