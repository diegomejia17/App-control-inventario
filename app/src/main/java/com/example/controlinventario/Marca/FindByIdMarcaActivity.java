package com.example.controlinventario.Marca;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.controlinventario.AppDatabase;
import com.example.controlinventario.R;

import java.util.Optional;

public class FindByIdMarcaActivity extends AppCompatActivity {



    private ActionBar actionBar;
    private AppDatabase db;
    EditText id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_by_id_marca);

        db = Room.databaseBuilder(getApplicationContext(),
            AppDatabase.class, "dbControlInventario").allowMainThreadQueries().build();
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        this.id = findViewById(R.id.buscar);
        actionBar.setTitle("Buscar Marca");
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    Optional<MarcaEntity> findById(Long id) {
        Optional<MarcaEntity> marcaEntity = Optional.ofNullable(db.marcaDao().findByIdMarca(id));
        return marcaEntity;
    }

    public void consultarMarca(View v) {
        Long id = Long.parseLong(this.id.getText().toString());
        Optional<MarcaEntity> marcaEntity = findById(id);
        if (marcaEntity.isPresent()) {
            try {
                MarcaEntity marca = marcaEntity.get();
                Class<?> clase = Class.forName(CreateMarcaActivity.class.getCanonicalName());
                Intent intent = new Intent(this, clase);

                intent.putExtra("marcaEntity", marca);
                intent.putExtra("isEditMode", true);
                startActivity(intent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return;
        }
        Toast.makeText(this, "No existe la marca", Toast.LENGTH_SHORT).show();

    }
}