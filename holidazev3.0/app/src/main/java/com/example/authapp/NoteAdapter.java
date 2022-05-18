package com.example.authapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class NoteAdapter extends ArrayAdapter<Note>
{
    public NoteAdapter(Context context, List<Note> notes) // constructor
    {
        super(context, 0, notes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        Note note = getItem(position);
        if(convertView == null)
            // if view empty, instantiates the xml layout file into the corresponding view object
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.note_cell, parent, false);

        // assigning respective views to variables
        TextView title = convertView.findViewById(R.id.cellTitle);
        TextView desc = convertView.findViewById(R.id.cellDesc);

        // setting textview content
        title.setText(note.getTitle());
        desc.setText(note.getDescription());

        // returns populated view
        return convertView;
    }
}