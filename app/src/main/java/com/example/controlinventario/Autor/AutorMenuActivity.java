package com.example.controlinventario.Autor;

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

public class AutorMenuActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public final String  values[] = {
            "Autor.CreateAutorActivity", "Autor.FindByIdAutorActivity", "insertar"
    };
    AutorEntity autorEntity = new AutorEntity();

    public final String viewValues[] = {
            "Crear","Buscar por ID", "Insertar Registros autor"
    };
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autor);

        db = AppDatabase.getDatabase(getApplicationContext());

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, viewValues);
        ListView listView = (ListView) findViewById(R.id.listV);
        listView.setAdapter(adaptador);
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        String nameValue = values[i];
        try {
            if (values[i].equals("insertar")){
                AutorEntity autorEntity = new AutorEntity();
                autorEntity.setNombre("Juan");
                autorEntity.setApellido("Perez");
                autorEntity.setFechaCreacion(new Date());
                db.autorDao().insert(autorEntity);
                autorEntity.setNombre("Pedro");
                autorEntity.setApellido("Perez");
                autorEntity.setFechaCreacion(new Date());
                db.autorDao().insert(autorEntity);
                autorEntity.setNombre("Maria");
                autorEntity.setApellido("Perez");
                autorEntity.setFechaCreacion(new Date());
                db.autorDao().insert(autorEntity);
                autorEntity.setNombre("Jose");
                autorEntity.setApellido("Perez");
                autorEntity.setFechaCreacion(new Date());
                db.autorDao().insert(autorEntity);

                Toast.makeText(this, "Registros insertados", Toast.LENGTH_SHORT).show();
                return;
            }

            Class<?> clase = Class.forName("com.example.controlinventario." + nameValue);
            Intent intent = new Intent(this,clase);
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
