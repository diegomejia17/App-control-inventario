package com.example.controlinventario.Autor;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.controlinventario.AppDatabase;
import com.example.controlinventario.Commons.DatePickerFragment;
import com.example.controlinventario.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CreateAutorActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etPlannedDate, nombre, apellido, id;
    private FloatingActionButton fab;
    private Boolean isEditMode;
    private ActionBar actionBar;

    AppDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_autor);
        actionBar = getSupportActionBar();
        fab = findViewById(R.id.fab);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").allowMainThreadQueries().build();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        this.apellido = findViewById(R.id.apellido);
        this.nombre = findViewById(R.id.nombre);
        this.id = findViewById(R.id.id);
        etPlannedDate = findViewById(R.id.fecha);
        etPlannedDate.setOnClickListener(this::onClick);

        Intent intent = getIntent();
        isEditMode = intent.getBooleanExtra("isEditMode", false);

        if (isEditMode) {
            AutorEntity autorEntity = (AutorEntity) intent.getSerializableExtra("autorEntity");
            String nombre = autorEntity.getNombre();
            String apellido = autorEntity.getApellido();
            Date fecha = autorEntity.getFechaCreacion();
            Long id = autorEntity.getIdAutor();

            this.nombre.setText(nombre);
            this.apellido.setText(apellido);
            this.etPlannedDate.setText(fecha.toString());
            this.id.setText(id.toString());

            actionBar.setTitle("Actualizar Autor");
        } else {
            actionBar.setTitle("Crear Autor");
        }

        fab.setOnClickListener(view -> {
            try {
                saveData();
            } catch (ParseException e) {
                Toast.makeText(getApplicationContext(), "A ocurrido un error, vuelva a intentarlo.", Toast.LENGTH_SHORT).show();
                throw new RuntimeException(e);
            }
        });
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance((datePicker, year, month, day) -> {
            // +1 because January is zero
            final String selectedDate = twoDigits(day) + "/" + (twoDigits(month + 1)) + "/" + year;
            etPlannedDate.setText(selectedDate);
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fecha:
                showDatePickerDialog();
                break;
        }
    }

    private void saveData() throws ParseException {
        String nombre = this.nombre.getText().toString();
        String apellido = this.apellido.getText().toString();
        String fecha = this.etPlannedDate.getText().toString();

        if (nombre.isEmpty() || apellido.isEmpty() || fecha.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Por favor llene todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        Date date = format.parse(fecha);
        AutorEntity autor = new AutorEntity(date, nombre, apellido);
        if (isEditMode) {
            autor.setIdAutor(Long.parseLong(this.id.getText().toString()));
            db.autorDao().update(autor);
            Toast.makeText(getApplicationContext(), "Autor actualizado", Toast.LENGTH_SHORT).show();
            return;
        }
        db.autorDao().insert(autor);
        Toast.makeText(getApplicationContext(), "Autor creado", Toast.LENGTH_SHORT).show();
    }

    private String twoDigits(int n) {
        return (n <= 9) ? ("0" + n) : String.valueOf(n);
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }


    //limpiar campos
    public void limpiar() {
        this.nombre.setText("");
        this.apellido.setText("");
        this.etPlannedDate.setText("");
        this.id.setText("");
    }
}