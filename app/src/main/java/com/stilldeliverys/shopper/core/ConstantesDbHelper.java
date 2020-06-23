package com.stilldeliverys.shopper.core;

public class ConstantesDbHelper {

    public static String BASE_DRIVES = "deliverys_shopper.db";
    public static int BASE_DRIVES_VERSION = 1;

    public static String OBJECT_ID = "auto_obj_id";
    public static String OBJECT_METADADOS = "metadados";


    public static String REQUESTS = "requests";
    public static String ORDERDELIVERED = "ordersdelivered";


    //Tabela de Configuraçoes Gerais do Sistema

    public static String SETTINGS = "settings";
    public static String SETTINGS_CODE = "ObjId";
    public static String SETTINGS_DESCRIPTION = "descricao";
    public static String SETTINGS_DESCRIPTION_VEHICLES_SELECTED = "vehicles_active";
    public static String SETTINGS_DESCRIPTION_VEHICLES_SELECTED_CODE = "vehicles_active_code";
    public static String SETTINGS_DESCRIPTION_VEHICLES_SELECTED_CONDUCTOR_ACTIVE = "conductor_active";

    public static String SUPERMARKET_TABLE = "supermarket";
    public static String SUPERMARKET_TABLE_CODE = "ObjId";
    public static String SUPERMARKET_TABLE_CODE_UUID = "ObjIduuId";
    public static String SUPERMARKET_TABLE_METADATA = "data";


    //Rotas

    public static String ROUTER_ITENS_TABLE = "route";
    public static String ROUTER_ITENS_CODE = "ObjId";
    public static String ROUTER_ITENS_CODE_UUID = "uuid";
    public static String ROUTER_ITENS_CODE_ROUTER = "router_id";
    public static String ROUTER_ITENS_CODE_FK_SUPERMARKET = "fk_supermarket";
    public static String ROUTER_ITENS_METADADOS = "data";

    public static String SETTINGS_IS_LOGIN_ACTIVE_MANTER = "is_login_active_manter_acess";
    public static String SETTINGS_IS_ORDER_ACTIVE_MANTER = "is_order_active_manter";
    public static String SETTINGS_IS_PAGER_ACTIVE_MANTER = "is_pager_active_manter";


    public static String SETTINGS_DESCRIPTION_IS_LOGIN_ACTIVE = "is_login_active";
    public static String SETTINGS_DESCRIPTION_IS_LOGIN_ACTIVE_TYPE = "is_login_active_type";
    public static String SETTINGS_DESCRIPTION_IS_LOGIN_ACTIVE_SUPERMARKS_SELECT = "is_login_active_supermarkts";
    public static String SETTINGS_DESCRIPTION_IS_LOGIN_ACTIVE_CAR_SELECT = "is_login_active_car_selecionado";




    public static String SETTINGS_ACESS_API_LOGIN = "jwt";
    public static String SETTINGS_METADATA = "metadados";
    public static String SETTINGS_SERVER_API = "url_api";
    //Tabela de Configuraçoes Gerais do Sistema


    //Tabela de Veiculos

    public static String VEHICLES = "vehicles";
    public static String VEHICLES_CODE_AUTO = "ObjId";
    public static String VEHICLES_CODE = "vehicles_code";
    public static String VEHICLES_TYPE = "vehicles_type";
    public static String VEHICLES_NAME = "vehicles_name";
    public static String VEHICLES_NAME_TYPE = "vehicles_name_type";
    public static String VEHICLES_CAPACITY = "vehicles_capacity";


    //Tabela de Veiculos


    //Tabela de Itens de Pedidos
    public static String PEDIDO_ITENS = "pedido_itens";
    public static String PEDIDO_ITENS_CODE_AUTO = "pedido_itens_code_auto";
    public static String PEDIDO_ITENS_CODE = "pedido_itens_code";
    public static String PEDIDO_ITENS_CODE_PEDIDO = "pedido_itens_code_pedido";
    public static String PEDIDO_ITENS_NAME = "pedido_itens_name";
    public static String PEDIDO_ITENS_QUANT = "pedido_itens_quantidade";
    public static String PEDIDO_ITENS_VL_UNIT = "pedido_itens_preco_unit";

    //Tabela de Itens de Pedidos


    //tabela de Fotos dos Pedidos


    public static String FOTO_PEDIDO_ITENS = "img_pedido_itens";
    public static String FOTO_PEDIDO_ITENS_CODE_AUTO = "img_pedido_itens_code_auto";
    public static String FOTO_PEDIDO_ITENS_CODE = "img_pedido_itens_code";
    public static String FOTO_PEDIDO_ITENS_LATLONG = "img_pedido_itens_latlong";
    public static String FOTO_PEDIDO_ITENS_PED_CODE = "img_pedido_itens_pedido_code";
    public static String FOTO_PEDIDO_ITENS_IMG_PATH = "img_pedido_itens_path_img";
    public static String FOTO_PEDIDO_ITENS_SYNC = "img_pedido_itens_sync";

    //tabela de Fotos dos Pedidos


}
