package com.example.controlinventario.Equipo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.controlinventario.AppDatabase;
import com.example.controlinventario.R;

import java.util.Date;

public class EquipoMenuActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public final String  values[] = {
        CreateEquipoActivity.class.getCanonicalName(),
        FindByIdEquipoActivity.class.getCanonicalName(),
        "insertar"
    };
    EquipoEntity equipoEntity = new EquipoEntity();

    public final String ViewValues[] = {
        "Crear",
        "Buscar por ID",
        "Insertar Registros Equipos"
    };

    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipo);

        db = AppDatabase.getDatabase(getApplicationContext());

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

            if(nameValue.equals("insertar")) {

                db.equipoDao().insert(new EquipoEntity(
                    1L,
                    "Laptop SN21",
                    "L000001",
                    "Fia",
                    new Date()
                ));
                db.equipoDao().insert(new EquipoEntity(
                    2L,
                    "Laptop SN23",
                    "L000002",
                    "Medicina",
                    new Date()
                ));
                db.equipoDao().insert(new EquipoEntity(
                    3L,
                    "Laptop SN24",
                    "L000002",
                    "Biblioteca",
                    new Date()
                ));
                Toast.makeText(this, "Registros insertados", Toast.LENGTH_SHORT).show();
                return;
            }

            Class<?> clase = Class.forName(nameValue);
            Intent intent = new Intent(this,clase);
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
