package com.example.mobdev_nhom7.remote;

import com.example.mobdev_nhom7.models.responseObj.DefaultResponseData;
import com.example.mobdev_nhom7.models.responseObj.cityName.CityNameResponseData;
import com.example.mobdev_nhom7.models.responseObj.hotelName.HotelNameResponseData;
import com.example.mobdev_nhom7.models.responseObj.reservation.BookingResponseObj;
import com.example.mobdev_nhom7.models.responseObj.reservation.CancelledReservation;
import com.example.mobdev_nhom7.models.responseObj.reservation.HistoryResponseObj;
import com.example.mobdev_nhom7.models.responseObj.search.SearchHotelResponseData;
import com.example.mobdev_nhom7.models.responseObj.user.UserResponseObj;

import org.checkerframework.checker.signature.qual.FieldDescriptor;

import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {
    @GET("user/history?limit=50")
    @FormUrlEncoded
    Call<HistoryResponseObj> getHistory(@Query("id") String userID);
    @DELETE("user/history?limit=50")

    @FormUrlEncoded
    Call<HistoryResponseObj> deleteHistory(@Query("id") String userID);

    @GET("user/booking?limit=50")
    @FormUrlEncoded
    Call<BookingResponseObj> getBooking(@Query("id") String userID);

    @POST("user/booking?limit=50")
    @FormUrlEncoded
    Call<BookingResponseObj> deleteBooking(@Query("id") String userID);

    @GET("user/cancelled")
    @FormUrlEncoded
    Call<CancelledReservation> getCancelledBooking(@Query("id") String userID);

    @PUT("user/cancelled")
    @FormUrlEncoded
    Call<CancelledReservation> deleteCancelledBooking(@Query("id") String userID);

    @POST("auth/")
    @FormUrlEncoded
    Call<HistoryResponseObj> register(@Field("username") String username,
                                    @Field("firstname") String firstname,
                                    @Field("lastname") String lastname,
                                    @Field("email") String email,
                                    @Field("password") String password);

    @GET("user/profile")
    @FormUrlEncoded
    Call<UserResponseObj> fetchUserInfo(@Header("Authorization") String token);

    @GET("get/hotel/by/destination")
    @FormUrlEncoded
    Call<SearchHotelResponseData> searchHotels(@Field("hotel_id") String hotel_id,
                                               @Field("city_id") String city_id,
                                               @Field("start_date") String start_date,
                                               @Field("end_date") String end_date,
                                               @Field("number_of_ppl") String numberPpl,
                                               @Field("number_of_room") String numberRoom);

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

    @GET("get/city/all")
    @FormUrlEncoded
    Call<CityNameResponseData> getAllCityName();

    @GET("get/hotel/all")
    @FormUrlEncoded
    Call<HotelNameResponseData> getAllHotelName();

    @POST("/user/{id}")
    Call<DefaultResponseData> createUser(@Path("id") String id);
}