package com.build.mobdev_nhom7.searchHotels;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import com.example.mobdev_nhom7.models.responseObj.search.SearchHotelItem;
import com.example.mobdev_nhom7.remote.APIService;
import com.example.mobdev_nhom7.remote.APIUtils;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class GetHotelByDateAndQuantityTest {
    APIService apiService;
    Call<List<SearchHotelItem>> getHotelCall;
    Response<List<SearchHotelItem>> response;

    private Date getNextDate(Date date) {
        // Create a Calendar instance and set it to the current date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // Add one day to the current date
        calendar.add(Calendar.DAY_OF_MONTH, 1);

        // Get the next day
        Date nextDay = calendar.getTime();
        return nextDay;
    }
    private String getStringDate(Date date) {
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDateString = outputFormat.format(date);
        return formattedDateString;
    }
    @BeforeAll
    public void setup() throws IOException {
        apiService = APIUtils.getTestService();
        Date startDate =  getNextDate(new Date());
        Date endDate =  getNextDate(startDate);
        String start_date = getStringDate(startDate);
        String end_date = getStringDate(endDate);
        getHotelCall = apiService.searchHotels("1xdyOLnYOsUAMLjhKQUq2a6GAX13", null, "a3gN4bdOKVEgpEjxi5nU", start_date, end_date, "2", "1");
        response = getHotelCall.execute();
    }
    @Test
    public void testGetSuggestedCity() throws IOException {
        setup();
        //check status
        assertEquals(200, response.code());

        //check not empty
        List<SearchHotelItem> result = response.body();
        assertNotEquals(0, result.size());

        //check necessary fields
        SearchHotelItem hotelItem = result.get(0);
        assertNotNull(hotelItem.getHotelId());
        assertNotNull(hotelItem.getImageItemURL());
        assertNotNull(hotelItem.getScore());
        assertNotNull(hotelItem.getPositionFromCenter());
    }
}
