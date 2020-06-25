package com.stilldeliverys.shopper.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.stilldeliverys.shopper.core.Constantes;
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

public class DisplaysOrders extends BaseActivity {
    private List<Settings> supermarkets_active;
    private RecyclerView act_conct_shopper_order_list;
    private RecyclerView.Adapter AdapterConductorAssociatedShopper;
    private RecyclerView.LayoutManager layoutManager;
    private OnClickedAndInteractingWithEventsBasic events;
    private List<Settings> setting = null;
    private List<Object> stores_shopper = new ArrayList<Object>();
    private CardView card_view_main;
    private ProgressBar br_status;
    private TextView card_view_mensagem_conteudo;
    private CardView card_view_mensagem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displays_orders);
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
        setting = Libs.seek_configurations(self, ConstantesDbHelper.SETTINGS_ACESS_API_LOGIN.toUpperCase(), "", false);
        JWT = Libs.jsonToObjectJwt(setting.get(0).getMetadados());
        supermarkets_active = (List<Settings>) settingsModel.find(ConstantesDbHelper.SETTINGS_DESCRIPTION_IS_LOGIN_ACTIVE_SUPERMARKS_SELECT.toUpperCase());
        set_barra_conteudo(true, false, false, "");
        Libs.seek_configurations(self,ConstantesDbHelper.SETTINGS_IS_ORDER_ACTIVE_MANTER," ",false);
        Libs.seek_configurations(self,ConstantesDbHelper.SETTINGS_IS_PAGER_ACTIVE_MANTER,this.getClass().getName(),false);

        events = new OnClickedAndInteractingWithEventsBasic() {
            @Override
            public void onClick(String type, String metadados) {
                Toast.makeText(self, "", Toast.LENGTH_SHORT).show();
                Libs.seek_configurations(self,ConstantesDbHelper.SETTINGS_IS_ORDER_ACTIVE_MANTER,metadados,false);
                startActivity(new Intent(self, DisplaysTheOrderProducts.class));
           }
        };


    }

    private void list_supermarket_orders() {
        stores_shopper.clear();
        act_conct_shopper_order_list.removeAllViews();

        try {




            String supermarket_chain_id = (new JSONArray(supermarkets_active.get(0).getMetadados())).getJSONObject(0).getString("supermarket_chain_id");
            //Validação do Supermercado selecionado
            //Validação do Supermercado selecionado
            new DriversEndPointActions().api().shopper_order_associates(JWT[0], supermarket_chain_id).
                    enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            try {
                                if (response.isSuccessful()) {
                                    JSONArray requests_to_shopper = (new JSONObject(response.body().string())).getJSONArray("data");
                                    int i=0;
                                    while (i < requests_to_shopper.length()){
                                         try{
                                             JSONObject rows_order = new JSONObject(requests_to_shopper.get(i).toString());
                                             stores_shopper.add(rows_order);
                                         }catch (Exception e){e.printStackTrace();}
                                        i++;
                                    }
                                    if(stores_shopper.size()!=0){
                                        AdapterConductorAssociatedShopper = new AdapterAssociatedShopperOrder(stores_shopper, events);
                                        act_conct_shopper_order_list.setAdapter(AdapterConductorAssociatedShopper);
                                        Toast.makeText(self, "", Toast.LENGTH_SHORT).show();
                                        set_barra_conteudo(false, true, false, "");
                                    }else{
                                        set_barra_conteudo(false, false, true, getString(R.string.str_not_order_shopper_select));
                                    }
                                    System.out.println("");
                                } else {
                                    set_barra_conteudo(false,false,true,getString(R.string.str_error_response_api));
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                                set_barra_conteudo(false,false,true,e.getMessage());
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            t.printStackTrace();
                            set_barra_conteudo(false,false,true,t.getMessage());
                        }
                    });

        } catch (Exception e) {
            set_barra_conteudo(false, false, true, e.getMessage());
        }
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
        list_supermarket_orders();
        super.onResume();

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


    public class AdapterAssociatedShopperOrder extends RecyclerView.Adapter<AdapterAssociatedShopperOrder.Shopper> {
        private List<Object> objects;
        private OnClickedAndInteractingWithEventsBasic mOnClickedAndInteractingWithEvents;

        public AdapterAssociatedShopperOrder(List<Object> objects, OnClickedAndInteractingWithEventsBasic listener) {
            this.objects = objects;
            mOnClickedAndInteractingWithEvents = listener;
        }

        @NonNull
        @Override
        public AdapterAssociatedShopperOrder.Shopper onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_shopper_orders_lines, parent, false);
            return new AdapterAssociatedShopperOrder.Shopper(v);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onBindViewHolder(@NonNull AdapterAssociatedShopperOrder.Shopper holder, int position) {

            try {
                JSONObject object = new JSONObject(this.objects.get(position).toString());
                final String json_object = this.objects.get(position).toString();


                String adrress=String.format("%s , %s , %s - %s",object.getString("address"),object.getString("zip_code"),
                        object.getString("city"),object.getString("state"));


                String contatos=String.format("%s / %s",object.getString("phone"),object.getString("cellphone"));

                holder.act_conct_router_dest_txt_uuid.setText(object.getString("id"));
                holder.act_conct_router_dest_txt_total.setText(object.getString("total"));
                holder.act_conct_router_dest_txt_name.setText(object.getString("freight_price"));

                holder.act_conct_router_dest_txt_name_distance.setText(object.getString("name"));
                holder.act_conct_router_dest_txt_email.setText(object.getString("email"));
                holder.act_conct_router_dest_txt_contatos.setText(contatos);
                holder.act_conct_router_dest_txt_endereco_cep.setText(object.getString("zip_code"));
                holder.act_conct_router_dest_txt_endereco.setText(adrress);


                holder.act_btn_shopper_orders.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnClickedAndInteractingWithEvents.onClick(Constantes.action_supermarkets_order_selected_, json_object);
                    }
                });


            } catch (Exception e) {
                e.printStackTrace();
            }


        }

        @Override
        public int getItemCount() {
            return objects.size();
        }

        public class Shopper extends RecyclerView.ViewHolder {

            private TextView act_conct_router_dest_txt_uuid,act_conct_router_dest_txt_total, act_conct_router_dest_txt_name,
                    act_conct_router_dest_txt_name_distance,act_conct_router_dest_txt_email, act_conct_router_dest_txt_contatos,
                    act_conct_router_dest_txt_endereco_cep,act_conct_router_dest_txt_endereco;

            private Button act_btn_shopper_orders;


            public Shopper(View itemView) {
                super(itemView);

                act_conct_router_dest_txt_total=(TextView)itemView.findViewById(R.id.act_conct_router_dest_txt_total);
                act_conct_router_dest_txt_uuid = (TextView) itemView.findViewById(R.id.act_conct_router_dest_txt_uuid);
                act_conct_router_dest_txt_name = (TextView) itemView.findViewById(R.id.act_conct_router_dest_txt_name);
                act_conct_router_dest_txt_name_distance = (TextView) itemView.findViewById(R.id.act_conct_router_dest_txt_name_distance);
                act_conct_router_dest_txt_email = (TextView) itemView.findViewById(R.id.act_conct_router_dest_txt_email);
                act_conct_router_dest_txt_contatos = (TextView) itemView.findViewById(R.id.act_conct_router_dest_txt_contatos);
                act_conct_router_dest_txt_endereco = (TextView) itemView.findViewById(R.id.act_conct_router_dest_txt_endereco);
                act_btn_shopper_orders = (Button) itemView.findViewById(R.id.act_btn_shopper_orders);
                act_conct_router_dest_txt_endereco_cep=(TextView)itemView.findViewById(R.id.act_conct_router_dest_txt_endereco_cep);



            }
        }

    }


}
