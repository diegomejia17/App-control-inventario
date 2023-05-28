package com.example.controlinventario.CategoriaLibro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.controlinventario.R;

public class CategoriaLibrosActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private final String values[] = {
            "CategoriaLibro.CrudCategoriaLibroActivity", "CategoriaLibro.FindByIdCategoriaLibroActivity",
    };
    private final String ViewValues[] = {
            "Crear", "Buscar por ID"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_libros);

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, ViewValues);
        ListView listView = (ListView) findViewById(R.id.listCategoriaLibros);
        listView.setAdapter(adaptador);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String nameValue = values[i];
        try {
            Class<?> clase = Class.forName("com.example.controlinventario." + nameValue);
            Intent intent = new Intent(this, clase);
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "A ocurrido un error", Toast.LENGTH_SHORT).show();
        }

    }
}
