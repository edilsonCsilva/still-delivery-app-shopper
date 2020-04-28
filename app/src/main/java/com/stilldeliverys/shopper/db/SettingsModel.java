package com.stilldeliverys.shopper.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

 
import com.stilldeliverys.shopper.core.ConstantesDbHelper;

import java.util.ArrayList;
import java.util.List;

public class SettingsModel implements TheFacesAreMongo {
    private Context self;


    public SettingsModel(Context context) {
        self = context;
    }


    @Override
    public Object insert(Object metadados) {
        Settings setting = (Settings) metadados;
        long ist = -1;
        try {
            ContentValues _values = new ContentValues();
            _values.put(ConstantesDbHelper.SETTINGS_DESCRIPTION, setting.getDescription());
            _values.put(ConstantesDbHelper.SETTINGS_METADATA, setting.getMetadados());
            CreationOfTheDatabase conn = new CreationOfTheDatabase(self);
            SQLiteDatabase db = conn.getWritableDatabase();
            ist = db.insert(ConstantesDbHelper.SETTINGS, null, _values);
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
        List<Settings> settings = new ArrayList<>();
        try {
            CreationOfTheDatabase conn = new CreationOfTheDatabase(self);
            SQLiteDatabase db = conn.getReadableDatabase();
            String opcao = "";
            Cursor c = db.query(ConstantesDbHelper.SETTINGS, null, ConstantesDbHelper.SETTINGS_DESCRIPTION + "=?",
                    new String[]{seach},
                    null,
                    null,
                    null,
                    null);
            if (c.moveToFirst()) {
                do {
                    settings.add(new Settings(c.getInt(0), c.getString(1), c.getString(2)));

                } while (c.moveToNext());
            }
            c.close();
            db.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return settings;

    }

    @Override
    public Object findAll() {

        List<Settings> settings = new ArrayList<>();
        try {
            CreationOfTheDatabase conn = new CreationOfTheDatabase(self);
            SQLiteDatabase db = conn.getReadableDatabase();
            String opcao = "";
            Cursor c = db.query(ConstantesDbHelper.SETTINGS, null, null,
                    null,
                    null,
                    null,
                    null,
                    null);
            if (c.moveToFirst()) {
                do {
                    settings.add(new Settings(c.getInt(0), c.getString(1), c.getString(2)));

                } while (c.moveToNext());
            }
            c.close();
            db.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return settings;

    }

    @Override
    public Object update(Object metadados) {
        Settings setting = (Settings) metadados;
        long ist = -1;
        try {
            ContentValues _values = new ContentValues();
             _values.put(ConstantesDbHelper.SETTINGS_METADATA, setting.getMetadados());
            CreationOfTheDatabase conn = new CreationOfTheDatabase(self);
            SQLiteDatabase db = conn.getWritableDatabase();


            ist = db.update(ConstantesDbHelper.SETTINGS,_values,String.format("%s=?",
                    ConstantesDbHelper.SETTINGS_CODE),
                    new String[]{String.valueOf(setting.getObjId()) });
            db.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ist;
    }

    @Override
    public Object remove(Object metadados) {
        Settings setting = (Settings) metadados;
        long ist = -1;
        try {
            ContentValues _values = new ContentValues();
            CreationOfTheDatabase conn = new CreationOfTheDatabase(self);
            SQLiteDatabase db = conn.getWritableDatabase();
            ist = db.delete(ConstantesDbHelper.SETTINGS,String.format("%s=?",
                    ConstantesDbHelper.SETTINGS_CODE),
                    new String[]{String.valueOf(setting.getObjId())});
            db.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ist;
    }


}
