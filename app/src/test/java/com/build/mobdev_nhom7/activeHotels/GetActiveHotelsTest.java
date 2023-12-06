package com.build.mobdev_nhom7.activeHotels;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import com.example.mobdev_nhom7.models.responseObj.comment.CommentItem;
import com.example.mobdev_nhom7.models.responseObj.trips.ActiveHotelItem;
import com.example.mobdev_nhom7.remote.APIService;
import com.example.mobdev_nhom7.remote.APIUtils;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class GetActiveHotelsTest {
    APIService apiService;
    Call<List<ActiveHotelItem>> getActiveHotelTest;
    Response<List<ActiveHotelItem>> response;

    @BeforeAll
    public void setup() throws IOException {
        apiService = APIUtils.getTestService();
        getActiveHotelTest = apiService.getActiveReservation("3LQ2wJJMwKAlfdnaVC9v");
        response = getActiveHotelTest.execute();
    }
    @Test
    public void testGetAllHotel() throws IOException {
        setup();
        assertEquals(200, response.code());

        List<ActiveHotelItem> result = response.body();

        if (result.size() > 0) {
            ActiveHotelItem activeHotelItem = result.get(0);
            assertNotNull(activeHotelItem.getHotel_id());
            assertNotNull(activeHotelItem.getName());
            assertNotNull(activeHotelItem.getAmount());
            assertNotNull(activeHotelItem.getReservation_id());
            assertNotNull(activeHotelItem.getImageURL());
            assertNotNull(activeHotelItem.getStartDate());
            assertNotNull(activeHotelItem.getEndDate());
        }

    }
}
