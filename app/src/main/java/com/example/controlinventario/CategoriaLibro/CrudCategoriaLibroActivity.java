package com.example.controlinventario.CategoriaLibro;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.controlinventario.AppDatabase;
import com.example.controlinventario.Commons.DatePickerFragment;
import com.example.controlinventario.Materia.MateriaEntity;
import com.example.controlinventario.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CrudCategoriaLibroActivity extends AppCompatActivity {

    private EditText date, descripcion, id, nombre;
    private FloatingActionButton fab;
    private Boolean isEditMode;
    private ActionBar actionBar;
    private Button btnModificar;
    private Button btnEliminar;

    AppDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_categoria_libro);

        actionBar = getSupportActionBar();
        fab = findViewById(R.id.fabCategoria);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "dbControlInventario").allowMainThreadQueries().build();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        fab.setOnClickListener(view -> {
            try {
                saveData();
            } catch (ParseException e) {
                Toast.makeText(getApplicationContext(), "A ocurrido un error, vuelva a intentarlo.", Toast.LENGTH_SHORT).show();
                throw new RuntimeException(e);
            }
        });

        this.id = findViewById(R.id.idCategoria);
        this.descripcion = findViewById(R.id.descripcionCategoria);
        this.nombre = findViewById(R.id.nombreCategoria);
        this.btnEliminar = findViewById(R.id.botonEliminarCategoria);
        this.btnModificar = findViewById(R.id.botonModificarCategoria);
        this.date = findViewById(R.id.fechaCategoria);
        date.setOnClickListener(this::onClick);

        Intent intent = getIntent();
        isEditMode = intent.getBooleanExtra("isEditMode", false);
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

        if (isEditMode) {
            CategoriaLibroEntity categoriaLibro = (CategoriaLibroEntity) intent.getSerializableExtra("categoriaLibro");
            this.id.setText(categoriaLibro.getId().toString());
            this.descripcion.setText(categoriaLibro.getDescripcion());
            this.nombre.setText(categoriaLibro.getNombre());
            this.date.setText(formatter.format(categoriaLibro.getFechaIngreso()));

            this.btnEliminar.setVisibility(View.VISIBLE);
            this.btnModificar.setVisibility(View.VISIBLE);
            this.fab.setVisibility(View.GONE);

            this.date.setEnabled(false);
            this.descripcion.setEnabled(false);
            actionBar.setTitle("Información de la Categoria");
            return;
        }
        actionBar.setTitle("Crear Categoria");

    }


    private void saveData() throws ParseException {
        String descripcion = this.descripcion.getText().toString();
        String fecha = this.date.getText().toString();
        String nombre = this.nombre.getText().toString();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        Date date = format.parse(fecha);
        CategoriaLibroEntity categoriaLibro = new CategoriaLibroEntity();
        //validate no empty fields
        if (descripcion.isEmpty() || fecha.isEmpty() || nombre.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Por favor, rellene todos los campos.", Toast.LENGTH_SHORT).show();
            return;
        }
        categoriaLibro.setDescripcion(descripcion);
        categoriaLibro.setFechaIngreso(date);
        categoriaLibro.setNombre(nombre);

        if (isEditMode) {
            categoriaLibro.setId(Long.parseLong(this.id.getText().toString()));
            db.categoriaLibroDao().update(categoriaLibro);
            Toast.makeText(getApplicationContext(), "Categoria actualizada correctamente.", Toast.LENGTH_SHORT).show();

            this.btnEliminar.setVisibility(View.VISIBLE);
            this.btnModificar.setVisibility(View.VISIBLE);
            this.fab.setVisibility(View.GONE);
            this.date.setEnabled(false);
            this.nombre.setEnabled(false);
            this.descripcion.setEnabled(false);
            return;
        }
        db.categoriaLibroDao().insert(categoriaLibro);
        Toast.makeText(getApplicationContext(), "categoria creada correctamente.", Toast.LENGTH_SHORT).show();
        cleanFields();
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fechaCategoria:
                showDatePickerDialog();
                break;
        }
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance((datePicker, year, month, day) -> {
            // +1 because January is zero
            final String selectedDate = twoDigits(day) + "/" + (twoDigits(month + 1)) + "/" + year;
            this.date.setText(selectedDate);
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private String twoDigits(int n) {
        return (n <= 9) ? ("0" + n) : String.valueOf(n);
    }

    //clean fields
    public void cleanFields() {
        this.id.setText("");
        this.nombre.setText("");
        this.descripcion.setText("");
        this.date.setText("");
    }

    public void eliminar(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Eliminar dato");
        builder.setMessage("¿Estás seguro de que quieres eliminar este dato?");

// Agregar botón de confirmación
        builder.setPositiveButton("Sí", (dialog, which) -> {

            int numero = db.libroDao().countLibrosPorCategoria(Long.parseLong(id.getText().toString()));
            if (numero > 0) {
                Toast.makeText(getApplicationContext(), "No se puede eliminar la categoria, tiene libros asociados.", Toast.LENGTH_SHORT).show();
                return;
            }
            // Acción de eliminación
            CategoriaLibroEntity categoriaLibro = new CategoriaLibroEntity();
            categoriaLibro.setId(Long.parseLong(id.getText().toString()));
            db.categoriaLibroDao().delete(categoriaLibro);
            Toast.makeText(getApplicationContext(), "Categoria eliminada", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
            finish();

        });
// Agregar botón de cancelar
        builder.setNegativeButton("No", (dialog, which) -> {
            // Cancelar eliminación
            dialog.dismiss();
        });

// Mostrar el diálogo
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void modificar(View view) {
        this.date.setEnabled(true);
        this.descripcion.setEnabled(true);
        this.fab.setVisibility(View.VISIBLE);
        this.nombre.setEnabled(true);
        this.btnEliminar.setVisibility(View.GONE);
        this.btnModificar.setVisibility(View.GONE);
        Toast.makeText(getApplicationContext(), "Ahora puede modificar los campos", Toast.LENGTH_SHORT).show();

    }
}