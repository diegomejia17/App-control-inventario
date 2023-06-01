package com.example.controlinventario.EscuelaJA;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
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
import com.example.controlinventario.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateEscuelaActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etPlannedDate, nombre, id, txtidFacultad;
    private FloatingActionButton fab;
    private Boolean isEditMode;
    private ActionBar actionBar;
    private Button btnModificar;
    private Button btnEliminar;

    AppDatabase db;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_escuela);
        actionBar = getSupportActionBar();
        fab = findViewById(R.id.fab);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "dbControlInventario").allowMainThreadQueries().build();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        this.nombre = findViewById(R.id.nombre);
        this.txtidFacultad = findViewById(R.id.txtidFacultad);
        this.id = findViewById(R.id.id);
        this.btnEliminar = findViewById(R.id.botonEliminar);
        this.btnModificar = findViewById(R.id.botonModificar);
        etPlannedDate = findViewById(R.id.fecha);
        etPlannedDate.setOnClickListener(this::onClick);

        Intent intent = getIntent();
        isEditMode = intent.getBooleanExtra("isEditMode", false);

        if (isEditMode) {
            EscuelaEntity escuelaEntity = (EscuelaEntity) intent.getSerializableExtra("escuelaEntity");
            String nombre = escuelaEntity.getNombre();
            Date fecha = escuelaEntity.getFechaCreacion();
            Long id = escuelaEntity.getIdMateria();
            Long idFacultad = escuelaEntity.getIdFacultad();
            this.nombre.setText(nombre);
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            this.etPlannedDate.setText(formatter.format(fecha));
            this.id.setText(id.toString());
            this.txtidFacultad.setText(idFacultad.toString());

            //activate view for btnEliminar and btnModificar
            this.btnEliminar.setVisibility(View.VISIBLE);
            this.btnModificar.setVisibility(View.VISIBLE);

            //no edit fields
            this.nombre.setEnabled(false);
            this.txtidFacultad.setEnabled(false);
            this.etPlannedDate.setEnabled(false);
            this.fab.setVisibility(View.GONE);

            actionBar.setTitle("Información del Escuela");
        } else {
            actionBar.setTitle("Crear Escuela");

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
        String idFacultad = this.txtidFacultad.getText().toString();
        String id = this.id.getText().toString();
        String fecha = this.etPlannedDate.getText().toString();

        if (nombre.isEmpty() || fecha.isEmpty() || idFacultad.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Por favor llene todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        Date date = format.parse(fecha);
        EscuelaEntity escuelaEntity = new EscuelaEntity(date, nombre, Long.parseLong(idFacultad));
        if (isEditMode) {
            EscuelaEntity escuelaEntity1 = new EscuelaEntity(date, nombre, Long.parseLong(idFacultad));
            escuelaEntity1.setIdMateria(Long.parseLong(this.id.getText().toString()));
            if (db.facultadDao().cantFacultad(Long.parseLong(idFacultad)) <= 0) {

                Toast.makeText(getApplicationContext(), "Id facultad no existe", Toast.LENGTH_SHORT).show();

            } else {
                db.escuelaDao().update(escuelaEntity1);
                Toast.makeText(getApplicationContext(), "Escuela actualizado", Toast.LENGTH_SHORT).show();

            }

            this.btnEliminar.setVisibility(View.VISIBLE);
            this.btnModificar.setVisibility(View.VISIBLE);
            //no edit fields
            this.nombre.setEnabled(false);
            this.txtidFacultad.setEnabled(false);
            this.etPlannedDate.setEnabled(false);
            this.fab.setVisibility(View.GONE);

            return;
        }
        if (db.facultadDao().cantFacultad(Long.parseLong(idFacultad)) <= 0) {

            Toast.makeText(getApplicationContext(), "Id facultad no existe", Toast.LENGTH_SHORT).show();
        } else {
            db.escuelaDao().insert(escuelaEntity);
            Toast.makeText(getApplicationContext(), "Escuela creado", Toast.LENGTH_SHORT).show();
        }
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
        this.etPlannedDate.setText("");
        this.txtidFacultad.setText("");
        this.id.setText("");
    }

    //eliminar Facultad
    public void eliminar(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Eliminar dato");
        builder.setMessage("¿Estás seguro de que quieres eliminar este dato?");

// Agregar botón de confirmación
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Acción de eliminación
                if (db.escuelaDao().existeLlaveForane(id.getText().toString()) <= 0) {
                    EscuelaEntity escuelaEntity = new EscuelaEntity();
                    escuelaEntity.setIdMateria(Long.parseLong(id.getText().toString()));
                    db.escuelaDao().delete(escuelaEntity);
                    Toast.makeText(getApplicationContext(), "Escuela eliminado", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } else {
                    Toast.makeText(getApplicationContext(), "La escuela no se puede eliminar, contiene llave foranea", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }

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

    //modificar autor
    public void modificar(View view) {
        this.nombre.setEnabled(true);
        this.etPlannedDate.setEnabled(true);
        this.txtidFacultad.setEnabled(true);
        this.fab.setVisibility(View.VISIBLE);
        this.btnEliminar.setVisibility(View.GONE);
        this.btnModificar.setVisibility(View.GONE);
        Toast.makeText(getApplicationContext(), "Ahora puede modificar los campos", Toast.LENGTH_SHORT).show();

    }
}