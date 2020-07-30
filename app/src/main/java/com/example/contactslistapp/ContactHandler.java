package com.example.contactslistapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ContactHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ContactsList";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "contact";

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String NUMBER = "number";
    private static final String EMAIL_ID  ="email";
    private static final String PICTURE = "picture";
    private static final String POSTAL_ADDRESS = "postal";

    private String[] columns = {ID, NAME, NUMBER, EMAIL_ID, PICTURE, POSTAL_ADDRESS};

    public ContactHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_table = "CREATE TABLE " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY," + NAME + " TEXT," + NUMBER + " TEXT," + EMAIL_ID + " TEXT," + PICTURE + " TEXT," + POSTAL_ADDRESS + " TEXT" + ")";
        sqLiteDatabase.execSQL(create_table);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean AddContactDetails(Contact contact){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, contact.getName());
        contentValues.put(NUMBER, contact.getNumber());
        contentValues.put(EMAIL_ID, contact.getEmail_id());
        contentValues.put(PICTURE, contact.getPicture());
        contentValues.put(POSTAL_ADDRESS, contact.getPostal_address());
        long i = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        return (i!=0 ? true: false);
    }

    public List<Contact> readContacts(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        List<Contact> contacts = new ArrayList<Contact>();
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, columns, null, null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Contact contact = new Contact();
            contact.setId(Integer.parseInt(cursor.getString(0)));
            contact.setName(cursor.getString(1));
            contact.setNumber(cursor.getString(2));
            contact.setEmail_id(cursor.getString(3));
            contact.setPicture(cursor.getString(4));
            contact.setPostal_address(cursor.getString(5));
            contacts.add(contact);
            cursor.moveToNext();
        }
        cursor.close();
        return contacts;
    }


}
