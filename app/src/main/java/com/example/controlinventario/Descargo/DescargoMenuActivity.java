package com.example.controlinventario.Descargo;

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

public class DescargoMenuActivity  extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public final String  values[] = {
        CreateDescargoActivity.class.getCanonicalName(),
        FindByIdDecargoActivity.class.getCanonicalName()
    };
    DescargoEntity descargoEntity = new DescargoEntity();

    public final String ViewValues[] = {
        "Crear",
        "Buscar por ID",
    };

    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descargo);

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
            Class<?> clase = Class.forName(nameValue);
            Intent intent = new Intent(this,clase);
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
