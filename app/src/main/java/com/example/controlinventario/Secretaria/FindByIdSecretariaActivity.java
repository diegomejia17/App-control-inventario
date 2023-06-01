
package com.example.controlinventario.Secretaria;

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

public class FindByIdSecretariaActivity extends AppCompatActivity {

    private ActionBar actionBar;
    private AppDatabase db;
    EditText id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_by_id_secretaria);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "dbControlInventario").allowMainThreadQueries().build();
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        this.id = findViewById(R.id.buscarSecretaria);
        actionBar.setTitle("Buscar Secretaria");
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    Optional<SecretariaEntity> findById(Long id) {
        Optional<SecretariaEntity> secretariaEntity = Optional.ofNullable(db.secretariaDao().findByIdSecretaria(id));
        return secretariaEntity;
    }

    public void consultarSecretaria(View v) {


        Long id = Long.parseLong(this.id.getText().toString());
        Optional<SecretariaEntity> secretariaEntity = findById(id);

        if (secretariaEntity.isPresent()) {
            try {
                SecretariaEntity secretaria = secretariaEntity.get();
                Class<?> clase = Class.forName("com.example.controlinventario.Secretaria.CreateSecretariaActivity");
                Intent intent = new Intent(this, clase);
                //autorEntity
                intent.putExtra("secretariaEntity", secretaria);
                intent.putExtra("isEditMode", true);
                startActivity(intent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return;
        }
        Toast.makeText(this, "No existe la Secretaria", Toast.LENGTH_SHORT).show();

    }
}