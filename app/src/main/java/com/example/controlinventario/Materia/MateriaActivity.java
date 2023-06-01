package com.example.controlinventario.Materia;

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

import java.util.Date;

public class MateriaActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private final String values[] = {
            "Materia.CrudMateriaActivity", "Materia.findByIdMateria", "insertar"

    };
    private final String ViewValues[] = {
            "Crear","Buscar por ID", "Insertar registros materia"
    };
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia);
        db= AppDatabase.getDatabase(getApplicationContext());

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, ViewValues);
        ListView listView = (ListView) findViewById(R.id.listMateria);
        listView.setAdapter(adaptador);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        String nameValue = values[i];
        if (values[i].equals("insertar")){
            MateriaEntity materiaEntity = new MateriaEntity();
            materiaEntity.setNombre("Matematicas");
            materiaEntity.setDescripcionMateria("Materia de matematicas");
            materiaEntity.setFechaCreacionMateria(new Date());
            db.materiaDao().insert(materiaEntity);
            materiaEntity.setNombre("Español");
            materiaEntity.setDescripcionMateria("Materia de español");
            materiaEntity.setFechaCreacionMateria(new Date());
            db.materiaDao().insert(materiaEntity);
            materiaEntity.setNombre("Ingles");
            materiaEntity.setDescripcionMateria("Materia de ingles");
            materiaEntity.setFechaCreacionMateria(new Date());
            db.materiaDao().insert(materiaEntity);
            Toast.makeText(this, "Se insertaron los registros", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            Class<?> clase = Class.forName("com.example.controlinventario." + nameValue);
            Intent intent = new Intent(this,clase);
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}