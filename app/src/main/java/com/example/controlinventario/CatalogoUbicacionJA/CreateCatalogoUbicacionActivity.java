package com.example.controlinventario.CatalogoUbicacionJA;

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
import com.example.controlinventario.EscuelaJA.EscuelaEntity;
import com.example.controlinventario.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateCatalogoUbicacionActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText etPlannedDate, nombre, idUbicacion,txtidEscuela;
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
        setContentView(R.layout.activity_create_catalogo_ubicacion_activity);
        actionBar = getSupportActionBar();
        fab = findViewById(R.id.fab);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "dbControlInventario").allowMainThreadQueries().build();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        this.nombre = findViewById(R.id.nombre);
        this.txtidEscuela = findViewById(R.id.txtidEscuela);
        this.idUbicacion = findViewById(R.id.idUbicacion);
        this.btnEliminar = findViewById(R.id.botonEliminar);
        this.btnModificar = findViewById(R.id.botonModificar);
        etPlannedDate = findViewById(R.id.fecha);
        etPlannedDate.setOnClickListener(this::onClick);

        Intent intent = getIntent();
        isEditMode = intent.getBooleanExtra("isEditMode", false);

        if (isEditMode) {
            UbicacionEntity ubicacionEntity = (UbicacionEntity) intent.getSerializableExtra("ubicacionEntity");
            String nombre = ubicacionEntity.getNombre();
            Date fecha = ubicacionEntity.getFechaCreacion();
           //Long id = ubicacionEntity.setIdUbicacion(Long.parseLong(this.id.getText().toString()));
            Long idEscuela = ubicacionEntity.getIdEscuela();
            this.nombre.setText(nombre);
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            this.etPlannedDate.setText(formatter.format(fecha));
            this.idUbicacion.setText(idUbicacion.toString());
            this.txtidEscuela.setText(idEscuela.toString());

            //activate view for btnEliminar and btnModificar
            this.btnEliminar.setVisibility(View.VISIBLE);
            this.btnModificar.setVisibility(View.VISIBLE);

            //no edit fields
            this.nombre.setEnabled(false);
            this.txtidEscuela.setEnabled(false);
            this.etPlannedDate.setEnabled(false);
            this.fab.setVisibility(View.GONE);

            actionBar.setTitle("Información del Ubicacion");
        } else {
            actionBar.setTitle("Crear Ubicacion");

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
        String idEscuela = this.txtidEscuela.getText().toString();
        String id = this.idUbicacion.getText().toString();
        String fecha = this.etPlannedDate.getText().toString();

        if (nombre.isEmpty() || fecha.isEmpty() || idEscuela.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Por favor llene todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        Date date = format.parse(fecha);
        UbicacionEntity ubicacion  = new UbicacionEntity(date, nombre, Long.parseLong(idEscuela));
        Intent intent = getIntent();
        isEditMode = intent.getBooleanExtra("isEditMode", false);

        if (isEditMode) {
            UbicacionEntity ubi = (UbicacionEntity) intent.getSerializableExtra("ubicacionEntity");
            ubicacion.setIdUbicacion(ubi.getIdUbicacion());
            //ubicacion.setIdUbicacion(Long.parseLong(this.idUbicacion.getText().toString()));
            db.ubicacionDao().update(ubicacion);
            Toast.makeText(getApplicationContext(), "Ubicacion actualizado", Toast.LENGTH_SHORT).show();

            this.btnEliminar.setVisibility(View.VISIBLE);
            this.btnModificar.setVisibility(View.VISIBLE);
            //no edit fields
            this.nombre.setEnabled(false);
            this.txtidEscuela.setEnabled(false);
            this.etPlannedDate.setEnabled(false);
            this.fab.setVisibility(View.GONE);

            return;
        }
        db.ubicacionDao().insert(ubicacion);
        Toast.makeText(getApplicationContext(), "Ubicacion creado", Toast.LENGTH_SHORT).show();
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
        this.txtidEscuela.setText("");
        this.idUbicacion.setText("");
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
                UbicacionEntity ubicacionEntity = new UbicacionEntity();
                Intent intent = getIntent();
                isEditMode = intent.getBooleanExtra("isEditMode", false);
                UbicacionEntity ubi = (UbicacionEntity) intent.getSerializableExtra("ubicacionEntity");

                ubicacionEntity.setIdUbicacion(ubi.getIdUbicacion());
                db.ubicacionDao().delete(ubicacionEntity);
                Toast.makeText(getApplicationContext(), "Ubicacion eliminado", Toast.LENGTH_SHORT).show();
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
        this.etPlannedDate.setEnabled(true);
        this.txtidEscuela.setEnabled(true);
        this.fab.setVisibility(View.VISIBLE);
        this.btnEliminar.setVisibility(View.GONE);
        this.btnModificar.setVisibility(View.GONE);
        Toast.makeText(getApplicationContext(), "Ahora puede modificar los campos", Toast.LENGTH_SHORT).show();

    }
}