package com.example.controlinventario.Editorial;

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

public class FindEditorialById extends AppCompatActivity {

    private ActionBar actionBar;
    private AppDatabase db;
    private EditText id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_editorial_by_id);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "dbControlInventario").allowMainThreadQueries().build();
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        this.id = findViewById(R.id.buscarEditorial);
        actionBar.setTitle("Buscar Editorial");
    }
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    private Optional<EditorialEntity> findById(Long id) {
        Optional<EditorialEntity> editorial = Optional.ofNullable(db.editorialDao().findByIdEditorial(id));
        return editorial;
    }
    public void consultarEditorial(View view) {
        Long id = Long.parseLong(this.id.getText().toString());
        Optional<EditorialEntity> editorialObj = findById(id);
        if (editorialObj.isPresent()) {
            try {
                EditorialEntity editorial = editorialObj.get();
                Class<?> clase = Class.forName("com.example.controlinventario.Editorial.CrudEditorial");
                Intent intent = new Intent(this, clase);
                //autorEntity
                intent.putExtra("editorial", editorial);
                intent.putExtra("isEditMode", true);
                startActivity(intent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "A ocurrido un error vuelva a intentar", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        Toast.makeText(this, "No existe la editorial", Toast.LENGTH_SHORT).show();

    }

}