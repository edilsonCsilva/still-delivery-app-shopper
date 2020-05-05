package com.stilldeliverys.shopper.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.stilldeliverys.shopper.R;
import com.stilldeliverys.shopper.api.DriversEndPointActions;
import com.stilldeliverys.shopper.core.BaseActivity;
import com.stilldeliverys.shopper.core.ConstantesDbHelper;
import com.stilldeliverys.shopper.core.Libs;
import com.stilldeliverys.shopper.db.Settings;
import com.stilldeliverys.shopper.db.SettingsModel;
import com.stilldeliverys.shopper.listener.OnClickedAndInteractingWithEventsBasic;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisplaysTheOrderProducts extends BaseActivity {
    private List<Settings> supermarkets_active;
    private RecyclerView act_conct_shopper_order_list;
    private RecyclerView.Adapter AdapterConductorAssociatedShopper;
    private RecyclerView.LayoutManager layoutManager;
    private OnClickedAndInteractingWithEventsBasic events;
    private List<Settings> setting = null;
    private List<Object> stores_shopper_productors = new ArrayList<Object>();
    private CardView card_view_main;
    private ProgressBar br_status;
    private TextView card_view_mensagem_conteudo;
    private CardView card_view_mensagem;
    private  List<Settings>  order_selected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displays_the_order_products);
        self = this;
        settingsModel = new SettingsModel(self);
        br_status = findViewById(R.id.br_status);
        card_view_mensagem = (CardView) findViewById(R.id.card_view_mensagem);
        card_view_mensagem_conteudo = (TextView) findViewById(R.id.card_view_mensagem_conteudo);
        card_view_main = (CardView) findViewById(R.id.card_view_main);
        act_conct_shopper_order_list = (RecyclerView) findViewById(R.id.act_conct_router_list);
        act_conct_shopper_order_list.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(self);
        act_conct_shopper_order_list.setLayoutManager(layoutManager);


        setting = (List<Settings>) settingsModel.find(ConstantesDbHelper.SETTINGS_ACESS_API_LOGIN.toUpperCase());
        JWT = Libs.jsonToObjectJwt(setting.get(0).getMetadados());
        supermarkets_active = (List<Settings>) settingsModel.find(ConstantesDbHelper.SETTINGS_DESCRIPTION_IS_LOGIN_ACTIVE_SUPERMARKS_SELECT.toUpperCase());
        set_barra_conteudo(true, false, false, "");
        order_selected = Libs.seek_configurations(self, ConstantesDbHelper.SETTINGS_IS_ORDER_ACTIVE_MANTER,"", false);
        Libs.seek_configurations(self,ConstantesDbHelper.SETTINGS_IS_PAGER_ACTIVE_MANTER,this.getClass().getName(),false);

        events = new OnClickedAndInteractingWithEventsBasic() {
            @Override
            public void onClick(String type, String metadados) {
                Toast.makeText(self, "", Toast.LENGTH_SHORT).show();


            }
        };

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.driver_search_menu_shopper, menu);

        //Pega o Componente.
        SearchView mSearchView = (SearchView) menu.findItem(R.id.search).getActionView();
        //Define um texto de ajuda:
        mSearchView.setQueryHint(getString(R.string.str_pesquisar));
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //callSearch(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        AlertDialog alertDialog = new AlertDialog.Builder(self).create();

        switch (item.getItemId()) {

            case R.id.end_application_logout_car:
                Intent settings_shopper = new Intent(self, ShopperSettings.class);
                startActivity(settings_shopper);
                return true;
            case R.id.perfil:
                startActivity(new Intent(self, ShopperSettings.class));
                return true;


            case R.id.end_application_logout_supermerkts:
                onBackPressed();
                return true;


            case R.id.end_application_logout:
                alertDialog.setTitle(getString(R.string.msn_title));
                alertDialog.setMessage(getString(R.string.msn_finalizar_logout_app));
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Libs.seek_configurations(self, ConstantesDbHelper.SETTINGS_DESCRIPTION_VEHICLES_SELECTED_CONDUCTOR_ACTIVE, " ", false);
                                Libs.deslogar(self);
                                Intent intent = new Intent(self, DisplayStores.class);
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


                return true;

            case R.id.end_application:


                alertDialog.setTitle(getString(R.string.msn_title));
                alertDialog.setMessage(getString(R.string.msn_finalizar_app));
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Libs.seek_configurations(self, ConstantesDbHelper.SETTINGS_DESCRIPTION_VEHICLES_SELECTED_CONDUCTOR_ACTIVE, " ", false);
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


                return true;
            default:
        }


        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onResume() {
        list_supermarket_orders_productos();
        super.onResume();

    }

    private void list_supermarket_orders_productos() {

        try{

            JSONObject json_to_obj = new JSONObject(order_selected.get(0).getMetadados());
            String order_uuid = json_to_obj.getString("order_uuid");

            //busca a lista de produtos

            new DriversEndPointActions().api().order_show(JWT[0],order_uuid).
                    enqueue(new Callback<ResponseBody>() {
                        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                            if(response.isSuccessful()){
                                try {


                                    JSONArray orders_products =(new JSONObject(response.body().string())).getJSONArray("order_products");
                                    int total=orders_products.length();
                                    for(int i=0;i < total;i++){
                                        try{

                                            System.out.println("4### - "+orders_products.getJSONObject(i).getString("product_id"));

                                            System.out.println("4### - "+orders_products.getJSONObject(i).getJSONObject("product").getString("name"));




                                        }catch (Exception e){}
                                    }




/*

                                    ("order_products"));*/





                                    System.out.println("");

                                }catch (Exception e){
                                    e.printStackTrace();
                                    set_barra_conteudo(false,false,true,e.getMessage());
                                }

                            }
                            
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });



            //busca a lista de produtos
            System.out.println("");

        }catch (Exception e){ set_barra_conteudo(false,false,true,e.getMessage());}

    }


    @Override
    public void onBackPressed() {
        AlertDialog alertDialog = new AlertDialog.Builder(self).create();
        alertDialog.setTitle(getString(R.string.msn_title));
        alertDialog.setMessage(getString(R.string.str_deslo_supermarkts));
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Libs.seek_configurations(self, ConstantesDbHelper.SETTINGS_DESCRIPTION_VEHICLES_SELECTED_CONDUCTOR_ACTIVE, " ", false);
                        Libs.deslogar(self);
                        Intent intent = new Intent(self, DisplayStores.class);
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


    }

    private void set_barra_conteudo(boolean barra, boolean conteudo, boolean messagens, String messagens_text) {
        if (barra) {
            br_status.setVisibility(View.VISIBLE);
            br_status.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
        } else {
            br_status.setVisibility(View.GONE);
            br_status.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
        }

        if (conteudo) {
            card_view_main.setVisibility(View.VISIBLE);
        } else {
            card_view_main.setVisibility(View.GONE);
        }


        if (!barra && !conteudo && messagens) {
            card_view_mensagem.setVisibility(View.VISIBLE);
            card_view_mensagem_conteudo.setText(messagens_text);

        } else {
            card_view_mensagem.setVisibility(View.GONE);
            card_view_mensagem_conteudo.setText("");
        }
    }


}
