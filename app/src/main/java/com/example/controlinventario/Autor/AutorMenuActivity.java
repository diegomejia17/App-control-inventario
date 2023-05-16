package com.example.controlinventario.Autor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.controlinventario.R;

public class AutorMenuActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public final String  values[] = {
            "Autor.AutorActivity", "Libro.LibroActivity",
            "Materia.MateriaActivity", "CheckBoxActivity",
            "RadioButtonActivity", "GalleryActivity",
            "SpinnerActivity", "TabWidgetActivity"
    };
    AutorEntity autorEntity = new AutorEntity();

    public final String ViewValues[] = {
            "Crear","Buscar por ID", "Ver todos", "Modificar" , "Eliminar"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autor);

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, ViewValues);
        ListView listView = (ListView) findViewById(R.id.listV);
        listView.setAdapter(adaptador);
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        String nameValue = values[i];
        try {
            Class<?> clase = Class.forName("com.example.controlinventario." + nameValue);
            startActivity(new Intent(this, clase));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
