package com.build.mobdev_nhom7;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.example.mobdev_nhom7.models.responseObj.comment.CommentItem;
import com.example.mobdev_nhom7.remote.APIService;
import com.example.mobdev_nhom7.remote.APIUtils;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class AddFavoriteHotelTest {
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

        assertEquals("{}", response.body());
    }
}
