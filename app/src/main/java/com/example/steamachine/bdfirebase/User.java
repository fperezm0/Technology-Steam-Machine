package com.example.steamachine.bdfirebase;

public class User {

    String correo;
    String name;
    int status;

    public User(String correo, String name, int status) {
        this.correo = correo;
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public User(String correo, int status) {
        this.correo = correo;
        this.status = status;
    }



    public User() {

    }



    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }




}
