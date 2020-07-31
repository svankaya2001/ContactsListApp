package com.example.contactslistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button newContactBtn;
    private ContactHandler handler;



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
        loadContactData();

    }
    private void loadContactData(){
        // Code for loading contact list in ListView
        // Reading all contacts
        List<Contact> contacts = handler.readContacts();

        for(Contact c : contacts){
            String record = "ID=" + c.getId() + " | Name=" + c.getName() + " | " + c.getNumber();

            Log.d("Record",record);
        }

    }
}