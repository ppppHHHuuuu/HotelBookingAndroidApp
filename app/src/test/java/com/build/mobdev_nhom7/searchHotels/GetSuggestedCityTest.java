package com.build.mobdev_nhom7.viewHotels;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.example.mobdev_nhom7.models.responseObj.cityName.CityItem;
import com.example.mobdev_nhom7.remote.APIService;
import com.example.mobdev_nhom7.remote.APIUtils;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class GetSuggestedCityTest {
    APIService apiService;
    Call<List<CityItem>> getAllHotelCall;
    Response<List<CityItem>> response;

    @BeforeAll
    public void setup() throws IOException {
        apiService = APIUtils.getTestService();
        getAllHotelCall = apiService.getSuggestedCity();
        response = getAllHotelCall.execute();
    }
    @Test
    public void testGetSuggestedCity() throws IOException {
        setup();

        assertEquals(200, response.code());

        List<CityItem> result = response.body();

        assertEquals(7, result.size());

        CityItem cityItem = result.get(0);

        assertNotNull(cityItem.getCityId());

        assertNotNull(cityItem.getCityName());

        assertNotNull(cityItem.getCountry());
    }
}
