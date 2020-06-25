package com.stilldeliverys.shopper.ui;

import android.accounts.AccountManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.stilldeliverys.shopper.R;
import com.stilldeliverys.shopper.Uteis.Utils;
import com.stilldeliverys.shopper.api.DriversEndPointActions;
import com.stilldeliverys.shopper.core.BaseActivity;
import com.stilldeliverys.shopper.core.Constantes;
import com.stilldeliverys.shopper.core.ConstantesDbHelper;
import com.stilldeliverys.shopper.db.CreationOfTheDatabase;
import com.stilldeliverys.shopper.db.Settings;
import com.stilldeliverys.shopper.db.SettingsModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class MainActivity extends BaseActivity {


    private Button btn_main_validate_user_system;


    private static TextView txt_main_user_input;
    private static TextView txt_main_user_password_entry;
    private CardView card_view_main;
    private ProgressBar br_status;
    private Switch switch1;
    private List<Settings> keep_connected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        self=this;
        btn_main_validate_user_system=(Button)findViewById(R.id.btn_main_validate_user_system);
        getSupportActionBar().hide(); //Esta linha contém o código necessário.
        InitPermisionGeral();
        new CreationOfTheDatabase(self);
        settingsModel = new SettingsModel(self);
        start_application_settings();
        AccountManager am = AccountManager.get(this);
        Bundle options = new Bundle();
        switch1 = (Switch) findViewById(R.id.switch1);
        btn_main_validate_user_system = findViewById(R.id.btn_main_validate_user_system);
        txt_main_user_input = findViewById(R.id.txt_main_user_input);
        txt_main_user_password_entry = findViewById(R.id.txt_main_user_password_entry);
        card_view_main = findViewById(R.id.card_view_main);
        br_status = findViewById(R.id.br_status);
        br_status.setVisibility(View.GONE);

        txt_main_user_input.setText("shopper@still.com");
                txt_main_user_password_entry.setText("shopper123!@#");

        List<Settings> url = (List<Settings>) settingsModel.find(ConstantesDbHelper.SETTINGS_SERVER_API.toUpperCase());
        if (url.size() == 1) {
            if (url.get(0).getMetadados().trim().isEmpty()) {
                startActivity(new Intent(self, StartApp.class));
                finish();
            }
        }
        Constantes.base = url.get(0).getMetadados();
        //Verifica se tem usuario
        keep_connected = (List<Settings>) settingsModel.find(ConstantesDbHelper.SETTINGS_IS_LOGIN_ACTIVE_MANTER.toUpperCase());
        if (keep_connected.size() == 1) {
            if (!keep_connected.get(0).getMetadados().trim().isEmpty()) {
                String[] inputs = keep_connected.get(0).getMetadados().trim().split("\\|");
                txt_main_user_input.setText(inputs[0]);
                txt_main_user_password_entry.setText(inputs[1]);
                switch1.setChecked(true);

            }
        }


        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (txt_main_user_input.getText().toString().trim().isEmpty()) {
                        AlertDialog alertDialog = new AlertDialog.Builder(self).create();
                        alertDialog.setTitle(getString(R.string.msn_title));
                        alertDialog.setMessage(getString(R.string.user_acess_invalid_input_login));
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();


                                    }
                                });
                        alertDialog.show();

                        txt_main_user_input.requestFocus();
                        switch1.setChecked(false);
                        return;
                    }
                    if (txt_main_user_password_entry.getText().toString().trim().isEmpty()) {
                        AlertDialog alertDialog = new AlertDialog.Builder(self).create();
                        alertDialog.setTitle(getString(R.string.msn_title));
                        alertDialog.setMessage(getString(R.string.user_acess_invalid_input_password));
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();

                                    }
                                });
                        alertDialog.show();
                        txt_main_user_password_entry.requestFocus();
                        switch1.setChecked(false);
                        return;
                    }
                    String acessos = String.format("%s|%s", txt_main_user_input.getText().toString(),
                            txt_main_user_password_entry.getText().toString());
                    keep_connected.get(0).setMetadados(acessos);
                    settingsModel.update(keep_connected.get(0));
                } else {
                    switch1.setChecked(false);
                    txt_main_user_input.setText("");
                    txt_main_user_password_entry.setText("");
                    txt_main_user_input.requestFocus();
                    keep_connected.get(0).setMetadados("");
                    settingsModel.update(keep_connected.get(0));
                }


            }
        });

        btn_main_validate_user_system.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txt_main_user_input.getText().toString().trim().isEmpty()) {
                    AlertDialog alertDialog = new AlertDialog.Builder(self).create();
                    alertDialog.setTitle(getString(R.string.msn_title));
                    alertDialog.setMessage(getString(R.string.user_acess_invalid_input_login));
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();

                                }
                            });
                    alertDialog.show();
                    txt_main_user_input.requestFocus();
                    return;
                }
                if (txt_main_user_password_entry.getText().toString().trim().isEmpty()) {
                    AlertDialog alertDialog = new AlertDialog.Builder(self).create();
                    alertDialog.setTitle(getString(R.string.msn_title));
                    alertDialog.setMessage(getString(R.string.user_acess_invalid_input_password));
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();

                                }
                            });
                    alertDialog.show();
                    txt_main_user_password_entry.requestFocus();
                    return;
                }
                if (Utils.isNetworkAvailable(self)) {
                    //realiza a autentificaçao do usuario
                    br_status.setVisibility(View.VISIBLE);
                    card_view_main.setVisibility(View.GONE);
                    //Cria a Solicitacao no corpo da messsagem
                    //Cria a Solicitacao no corpo da messsagem
                    RequestBody username = RequestBody.create(MediaType.parse("text/plain"), txt_main_user_input.getText().toString());
                    RequestBody password = RequestBody.create(MediaType.parse("text/plain"), txt_main_user_password_entry.getText().toString());
                    RequestBody grant_type = RequestBody.create(MediaType.parse("text/plain"), Constantes.base_grant_type);
                    RequestBody client_id = RequestBody.create(MediaType.parse("text/plain"), Constantes.base_client_id);
                    RequestBody client_secret = RequestBody.create(MediaType.parse("text/plain"), Constantes.base_client_secret);
                    //Cria a Solicitacao no corpo da messsagem
                    //Cria a Solicitacao no corpo da messsagem*/

                    new DriversEndPointActions().oauth_token().oauth_token(username, password, grant_type, client_id, client_secret).
                            enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    if (response.isSuccessful()) {
                                        try {
                                            JSONObject oauth = new JSONObject(response.body().string());
                                            StringBuffer jwt = new StringBuffer();
                                            jwt.append("{");
                                            jwt.append(String.format("\"token_type\":\"%s\" ,", oauth.getString("token_type")));
                                            jwt.append(String.format("\"expires_in\":\"%s\" ,", oauth.getString("expires_in")));
                                            jwt.append(String.format("\"access_token\":\"%s\" ,", oauth.getString("access_token")));
                                            jwt.append(String.format("\"refresh_token\":\"%s\" ", oauth.getString("refresh_token")));
                                            jwt.append("}");
                                            List<Settings> setting = null;
                                            setting = (List<Settings>) settingsModel.find(ConstantesDbHelper.SETTINGS_ACESS_API_LOGIN.toUpperCase());
                                            if (setting.size() != 0) {
                                                setting.get(0).setMetadados(jwt.toString());
                                                settingsModel.update(setting.get(0));
                                            }
                                            // get_progress_bar(self, getString(str_process_bar), getString(str_process_msn_load));
                                            Intent screen_home;


                                            screen_home = new Intent(self, DisplayStores.class);
                                            screen_home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            startActivity(screen_home);
                                            progress_bar_active = false;
                                            finish();


                                        } catch (Exception e) {
                                            e.printStackTrace();
                                            AlertDialog alertDialog = new AlertDialog.Builder(self).create();
                                            alertDialog.setTitle(getString(R.string.msn_title));
                                            alertDialog.setMessage(getString(R.string.str_error_response_api));
                                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                                    new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            dialog.dismiss();
                                                            br_status.setVisibility(View.GONE);
                                                            card_view_main.setVisibility(View.VISIBLE);

                                                        }
                                                    });
                                            alertDialog.show();
                                        }
                                    } else {
                                        if (!response.isSuccessful()) {
                                            JSONObject jsonObject = null;
                                            try {
                                                jsonObject = new JSONObject(response.errorBody().string());
                                                String userMessage = jsonObject.getString("error");
                                                String internalMessage = jsonObject.getString("message");
                                                String errorDescript = jsonObject.getString("error_description");

                                                AlertDialog alertDialog = new AlertDialog.Builder(self).create();
                                                alertDialog.setTitle(getString(R.string.msn_title));
                                                alertDialog.setMessage(getString(R.string.user_invalid_acess));
                                                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                                        new DialogInterface.OnClickListener() {
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                dialog.dismiss();
                                                                br_status.setVisibility(View.GONE);
                                                                card_view_main.setVisibility(View.VISIBLE);

                                                            }
                                                        });
                                                alertDialog.show();
                                                txt_main_user_password_entry.setText("");
                                                txt_main_user_input.setText("");
                                                txt_main_user_input.requestFocus();
                                            } catch (JSONException | IOException e) {
                                                e.printStackTrace();
                                                AlertDialog alertDialog = new AlertDialog.Builder(self).create();
                                                alertDialog.setTitle(getString(R.string.msn_title));
                                                alertDialog.setMessage(getString(R.string.str_error_response_api));
                                                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                                        new DialogInterface.OnClickListener() {
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                dialog.dismiss();
                                                                br_status.setVisibility(View.GONE);
                                                                card_view_main.setVisibility(View.VISIBLE);

                                                            }
                                                        });
                                                alertDialog.show();

                                            }
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {

                                    AlertDialog alertDialog = new AlertDialog.Builder(self).create();
                                    alertDialog.setTitle(getString(R.string.msn_title));
                                    alertDialog.setMessage(getString(R.string.str_error_response_api));
                                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();
                                                    br_status.setVisibility(View.GONE);
                                                    card_view_main.setVisibility(View.VISIBLE);

                                                }
                                            });
                                    alertDialog.show();


                                }
                            });


                    //realiza a autentificaçao do usuario



                    /*//"Processando"  "Loading........."
                    get_progress_bar(self,getString(R.string.str_process_bar) , getString(R.string.str_process_msn_load));
                    Intent screen_home;
                    screen_home = new Intent(self, DriverHomeScreen.class);
                    screen_home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(screen_home);
*/
                }




/*
                 Intent displaystores=new Intent(self,DisplayStores.class);
                 startActivity(displaystores);
*/




            }
        });


    }







}
