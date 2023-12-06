package com.build.mobdev_nhom7.favouriteHotels;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.example.mobdev_nhom7.remote.APIService;
import com.example.mobdev_nhom7.remote.APIUtils;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class PostFavoriteHotelTest {
    APIService apiService;
    Call<String> addFavoriteHotel;
    Response<String> response;


    @BeforeAll
    public void setup() throws IOException {
        apiService = APIUtils.getTestService();
        addFavoriteHotel = apiService.addFavouriteHotel("1xdyOLnYOsUAMLjhKQUq2a6GAX13", "3LQ2wJJMwKAlfdnaVC9v");
        response = addFavoriteHotel.execute();
    }

    @Test
    public void testAddFavoriteHotel() throws IOException {
        setup();

        assertEquals(200, response.code());

        assertEquals(true, response.isSuccessful());

        assertEquals("already exist", response.body());

    }
}
