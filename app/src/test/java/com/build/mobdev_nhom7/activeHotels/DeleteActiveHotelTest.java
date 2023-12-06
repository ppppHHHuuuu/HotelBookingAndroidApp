package com.build.mobdev_nhom7.activeHotels;

import static org.junit.Assert.assertEquals;

import com.example.mobdev_nhom7.models.responseObj.DefaultResponseObj;
import com.example.mobdev_nhom7.remote.APIService;
import com.example.mobdev_nhom7.remote.APIUtils;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class DeleteActiveHotelTest {
    APIService apiService;
    Call<DefaultResponseObj> putActiveHotel;
    Response<DefaultResponseObj> response;

    @BeforeAll
    public void setup() throws IOException {
        apiService = APIUtils.getTestService();
        putActiveHotel = apiService.cancelUserComment("3LQ2wJJMwKAlfdnaVC9v");
        response = putActiveHotel.execute();
    }
    @Test
    public void testDeleteFavoriteHotel() throws IOException {
        setup();
        assertEquals(200, response.code());
        assertEquals(true, response.isSuccessful());
    }
}
