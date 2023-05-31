package com.example.controlinventario.Idioma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.controlinventario.AppDatabase;
import com.example.controlinventario.R;

public class IdiomaActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private final String values[] = {
            "Idioma.CrudIdiomaActivity", "Idioma.FindIdiomaByIdActivity", "insertar"
    };
    private final String ViewValues[] = {
            "Crear","Buscar por ID", "Insertar registros idioma"
    };
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idioma);
        db= AppDatabase.getDatabase(getApplicationContext());

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, ViewValues);
        ListView listView = (ListView) findViewById(R.id.listIdioma);
        listView.setAdapter(adaptador);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        String nameValue = values[i];
        if (values[i].equals("insertar")){
            IdiomaEntity idiomaEntity = new IdiomaEntity();
            idiomaEntity.setNombre("Español");
            idiomaEntity.setDescricion("Idioma español");
            idiomaEntity.setFechaCreacion(new java.util.Date());
            db.idiomaDao().insert(idiomaEntity);
            idiomaEntity.setNombre("Ingles");
            idiomaEntity.setDescricion("Idioma ingles");
            idiomaEntity.setFechaCreacion(new java.util.Date());
            db.idiomaDao().insert(idiomaEntity);
            idiomaEntity.setNombre("Frances");
            idiomaEntity.setDescricion("Idioma frances");
            idiomaEntity.setFechaCreacion(new java.util.Date());
            db.idiomaDao().insert(idiomaEntity);

            Toast.makeText(this, "Se insertaron los registros", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            Class<?> clase = Class.forName("com.example.controlinventario." + nameValue);
            Intent intent = new Intent(this,clase);
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "A ocurrido un error", Toast.LENGTH_SHORT).show();
        }

    }
}