package com.example.authapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class BucketList extends AppCompatActivity
{
    //  declaring variables
    private ListView noteListView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // initialising user interface, populating with current bucket list items from database
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bucket_list);
        initWidgets();
        loadFromDBToMemory(); // uses SQLiteManager.class
        setNoteAdapter();
        setOnClickListener();
    }
    private void initWidgets()
    {
        // assigning respective view to variable
        noteListView = findViewById(R.id.noteListView);
    }

    private void loadFromDBToMemory()
    {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.populateNoteListArray();
    }

    private void setNoteAdapter()
    {
        NoteAdapter noteAdapter = new NoteAdapter(getApplicationContext(), Note.nonDeletedNotes());
        noteListView.setAdapter(noteAdapter);
    }

    // responding to click event
    private void setOnClickListener()
    {
        noteListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                Note selectedNote = (Note) noteListView.getItemAtPosition(position);
                // takes user to page where the chosen item's title and description can be edited
                Intent editNoteIntent = new Intent(getApplicationContext(), NoteDetailActivity.class);
                editNoteIntent.putExtra(Note.NOTE_EDIT_EXTRA, selectedNote.getId());
                startActivity(editNoteIntent);
            }
        });
    }


    public void newNote(View view)
    {
        // takes user to page where new item title and description can be written
        Intent newNoteIntent = new Intent(this, NoteDetailActivity.class);
        startActivity(newNoteIntent);
    }



    @Override
    protected void onResume()
    {
        super.onResume();
        setNoteAdapter();
    }
}