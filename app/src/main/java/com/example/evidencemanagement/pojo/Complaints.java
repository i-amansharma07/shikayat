package com.example.evidencemanagement.pojo;

public class Complaints {

    private String complaint ="";

    public  Complaints(){
    }
    public Complaints(String comp) {
        this.complaint = comp;
    }


    public void setComp(String comp) {
        this.complaint = comp;
    }

    public String getComp() {
        return complaint;
    }
}
