package com.example.darycam;

import java.io.Serializable;

public class ListElementm implements Serializable {
    public String colorm;
    public String clasem;
    public String horam;
    public String estatusm;


    public ListElementm(String colorm, String clasem, String horam, String estatusm) {
        this.colorm = colorm;
        this.clasem = clasem;
        this.horam = horam;
        this.estatusm = estatusm;
    }

    public String getColorm() {
        return colorm;
    }

    public void setColorm(String colorm) {
        this.colorm = colorm;
    }

    public String getClasem() {
        return clasem;
    }

    public void setClasem(String clasem) {
        this.clasem = clasem;
    }

    public String getHoram() {
        return horam;
    }

    public void setHoram(String horam) {
        this.horam = horam;
    }

    public String getEstatusm() {
        return estatusm;
    }

    public void setEstatusm(String estatusm) {
        this.estatusm = estatusm;
    }



}