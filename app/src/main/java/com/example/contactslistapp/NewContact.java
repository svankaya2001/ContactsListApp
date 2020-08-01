package com.example.contactslistapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class NewContact extends Activity {
    private ImageView contact_image;
    private Button btnadd;
    private Contact contact;
    private EditText contact_name, contact_number, contact_email, contact_postal;
    String picturePath = "";
    private ContactHandler contactHandler;
    private Bitmap bitmap;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_contact_item);
        contactHandler = new ContactHandler(getApplicationContext());
        contact_image = (ImageView)findViewById(R.id.iv_user_photo);
        contact_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        });
        btnadd = (Button)findViewById(R.id.btn_add);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contact_name = (EditText)findViewById(R.id.et_name);
                contact_number = (EditText)findViewById(R.id.et_phone);
                contact_email = (EditText)findViewById(R.id.et_email);
                contact_postal = (EditText)findViewById(R.id.et_address);
                contact = new Contact();
                contact.setName(contact_name.getText().toString());
                contact.setNumber(contact_number.getText().toString());
                contact.setEmail_id(contact_email.getText().toString());
                contact.setPicture(picturePath);
                contact.setPostal_address(contact_postal.getText().toString());


                Boolean added = contactHandler.AddContactDetails(contact);
                if(added){
                    Intent intent = new Intent(NewContact.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Contact data not added. Please try again", Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && null != data) {
            Uri imageUri = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(imageUri,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();
            ImageView imageView = (ImageView)findViewById(R.id.iv_user_photo);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

            //bitmap = BitmapFactory.decodeFile(picturePath);

        }

    }
}
