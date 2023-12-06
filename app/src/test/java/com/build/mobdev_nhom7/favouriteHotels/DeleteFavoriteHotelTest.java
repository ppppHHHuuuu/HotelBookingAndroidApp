package com.build.mobdev_nhom7.favouriteHotels;

import static org.junit.Assert.assertEquals;

import com.example.mobdev_nhom7.remote.APIService;
import com.example.mobdev_nhom7.remote.APIUtils;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class DeleteFavoriteHotelTest {
    APIService apiService;
    Call<String> deleteFavoriteHotel;
    Response<String> response;


    @BeforeAll
    public void setup() throws IOException {
        apiService = APIUtils.getTestService();
        deleteFavoriteHotel = apiService.deleteFavouriteHotel("1xdyOLnYOsUAMLjhKQUq2a6GAX13", "3LQ2wJJMwKAlfdnaVC9v");
        response = deleteFavoriteHotel.execute();
    }

    @Test
    public void testDeleteFavoriteHotel() throws IOException {
        setup();

        assertEquals(200, response.code());

        assertEquals(true, response.isSuccessful());

        assertEquals("success", response.body());
    }
}
