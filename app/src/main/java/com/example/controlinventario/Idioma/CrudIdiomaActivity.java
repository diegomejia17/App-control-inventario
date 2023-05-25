package com.example.controlinventario.Idioma;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

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

public class CrudIdiomaActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_crud_idioma);

        actionBar = getSupportActionBar();
        fab = findViewById(R.id.fabIdioma);
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

        this.id = findViewById(R.id.idIdioma);
        this.descripcion = findViewById(R.id.descripcionIdioma);
        this.btnEliminar = findViewById(R.id.botonEliminarIdioma);
        this.btnModificar = findViewById(R.id.botonModificariIdioma);
        this.date = findViewById(R.id.fechaIdioma);
        this.nombre = findViewById(R.id.nombreIdioma);
        date.setOnClickListener(this::onClick);

        Intent intent = getIntent();
        isEditMode = intent.getBooleanExtra("isEditMode", false);
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

        if (isEditMode) {
            IdiomaEntity idioma = (IdiomaEntity) intent.getSerializableExtra("idioma");
            this.id.setText(idioma.getId().toString());
            this.descripcion.setText(idioma.getDescricion());
            this.nombre.setText(idioma.getNombre());
            this.date.setText(formatter.format(idioma.getFechaCreacion()));

            this.btnEliminar.setVisibility(View.VISIBLE);
            this.btnModificar.setVisibility(View.VISIBLE);
            this.fab.setVisibility(View.GONE);

            this.date.setEnabled(false);
            this.descripcion.setEnabled(false);
            actionBar.setTitle("Información del Idioma");
            return;
        }
        actionBar.setTitle("Crear Idioma");
    }

    private void saveData() throws ParseException {
        String descripcion = this.descripcion.getText().toString();
        String fecha = this.date.getText().toString();
        String nombre = this.nombre.getText().toString();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        Date date = format.parse(fecha);
        IdiomaEntity idioma = new IdiomaEntity();
        //validate no empty fields
        if (descripcion.isEmpty() || fecha.isEmpty() || nombre.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Por favor, rellene todos los campos.", Toast.LENGTH_SHORT).show();
            return;
        }
        idioma.setDescricion(descripcion);
        idioma.setNombre(nombre);
        idioma.setFechaCreacion(date);
        if (isEditMode) {
            idioma.setId(Long.parseLong(this.id.getText().toString()));
            db.idiomaDao().update(idioma);
            Toast.makeText(getApplicationContext(), "Idioma actualizado correctamente.", Toast.LENGTH_SHORT).show();

            this.btnEliminar.setVisibility(View.VISIBLE);
            this.btnModificar.setVisibility(View.VISIBLE);
            this.fab.setVisibility(View.GONE);
            this.date.setEnabled(false);
            this.descripcion.setEnabled(false);
            return;
        }
        db.idiomaDao().insert(idioma);
        Toast.makeText(getApplicationContext(), "Idioma creado correctamente.", Toast.LENGTH_SHORT).show();
        cleanFields();
    }

    public void cleanFields() {
        this.id.setText("");
        this.descripcion.setText("");
        this.date.setText("");
        this.nombre.setText("");
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fechaIdioma:
                showDatePickerDialog();
                break;
        }
    }

    public void modificar(View view) {
        this.date.setEnabled(true);
        this.nombre.setEnabled(true);
        this.descripcion.setEnabled(true);
        this.fab.setVisibility(View.VISIBLE);
        this.btnEliminar.setVisibility(View.GONE);
        this.btnModificar.setVisibility(View.GONE);
        Toast.makeText(getApplicationContext(), "Ahora puede modificar los campos", Toast.LENGTH_SHORT).show();

    }

    public void eliminar(View view) {
        IdiomaEntity idioma = new IdiomaEntity();
        idioma.setId(Long.parseLong(this.id.getText().toString()));
        db.idiomaDao().delete(idioma);
        Toast.makeText(getApplicationContext(), "Idioma eliminada correctamente.", Toast.LENGTH_SHORT).show();
        this.finish();
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
}