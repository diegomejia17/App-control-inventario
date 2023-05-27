package com.example.controlinventario.Libro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.SearchManager;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.controlinventario.AppDatabase;
import com.example.controlinventario.Autor.AutorEntity;
import com.example.controlinventario.CategoriaLibro.CategoriaLibroEntity;
import com.example.controlinventario.Editorial.EditorialEntity;
import com.example.controlinventario.Idioma.IdiomaEntity;
import com.example.controlinventario.Materia.MateriaEntity;
import com.example.controlinventario.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

public class LibroActivity extends AppCompatActivity {

    private SearchView searchAutor, searchEditorial, searchIdioma, searchMateria, searchCategoriaLibro;
    private EditText id,tomo,isbn,fechaPublicacion,descripcion,fechaCreacion,titulo ;
    public ChipGroup chipGroup;
    public List<String> suggestionAutorList, suggestionEditorialList, suggestionIdiomaList, suggestionMateriaList, suggestionCategoriaLibroList;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libro);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "dbControlInventario").allowMainThreadQueries().build();

        // inicializacion de variables EditText
        id = findViewById(R.id.idLibro);
        tomo = findViewById(R.id.tomoLibro);
        isbn = findViewById(R.id.isbnLibro);
        fechaPublicacion = findViewById(R.id.fechaPublicacionLibro);
        descripcion = findViewById(R.id.descripcionLibro);
        fechaCreacion = findViewById(R.id.fechaLibro);
        titulo = findViewById(R.id.tituloLibro);


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
                        if (suggestion.contains(newText)) {
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
                        if (suggestion.contains(newText)) {
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
                        if (suggestion.contains(newText)) {
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
                        if (suggestion.contains(newText)) {
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
                        if (suggestion.contains(newText)) {
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
}