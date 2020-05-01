package com.stilldeliverys.shopper.core;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import com.stilldeliverys.shopper.R;
import com.stilldeliverys.shopper.Uteis.Utils;
import com.stilldeliverys.shopper.db.Settings;
import com.stilldeliverys.shopper.db.SettingsModel;

import java.util.List;

public class BaseActivity extends AppCompatActivity {
    protected ProgressDialog progress_bar;
    protected Boolean progress_bar_active=false;
    protected SettingsModel settingsModel;
    protected Context self;

    protected String[] JWT;
    protected List<Settings> setting;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }
    public void is_conected() {
        if (!Utils.isNetworkAvailable(self)) {
            AlertDialog alertDialog = new AlertDialog.Builder(self).create();
            alertDialog.setTitle(getString(R.string.msn_title) );
            alertDialog.setMessage(getString(R.string.not_conect));
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
            return;
        }
    }
    public void InitPermisionGeral() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(this, Manifest.permission.INSTANT_APP_FOREGROUND_SERVICE) != PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_BOOT_COMPLETED) != PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(this, Manifest.permission.INSTALL_SHORTCUT) != PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(this, Manifest.permission.LOCATION_HARDWARE) != PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(this, Manifest.permission.SYSTEM_ALERT_WINDOW) != PackageManager.PERMISSION_GRANTED) {

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{
                                Manifest.permission.INTERNET,
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.INSTANT_APP_FOREGROUND_SERVICE,
                                Manifest.permission.ACCESS_NETWORK_STATE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.CAMERA,
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.READ_PHONE_STATE,
                                Manifest.permission.RECEIVE_BOOT_COMPLETED,
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.INSTALL_SHORTCUT,
                                Manifest.permission.LOCATION_HARDWARE,
                                Manifest.permission.SYSTEM_ALERT_WINDOW
                        }, 10000);
            }

        }


    }
    public void get_progress_bar(Context context,String head,String msn){
        progress_bar_active=true;
        progress_bar = new ProgressDialog(context);
        progress_bar.setCancelable(false);
        // Set progress dialog style spinner
        progress_bar.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        progress_bar.setTitle(head);
        progress_bar.setMessage(msn);

        // Set the progress dialog background color
        progress_bar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFD4D9D0")));

        progress_bar.setIndeterminate(false);
        progress_bar.closeOptionsMenu();
        // Finally, show the progress dialog
        progress_bar.show();
    }
    public void start_application_settings() {
        try {
            /*Toast.makeText(self,"Configurando o App...",Toast.LENGTH_SHORT).show();*/
            List<Settings> setting=null;
            setting =(List<Settings>)settingsModel.find(ConstantesDbHelper.SETTINGS_DESCRIPTION_IS_LOGIN_ACTIVE.toUpperCase());
            if(setting.size()==0){
                settingsModel.insert(new Settings(ConstantesDbHelper.SETTINGS_DESCRIPTION_IS_LOGIN_ACTIVE.toUpperCase(),"false"));
            }
            setting =(List<Settings>)settingsModel.find(ConstantesDbHelper.SETTINGS_DESCRIPTION_VEHICLES_SELECTED.toUpperCase());
            if(setting.size()==0){
                settingsModel.insert(new Settings(ConstantesDbHelper.SETTINGS_DESCRIPTION_VEHICLES_SELECTED.toUpperCase(),"false"));
            }

            setting =(List<Settings>)settingsModel.find(ConstantesDbHelper.SETTINGS_DESCRIPTION_VEHICLES_SELECTED_CODE.toUpperCase());
            if(setting.size()==0){
                settingsModel.insert(new Settings(ConstantesDbHelper.SETTINGS_DESCRIPTION_VEHICLES_SELECTED_CODE.toUpperCase(),"false"));
            }

            setting =(List<Settings>)settingsModel.find(ConstantesDbHelper.SETTINGS_ACESS_API_LOGIN.toUpperCase());
            if(setting.size()==0){
                settingsModel.insert(new Settings(ConstantesDbHelper.SETTINGS_ACESS_API_LOGIN.toUpperCase(),""));
            }

            setting =(List<Settings>)settingsModel.find(ConstantesDbHelper.SETTINGS_DESCRIPTION_VEHICLES_SELECTED_CONDUCTOR_ACTIVE.toUpperCase());
            if(setting.size()==0){
                settingsModel.insert(new Settings(ConstantesDbHelper.SETTINGS_DESCRIPTION_VEHICLES_SELECTED_CONDUCTOR_ACTIVE.toUpperCase(),""));
            }


            setting =(List<Settings>)settingsModel.find(ConstantesDbHelper.SETTINGS_SERVER_API.toUpperCase());
            if(setting.size()==0){
                settingsModel.insert(new Settings(ConstantesDbHelper.SETTINGS_SERVER_API.toUpperCase(),""));
            }

            setting =(List<Settings>)settingsModel.find(ConstantesDbHelper.SETTINGS_DESCRIPTION_IS_LOGIN_ACTIVE_TYPE.toUpperCase());
            if(setting.size()==0){
                settingsModel.insert(new Settings(ConstantesDbHelper.SETTINGS_DESCRIPTION_IS_LOGIN_ACTIVE_TYPE.toUpperCase(),""));
            }

            setting =(List<Settings>)settingsModel.find(ConstantesDbHelper.SETTINGS_DESCRIPTION_IS_LOGIN_ACTIVE_SUPERMARKS_SELECT.toUpperCase());
            if(setting.size()==0){
                settingsModel.insert(new Settings(ConstantesDbHelper.SETTINGS_DESCRIPTION_IS_LOGIN_ACTIVE_SUPERMARKS_SELECT.toUpperCase(),""));
            }

            setting =(List<Settings>)settingsModel.find(ConstantesDbHelper.SETTINGS_DESCRIPTION_IS_LOGIN_ACTIVE_CAR_SELECT.toUpperCase());
            if(setting.size()==0){
                settingsModel.insert(new Settings(ConstantesDbHelper.SETTINGS_DESCRIPTION_IS_LOGIN_ACTIVE_CAR_SELECT.toUpperCase(),""));
            }


            setting =(List<Settings>)settingsModel.find(ConstantesDbHelper.SETTINGS_IS_LOGIN_ACTIVE_MANTER.toUpperCase());
            if(setting.size()==0){
                settingsModel.insert(new Settings(ConstantesDbHelper.SETTINGS_IS_LOGIN_ACTIVE_MANTER.toUpperCase(),""));
            }

            setting =(List<Settings>)settingsModel.find(ConstantesDbHelper.SETTINGS_IS_ORDER_ACTIVE_MANTER.toUpperCase());
            if(setting.size()==0){
                settingsModel.insert(new Settings(ConstantesDbHelper.SETTINGS_IS_ORDER_ACTIVE_MANTER.toUpperCase(),""));
            }

            setting =(List<Settings>)settingsModel.find(ConstantesDbHelper.SETTINGS_IS_PAGER_ACTIVE_MANTER.toUpperCase());
            if(setting.size()==0){
                settingsModel.insert(new Settings(ConstantesDbHelper.SETTINGS_IS_PAGER_ACTIVE_MANTER.toUpperCase(),""));
            }





        }catch (Exception e){
            e.printStackTrace();
        }


    }




}


