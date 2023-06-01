package com.example.controlinventario.Marca;

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

public class MarcaMenuActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public final String  values[] = {
        CreateMarcaActivity.class.getCanonicalName(),
        FindByIdMarcaActivity.class.getCanonicalName(),
        "insertar"
    };
    MarcaEntity marcaEntity = new MarcaEntity();

    public final String ViewValues[] = {
        "Crear",
        "Buscar por ID",
        "Insertar Registros Marca"
    };

    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marca);

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
                db.marcaDao().insert(new MarcaEntity(
                    "HP",
                    new Date(),
                    "Hewlett-Packard"
                ));
                db.marcaDao().insert(new MarcaEntity(
                    "Gigabyte",
                    new Date(),
                    "Gigabyte Technology"
                ));
                db.marcaDao().insert(new MarcaEntity(
                    "MSI",
                    new Date(),
                    "Micro-Star International"
                ));
                db.marcaDao().insert(new MarcaEntity(
                    "ASUS",
                    new Date(),
                    "ASUSTeK Computer, Inc."
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
