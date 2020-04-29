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
import android.widget.TextView;

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

public class DisplayStores extends BaseActivity {
    private List<Settings> supermarkets_active;
    private RecyclerView act_conct_router_list;
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
        setContentView(R.layout.activity_display_stores);
        self = this;
        settingsModel = new SettingsModel(self);
        card_view_main = findViewById(R.id.card_view_main);
        br_status = findViewById(R.id.br_status);
        card_view_mensagem = (CardView) findViewById(R.id.card_view_mensagem);
        card_view_mensagem_conteudo = (TextView) findViewById(R.id.card_view_mensagem_conteudo);
        act_conct_router_list = (RecyclerView) findViewById(R.id.act_conct_router_list);
        act_conct_router_list.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(self);
        act_conct_router_list.setLayoutManager(layoutManager);
        Libs.seek_configurations(self,ConstantesDbHelper.SETTINGS_DESCRIPTION_VEHICLES_SELECTED_CONDUCTOR_ACTIVE," ",false);
        setting = (List<Settings>) settingsModel.find(ConstantesDbHelper.SETTINGS_ACESS_API_LOGIN.toUpperCase());
        JWT = Libs.jsonToObjectJwt(setting.get(0).getMetadados());
        Intent intent = getIntent();

        events = new OnClickedAndInteractingWithEventsBasic() {
            @Override
            public void onClick(String type, String metadados) {

                //E20
                if (type.equals(Constantes.action_supermarkets_selected)) {
                    if (supermarkets_active.size() != 0) {
                        supermarkets_active.get(0).setMetadados(metadados);
                        settingsModel.update(supermarkets_active.get(0));
                        startActivity(new Intent(self, DisplaysOrders.class));
                    }
                }


            }
        };

        clear_supermarket_settings();
        set_barra_conteudo(true, false, false, "");
        load_stores_to_shoppe();



    }

    private void clear_supermarket_settings() {
        supermarkets_active = (List<Settings>) settingsModel.find(ConstantesDbHelper.SETTINGS_DESCRIPTION_IS_LOGIN_ACTIVE_SUPERMARKS_SELECT.toUpperCase());
        if (supermarkets_active.size() != 0) {
            supermarkets_active.get(0).setMetadados("");
            settingsModel.update(supermarkets_active.get(0));

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.driver_search_menu_login, menu);
        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        AlertDialog alertDialog = new AlertDialog.Builder(self).create();

        switch (item.getItemId()) {

            case R.id.end_application:
                alertDialog.setTitle(getString(R.string.msn_title));
                alertDialog.setMessage(getString(R.string.msn_finalizar_logout_app));
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Libs.seek_configurations(self,ConstantesDbHelper.SETTINGS_DESCRIPTION_VEHICLES_SELECTED_CONDUCTOR_ACTIVE," ",false);
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

    private void load_stores_to_shoppe() {


        new DriversEndPointActions().api().shopper_supermarket_chain_associated(JWT[0]).
                enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                JSONObject list_stores_shopper = new JSONObject(response.body().string());
                                //recupera o array "elenco"
                                JSONObject dados = new JSONObject(list_stores_shopper.getJSONArray("data").get(0).toString());
                                String shopper = dados.getJSONObject("shopper").toString();
                                Libs.seek_configurations(self,ConstantesDbHelper.SETTINGS_DESCRIPTION_IS_LOGIN_ACTIVE,"true",false);
                                Libs.seek_configurations(self,ConstantesDbHelper.SETTINGS_DESCRIPTION_VEHICLES_SELECTED_CONDUCTOR_ACTIVE,shopper,false);
                                stores_shopper.add(list_stores_shopper.getJSONArray("data"));
                                AdapterConductorAssociatedShopper = new AdapterAssociated(stores_shopper, events);
                                act_conct_router_list.setAdapter(AdapterConductorAssociatedShopper);
                                set_barra_conteudo(false, true, false, "");

                            } catch (Exception e) {
                                e.printStackTrace();
                                set_barra_conteudo(false, false, true, e.getMessage());

                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });


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


    public class AdapterAssociated extends RecyclerView.Adapter<AdapterAssociated.Shopper> {
        private List<Object> objects;
        private OnClickedAndInteractingWithEventsBasic mOnClickedAndInteractingWithEvents;

        public AdapterAssociated(List<Object> objects, OnClickedAndInteractingWithEventsBasic listener) {
            this.objects = objects;
            mOnClickedAndInteractingWithEvents = listener;
        }

        @NonNull
        @Override
        public Shopper onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_shopper_lines, parent, false);
            return new Shopper(v);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onBindViewHolder(@NonNull Shopper holder, int position) {

            try {

                JSONArray object = new JSONArray(this.objects.get(position).toString());
                final String json_object = this.objects.get(position).toString();
                JSONObject object1 = new JSONObject(object.get(0).toString());

                holder.act_conct_router_dest_txt_uuid.setText(object1.getString("id"));
                holder.act_conct_router_dest_txt_name.setText(object1.getJSONObject("supermarket_chain").getJSONObject("person").getString("name"));
                holder.act_conct_router_dest_txt_name_distance.setText(object1.getJSONObject("supermarket_chain").getJSONObject("person").getString("name"));

                holder.act_conct_router_dest_txt_email.setText(object1.getJSONObject("supermarket_chain").getJSONObject("person").getString("email"));
                holder.act_conct_router_dest_txt_contatos.setText(
                        String.format("%s/%s", object1.getJSONObject("supermarket_chain").getJSONObject("person").getString("cellphone"),
                                object1.getJSONObject("supermarket_chain").getJSONObject("person").getString("phone")));
                holder.act_conct_router_dest_txt_endereco.setText(object1.getJSONObject("supermarket_chain").getJSONObject("person").getString("full_address"));


                holder.act_btn_shopper_orders.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnClickedAndInteractingWithEvents.onClick(Constantes.action_supermarkets_selected, json_object);
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

            private TextView act_conct_router_dest_txt_uuid, act_conct_router_dest_txt_name, act_conct_router_dest_txt_name_distance,
                    act_conct_router_dest_txt_email, act_conct_router_dest_txt_contatos, act_conct_router_dest_txt_endereco;

            private Button act_btn_shopper_orders;


            public Shopper(View itemView) {
                super(itemView);

                act_conct_router_dest_txt_uuid = (TextView) itemView.findViewById(R.id.act_conct_router_dest_txt_uuid);
                act_conct_router_dest_txt_name = (TextView) itemView.findViewById(R.id.act_conct_router_dest_txt_name);
                act_conct_router_dest_txt_name_distance = (TextView) itemView.findViewById(R.id.act_conct_router_dest_txt_name_distance);
                act_conct_router_dest_txt_email = (TextView) itemView.findViewById(R.id.act_conct_router_dest_txt_email);
                act_conct_router_dest_txt_contatos = (TextView) itemView.findViewById(R.id.act_conct_router_dest_txt_contatos);
                act_conct_router_dest_txt_endereco = (TextView) itemView.findViewById(R.id.act_conct_router_dest_txt_endereco);
                act_btn_shopper_orders = (Button) itemView.findViewById(R.id.act_btn_shopper_orders);


            }
        }

    }
}
