package com.stilldeliverys.shopper.db;

public interface TheFacesAreMongo {


    Object insert(Object metadados);
    Object find(Object obj);
    Object findAll();
    Object update(Object metadados);

    Object remove(Object metadados);

}
