package com.example.controlinventario.Commons;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.List;

public class MySuggestionsAdapter extends CursorAdapter {
    private List<String> suggestionsList;

    public MySuggestionsAdapter(Context context, List<String> suggestionsList) {
        super(context, null, 0);
        this.suggestionsList = suggestionsList;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textView = view.findViewById(android.R.id.text1);
        int position = cursor.getPosition();
        textView.setText(suggestionsList.get(position));
    }

    @Override
    public Cursor runQueryOnBackgroundThread(CharSequence constraint) {
        return null; // We don't need to perform any query
    }

    @Override
    public String convertToString(Cursor cursor) {
        // Convert the suggestion item into a string for the search query
        int position = cursor.getPosition();
        return suggestionsList.get(position);
    }
}
