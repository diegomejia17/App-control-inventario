package com.example.controlinventario.Editorial;

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

public class CrudEditorial extends AppCompatActivity {

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
        setContentView(R.layout.activity_crud_editorial);


        actionBar = getSupportActionBar();
        fab = findViewById(R.id.fabEditorial);
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

        this.id = findViewById(R.id.idEditorial);
        this.nombre = findViewById(R.id.nombreEditorial);
        this.descripcion = findViewById(R.id.descripcionEditorial);
        this.btnEliminar = findViewById(R.id.botonEliminarEditorial);
        this.btnModificar = findViewById(R.id.botonModificarEditorial);
        this.date = findViewById(R.id.fechaEditorial);
        date.setOnClickListener(this::onClick);

        Intent intent = getIntent();
        isEditMode = intent.getBooleanExtra("isEditMode", false);
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");


        if (isEditMode) {
            EditorialEntity editorial = (EditorialEntity) intent.getSerializableExtra("editorial");
            this.id.setText(editorial.getId().toString());
            this.nombre.setText(editorial.getNombre());
            this.descripcion.setText(editorial.getDescripcion());
            this.date.setText(formatter.format(editorial.getFechaCreacion()));

            this.btnEliminar.setVisibility(View.VISIBLE);
            this.btnModificar.setVisibility(View.VISIBLE);
            this.fab.setVisibility(View.GONE);

            this.date.setEnabled(false);
            this.nombre.setEnabled(false);
            this.descripcion.setEnabled(false);
            actionBar.setTitle("Información de la editorial");
            return;
        }
        actionBar.setTitle("Crear editorial");
    }


    private void saveData() throws ParseException {
        String nombre = this.nombre.getText().toString();
        String descripcion = this.descripcion.getText().toString();
        String fecha = this.date.getText().toString();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        Date date = format.parse(fecha);
        EditorialEntity editorial = new EditorialEntity();
        //validate no empty fields
        if (descripcion.isEmpty() || fecha.isEmpty() || nombre.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Por favor, rellene todos los campos.", Toast.LENGTH_SHORT).show();
            return;
        }
        editorial.setDescripcion(descripcion);
        editorial.setFechaCreacion(date);
        editorial.setNombre(nombre);

        if (isEditMode) {
            editorial.setId(Long.parseLong(this.id.getText().toString()));
            db.editorialDao().update(editorial);
            Toast.makeText(getApplicationContext(), "Editorial actualizada correctamente.", Toast.LENGTH_SHORT).show();

            this.btnEliminar.setVisibility(View.VISIBLE);
            this.btnModificar.setVisibility(View.VISIBLE);
            this.fab.setVisibility(View.GONE);
            this.date.setEnabled(false);
            this.descripcion.setEnabled(false);
            this.nombre.setEnabled(false);
            return;
        }
        db.editorialDao().insert(editorial);
        Toast.makeText(getApplicationContext(), "Editorial creada correctamente.", Toast.LENGTH_SHORT).show();
        cleanFields();
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fechaEditorial:
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
        this.descripcion.setText("");
        this.date.setText("");
    }

    public void eliminar(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Eliminar dato");
        builder.setMessage("¿Estás seguro de que quieres eliminar este dato?");

// Agregar botón de confirmación
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                int numero = db.libroDao().countLibrosPorEditorial(Long.parseLong(id.getText().toString()));
                if (numero > 0) {
                    Toast.makeText(getApplicationContext(), "No se puede eliminar la editorial porque tiene libros asociados.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Acción de eliminación
                EditorialEntity editorial = new EditorialEntity();
                editorial.setId(Long.parseLong(id.getText().toString()));
                db.editorialDao().delete(editorial);
                Toast.makeText(getApplicationContext(), "Editorial eliminada", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                finish();

            }
        });

// Agregar botón de cancelar
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Cancelar eliminación
                dialog.dismiss();
            }
        });

// Mostrar el diálogo
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public void modificar(View view) {
        this.date.setEnabled(true);
        this.descripcion.setEnabled(true);
        this.fab.setVisibility(View.VISIBLE);
        this.btnEliminar.setVisibility(View.GONE);
        this.btnModificar.setVisibility(View.GONE);
        this.nombre.setEnabled(true);
        Toast.makeText(getApplicationContext(), "Ahora puede modificar los campos", Toast.LENGTH_SHORT).show();

    }
}