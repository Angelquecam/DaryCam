package com.example.darycam;

public class ListElement {
    public String color;
    public String clase;
    public String hora;
    public String estatus;

    public ListElement(String color, String clase, String hora, String estatus) {
        this.color = color;
        this.clase = clase;
        this.hora = hora;
        this.estatus = estatus;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
}
