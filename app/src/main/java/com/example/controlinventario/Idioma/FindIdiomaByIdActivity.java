package com.example.controlinventario.Idioma;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.controlinventario.AppDatabase;
import com.example.controlinventario.Materia.MateriaEntity;
import com.example.controlinventario.R;

import java.util.Optional;

public class FindIdiomaByIdActivity extends AppCompatActivity {

    private ActionBar actionBar;
    private AppDatabase db;
    private EditText id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_idioma_by_id);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "dbControlInventario").allowMainThreadQueries().build();
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        this.id = findViewById(R.id.buscarIdioma);
        actionBar.setTitle("Buscar Idioma");
    }
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    private Optional<IdiomaEntity> findById(Long id) {
        Optional<IdiomaEntity> idiomaEntity = Optional.ofNullable(db.idiomaDao().findByIdIdioma(id));
        return idiomaEntity;
    }


    public void consultarIdioma(View view) {
        Long id = Long.parseLong(this.id.getText().toString());
        Optional<IdiomaEntity> idiomaEntity = findById(id);
        if (idiomaEntity.isPresent()) {
            try {
                IdiomaEntity idioma = idiomaEntity.get();
                Class<?> clase = Class.forName("com.example.controlinventario.Idioma.CrudIdiomaActivity");
                Intent intent = new Intent(this, clase);
                //autorEntity
                intent.putExtra("idioma", idioma);
                intent.putExtra("isEditMode", true);
                startActivity(intent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "A ocurrido un error vuelva a intentar", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        Toast.makeText(this, "No existe el Idioma", Toast.LENGTH_SHORT).show();

    }

}