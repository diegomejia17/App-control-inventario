package com.example.controlinventario.CatalogoUbicacionJA;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

    public class MenuCatalogoUbicacionActivity extends ListActivity {
    String[] menu={"Ingresar Catalogo", "Modificar/buscar"};
    String[] activities = {"CatalogoUbicacionJA.CreateCatalogoUbicacionActivity","CatalogoUbicacionJA.FindByIdCatalogoUbicacionActivity"};
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