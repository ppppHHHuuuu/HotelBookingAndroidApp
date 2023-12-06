package com.build.mobdev_nhom7.viewHotel.comments;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import com.example.mobdev_nhom7.models.bookingRequest.BookingRequest;
import com.example.mobdev_nhom7.models.requestObj.feedback.FeedbackRequest;
import com.example.mobdev_nhom7.models.responseObj.DefaultResponseObj;
import com.example.mobdev_nhom7.models.responseObj.ratings.RatingItem;
import com.example.mobdev_nhom7.remote.APIService;
import com.example.mobdev_nhom7.remote.APIUtils;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

public class PostBookingTest {
    APIService apiService;
    Call<Object> postBooking;
    Response<Object> response;
    BookingRequest bookingRequest;
    private static Map<String, Integer> createSampleRooms() {
        Map<String, Integer> rooms = new HashMap<>();
        // Add sample room data
        rooms.put("single", 2);
        rooms.put("double", 1);
        return rooms;
    }

    public static BookingRequest createSampleBookingRequest() {
        // Sample data
        String userId = "user123";
        String hotelId = "hotel456";
        Map<String, Integer> rooms = createSampleRooms();
        String startDate = "2023-12-08";
        String endDate = "2023-12-09";
        long totalCost = 1500;

        // Create a sample BookingRequest object
        return new BookingRequest(userId, hotelId, rooms, startDate, endDate, totalCost);
    }
    @BeforeAll
    public void setup() throws IOException {
        bookingRequest = createSampleBookingRequest();
        apiService = APIUtils.getTestService();
        postBooking = apiService.booking(bookingRequest);
        response = postBooking.execute();
        // Set sample values for the feedback request

    }
    @Test
    public void testAddUserComment() throws IOException {
        setup();
        assertEquals(200, response.code());
        assertEquals(true, response.isSuccessful());


        assertNotEquals("", response.body());
        Object result = response.body();

    }
}
