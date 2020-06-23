package com.stilldeliverys.shopper.core;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Environment;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stilldeliverys.shopper.db.Settings;
import com.stilldeliverys.shopper.db.SettingsModel;
import com.stilldeliverys.shopper.services.Gps;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Libs {












    public  static  List<Settings>  seek_configurations(Context self, String type, String metadados, boolean insert){
        List<Settings> settings =null;
        SettingsModel settingsModel = new SettingsModel(self);
        settings =(List<Settings>)settingsModel.find(type.toUpperCase());

        if(insert && !metadados.isEmpty()){
            if(settings.size()==0){
                settingsModel.insert(new Settings(type.toUpperCase(),metadados));
            }
        }else if(!insert && !metadados.isEmpty()){
            settings.get(0).setMetadados(metadados);
            settingsModel.update(settings.get(0));
        }
        return settings;
   }





    public static void deslogar(Context self) {
        SettingsModel settingsModel = new SettingsModel(self);
        List<Settings> setting = (List<Settings>) settingsModel.find(ConstantesDbHelper.SETTINGS_DESCRIPTION_IS_LOGIN_ACTIVE_TYPE.toUpperCase());
        if (setting.size() != 0) {
            setting.get(0).setMetadados("");
            settingsModel.update(setting.get(0));
            setting = (List<Settings>) settingsModel.find(ConstantesDbHelper.SETTINGS_DESCRIPTION_IS_LOGIN_ACTIVE_SUPERMARKS_SELECT.toUpperCase());
            if (setting.size() != 0) {
                setting.get(0).setMetadados("");
                settingsModel.update(setting.get(0));

                setting = (List<Settings>) settingsModel.find(ConstantesDbHelper.SETTINGS_DESCRIPTION_IS_LOGIN_ACTIVE.toUpperCase());
                if (setting.size() != 0) {
                    setting.get(0).setMetadados("false");
                    settingsModel.update(setting.get(0));

                }



                setting = (List<Settings>) settingsModel.find(ConstantesDbHelper.SETTINGS_DESCRIPTION_IS_LOGIN_ACTIVE_TYPE.toUpperCase());
                if (setting.size() != 0) {
                    setting.get(0).setMetadados("");
                    settingsModel.update(setting.get(0));
                }

            }

        }

    }

    public static int indexOf(String busca, List<String> lista) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).contains(busca)) {
                return i;
            }
        }
        return -1;
    }


    public static String builderToString(String key, String val) {
        return String.format("\"%s\":%s", key, val);
    }

    public static String conv_to_br_price(Double value){

        Locale localeBR = new Locale("pt", "BR");
        NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);

        return dinheiro.format(value);
    }


    public static String completeToLeft(String value, char c, int size) {
        String result = value;

        while (result.length() < size) {
            result = c + result;
        }

        return result;
    }




    public  static String  mapsToJson(Map<String,Object> dados){
        String JSONObject="";
        try {
            // Use this builder to construct a Gson instance when you need to set configuration options other than the default.
            GsonBuilder gsonMapBuilder = new GsonBuilder();

            Gson gsonObject = gsonMapBuilder.create();
            JSONObject = gsonObject.toJson(dados);


            System.out.println("as");
        }catch (Exception e){
            System.out.println("E:"+e.getMessage());
        }
        return JSONObject;
    }


    public static String[] jsonToObjectJwt(String json) {
        JSONObject jsonObject;
        String [] active_access = new String[3];
        try {
            jsonObject = new JSONObject(json);
            active_access[0]=jsonObject.getString("access_token");
            active_access[1]=jsonObject.getString("refresh_token");
            active_access[2]=jsonObject.getString("token_type");
            active_access[0]=String.format("%s %s ",active_access[2],active_access[0]);

        } catch (Exception e) {
            jsonObject = null;
            active_access=null;

        }
        return active_access;
    }




    public static String MyGlobalLocation(Context context) {
        // instancia o service, GPSTracker gps
        Gps gps = new Gps(context);
        double latitude = 0.0;
        double longitude = 0.0;
        // verifica ele
        if (gps.canGetLocation()) {
            // passa sua latitude e longitude para duas variaveis
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
        }
        return String.format("%s,%s", String.valueOf(latitude), String.valueOf(longitude));
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public static File createFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        timeStamp = "_" + timeStamp;
        File pasta = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File imagem = new File(pasta.getPath() + File.separator
                + "JPG_" + timeStamp + ".jpg");
        return imagem;
    }
}
