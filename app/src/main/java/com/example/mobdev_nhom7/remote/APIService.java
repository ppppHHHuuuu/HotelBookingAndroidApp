package com.example.mobdev_nhom7.remote;

import com.example.mobdev_nhom7.models.responseObj.hotel.HotelResponseObj;
import com.example.mobdev_nhom7.models.responseObj.reservation.BookingResponseObj;
import com.example.mobdev_nhom7.models.responseObj.reservation.CancelledReservation;
import com.example.mobdev_nhom7.models.responseObj.reservation.HistoryResponseObj;
import com.example.mobdev_nhom7.models.responseObj.search.SearchHotelItem;
import com.example.mobdev_nhom7.models.responseObj.search.SearchHotelResponseData;
import com.example.mobdev_nhom7.models.responseObj.user.UserResponseObj;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
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

    @POST("user/cancelled")
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

    @GET("hotels/api?limit=10")
    Call<SearchHotelResponseData> searchHotels(@Query("destination") String destination,
                                               @Query("date") String date,
                                               @Query("number_of_ppl") String numberPpl);

    @GET("hotel/detail")
    Call<SearchHotelResponseData> searchHotelDetail(@Query("id") String id);

    @POST("booking")
    Call<Object> booking(@Field("user_id") String user_id,
                         @Field("hotel_id") String hotel_id,
                         @Field("room_1_number") String room_1_number,
                         @Field("room_2_number") String room_2_number,
                         @Field("start_date") String start_date,
                         @Field("end_number") String end_number);
}