package com.stilldeliverys.shopper.core;

import java.util.ArrayList;
import java.util.List;

public class Constantes {

    public static String vehicle_origen = "vehicle";
    public static String qrcode_origen = "qr_code";
    public static List<String> transit_orders = new ArrayList<>();
    public static String order_details = "order_details";
    public static String check_out = "check_out";
    public static String check_photos = "photos";
    //public static String base = "http://192.168.50.104:8000";
   // public static String base = "http://200.107.117.6:26011";

    public static String base = "";


    public static String base_develop = base;
    public static String base_grant_type = "password";
    public static String base_client_id = "2";
    public static String base_client_secret ="L8TmdNcdI2g6e8N36Oc3Fm4e91O0jOmBHiFNLbMv"; // "iLlBp5sAlQC1B7pofJE2Z2Y2TG6kGziF1zb2JoOP";



    public static String view_destination = "view_destinations";
    public static String view_routers_destination = "view_routers_destination";
    public static String action_recused = "action_recused";
    public static String action_aception = "action_aception";
    public static String action_start_without_qr_code = "action_start_without_qr_code";
    public static String action_loads = "action_loads";
    public static String action_supermarkets_selected = "action_supermarkets_selected";


    public static String action_load_details = "action_load_details";
    //'not_started', 'started', 'paused', 'finished', 'canceled'


    public static String [] operation_status = new String[]{"Não Inicializado","Inicializado","Pausado","Finalizado","Cancelado"};
    //['transit', 'delivered', 'refused', 'not_found', 'pending'])->default('pending')
    public static String [] operation_status_router = new String[]{"Pendente","Em Transito","Entregue","Recusado","Não encontrado"};
    public static String conductor_edit="update_conductor";
    public static String initializes_script="initializes_script";

    public static String initializes_routes_separations="initializes_routes_separations";
    public static String initializes_routes_separations_detalis="initializes_routes_separations_detalis";



    public static String initializes_script_finalized="act_conct_router_btn_initializes_script_canceled";
    public static String initializes_script_canceled="initializes_script_canceled";
    public static String view_destination_history="view_destination_history";
    public static String menus_actions="menus_actions";
}
