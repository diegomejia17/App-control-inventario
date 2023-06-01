package com.example.controlinventario.Equipo;

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

public class FindByIdEquipoActivity extends AppCompatActivity {
    private ActionBar actionBar;
    private AppDatabase db;
    EditText id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_by_id_equipo);

        db = Room.databaseBuilder(getApplicationContext(),
            AppDatabase.class, "dbControlInventario").allowMainThreadQueries().build();
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        this.id = findViewById(R.id.buscar);
        actionBar.setTitle("Buscar Equipo");
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    Optional<EquipoEntity> findById(Long id) {
        Optional<EquipoEntity> equipoEntity = Optional.ofNullable(db.equipoDao().findByIdEquipo(id));
        return equipoEntity;
    }

    public void consultarEquipo(View v) {
        Long id = Long.parseLong(this.id.getText().toString());
        Optional<EquipoEntity> equipoEntity = findById(id);
        if (equipoEntity.isPresent()) {
            try {
                EquipoEntity marca = equipoEntity.get();
                Class<?> clase = Class.forName(CreateEquipoActivity.class.getCanonicalName());
                Intent intent = new Intent(this, clase);

                intent.putExtra("equipoEntity", marca);
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