package com.example.controlinventario;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    String values[] = {
            "Autor.AutorMenuActivity", "Libro.LibroActivity",
            "Materia.MateriaActivity", "CheckBoxActivity",
            "RadioButtonActivity", "GalleryActivity",
            "SpinnerActivity", "TabWidgetActivity"
    };
    String ViewValues[] = {
            "Autor", "Libro", "Materia"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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