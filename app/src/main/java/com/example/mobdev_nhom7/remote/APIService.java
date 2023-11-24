package com.example.mobdev_nhom7.remote;

import com.example.mobdev_nhom7.models.responseObj.DefaultResponseData;
import com.example.mobdev_nhom7.models.responseObj.cityName.CityItemResponseData;
import com.example.mobdev_nhom7.models.responseObj.hotelName.HotelNameResponseData;
import com.example.mobdev_nhom7.models.responseObj.reservation.BookingResponseObj;
import com.example.mobdev_nhom7.models.responseObj.reservation.CancelledReservation;
import com.example.mobdev_nhom7.models.hotel.HotelItem;
import com.example.mobdev_nhom7.models.responseObj.cityName.CityItem;
import com.example.mobdev_nhom7.models.responseObj.cityName.CityItemResponseData;
import com.example.mobdev_nhom7.models.responseObj.places.PlaceItem;
import com.example.mobdev_nhom7.models.responseObj.places.PlaceItemResponseData;
import com.example.mobdev_nhom7.models.responseObj.reservation.HistoryResponseObj;
import com.example.mobdev_nhom7.models.responseObj.search.SearchHotelItem;
import com.example.mobdev_nhom7.models.responseObj.search.SearchHotelResponseData;
import com.example.mobdev_nhom7.models.responseObj.trips.ActiveHotelItem;
import com.example.mobdev_nhom7.models.responseObj.trips.CancelledHotelItem;
import com.example.mobdev_nhom7.models.responseObj.trips.HistoryHotelItem;
import com.example.mobdev_nhom7.models.responseObj.trips.PastHotelItem;

import org.checkerframework.checker.signature.qual.FieldDescriptor;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
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
    @GET("place/getAutoComplete")
    Call<List<PlaceItem> > getPlaceByWord();
    @GET("/cities/find")
    Call<List<CityItem>> getAllCity();
    @GET("hotel")
    Call<List<HotelItem>> getAllHotel();
    @GET("reservation/history/{user_id}")
    Call<List<HistoryHotelItem>> getHistoryReservation(@Path("user_id") String user_id);

    @GET("get/hotel/all")
    @FormUrlEncoded
    Call<HotelNameResponseData> getAllHotelName();

    @POST("/user/{id}")
    Call<DefaultResponseData> createUser(@Path("id") String id);
}