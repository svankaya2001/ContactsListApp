package com.example.contactslistapp;

public class Contact {
    private int id;
    private String name;
    private String number;
    private String email_id;
    private String picture;
    private String postal_address;

    public Contact(){

    }

    public Contact(String _name, String _number, String _email_id, String _picture, String _postal_address){
        this.name = _name;
        this.number = _number;
        this.email_id = _email_id;
        this.picture = _picture;
        this.postal_address = _postal_address;
    }

    public int getId() {
        return id;
    }
    public void setId(int __id){
        this.id = __id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getNumber(){
        return number;
    }
    public void setNumber(String number){
        this.number = number;
    }
    public String getEmail_id(){
        return email_id;
    }
    public void setEmail_id(String email_id){
        this.email_id = email_id;
    }
    public String getPicture(){
        return picture;
    }
    public void setPicture(String picture){
        this.picture = picture;
    }
    public String getPostal_address(){
        return postal_address;
    }
    public void setPostal_address(String postal_address){
        this.postal_address = postal_address;
    }




}
