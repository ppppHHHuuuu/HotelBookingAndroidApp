package com.build.mobdev_nhom7.pastHotels;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.example.mobdev_nhom7.models.responseObj.trips.PastHotelItem;
import com.example.mobdev_nhom7.remote.APIService;
import com.example.mobdev_nhom7.remote.APIUtils;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class GetPastHotelsTest {
    APIService apiService;
    Call<List<PastHotelItem>> getPastUnCommentedHotelTest;
    Call<List<PastHotelItem>> getPastCommentedHotelTest;

    Response<List<PastHotelItem>> responseUnrated;
    Response<List<PastHotelItem>> responseRated;

    @BeforeAll
    public void setup() throws IOException {
        apiService = APIUtils.getTestService();
        getPastUnCommentedHotelTest = apiService.getNotRatedReservation("OEruAO7IVtPgdroSvlbNNP3IHaP2");
        getPastCommentedHotelTest = apiService.getRatedReservation("OEruAO7IVtPgdroSvlbNNP3IHaP2");
        responseUnrated = getPastUnCommentedHotelTest.execute();
        responseRated = getPastCommentedHotelTest.execute();
    }
    @Test
    public void testGetNotRated() throws IOException {
        setup();
        assertEquals(200, responseUnrated.code());
        List<PastHotelItem> result = responseUnrated.body();
        assert result != null;
        if (result.size() > 0) {
            for (int i = 0; i < result.size(); i++) {
                PastHotelItem pastHotelItem = result.get(0);
                assertNotNull(pastHotelItem.getHotel_id());
                assertNotNull(pastHotelItem.getName());
                assertNotNull(pastHotelItem.getAmount());
                assertNotNull(pastHotelItem.getImageURL());
                assertNotNull(pastHotelItem.getStartDate());
                assertNotNull(pastHotelItem.getEndDate());
            }
        }
    }
    @Test
    public void testGetRated() throws IOException {
        setup();

        assertEquals(200, responseRated.code());

        List<PastHotelItem> result = responseRated.body();
        assert result != null;
        if (result.size() > 0) {
            for (int i = 0; i < result.size(); i++) {
                PastHotelItem pastHotelItem = result.get(i);
                assertNotNull(pastHotelItem.getHotel_id());
                assertNotNull(pastHotelItem.getName());
                assertNotNull(pastHotelItem.getAmount());
                assertNotNull(pastHotelItem.getImageURL());
                assertNotNull(pastHotelItem.getStartDate());
//            assertNotNull(pastHotelItem.getFeedbackItem().getRatingItemList()getCount());
                assertNotNull(pastHotelItem.getFeedbackItem().getRatingItemList().getValue());
                assertNotNull(pastHotelItem.getFeedbackItem().getRatingItemList().getBuilding());
                assertNotNull(pastHotelItem.getFeedbackItem().getRatingItemList().getCleanliness());
                assertNotNull(pastHotelItem.getEndDate());
            }

        }
    }
}
