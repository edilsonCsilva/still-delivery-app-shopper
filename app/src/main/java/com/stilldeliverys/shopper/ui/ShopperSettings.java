package com.stilldeliverys.shopper.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.stilldeliverys.shopper.R;
import com.stilldeliverys.shopper.core.BaseActivity;
import com.stilldeliverys.shopper.core.ConstantesDbHelper;
import com.stilldeliverys.shopper.core.Libs;
import com.stilldeliverys.shopper.db.Settings;

import org.json.JSONObject;

import java.util.List;

public class ShopperSettings extends BaseActivity {

    private TextView cdp_txt_conductor_registrer;
    private EditText cdp_edt_conductor_name, cdp_edt_conductor_document, cdp_edt_conductor_email,
            cdp_edt_conductor_tel_moblie, cdp_edt_conductor_tel_fixo, cdp_edt_conductor_address,
            cdp_edt_conductor_zipcode, cdp_edt_conductor_cargar_min;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopper_settings);
        self=this;
        List<Settings> settings = Libs.seek_configurations(self, ConstantesDbHelper.SETTINGS_ACESS_API_LOGIN, "", false);
        JWT = Libs.jsonToObjectJwt(settings.get(0).getMetadados());
        Intent intent = getIntent();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        getSupportActionBar().setTitle("");     //Titulo para ser exibido na sua Action Bar em frente à seta


        cdp_txt_conductor_registrer = findViewById(R.id.cdp_txt_conductor_registrer);
        cdp_edt_conductor_name = findViewById(R.id.cdp_edt_conductor_name);
        cdp_edt_conductor_document = findViewById(R.id.cdp_edt_conductor_document);
        cdp_edt_conductor_email = findViewById(R.id.cdp_edt_conductor_email);
        cdp_edt_conductor_tel_moblie = findViewById(R.id.cdp_edt_conductor_tel_moblie);
        cdp_edt_conductor_tel_fixo = findViewById(R.id.cdp_edt_conductor_tel_fixo);
        cdp_edt_conductor_address = findViewById(R.id.cdp_edt_conductor_address);
        cdp_edt_conductor_zipcode = findViewById(R.id.cdp_edt_conductor_zipcode);







        load_shopper_settings();







    }

    private void load_shopper_settings() {
        try{
            List<Settings> shopper = Libs.seek_configurations(self, ConstantesDbHelper.SETTINGS_DESCRIPTION_VEHICLES_SELECTED_CONDUCTOR_ACTIVE, "", false);
            JSONObject shopper_active = new JSONObject(shopper.get(0).getMetadados());



            cdp_txt_conductor_registrer.setText(shopper_active.getJSONObject("person").getString("uuid"));
            cdp_edt_conductor_name.setText(shopper_active.getJSONObject("person").getString("name"));
            cdp_edt_conductor_email.setText(shopper_active.getJSONObject("person").getString("email"));
            cdp_edt_conductor_document.setText(shopper_active.getJSONObject("person").getString("document"));
            cdp_edt_conductor_tel_moblie.setText(shopper_active.getJSONObject("person").getString("cellphone"));
            cdp_edt_conductor_tel_fixo.setText(shopper_active.getJSONObject("person").getString("phone"));
            cdp_edt_conductor_address.setText(shopper_active.getJSONObject("person").getString("address"));
            cdp_edt_conductor_zipcode.setText(shopper_active.getJSONObject("person").getString("zip_code"));








            System.out.println("");

        }catch (Exception e ){
            e.printStackTrace();


        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.driver_perfil_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:  //ID do seu botão (gerado automaticamente pelo android, usando como está, deve funcionar
                onBackPressed();
                finish();
                break;

            case R.id.close:

                AlertDialog alertDialog = new AlertDialog.Builder(self).create();
                alertDialog.setTitle(getString(R.string.msn_title));
                alertDialog.setMessage(getString(R.string.msn_finalizar_app));
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();

                                Libs.deslogar(self);
                                Intent intent = new Intent(self, MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();


                            }
                        });

                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancelar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();

                            }
                        });

                alertDialog.show();

                break;


            default:
                break;
        }
        return true;


    }















}
