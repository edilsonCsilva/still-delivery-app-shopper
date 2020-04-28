package com.stilldeliverys.shopper.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.stilldeliverys.shopper.core.ConstantesDbHelper;

public class CreationOfTheDatabase extends SQLiteOpenHelper {

    private static final String name = ConstantesDbHelper.BASE_DRIVES;
    private static final int version = ConstantesDbHelper.BASE_DRIVES_VERSION;

    public CreationOfTheDatabase(Context context) {
        super(context, name, null, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String script_db_tbl = "";



        script_db_tbl = " CREATE TABLE IF NOT EXISTS " +  ConstantesDbHelper.SETTINGS + "("
                + " " + ConstantesDbHelper.SETTINGS_CODE + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +"   "+ConstantesDbHelper.SETTINGS_DESCRIPTION+" TEXT  ,"
                + " " + ConstantesDbHelper.SETTINGS_METADATA+ " TEXT)";
        db.execSQL(script_db_tbl);


        script_db_tbl = " CREATE TABLE IF NOT EXISTS " +  ConstantesDbHelper.SUPERMARKET_TABLE + "("
                + " " + ConstantesDbHelper.SUPERMARKET_TABLE_CODE + " TEXT PRIMARY KEY ,"
                +"   "+ConstantesDbHelper.SUPERMARKET_TABLE_CODE_UUID+" TEXT  ,"
                + " " + ConstantesDbHelper.SUPERMARKET_TABLE_METADATA+ " TEXT)";
        db.execSQL(script_db_tbl);



        script_db_tbl = " CREATE TABLE IF NOT EXISTS " +  ConstantesDbHelper.ROUTER_ITENS_TABLE + "("
                + " " + ConstantesDbHelper.ROUTER_ITENS_CODE + " TEXT PRIMARY KEY ,"
                +"   "+ConstantesDbHelper.ROUTER_ITENS_CODE_UUID+" TEXT  ,"
                +"   "+ConstantesDbHelper.ROUTER_ITENS_CODE_ROUTER+" TEXT  ,"
                +"   "+ConstantesDbHelper.ROUTER_ITENS_CODE_FK_SUPERMARKET+" TEXT  ,"
                +"   "+ConstantesDbHelper.ROUTER_ITENS_METADADOS+" TEXT)";

        db.execSQL(script_db_tbl);







        script_db_tbl = " CREATE TABLE IF NOT EXISTS " +  ConstantesDbHelper.VEHICLES + "("
                + " " + ConstantesDbHelper.VEHICLES_CODE_AUTO + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +"   "+ConstantesDbHelper.VEHICLES_CODE+" TEXT  ,"
                +"   "+ConstantesDbHelper.VEHICLES_TYPE+" TEXT  ,"
                +"   "+ConstantesDbHelper.VEHICLES_NAME+" TEXT  ,"
                +"   "+ConstantesDbHelper.VEHICLES_NAME_TYPE+" TEXT  ,"
                + " " + ConstantesDbHelper.VEHICLES_CAPACITY+ " TEXT)";
        db.execSQL(script_db_tbl);

        script_db_tbl = " CREATE TABLE IF NOT EXISTS " +  ConstantesDbHelper.PEDIDO_ITENS + "("
                + " " + ConstantesDbHelper.PEDIDO_ITENS_CODE_AUTO + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +"   "+ConstantesDbHelper.PEDIDO_ITENS_CODE+" TEXT  ,"
                +"   "+ConstantesDbHelper.PEDIDO_ITENS_CODE_PEDIDO+" TEXT  ,"
                +"   "+ConstantesDbHelper.PEDIDO_ITENS_NAME+" TEXT  ,"
                +"   "+ConstantesDbHelper.PEDIDO_ITENS_QUANT+" TEXT  ,"
                + " " + ConstantesDbHelper.PEDIDO_ITENS_VL_UNIT+ " TEXT)";
        db.execSQL(script_db_tbl);


        script_db_tbl = " CREATE TABLE IF NOT EXISTS " +  ConstantesDbHelper.FOTO_PEDIDO_ITENS + "("
                + " " + ConstantesDbHelper.FOTO_PEDIDO_ITENS_CODE_AUTO + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +"   "+ConstantesDbHelper.FOTO_PEDIDO_ITENS_LATLONG+" TEXT  ,"
                +"   "+ConstantesDbHelper.FOTO_PEDIDO_ITENS_IMG_PATH+" TEXT  ,"
                +"   "+ConstantesDbHelper.FOTO_PEDIDO_ITENS_PED_CODE+" TEXT  ,"
                + " " + ConstantesDbHelper.FOTO_PEDIDO_ITENS_SYNC+ " TEXT)";
        db.execSQL(script_db_tbl);














    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        onCreate(db);
    }
}
