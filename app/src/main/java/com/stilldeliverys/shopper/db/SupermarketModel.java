package com.stilldeliverys.shopper.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.stilldeliverys.shopper.core.ConstantesDbHelper;

import java.util.ArrayList;
import java.util.List;

public class SupermarketModel implements TheFacesAreMongo {
    private Context self;


    public SupermarketModel(Context context) {
        self = context;
    }


    @Override
    public Object insert(Object metadados) {
        Supermarket object = (Supermarket) metadados;
        long ist = -1;
        try {
            ContentValues _values = new ContentValues();
            _values.put(ConstantesDbHelper.SUPERMARKET_TABLE_CODE, object.getObjId());
            _values.put(ConstantesDbHelper.SUPERMARKET_TABLE_CODE_UUID, object.getObjIduuid());
            _values.put(ConstantesDbHelper.SUPERMARKET_TABLE_METADATA, object.getMetadados());

            CreationOfTheDatabase conn = new CreationOfTheDatabase(self);
            SQLiteDatabase db = conn.getWritableDatabase();
            ist = db.insert(ConstantesDbHelper.SUPERMARKET_TABLE, null, _values);
            db.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ist;
    }

    @Override
    public Object find(Object obj) {
        String seach = (String) obj;
        List<Supermarket> objects = new ArrayList<>();
        try {
            CreationOfTheDatabase conn = new CreationOfTheDatabase(self);
            SQLiteDatabase db = conn.getReadableDatabase();
            String opcao = "";
            Cursor c = db.query(ConstantesDbHelper.SUPERMARKET_TABLE,
                    null, ConstantesDbHelper.SUPERMARKET_TABLE_CODE_UUID + "=?",
                    new String[]{seach},
                    null,
                    null,
                    null,
                    null);
            if (c.moveToFirst()) {
                do {
                    objects.add(new Supermarket(c.getString(0), c.getString(1), c.getString(2)));

                } while (c.moveToNext());
            }
            c.close();
            db.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;

    }

    @Override
    public Object findAll() {

        List<Supermarket> objects = new ArrayList<>();
        try {
            CreationOfTheDatabase conn = new CreationOfTheDatabase(self);
            SQLiteDatabase db = conn.getReadableDatabase();
            String opcao = "";
            Cursor c = db.query(ConstantesDbHelper.SUPERMARKET_TABLE, null, null,
                    null,
                    null,
                    null,
                    null,
                    null);
            if (c.moveToFirst()) {
                do {
                    if (c.getString(0) != null) {
                        objects.add(new Supermarket(c.getString(0), c.getString(1), c.getString(2)));

                    }


                } while (c.moveToNext());
            }
            c.close();
            db.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;

    }

    @Override
    public Object update(Object metadados) {
        Supermarket object = (Supermarket) metadados;
        long ist = -1;
        try {
            ContentValues _values = new ContentValues();
            _values.put(ConstantesDbHelper.SUPERMARKET_TABLE_METADATA, object.getMetadados());

            CreationOfTheDatabase conn = new CreationOfTheDatabase(self);
            SQLiteDatabase db = conn.getWritableDatabase();
            ist = db.update(ConstantesDbHelper.SUPERMARKET_TABLE, _values, String.format("%s=?",
                    ConstantesDbHelper.SUPERMARKET_TABLE_CODE_UUID),
                    new String[]{String.valueOf(object.getObjIduuid())});
            db.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ist;
    }

    @Override
    public Object remove(Object metadados) {
        Supermarket object = (Supermarket) metadados;
        long ist = -1;
        try {
            ContentValues _values = new ContentValues();
            CreationOfTheDatabase conn = new CreationOfTheDatabase(self);
            SQLiteDatabase db = conn.getWritableDatabase();
            ist = db.delete(ConstantesDbHelper.SUPERMARKET_TABLE, String.format("%s=?",
                    ConstantesDbHelper.SUPERMARKET_TABLE_CODE_UUID),
                    new String[]{String.valueOf(object.getObjIduuid())});
            db.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ist;
    }

}
