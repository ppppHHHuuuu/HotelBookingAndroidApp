package com.example.mobdev_nhom7.remote;

import com.example.mobdev_nhom7.models.responseObj.cityName.CityNameResponseData;
import com.example.mobdev_nhom7.models.responseObj.hotelName.HotelNameResponseData;
import com.example.mobdev_nhom7.models.responseObj.reservation.BookingResponseObj;
import com.example.mobdev_nhom7.models.responseObj.reservation.CancelledReservation;
import com.example.mobdev_nhom7.models.responseObj.reservation.HistoryResponseObj;
import com.example.mobdev_nhom7.models.responseObj.search.SearchHotelResponseData;
import com.example.mobdev_nhom7.models.responseObj.user.UserResponseObj;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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
    Call<SearchHotelResponseData> searchHotels(@Query("hotel_id") String hotel_id,
                                               @Query("city") String city,
                                               @Query("start_date") String start_date,
                                               @Query("end_date") String end_date,
                                               @Query("room_quantity") Integer numberRoom,
                                               @Query("ppl_quantity") Integer numberPpl);

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

    @GET("city/suggested")
    Call<CityNameResponseData> getSuggestedCity();
    @GET("city")
    Call<CityNameResponseData> getAllCity();

}