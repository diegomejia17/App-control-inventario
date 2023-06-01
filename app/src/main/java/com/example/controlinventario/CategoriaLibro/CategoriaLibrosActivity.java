package com.example.controlinventario.CategoriaLibro;

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

public class CategoriaLibrosActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private final String values[] = {
            "CategoriaLibro.CrudCategoriaLibroActivity", "CategoriaLibro.FindByIdCategoriaLibroActivity"
            ,"insertar"
    };
    private final String ViewValues[] = {
            "Crear", "Buscar por ID", "Insertar registros categoria libro"
    };
    AppDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_libros);

        db = AppDatabase.getDatabase(getApplicationContext());

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

            if (values[i].equals("insertar")) {
                CategoriaLibroEntity categoriaLibroEntity = new CategoriaLibroEntity();
                categoriaLibroEntity.setNombre("Romance");
                categoriaLibroEntity.setDescripcion("Libros de romance");
                categoriaLibroEntity.setFechaIngreso(new Date());
                db.categoriaLibroDao().insert(categoriaLibroEntity);
                categoriaLibroEntity.setNombre("Terror");
                categoriaLibroEntity.setDescripcion("Libros de terror");
                categoriaLibroEntity.setFechaIngreso(new Date());
                db.categoriaLibroDao().insert(categoriaLibroEntity);
                categoriaLibroEntity.setNombre("Aventura");
                categoriaLibroEntity.setDescripcion("Libros de aventura");
                categoriaLibroEntity.setFechaIngreso(new Date());
                db.categoriaLibroDao().insert(categoriaLibroEntity);
                categoriaLibroEntity.setNombre("Ciencia Ficcion");
                categoriaLibroEntity.setDescripcion("Libros de ciencia ficcion");
                categoriaLibroEntity.setFechaIngreso(new Date());
                db.categoriaLibroDao().insert(categoriaLibroEntity);
                Toast.makeText(this, "Se inserto correctamente", Toast.LENGTH_SHORT).show();
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
