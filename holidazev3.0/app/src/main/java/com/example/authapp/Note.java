package com.example.authapp;

import java.util.ArrayList;
import java.util.Date;

public class Note
{
    //  declaring variables
    public static ArrayList<Note> noteArrayList = new ArrayList<>();
    public static String NOTE_EDIT_EXTRA =  "noteEdit";

    private int id;
    private String title;
    private String description;
    private Date deleted;

    public Note(int id, String title, String description, Date deleted) // constructor for deleted items
    {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deleted = deleted;
    }

    public Note(int id, String title, String description) // constructor for created items
    {
        this.id = id;
        this.title = title;
        this.description = description;
        deleted = null;
    }

    public static Note getNoteForID(int passedNoteID) // finding an item by its ID, when clicked on to be edited
    {
        for (Note note : noteArrayList)
        {
            if(note.getId() == passedNoteID)
                return note;
        }

        return null;
    }

    public static ArrayList<Note> nonDeletedNotes() // providing non-deleted items to be displayed
    {
        ArrayList<Note> nonDeleted = new ArrayList<>();
        for(Note note : noteArrayList)
        {
            if(note.getDeleted() == null)
                nonDeleted.add(note);
        }
        return nonDeleted;
    }

    public int getId() // getter method of item ID
    {
        return id;
    }

    public void setId(int id) // setter method of item ID
    {
        this.id = id;
    }

    public String getTitle() // getter method of item title
    {
        return title;
    }

    public void setTitle(String title) // setter method of item title
    {
        this.title = title;
    }

    public String getDescription() // getter method of item description
    {
        return description;
    }

    public void setDescription(String description) // setter method of item description
    {
        this.description = description;
    }

    public Date getDeleted() // getter method of deleted items
    {
        return deleted;
    }

    public void setDeleted(Date deleted) // setter method of deleted items
    {
        this.deleted = deleted;
    }
}
