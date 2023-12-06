package com.build.mobdev_nhom7.favouriteHotels;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;

import com.example.mobdev_nhom7.models.responseObj.search.SearchHotelItem;
import com.example.mobdev_nhom7.remote.APIService;
import com.example.mobdev_nhom7.remote.APIUtils;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class GetFavouriteHotelsTest {
    APIService apiService;
    Call<List<SearchHotelItem>> getFavouriteHotelTest;
    Response<List<SearchHotelItem>> response;

    @BeforeAll
    public void setup() throws IOException {
        apiService = APIUtils.getTestService();
        getFavouriteHotelTest = apiService.getFavouriteHotel("1xdyOLnYOsUAMLjhKQUq2a6GAX13");
        response = getFavouriteHotelTest.execute();
    }
    @Test
    public void testGetAllHotel() throws IOException {
        setup();
        assertEquals(200, response.code());

        List<SearchHotelItem> result = response.body();
        assertNotEquals(0, result.size());

        SearchHotelItem hotelItem = result.get(0);
        assertNotNull(hotelItem.getHotelId());
        assertNotNull(hotelItem.getName());
        assertNotNull(hotelItem.getScore().getValue());
        assertNotNull(hotelItem.getScore().getCount());
        assertNotNull(hotelItem.getIs_favorite());
        assertNotNull(hotelItem.getImageItemURL());
        assertNotNull(hotelItem.getCity());
    }
}
