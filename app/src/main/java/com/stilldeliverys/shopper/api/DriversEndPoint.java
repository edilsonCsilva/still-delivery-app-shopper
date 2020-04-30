package com.stilldeliverys.shopper.api;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DriversEndPoint {

    @Multipart
    //@Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("/oauth/token")
    Call<ResponseBody> oauth_token(
            @Part("username") RequestBody username,
            @Part("password") RequestBody password,
            @Part("grant_type") RequestBody grant_type,
            @Part("client_id") RequestBody client_id,
            @Part("client_secret") RequestBody client_secret);


    //Domain
    //conductor Vehicle


    @GET("/api/v1/conductor-vehicle-event/associated")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<ResponseBody> conductor_vehicle_event_associated(
            @Header("Authorization") String authHeader
    );

    @GET(" /api/v1/conductor-vehicle-event/all")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<ResponseBody> conductor_vehicle_event_all(
            @Header("Authorization") String authHeader
    );




    @GET("/api/v1/conductor-supermarket-chain/associated")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<ResponseBody> conductor_supermarket_chain_associated(
            @Header("Authorization") String authHeader
    );


    @GET("/api/v1/conductor-supermarket-chain/show/{uuid}")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<ResponseBody> conductor_supermarket_chain_show(
            @Header("Authorization") String authHeader,
            @Path("uuid") String uuid
    );


    @GET("/api/v1/conductor/show/{uuid}")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<ResponseBody> conductor_show(
            @Header("Authorization") String authHeader,
            @Path("uuid") String uuid
    );


    @PUT("/api/v1/conductor/update/{personUuid}")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    Call<ResponseBody> conductor_update(
            @Header("Authorization") String authHeader,
            @Path("personUuid") String uuid,
            @Body String content
    );


    @GET("/api/v1/conductor-vehicle-event/show/{uuid}")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    Call<ResponseBody> conductor_vehicle_event_show(
            @Path("uuid") String uuid
    );
    @GET("/api/v1/conductor-route/associated")
    //@FormUrlEncoded
   // @HTTP(method = "GET", path = "/api/v1/conductor-route/associated", hasBody = true)
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<ResponseBody> conductor_route_associated(
            @Header("Authorization") String authHeader,
            @Query("supermarket_chain_id") String entity_id
    );
    //Domain
    //conductor Router
    @GET("/api/v1/route-destination/all")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<ResponseBody> route_destination_all(
            @Header("Authorization") String authHeader,
            @Query("route_id") String route_id
    );






   // /api/v1/order-box-quantity/add

    @POST("/api/v1/order-box-quantity/add")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    Call<ResponseBody> order_box_quantity_add(
            @Header("Authorization") String authHeader,
            @Body String content
    );

    ///api/v1/order-box-quantity/all
    @GET("/api/v1/order-box-quantity/all")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<ResponseBody> order_box_quantity_all(
            @Header("Authorization") String authHeader
    );

    //Domain
    //conductor Router


    //Domain
    //Pedidos

    ///api/v1/order/show/6b6c8b1c-4354-41b2-b0bb-3eb7b42e68c4
    @GET("/api/v1/order/show/{uuid}")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<ResponseBody> order_show(
            @Header("Authorization") String authHeader,
            @Path("uuid") String uuid
    );



    @POST("/api/v1/route-event/add")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    Call<ResponseBody> route_event_add(
            @Header("Authorization") String authHeader,
            @Body String content
    );


    @PUT("/api/v1/route-destination/update/{personUuid}")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    Call<ResponseBody> route_destination_update(
            @Header("Authorization") String authHeader,
            @Path("personUuid") String uuid,
            @Body String content
    );

    @POST("/api/v1/route-destination-event/add")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    Call<ResponseBody> route_destination_event_add(
            @Header("Authorization") String authHeader,
            @Body String content
    );



    @GET("/api/v1/route-destination-event/all")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<ResponseBody> route_destination_event_all(
            @Header("Authorization") String authHeader,
            @Query("route_destination_id") String route_destination_id,
            @Query("status") String status
    );


   // /api/v1/route-destination-event/show/293db913-be34-413e-9b7b-0d51d3f8f85a
   @GET("/api/v1/route-destination-event/show/{uuid}")
   @Headers({"Content-Type: application/json", "Accept: application/json"})
   Call<ResponseBody> route_destination_event_show(
           @Header("Authorization") String authHeader,
           @Path("uuid") String uuid
   );


   ///api/v1/route-event/all

    @GET("/api/v1/route-event/all")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<ResponseBody> route_event_all(
            @Header("Authorization") String authHeader
    );

    ///api/v1/route-event/event-by-route

    @GET("/api/v1/route-event/event-by-route")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<ResponseBody> route_event_event_by_route(
            @Header("Authorization") String authHeader,
            @Query("route_id") String route_id
    );



    ///api/v1/route-destination/all

    //{{urlApi}}/api/v1/shopper-supermarket-chain/associated
    @GET("/api/v1/shopper-supermarket-chain/associated")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<ResponseBody> shopper_supermarket_chain_associated(
            @Header("Authorization") String authHeader
    );



    //{{urlApi}}/api/v1/shopper-order/associates


    @GET("/api/v1/shopper-order/associates")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<ResponseBody> shopper_order_associates(
            @Header("Authorization") String authHeader,
            @Query("supermarket_chain_id") String supermarket_chain_id
    );





    //Domain
    //Pedidos
}
