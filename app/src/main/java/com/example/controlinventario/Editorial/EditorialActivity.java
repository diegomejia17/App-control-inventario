package com.example.controlinventario.Editorial;

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

public class EditorialActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private final String values[] = {
            "Editorial.CrudEditorial", "Editorial.FindEditorialById", "insertar"
    };
    private final String ViewValues[] = {
            "Crear", "Buscar por ID", "Insertar registros editorial"
    };
    AppDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editorial);

        db = AppDatabase.getDatabase(getApplicationContext());

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, ViewValues);
        ListView listView = (ListView) findViewById(R.id.listEditorial);
        listView.setAdapter(adaptador);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        String nameValue = values[i];
        try {
            if (values[i].equals("insertar")){
                EditorialEntity editorialEntity = new EditorialEntity();
                editorialEntity.setNombre("La casa del libro");
                editorialEntity.setDescripcion("Gran variedad de libros, ubicados en la ciudad de Quito");
                db.editorialDao().insert(editorialEntity);
                editorialEntity.setNombre("El quinde");
                editorialEntity.setDescripcion("Gran variedad de libros, ubicados en la ciudad de Guayaquil");
                db.editorialDao().insert(editorialEntity);
                editorialEntity.setNombre("Pearson");
                editorialEntity.setDescripcion("Gran variedad de libros, ubicados en la ciudad de Cuenca");
                db.editorialDao().insert(editorialEntity);
                editorialEntity.setNombre("Santillana");
                editorialEntity.setDescripcion("Gran variedad de libros, ubicados en la ciudad de Ambato");
                db.editorialDao().insert(editorialEntity);
                editorialEntity.setNombre("El universo");
                editorialEntity.setDescripcion("Gran variedad de libros, ubicados en la ciudad de Loja");
                db.editorialDao().insert(editorialEntity);

                Toast.makeText(this, "Se insertaron los registros", Toast.LENGTH_SHORT).show();
                return;
            }

            Class<?> clase = Class.forName("com.example.controlinventario." + nameValue);
            Intent intent = new Intent(this, clase);
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "A ocurrido un error", Toast.LENGTH_SHORT).show();
        }

    }


}