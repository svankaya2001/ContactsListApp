package com.example.contactslistapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import org.w3c.dom.Text;

public class ContactDetails extends Activity {
    private ContactHandler contactHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_details);
        final Bundle extras = getIntent().getExtras();
        ImageView contact_image = (ImageView)findViewById(R.id.detail_image);
        contact_image.setImageBitmap(BitmapFactory.decodeFile(extras.getString("picture")));
        TextView contact_name = (TextView)findViewById(R.id.textView2);
        contact_name.setText(extras.getString("name"));
        TextView contact_number = (TextView)findViewById(R.id.textView4);
        contact_number.setText(extras.getString("number"));
        TextView contact_email = (TextView)findViewById(R.id.textView6);
        contact_email.setText(extras.getString("email"));
        TextView contact_postal = (TextView)findViewById(R.id.textView8);
        contact_postal.setText(extras.getString("postal"));
        Button btn_back = (Button)findViewById(R.id.btn_back_to_contact);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactDetails.this, MainActivity.class);
                startActivity(intent);
            }
        });
        Button btn_map = (Button)findViewById(R.id.btn_map);
        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactDetails.this, MapsActivity.class);
                intent.putExtra("address", extras.getString("postal"));
                startActivity(intent);
            }
        });
    }
}
