package com.example.controlinventario.Libro;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.text.Selection;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.Switch;
import android.widget.Toast;

import com.example.controlinventario.AppDatabase;
import com.example.controlinventario.Autor.AutorEntity;
import com.example.controlinventario.CategoriaLibro.CategoriaLibroEntity;
import com.example.controlinventario.Commons.DatePickerFragment;
import com.example.controlinventario.Editorial.EditorialEntity;
import com.example.controlinventario.Idioma.IdiomaEntity;
import com.example.controlinventario.Materia.MateriaEntity;
import com.example.controlinventario.R;
import com.example.controlinventario.manytomanytables.AutorLibroEntity;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LibroActivity extends AppCompatActivity {
    private Boolean isEditMode;

    private SearchView searchAutor, searchEditorial, searchIdioma, searchMateria, searchCategoriaLibro;
    private EditText id, tomo, isbn, fechaPublicacion, descripcion, fechaCreacion, titulo;
    public ChipGroup chipGroup;
    public List<String> suggestionAutorList, suggestionEditorialList, suggestionIdiomaList, suggestionMateriaList, suggestionCategoriaLibroList;
    private ActionBar actionBar;

    private FloatingActionButton fab;
    private Button btnModificar, btnEliminar;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libro);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "dbControlInventario").allowMainThreadQueries().build();

        this.btnEliminar = findViewById(R.id.botonEliminarLibro);
        this.btnModificar = findViewById(R.id.botonModificarLibro);
        actionBar = getSupportActionBar();
        fab = findViewById(R.id.fabLibro);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        // inicializacion de variables EditText
        this.id = findViewById(R.id.idLibro);
        this.tomo = findViewById(R.id.tomoLibro);
        this.isbn = findViewById(R.id.isbnLibro);
        this.fechaPublicacion = findViewById(R.id.fechaPublicacionLibro);
        this.descripcion = findViewById(R.id.descripcionLibro);
        this.fechaCreacion = findViewById(R.id.fechaLibro);
        this.titulo = findViewById(R.id.tituloLibro);

        this.fechaCreacion.setOnClickListener(this::onClick);
        this.fechaPublicacion.setOnClickListener(this::onClick);

        Intent intent = getIntent();
        isEditMode = intent.getBooleanExtra("isEditMode", false);
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");


        //inicializacion de variables de busqueda
        searchAutor = findViewById(R.id.searchAutor);
        searchEditorial = findViewById(R.id.findEditorial);
        searchIdioma = findViewById(R.id.findIdioma);
        searchMateria = findViewById(R.id.findMateria);
        searchCategoriaLibro = findViewById(R.id.findCategoria);

        //llenado de datos
        suggestionAutorList = listaAutores();
        suggestionEditorialList = listaEditorial();
        suggestionIdiomaList = listaIdiomas();
        suggestionMateriaList = listaMateria();
        suggestionCategoriaLibroList = listaCategoria();

        chipGroup = findViewById(R.id.chipGroup);

        //Asignacion de datos a los searchView
        String from[] = {SearchManager.SUGGEST_COLUMN_TEXT_1};
        int toAutor[] = {R.id.searchAutorID};
        int toEditorial[] = {R.id.searchEditorialID};
        int toIdiomas[] = {R.id.searchIdiomaID};
        int toMaterias[] = {R.id.searchMateriaID};
        int toCategorias[] = {R.id.searchCategoriaID};


        if (isEditMode) {

            LibroEntity libro = (LibroEntity) intent.getSerializableExtra("libro");
            this.id.setText(libro.getIdLibro().toString());
            this.tomo.setText(libro.getTomoLibro().toString());
            this.isbn.setText(libro.getIsbnLibro().toString());
            this.fechaPublicacion.setText(formatter.format(libro.getFechaPublicacionLibro()));
            this.descripcion.setText(libro.getDescripcionLibro());
            this.fechaCreacion.setText(formatter.format(libro.getFechaCreacionLibro()));
            this.titulo.setText(libro.getTituloLibro());

            this.searchIdioma.setQuery(buscarPorId(libro.getIdIdioma(), this.suggestionIdiomaList), false);
            this.searchCategoriaLibro.setQuery(buscarPorId(libro.getIdCategoriaLibro(), this.suggestionCategoriaLibroList), false);
            this.searchMateria.setQuery(buscarPorId(libro.getIdMateria(), this.suggestionMateriaList), false);
            this.searchEditorial.setQuery(buscarPorId(libro.getIdEditorial(), this.suggestionEditorialList), false);

            db.autorDao().getAutoresPorIdLibro(libro.getIdLibro()).forEach(
                    autor -> {
                        Chip chip = new Chip(this);
                        chip.setText(buscarPorId(autor.getIdAutor(), this.suggestionAutorList));
                        chip.setCloseIconVisible(true);
                        chip.setOnCloseIconClickListener(v -> {
                            // Remove the chip from chip group
                            chipGroup.removeView(chip);
                        });
                        chipGroup.addView(chip);
                    }
            );
            bloquear();


            actionBar.setTitle("Información del libro");
        } else {
            actionBar.setTitle("Crear Materia");
        }


        SimpleCursorAdapter cursorAutorAdapter = new SimpleCursorAdapter(LibroActivity.this, R.layout.suggestion_autor, null, from, toAutor, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        SimpleCursorAdapter cursorEditorialAdapter = new SimpleCursorAdapter(LibroActivity.this, R.layout.suggestion_editorial, null, from, toEditorial, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        SimpleCursorAdapter cursorIdiomaAdapter = new SimpleCursorAdapter(LibroActivity.this, R.layout.suggestion_idioma, null, from, toIdiomas, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        SimpleCursorAdapter cursorMateriaAdapter = new SimpleCursorAdapter(LibroActivity.this, R.layout.suggestion_materia, null, from, toMaterias, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        SimpleCursorAdapter cursorCategoriaAdapter = new SimpleCursorAdapter(LibroActivity.this, R.layout.suggestion_categoria, null, from, toCategorias, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        searchAutor.setSuggestionsAdapter(cursorAutorAdapter);
        searchEditorial.setSuggestionsAdapter(cursorEditorialAdapter);
        searchIdioma.setSuggestionsAdapter(cursorIdiomaAdapter);
        searchMateria.setSuggestionsAdapter(cursorMateriaAdapter);
        searchCategoriaLibro.setSuggestionsAdapter(cursorCategoriaAdapter);

        //Autor
        searchAutor.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                MatrixCursor cursor = new MatrixCursor(new String[]{BaseColumns._ID, SearchManager.SUGGEST_COLUMN_TEXT_1});
                if (newText != null) {

                    for (String suggestion : suggestionAutorList) {
                        if (suggestion.toLowerCase().contains(newText.toLowerCase())) {
                            int index = suggestionAutorList.indexOf(suggestion);
                            cursor.addRow(new Object[]{index, suggestion});
                        }
                    }
                }
                cursorAutorAdapter.changeCursor(cursor);
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
        });
        searchAutor.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionClick(int position) {
                Cursor cursor = (Cursor) searchAutor.getSuggestionsAdapter().getItem(position);
                int pos = cursor.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1);
                String selection = cursor.getString(pos);
                Chip chip = new Chip(LibroActivity.this);
                chip.setText(selection);
                chip.setCloseIconVisible(true);
                chip.setOnCloseIconClickListener(v -> {
                    chipGroup.removeView(chip);
                });
                suggestionAutorList.remove(selection);
                chipGroup.addView(chip);
                return true;
            }

            @Override
            public boolean onSuggestionSelect(int position) {
                return false;
            }
        });


        //Editorial
        searchEditorial.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                MatrixCursor cursor = new MatrixCursor(new String[]{BaseColumns._ID, SearchManager.SUGGEST_COLUMN_TEXT_1});
                if (newText != null) {

                    for (String suggestion : suggestionEditorialList) {
                        if (suggestion.toLowerCase().contains(newText.toLowerCase())) {
                            cursor.addRow(new Object[]{suggestionEditorialList.indexOf(suggestion), suggestion});
                        }
                    }
                }

                cursorEditorialAdapter.changeCursor(cursor);
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
        });
        searchEditorial.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionClick(int position) {
                Cursor cursor = (Cursor) searchEditorial.getSuggestionsAdapter().getItem(position);
                int pos = cursor.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1);
                String selection = cursor.getString(pos);
                searchEditorial.setQuery(selection, false);
                return true;
            }

            @Override
            public boolean onSuggestionSelect(int position) {
                return false;
            }
        });


        //idioma
        searchIdioma.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                MatrixCursor cursor = new MatrixCursor(new String[]{BaseColumns._ID, SearchManager.SUGGEST_COLUMN_TEXT_1});
                if (newText != null) {

                    for (String suggestion : suggestionIdiomaList) {
                        if (suggestion.toLowerCase().contains(newText.toLowerCase())) {
                            cursor.addRow(new Object[]{suggestionIdiomaList.indexOf(suggestion), suggestion});
                        }
                    }
                }
                cursorIdiomaAdapter.changeCursor(cursor);
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
        });
        searchIdioma.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionClick(int position) {
                Cursor cursor = (Cursor) searchIdioma.getSuggestionsAdapter().getItem(position);
                int pos = cursor.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1);
                String selection = cursor.getString(pos);
                searchIdioma.setQuery(selection, false);
                return true;
            }

            @Override
            public boolean onSuggestionSelect(int position) {
                return false;
            }
        });

        //materia


        searchMateria.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                MatrixCursor cursor = new MatrixCursor(new String[]{BaseColumns._ID, SearchManager.SUGGEST_COLUMN_TEXT_1});
                if (newText != null) {

                    for (String suggestion : suggestionMateriaList) {
                        if (suggestion.toLowerCase().contains(newText.toLowerCase())) {
                            cursor.addRow(new Object[]{suggestionMateriaList.indexOf(suggestion), suggestion});
                        }
                    }
                }
                cursorMateriaAdapter.changeCursor(cursor);
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
        });
        searchMateria.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionClick(int position) {
                Cursor cursor = (Cursor) searchMateria.getSuggestionsAdapter().getItem(position);
                int pos = cursor.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1);
                String selection = cursor.getString(pos);
                searchMateria.setQuery(selection, false);
                return true;
            }

            @Override
            public boolean onSuggestionSelect(int position) {
                return false;
            }
        });


        //Categoria
        searchCategoriaLibro.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                MatrixCursor cursor = new MatrixCursor(new String[]{BaseColumns._ID, SearchManager.SUGGEST_COLUMN_TEXT_1});
                if (newText != null) {

                    for (String suggestion : suggestionCategoriaLibroList) {
                        if (suggestion.toLowerCase().contains(newText.toLowerCase())) {
                            cursor.addRow(new Object[]{suggestionCategoriaLibroList.indexOf(suggestion), suggestion});
                        }
                    }
                }
                cursorCategoriaAdapter.changeCursor(cursor);
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
        });

        searchCategoriaLibro.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionClick(int position) {
                Cursor cursor = (Cursor) searchCategoriaLibro.getSuggestionsAdapter().getItem(position);
                int pos = cursor.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1);
                String selection = cursor.getString(pos);
                searchCategoriaLibro.setQuery(selection, false);
                return true;
            }

            @Override
            public boolean onSuggestionSelect(int position) {
                return false;
            }
        });

        fab.setOnClickListener(view -> {
            try {
                saveData();
            } catch (Exception e) {
                notificacion("Error al guardar datos, asegurese que todo este correcto");
                throw new RuntimeException(e);
            }
        });

    }

    private void saveData() throws Exception {
        String titulo = this.titulo.getText().toString();
        String isbn = this.isbn.getText().toString();
        String tomo = this.tomo.getText().toString();
        String fechaPublicacion = this.fechaPublicacion.getText().toString();
        String descripcion = this.descripcion.getText().toString();
        String fechaCreacion = this.fechaCreacion.getText().toString();

        String editorial = this.searchEditorial.getQuery().toString();
        String idioma = this.searchIdioma.getQuery().toString();
        String materia = this.searchMateria.getQuery().toString();
        String categoria = this.searchCategoriaLibro.getQuery().toString();

        List<String> autores = new ArrayList<>();
        for (int i = 0; i < this.chipGroup.getChildCount(); i++) {
            Chip chip = (Chip) this.chipGroup.getChildAt(i);
            autores.add(chip.getText().toString());
        }

        if (titulo.isEmpty() || isbn.isEmpty() || tomo.isEmpty() || fechaPublicacion.isEmpty() || descripcion.isEmpty() || fechaCreacion.isEmpty() || editorial.isEmpty() || idioma.isEmpty() || materia.isEmpty() || categoria.isEmpty() || autores.isEmpty()) {
            notificacion("Debe llenar todos los campos");
            return;
        }

        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        Long idLibro = (this.db.libroDao().getLastId());
        LibroEntity libro = new LibroEntity();
        libro.setIdLibro((idLibro == null) ? 1 : idLibro + 1);
        if (isEditMode) libro.setIdLibro(Long.parseLong(this.id.getText().toString()));
        libro.setTituloLibro(titulo);
        libro.setIsbnLibro(Long.parseLong(isbn));
        libro.setTomoLibro(Long.parseLong(tomo));
        libro.setFechaPublicacionLibro(format.parse(fechaPublicacion));
        libro.setDescripcionLibro(descripcion);
        libro.setFechaCreacionLibro(format.parse(fechaCreacion));
        ArrayList<Long> ids = new ArrayList<>();
        try {
            ids.add(this.db.categoriaLibroDao().findByIdCategoriaLibro(recuperarId(categoria)).getId());
            ids.add(this.db.editorialDao().findByIdEditorial(recuperarId(editorial)).getId());
            ids.add(this.db.idiomaDao().findByIdIdioma(recuperarId(idioma)).getId());
            ids.add(this.db.materiaDao().findByIdMateria(recuperarId(materia)).getIdMateria());

        } catch (Exception e) {
            notificacion("Debe completar los campos correctamente");
            e.printStackTrace();
            return;
        }

        if (ids.get(0) == null || ids.get(1) == null || ids.get(2) == null || ids.get(3) == null) {
            notificacion("Debe seleccionar una de las sugerencias");
            return;
        }
        libro.setIdCategoriaLibro(ids.get(0));
        libro.setIdEditorial(ids.get(1));
        libro.setIdIdioma(ids.get(2));
        libro.setIdMateria(ids.get(3));

        ArrayList<AutorLibroEntity> autorLibroEntities = new ArrayList<>();
        for (String autor : autores) {
            String[] split = autor.split("-");
            Long idAutor = Long.parseLong(split[0]);
            autorLibroEntities.add(new AutorLibroEntity(idAutor, libro.getIdLibro()));
        }

        this.suggestionAutorList = listaAutores();
        if (isEditMode) {
            this.db.autorLibroDao().deleteLibroConAutores(libro.getIdLibro());
            this.db.libroDao().update(libro);
            autorLibroEntities.forEach(al -> this.db.autorLibroDao().insert(al));
            notificacion("Libro actualizado con exito");

            bloquear();
            return;
        }

        this.db.libroDao().insert(libro);
        limpiar();
        autorLibroEntities.forEach(al -> this.db.autorLibroDao().insert(al));
        notificacion("Libro guardado con exito");
    }

    private List<String> listaAutores() {
        List<AutorEntity> autores = db.autorDao().findAll();
        List<String> listaAutores = new ArrayList<>();

        if (autores.isEmpty()) {
            listaAutores.add("No hay autores registrados");
            return listaAutores;
        }
        // concat id autor + nombre autor + apellido autor
        for (AutorEntity autor : autores) {
            listaAutores.add(autor.getIdAutor() + "- " + autor.getNombre() + " " + autor.getApellido());
        }
        return listaAutores;
    }

    private List<String> listaIdiomas() {
        List<IdiomaEntity> idiomas = db.idiomaDao().findAll();
        List<String> listIdiomas = new ArrayList<>();

        if (idiomas.isEmpty()) {
            listIdiomas.add("No hay idiomas registrados");
            return listIdiomas;
        }
        // concat id autor + nombre autor + apellido autor
        for (IdiomaEntity idioma : idiomas) {
            listIdiomas.add(idioma.getId() + "- " + idioma.getNombre());
        }
        return listIdiomas;
    }

    private List<String> listaCategoria() {
        List<CategoriaLibroEntity> categorias = db.categoriaLibroDao().findAllCategoriaLibro();
        List<String> list = new ArrayList<>();

        if (categorias.isEmpty()) {
            list.add("No hay Categorias registrados");
            notificacion("No hay Categorias registrados");
            return list;
        }
        // concat id autor + nombre autor + apellido autor
        for (CategoriaLibroEntity categoriaLibro : categorias) {
            list.add(categoriaLibro.getId() + "- " + categoriaLibro.getNombre());
        }
        return list;
    }

    private List<String> listaMateria() {
        List<MateriaEntity> materias = db.materiaDao().findAll();

        List<String> list = new ArrayList<>();

        if (materias.isEmpty()) {
            String texto = "No hay materias registradas";
            list.add(texto);
            notificacion(texto);
            return list;
        }

        for (MateriaEntity materia : materias) {
            list.add(materia.getIdMateria() + "- " + materia.getNombre());
        }
        return list;
    }

    private List<String> listaEditorial() {
        List<EditorialEntity> editoriales = db.editorialDao().findAll();

        List<String> list = new ArrayList<>();

        if (editoriales.isEmpty()) {
            String texto = "No hay editoriales registradas";
            list.add(texto);
            notificacion(texto);
            return list;
        }

        for (EditorialEntity materia : editoriales) {
            list.add(materia.getId() + "- " + materia.getNombre());
        }
        return list;
    }


    void notificacion(String texto) {
        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
    }

    private String twoDigits(int n) {
        return (n <= 9) ? ("0" + n) : String.valueOf(n);
    }

    private void showDatePickerDialog(View v) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance((datePicker, year, month, day) -> {
            // +1 because January is zero
            final String selectedDate = twoDigits(day) + "/" + (twoDigits(month + 1)) + "/" + year;
            switch (v.getId()) {
                case R.id.fechaPublicacionLibro:
                    fechaPublicacion.setText(selectedDate);
                    break;
                case R.id.fechaLibro:
                    fechaCreacion.setText(selectedDate);
                    break;
            }
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void onClick(View view) {
        showDatePickerDialog(view);
    }

    public String buscarPorId(Long id, List<String> e) {

        return e.stream().filter(cadena -> cadena.startsWith(id.toString() + "-")).findFirst().orElseGet(() -> "No encontrado");
    }

    public Long recuperarId(String cadena) throws NumberFormatException {
        return Long.parseLong(cadena.split("-")[0]);
    }

    private void limpiar() {
        this.titulo.setText("");
        this.isbn.setText("");
        this.tomo.setText("");
        this.fechaPublicacion.setText("");
        this.descripcion.setText("");
        this.fechaCreacion.setText("");
        this.searchEditorial.setQuery("", false);
        this.searchIdioma.setQuery("", false);
        this.searchMateria.setQuery("", false);
        this.searchCategoriaLibro.setQuery("", false);
        this.searchAutor.setQuery("", false);
        this.chipGroup.removeAllViews();
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }


    public void eliminar(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Eliminar dato");
        builder.setMessage("¿Estás seguro de que quieres eliminar este dato?");

// Agregar botón de confirmación
        builder.setPositiveButton("Sí", (dialog, which) -> {

            db.autorLibroDao().deleteLibroConAutores(Long.parseLong(id.getText().toString()));
            // Acción de eliminación
            LibroEntity libro = new LibroEntity();
            libro.setIdLibro(Long.parseLong(id.getText().toString()));
            db.libroDao().delete(libro);
            notificacion("Libro eliminado correctamente");
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
        this.titulo.setEnabled(true);
        this.isbn.setEnabled(true);
        this.tomo.setEnabled(true);
        this.fechaPublicacion.setEnabled(true);
        this.descripcion.setEnabled(true);
        this.fechaCreacion.setEnabled(true);
        this.searchEditorial.setEnabled(true);
        this.searchIdioma.setEnabled(true);
        this.searchMateria.setEnabled(true);
        this.searchCategoriaLibro.setEnabled(true);
        this.searchAutor.setEnabled(true);
        this.chipGroup.setEnabled(true);
        this.chipGroup.setClickable(true);

        this.btnModificar.setVisibility(View.GONE);
        this.btnEliminar.setVisibility(View.GONE);
        this.fab.setVisibility(View.VISIBLE);

        notificacion("Ahora puede editar");
    }

    void bloquear() {
        this.btnEliminar.setVisibility(View.VISIBLE);
        this.btnModificar.setVisibility(View.VISIBLE);
        this.fab.setVisibility(View.GONE);

        this.titulo.setEnabled(false);
        this.isbn.setEnabled(false);
        this.tomo.setEnabled(false);
        this.fechaPublicacion.setEnabled(false);
        this.fechaCreacion.setEnabled(false);
        this.descripcion.setEnabled(false);

        this.searchIdioma.setEnabled(false);
        this.searchIdioma.setClickable(false);
        this.searchCategoriaLibro.setEnabled(false);
        this.searchCategoriaLibro.setClickable(false);
        this.searchMateria.setEnabled(false);
        this.searchMateria.setClickable(false);
        this.searchEditorial.setEnabled(false);
        this.searchEditorial.setClickable(false);
        this.searchAutor.setEnabled(false);
        this.searchAutor.setClickable(false);
        this.chipGroup.setEnabled(false);
        this.chipGroup.setClickable(false);

    }
}
