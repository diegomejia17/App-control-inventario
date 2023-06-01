
package com.example.controlinventario.FacultadJA;

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

public class FindByIdFacultadActivity extends AppCompatActivity {

    private ActionBar actionBar;
    private AppDatabase db;
    EditText id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_by_id_facultad);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "dbControlInventario").allowMainThreadQueries().build();
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        this.id = findViewById(R.id.buscar);
        actionBar.setTitle("Buscar Facultad");
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    Optional<FacultadEntity> findById(Long id) {
        Optional<FacultadEntity> facultadEntity = Optional.ofNullable(db.facultadDao().findByIdFacultad(id));
        return facultadEntity;
    }

    public void consultarFacultad(View v) {

        if (this.id.getText().toString().equals("")) {
            Toast.makeText(this, "Ingrese el id", Toast.LENGTH_SHORT).show();
        } else {
            Long id = Long.parseLong(this.id.getText().toString());
            Optional<FacultadEntity> facultadEntity = findById(id);

            if (facultadEntity.isPresent()) {
                try {
                    FacultadEntity facultad = facultadEntity.get();
                    Class<?> clase = Class.forName("com.example.controlinventario.FacultadJA.CreateFacultadActivity");
                    Intent intent = new Intent(this, clase);
                    //autorEntity
                    intent.putExtra("facultadEntity", facultad);
                    intent.putExtra("isEditMode", true);
                    startActivity(intent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                return;
            }
            Toast.makeText(this, "No existe el facultad", Toast.LENGTH_SHORT).show();
        }
    }
}