package com.example.controlinventario.CatalogoJA;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.controlinventario.R;

public class MenuCatalogoActivity extends ListActivity {
    String[] menu={"Ingresar Catalogo", "Eliminar Catalogo", "Consultar Catalogo", "Actualizar Catalogo"};
    String[] activities = {"CatalogoJA.CreateCatalogoActivity","CatalogoJA.DeleteCatalogoActivity","CatalogoJA.FindByIdCatalogoActivity","CatalogoJA.UpdateCatalogoActivity"};
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