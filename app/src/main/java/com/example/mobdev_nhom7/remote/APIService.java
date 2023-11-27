package com.example.mobdev_nhom7.remote;

import com.example.mobdev_nhom7.models.hotel.HotelItem;
import com.example.mobdev_nhom7.models.responseObj.DefaultResponseData;
import com.example.mobdev_nhom7.models.responseObj.cityDetail.Alert;
import com.example.mobdev_nhom7.models.responseObj.cityDetail.Restaurant;
import com.example.mobdev_nhom7.models.responseObj.cityDetail.Todo;
import com.example.mobdev_nhom7.models.responseObj.cityDetail.Transportation;
import com.example.mobdev_nhom7.models.responseObj.cityName.CityItem;
import com.example.mobdev_nhom7.models.responseObj.places.PlaceItem;
import com.example.mobdev_nhom7.models.responseObj.reservation.HistoryResponseObj;
import com.example.mobdev_nhom7.models.responseObj.search.SearchHotelItem;
import com.example.mobdev_nhom7.models.responseObj.search.SearchHotelResponseData;
import com.example.mobdev_nhom7.models.responseObj.trips.ActiveHotelItem;
import com.example.mobdev_nhom7.models.responseObj.trips.CancelledHotelItem;
import com.example.mobdev_nhom7.models.responseObj.trips.PastHotelItem;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {
    @POST("auth/")
    @FormUrlEncoded
    Call<HistoryResponseObj> register(@Field("username") String username,
                                    @Field("firstname") String firstname,
                                    @Field("lastname") String lastname,
                                    @Field("email") String email,
                                    @Field("password") String password);

    @GET("hotel/search?limit=10")
        Call<List<SearchHotelItem>> searchHotels(@Query("user_id") String user_id,
                                             @Query("hotel_id") String hotel_id,
                                             @Query("city") String city,
                                             @Query("start_date") String start_date,
                                             @Query("end_date") String end_date,
                                             @Query("room_quantity") String numberRoom,
                                             @Query("ppl_quantity") String numberPpl);

    @GET("hotel/detail")
    @FormUrlEncoded
    Call<SearchHotelResponseData> searchHotelDetail(@Query("id") String id);

    @POST("booking")
    @FormUrlEncoded
    Call<Object> booking(@Field("user_id") String user_id,
                         @Field("hotel_id") String hotel_id,
                         @Field("rooms") Map<String, Integer> room_number,
                         @Field("start_date") String start_date,
                         @Field("end_date") String end_date);

    @GET("cities/find")
    Call<List<CityItem>> getSuggestedCity();
    @GET("hotel/favourite")
    Call<List<SearchHotelItem>> getFavouriteHotel(@Query("user_id") String user_id);
    @GET("/cities/find")
    Call<List<CityItem>> getAllCity();
    @GET("hotel")
    Call<List<HotelItem>> getAllHotel();
    @GET("hotel/suggest")
    Call<List<SearchHotelItem>> getSuggestedHotel();
    @GET("reservation/active/{user_id}")
    Call<List<ActiveHotelItem>> getActiveReservation(@Path("user_id") String user_id);
    @GET("reservation/cancel/{user_id}")
    Call<List<CancelledHotelItem>> getCancelReservation(@Path("user_id") String user_id);
    @GET("reservation/rated/{user_id}")
    Call<List<PastHotelItem>> getRatedReservation(@Path("user_id") String user_id);
    @GET("reservation/notRated/{user_id}")
    Call<List<PastHotelItem>> getNotRatedReservation(@Path("user_id") String user_id);
    @POST("/user/{id}")
    Call<DefaultResponseData> createUser(@Path("id") String id);

    @GET("/cities/restaurant/{id}")
    Call<List<Restaurant>> getRestaurant(@Path("id") String id);

    @GET("/cities/transportation/{id}")
    Call<List<Transportation>> getTransportation(@Path("id") String id);

    @GET("/cities/todo/{id}")
    Call<List<Todo>> getTodo(@Path("id") String id);

    @GET("/cities/alert/{id}")
    Call<List<Alert>> getAlert(@Path("id") String id);
}