package com.example.controlinventario.Marca;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.controlinventario.AppDatabase;
import com.example.controlinventario.Commons.DatePickerFragment;
import com.example.controlinventario.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateMarcaActivity  extends AppCompatActivity implements View.OnClickListener {

    private EditText etPlannedDate, nombre, descripcion, id;
    private FloatingActionButton fab;
    private Boolean isEditMode;
    private ActionBar actionBar;
    private Button btnModificar;
    private Button btnEliminar;

    AppDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_marca);

        actionBar = getSupportActionBar();
        fab = findViewById(R.id.fab);



        db = Room.databaseBuilder(getApplicationContext(),
            AppDatabase.class, "dbControlInventario").allowMainThreadQueries().build();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        this.nombre = findViewById(R.id.nombre);
        this.descripcion = findViewById(R.id.descripcion);
        this.id = findViewById(R.id.id);
        this.btnEliminar = findViewById(R.id.botonEliminar);
        this.btnModificar = findViewById(R.id.botonModificar);
        etPlannedDate = findViewById(R.id.fecha);
        etPlannedDate.setOnClickListener(this::onClick);

        Intent intent = getIntent();
        isEditMode = intent.getBooleanExtra("isEditMode", false);

        if (isEditMode) {
            MarcaEntity marcaEntity = (MarcaEntity) intent.getSerializableExtra("marcaEntity");
            String nombre = marcaEntity.getNombre();
            String descripcion = marcaEntity.getDescripcion();
            Date fecha = marcaEntity.getFechaCreacion();
            Long id = marcaEntity.getIdMarca();

            this.nombre.setText(nombre);
            this.descripcion.setText(descripcion);
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            this.etPlannedDate.setText(formatter.format(fecha));
            this.id.setText(id.toString());

            //activate view for btnEliminar and btnModificar
            this.btnEliminar.setVisibility(View.VISIBLE);
            this.btnModificar.setVisibility(View.VISIBLE);

            //no edit fields
            this.nombre.setEnabled(false);
            this.descripcion.setEnabled(false);
            this.etPlannedDate.setEnabled(false);
            this.fab.setVisibility(View.GONE);

            actionBar.setTitle("Información de la Marca");
        } else {
            actionBar.setTitle("Crear Marca");

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
        String descripcion = this.descripcion.getText().toString();
        String fecha = this.etPlannedDate.getText().toString();

        if (nombre.isEmpty() || descripcion.isEmpty() || fecha.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Por favor llene todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        Date date = format.parse(fecha);
        MarcaEntity marca = new MarcaEntity(
            nombre,
            date,
            descripcion
        );
        if (isEditMode) {
            marca.setIdMarca(Long.parseLong(this.id.getText().toString()));
            db.marcaDao().update(marca);
            Toast.makeText(getApplicationContext(), "Marca actualizada", Toast.LENGTH_SHORT).show();

            this.btnEliminar.setVisibility(View.VISIBLE);
            this.btnModificar.setVisibility(View.VISIBLE);
            //no edit fields
            this.nombre.setEnabled(false);
            this.descripcion.setEnabled(false);
            this.etPlannedDate.setEnabled(false);
            this.fab.setVisibility(View.GONE);

            return;
        }
        db.marcaDao().insert(marca);
        limpiar();
        Toast.makeText(getApplicationContext(), "Marca creada", Toast.LENGTH_SHORT).show();
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
        this.descripcion.setText("");
        this.etPlannedDate.setText("");
        this.id.setText("");
    }

    //eliminar autor
    public void eliminar(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Eliminar dato");
        builder.setMessage("¿Estás seguro de que quieres eliminar este dato?");

// Agregar botón de confirmación
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Acción de eliminación
                MarcaEntity marcaEntity = new MarcaEntity();
                marcaEntity.setIdMarca(Long.parseLong(id.getText().toString()));
                db.marcaDao().delete(marcaEntity);
                Toast.makeText(getApplicationContext(), "Marca eliminada", Toast.LENGTH_SHORT).show();
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

    //modificar autor
    public void modificar(View view) {
        this.nombre.setEnabled(true);
        this.descripcion.setEnabled(true);
        this.etPlannedDate.setEnabled(true);
        this.fab.setVisibility(View.VISIBLE);
        this.btnEliminar.setVisibility(View.GONE);
        this.btnModificar.setVisibility(View.GONE);
        Toast.makeText(getApplicationContext(), "Ahora puede modificar los campos", Toast.LENGTH_SHORT).show();

    }
}
