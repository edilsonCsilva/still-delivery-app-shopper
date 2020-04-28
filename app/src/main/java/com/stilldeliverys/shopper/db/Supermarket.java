package com.stilldeliverys.shopper.db;

public class Supermarket {



    private String ObjId;

    private String ObjIduuid;

    private String metadados;


    public Supermarket(String id, String uuid, String metadado) {

        this.ObjId=id;
        this.ObjIduuid=uuid;
        this.metadados=metadado;



    }

    public String getObjId() {
        return ObjId;
    }

    public void setObjId(String objId) {
        ObjId = objId;
    }

    public String getObjIduuid() {
        return ObjIduuid;
    }

    public void setObjIduuid(String objIduuid) {
        ObjIduuid = objIduuid;
    }




    public String getMetadados() {
        return metadados;
    }

    public void setMetadados(String metadados) {
        this.metadados = metadados;
    }
}
