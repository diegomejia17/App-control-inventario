package com.example.controlinventario.FacultadJA;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.controlinventario.R;

public class MenuFacultadActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    String[] menu={"Crear", "Eliminar", "Consultar", "Modificar"};
    String[] activities = {"FacultadJA.CreateFacultadActivity","FacultadJA.DeleteFacultadActivity","FacultadJA.FindByIdFacultadActivity","FacultadJA.CreateFacultadActivity"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_facultad);

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, menu);
       ListView listView = (ListView) findViewById(R.id.listV);
        listView.setAdapter(adaptador);
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        String nameValue = activities[i];
        try {
            Class<?> clase = Class.forName("com.example.controlinventario." + nameValue);
            Intent intent = new Intent(this,clase);
            if (menu[i].equals("Crear")) intent.putExtra("isEditMode",false);
            if (menu[i].equals("Modificar")) intent.putExtra("isEditMode",true);
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    }