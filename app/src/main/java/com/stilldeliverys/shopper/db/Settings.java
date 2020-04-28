package com.stilldeliverys.shopper.db;

public class Settings {
    private String description;
    private int ObjId;
    private String metadados;


    public Settings(String description, String metadados) {
        this.description = description;
        this.metadados = metadados;
    }


    public Settings(int objId,String description, String metadados) {
        this.description = description;
        ObjId = objId;
        this.metadados = metadados;
    }

    public Settings() {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getObjId() {
        return ObjId;
    }

    public void setObjId(int objId) {
        ObjId = objId;
    }

    public String getMetadados() {
        return metadados;
    }

    public void setMetadados(String metadados) {
        this.metadados = metadados;
    }
}
