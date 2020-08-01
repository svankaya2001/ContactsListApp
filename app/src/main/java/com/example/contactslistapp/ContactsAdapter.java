package com.example.contactslistapp;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ContactsAdapter extends ArrayAdapter<Contact> {
    public class ViewHolder{
        public ImageView contact_image;
        public TextView contact_name, contact_number;
    }
    public ContactsAdapter(Context context, ArrayList<Contact> acontacts){
        super(context, 0, acontacts);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Contact contact = getItem(position);
        final ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.contact_item, parent, false);
            viewHolder.contact_image = (ImageView)convertView.findViewById(R.id.contact_image);
            viewHolder.contact_name = (TextView) convertView.findViewById(R.id.tv_full_name);
            viewHolder.contact_number = (TextView)convertView.findViewById(R.id.tv_phone_number);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.contact_name.setText(contact.getName());
        viewHolder.contact_number.setText(contact.getNumber());
        viewHolder.contact_image.setImageBitmap(BitmapFactory.decodeFile(contact.getPicture()));
        return convertView;



        //return super.getView(position, convertView, parent);

    }
}
