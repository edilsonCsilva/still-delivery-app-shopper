package com.stilldeliverys.shopper.api;


import com.stilldeliverys.shopper.core.Constantes;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class DriversEndPointActions {


    public static  String BASE_URL = Constantes.base;

    /* public static final String BASE_URL = Constantes.base_production;*/

    public static final String HEADER = "X-APPLICATION-TOKEN:";
    public static final String API_PATH_PRIVATE_INSTALL = "d217d774c31527d61ebb3a4890421ab6a31b66d8";

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


    ////////////////////////////////////
    /////////////////////////////////////
    //todas as Funçõe de acessos
    ////////////////////////////////////
    /////////////////////////////////////


    public DriversEndPoint api() {
        return getClient().create(DriversEndPoint.class);
    }

    public DriversEndPoint oauth_token() {
        return getClient().create(DriversEndPoint.class);
    }


    public DriversEndPoint conductor_supermarket_chain_show() {
        return getClient().create(DriversEndPoint.class);
    }


    public DriversEndPoint conductor_show() {
        return getClient().create(DriversEndPoint.class);
    }

    public DriversEndPoint conductor_update() {
        return getClient().create(DriversEndPoint.class);
    }


    public DriversEndPoint conductor_vehicle_event_show() {
        return getClient().create(DriversEndPoint.class);
    }


    public DriversEndPoint conductor_supermarket_chain_associated() {
        return getClient().create(DriversEndPoint.class);
    }

    public DriversEndPoint conductor_vehicle_event_associated() {
        return getClient().create(DriversEndPoint.class);
    }


    public DriversEndPoint conductor_route_associated() {
        return getClient().create(DriversEndPoint.class);
    }


    public DriversEndPoint route_destination_all() {
        return getClient().create(DriversEndPoint.class);
    }

    public DriversEndPoint order_box_quantity_add() {
        return getClient().create(DriversEndPoint.class);
    }

    public DriversEndPoint order_box_quantity_all() {
        return getClient().create(DriversEndPoint.class);
    }


    public DriversEndPoint order_show() {
        return getClient().create(DriversEndPoint.class);
    }

    public DriversEndPoint route_event_add(){
        return getClient().create(DriversEndPoint.class);
    }


    public DriversEndPoint route_destination_update(){
        return  getClient().create(DriversEndPoint.class);
    }

    public DriversEndPoint route_destination_event_add(){
        return  getClient().create(DriversEndPoint.class);
    }


    public DriversEndPoint route_destination_event_all(){
        return getClient().create(DriversEndPoint.class);
    }


    public DriversEndPoint route_destination_event_show(){
        return getClient().create(DriversEndPoint.class);
    }


    public DriversEndPoint route_event_event_by_route(){
        return getClient().create(DriversEndPoint.class);
    }


    public  DriversEndPoint shopper_supermarket_chain_associated(){
        return getClient().create(DriversEndPoint.class);
    }


    public  DriversEndPoint shopper_order_associates(){
        return  getClient().create(DriversEndPoint.class);
    }

    public  DriversEndPoint shopper_order_product_add(){
        return  getClient().create(DriversEndPoint.class);
    }


    ////////////////////////////////////
    /////////////////////////////////////
    //todas as Funçõe de acessos
    ////////////////////////////////////
    /////////////////////////////////////
}
