package com.build.mobdev_nhom7.viewHotels;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;

import com.example.mobdev_nhom7.models.hotel.HotelItem;
import com.example.mobdev_nhom7.models.responseObj.search.SearchHotelItem;
import com.example.mobdev_nhom7.remote.APIService;
import com.example.mobdev_nhom7.remote.APIUtils;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class GetAllHotelTest {
    APIService apiService;
    Call<List<HotelItem>> getAllHotelCall;
    Response<List<HotelItem>> response;

    @BeforeAll
    public void setup() throws IOException {
        apiService = APIUtils.getTestService();
        getAllHotelCall = apiService.getAllHotel();
        response = getAllHotelCall.execute();
    }
    @Test
    public void testGetAllHotel() throws IOException {
        setup();

        assertEquals(200, response.code());

        List<HotelItem> result = response.body();

        assertEquals(23, result.size());

        HotelItem hotelItem = result.get(0);

        assertNotNull(hotelItem.getHotelId());

        assertNotNull(hotelItem.getName());

        assertNotNull(hotelItem.getCountry());
    }
}
