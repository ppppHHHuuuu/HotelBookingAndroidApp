package com.build.mobdev_nhom7.cancelledHotels;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import com.example.mobdev_nhom7.models.responseObj.comment.CommentItem;
import com.example.mobdev_nhom7.models.responseObj.trips.ActiveHotelItem;
import com.example.mobdev_nhom7.models.responseObj.trips.CancelledHotelItem;
import com.example.mobdev_nhom7.remote.APIService;
import com.example.mobdev_nhom7.remote.APIUtils;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class GetCancelledHotelTest {
    APIService apiService;
    Call<List<CancelledHotelItem>> getCancelHotelTest;
    Response<List<CancelledHotelItem>> response;

    @BeforeAll
    public void setup() throws IOException {
        apiService = APIUtils.getTestService();
        getCancelHotelTest = apiService.getCancelReservation("OEruAO7IVtPgdroSvlbNNP3IHaP2");
        response = getCancelHotelTest.execute();
    }
    @Test
    public void testCancelHotelTest() throws IOException {
        setup();
        assertEquals(200, response.code());

        List<CancelledHotelItem> result = response.body();
        assertNotEquals(0, result.size());
        if (result.size() > 0) {
            CancelledHotelItem cancelledHotelItem = result.get(0);
            assertNotNull(cancelledHotelItem.getHotel_id());
            assertNotNull(cancelledHotelItem.getName());
            assertNotNull(cancelledHotelItem.getAmount());
            assertNotNull(cancelledHotelItem.getImageURL());
            assertNotNull(cancelledHotelItem.getStartDate());
            assertNotNull(cancelledHotelItem.getEndDate());
        }
    }
}
