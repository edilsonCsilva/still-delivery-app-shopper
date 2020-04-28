package com.stilldeliverys.shopper.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.stilldeliverys.shopper.R;
import com.stilldeliverys.shopper.core.ConstantesDbHelper;
import com.stilldeliverys.shopper.db.Settings;
import com.stilldeliverys.shopper.db.SettingsModel;

import java.util.List;

public class StartApp extends AppCompatActivity{


    protected SettingsModel settingsModel;
    protected Context self;
    protected Button btn;
    protected EditText url;
    private boolean progress_bar_active;
    private ProgressDialog progress_bar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_app);
        self = this;
        settingsModel = new SettingsModel(self);

        btn = findViewById(R.id.btn);
        url = findViewById(R.id.url);


        if (url.getText().toString().trim().isEmpty()) {
            AlertDialog alertDialog = new AlertDialog.Builder(self).create();
            alertDialog.setTitle(getString(R.string.msn_title));
            alertDialog.setMessage(getString(R.string.not_fault_server));
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            url.requestFocus();
                        }
                    });
            alertDialog.show();

            return;
        }


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                get_progress_bar(self, "Configurando Aplicativo . Aguarde...", "Configurando...");

                List<Settings> urls = (List<Settings>) settingsModel.find(ConstantesDbHelper.SETTINGS_SERVER_API.toUpperCase());
                if (urls.size() == 1) {
                    if (urls.get(0).getMetadados().trim().isEmpty()) {
                        urls.get(0).setMetadados(url.getText().toString());
                        settingsModel.update(urls.get(0));
                        startActivity(new Intent(self, MainActivity.class));
                        progress_bar_active = false;
                        finish();
                    }
                }

            }
        });


    }


    public void get_progress_bar(Context context, String head, String msn) {
        progress_bar_active = true;

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

}
