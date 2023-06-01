package com.example.controlinventario.CatalogoUbicacionJA;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.controlinventario.AppDatabase;
import com.example.controlinventario.EscuelaJA.EscuelaEntity;
import com.example.controlinventario.R;

import java.util.Optional;

public class FindByIdCatalogoUbicacionActivity extends AppCompatActivity {


    private ActionBar actionBar;
    private AppDatabase db;
    EditText id;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_by_id_catalogo_ubicacion);
        //  AppDatabase.getDatabase(getApplicationContext());
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "dbControlInventario").allowMainThreadQueries().build();
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        this.id = findViewById(R.id.buscar);
        actionBar.setTitle("Buscar Ubicacion");
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    Optional<UbicacionEntity> findById(Long id) {
        Optional<UbicacionEntity> ubicacionEntity = Optional.ofNullable(db.ubicacionDao().findByIdUbicacion(id));
        return ubicacionEntity;
    }

    public void consultar(View v) {

        if(this.id.getText().toString().equals("")){
            Toast.makeText(this, "Ingrese el id", Toast.LENGTH_SHORT).show();
        }else{
            Long id = Long.parseLong(this.id.getText().toString());
            Optional<UbicacionEntity> ubicacionEntity = findById(id);

            if (ubicacionEntity.isPresent()) {
                try {
                    UbicacionEntity ubicacion = ubicacionEntity.get();
                    Class<?> clase = Class.forName("com.example.controlinventario.CatalogoUbicacionJA.CreateCatalogoUbicacionActivity");
                    Intent intent = new Intent(this, clase);
                    //autorEntity
                    intent.putExtra("ubicacionEntity", ubicacion);
                    intent.putExtra("isEditMode", true);
                    startActivity(intent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                return;
            }
            Toast.makeText(this, "No existe el ubicacion", Toast.LENGTH_SHORT).show();
        }

    }
}