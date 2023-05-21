package com.example.controlinventario.EscuelaJA;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.controlinventario.R;

public class MenuEscuelaActivity extends ListActivity {
    String[] menu={"Ingresar Escuela", "Eliminar Escuela", "Consultar Escuela", "Actualizar Escuela"};
    String[] activities = {"EscuelaJA.CreateEscuelaActivity","EscuelaJA.DeleteEscuelaActivity","EscuelaJA.FindByEscuelaActivity","EscuelaJA.UpdateCatalogoActivity"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,menu));
        ListView listView = getListView();
        listView.setBackgroundColor(Color.rgb(0,128,64));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,menu);
        setListAdapter(adapter);
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l,v,position,id);
        String nombreValue = activities[position];
        l.getChildAt(position).setBackgroundColor(Color.rgb(0,128,64));
        try{
            Class<?> clase = Class.forName("com.example.controlinventario."+nombreValue);
            Intent inte = new Intent(this,clase);
            this.startActivity(inte);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}