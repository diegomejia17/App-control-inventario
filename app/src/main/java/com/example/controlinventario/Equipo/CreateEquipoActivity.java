package com.example.controlinventario.Equipo;

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
import com.example.controlinventario.Marca.MarcaEntity;
import com.example.controlinventario.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateEquipoActivity  extends AppCompatActivity implements View.OnClickListener {

    private EditText etPlannedDate, idMarca, descripcion, id, numeroSerie, ubicacion;
    private FloatingActionButton fab;
    private Boolean isEditMode;
    private ActionBar actionBar;
    private Button btnModificar;
    private Button btnEliminar;

    AppDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_equipo);

        actionBar = getSupportActionBar();
        fab = findViewById(R.id.fab);



        db = Room.databaseBuilder(getApplicationContext(),
            AppDatabase.class, "dbControlInventario").allowMainThreadQueries().build();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        this.idMarca = findViewById(R.id.idMarca);
        this.descripcion = findViewById(R.id.descripcion);
        this.numeroSerie = findViewById(R.id.numeroSerie);
        this.ubicacion = findViewById(R.id.ubicacion);

        this.id = findViewById(R.id.id);
        this.btnEliminar = findViewById(R.id.botonEliminar);
        this.btnModificar = findViewById(R.id.botonModificar);
        etPlannedDate = findViewById(R.id.fecha);
        etPlannedDate.setOnClickListener(this::onClick);

        Intent intent = getIntent();
        isEditMode = intent.getBooleanExtra("isEditMode", false);

        if (isEditMode) {
            EquipoEntity equipoEntity = (EquipoEntity) intent.getSerializableExtra("equipoEntity");
            String idMarca = equipoEntity.getIdMarca().toString();
            String descripcion = equipoEntity.getDescripcion();
            String numeroSerie = equipoEntity.getNumeroSerie();
            String ubicacion = equipoEntity.getUbicacion();


            Date fecha = equipoEntity.getFechaCreacion();
            Long id = equipoEntity.getIdEquipo();

            this.idMarca.setText(idMarca);
            this.descripcion.setText(descripcion);
            this.numeroSerie.setText(numeroSerie);
            this.ubicacion.setText(ubicacion);

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            this.etPlannedDate.setText(formatter.format(fecha));
            this.id.setText(id.toString());

            //activate view for btnEliminar and btnModificar
            this.btnEliminar.setVisibility(View.VISIBLE);
            this.btnModificar.setVisibility(View.VISIBLE);

            //no edit fields
            this.idMarca.setEnabled(false);
            this.descripcion.setEnabled(false);
            this.numeroSerie.setEnabled(false);
            this.ubicacion.setEnabled(false);
            this.etPlannedDate.setEnabled(false);
            this.fab.setVisibility(View.GONE);

            actionBar.setTitle("Información del Equipo");
        } else {
            actionBar.setTitle("Crear Equipo");

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
        String idMarca = this.idMarca.getText().toString();
        String descripcion = this.descripcion.getText().toString();
        String numeroSerie = this.numeroSerie.getText().toString();
        String ubicacion = this.ubicacion.getText().toString();
        String fecha = this.etPlannedDate.getText().toString();

        if (idMarca.isEmpty() || descripcion.isEmpty() || numeroSerie.isEmpty()  || ubicacion.isEmpty()  || fecha.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Por favor llene todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        Date date = format.parse(fecha);

        EquipoEntity equipo = new EquipoEntity(
            Long.parseLong(idMarca),
            descripcion,
            numeroSerie,
            ubicacion,
            date
        );

        if (isEditMode) {
            equipo.setIdEquipo(Long.parseLong(this.id.getText().toString()));
            db.equipoDao().update(equipo);
            Toast.makeText(getApplicationContext(), "Equipo actualizado", Toast.LENGTH_SHORT).show();

            this.btnEliminar.setVisibility(View.VISIBLE);
            this.btnModificar.setVisibility(View.VISIBLE);
            //no edit fields
            this.idMarca.setEnabled(false);
            this.descripcion.setEnabled(false);
            this.numeroSerie.setEnabled(false);
            this.ubicacion.setEnabled(false);
            this.etPlannedDate.setEnabled(false);
            this.fab.setVisibility(View.GONE);

            return;
        }
        db.equipoDao().insert(equipo);
        limpiar();
        Toast.makeText(getApplicationContext(), "Equipo creado", Toast.LENGTH_SHORT).show();
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
        this.idMarca.setText("");
        this.descripcion.setText("");
        this.numeroSerie.setText("");
        this.ubicacion.setText("");
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
                EquipoEntity equipoEntity = new EquipoEntity();
                equipoEntity.setIdEquipo(Long.parseLong(id.getText().toString()));
                db.equipoDao().delete(equipoEntity);
                Toast.makeText(getApplicationContext(), "Equipo eliminada", Toast.LENGTH_SHORT).show();
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
        this.idMarca.setEnabled(true);
        this.descripcion.setEnabled(true);
        this.numeroSerie.setEnabled(true);
        this.ubicacion.setEnabled(true);
        this.etPlannedDate.setEnabled(true);
        this.fab.setVisibility(View.VISIBLE);
        this.btnEliminar.setVisibility(View.GONE);
        this.btnModificar.setVisibility(View.GONE);
        Toast.makeText(getApplicationContext(), "Ahora puede modificar los campos", Toast.LENGTH_SHORT).show();

    }
}
