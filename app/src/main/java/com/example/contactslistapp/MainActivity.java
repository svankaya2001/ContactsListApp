package com.example.contactslistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button newContactBtn;
    private ContactHandler handler;
    private ListView contactsList;
    private ContactsAdapter contactsAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new ContactHandler(getApplicationContext());
        newContactBtn = (Button)findViewById(R.id.ContactBtn);

        newContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewContact.class);
                startActivity(intent);
            }

        });

        contactsList = (ListView)findViewById(R.id.ContactsList);
        ArrayList<Contact> acontacts = new ArrayList<Contact>();
        contactsAdapter = new ContactsAdapter(this, acontacts);
        contactsList.setAdapter(contactsAdapter);
        loadContactData();

    }
    private void loadContactData(){
        // Code for loading contact list in ListView
        // Reading all contacts
        List<Contact> contacts = handler.readContacts();
        contactsAdapter.clear();

        for(Contact c : contacts){
            /*handler.deleteContact(c.getId());
            String record = "ID=" + c.getId() + " | Name=" + c.getName() + " | " + c.getNumber() + "|" + c.getPicture();

            Log.d("Record",record);*/
            contactsAdapter.add(c);
        }
        contactsAdapter.notifyDataSetChanged();

    }
}