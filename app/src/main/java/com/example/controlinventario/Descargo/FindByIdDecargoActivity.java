package com.example.controlinventario.Descargo;

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

public class FindByIdDecargoActivity  extends AppCompatActivity {
    private ActionBar actionBar;
    private AppDatabase db;
    EditText id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_by_id_descargo);

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

    Optional<DescargoEntity> findById(Long id) {
        Optional<DescargoEntity> descargoEntity = Optional.ofNullable(db.descargoDao().findByIdDescargo(id));
        return descargoEntity;
    }

    public void consultarDescargo(View v) {
        Long id = Long.parseLong(this.id.getText().toString());
        Optional<DescargoEntity> descargoEntity = findById(id);
        if (descargoEntity.isPresent()) {
            try {
                DescargoEntity marca = descargoEntity.get();
                Class<?> clase = Class.forName(CreateDescargoActivity.class.getCanonicalName());
                Intent intent = new Intent(this, clase);

                intent.putExtra("descargoEntity", marca);
                intent.putExtra("isEditMode", true);
                startActivity(intent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return;
        }
        Toast.makeText(this, "No existe el descargo", Toast.LENGTH_SHORT).show();

    }
}