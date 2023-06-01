package com.example.controlinventario.Secretaria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.controlinventario.R;

public class MenuSecretariaActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    String[] ViewValues = {"Crear", "Modificar", "Ver todos"};
    String[] values = {"Secretaria.CreateSecretariaActivity", "Secretaria.FindByIdSecretariaActivity", ""};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autor);

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ViewValues);
        ListView listView = (ListView) findViewById(R.id.listV);
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
        }

    }
}