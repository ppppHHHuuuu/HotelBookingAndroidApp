package com.build.mobdev_nhom7;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import android.util.Log;

import com.example.mobdev_nhom7.models.responseObj.search.SearchHotelItem;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public class APITest {

    private static final String MOCK_FILE_NAME = "assets/mock.json";
    private List<SearchHotelItem> mockData;

    @Before
    public void setup() {
        // Load mock data from the assets directory
        mockData = loadMockData();
    }

    private List<SearchHotelItem> loadMockData() {
        try {
            // Use class loader to load the file directly
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream( MOCK_FILE_NAME);


            InputStreamReader reader = new InputStreamReader(inputStream);

            // Parse JSON using Gson
            Type type = new TypeToken<List<SearchHotelItem>>() {}.getType();
            List<SearchHotelItem> mockData = new Gson().fromJson(reader, type);

            reader.close();
            inputStream.close();

            return mockData;
        } catch (IOException e) {
            Log.e("APITest", "Error loading mock data", e);
            return null;
        }
    }

    @Test
    public void testLoadHotels() {
        // Use mockData in your test
        assertNotNull(mockData);
        assertEquals(1, mockData.size());

        SearchHotelItem searchHotelItem = mockData.get(0);

        assertNotNull(searchHotelItem);
        assertEquals("4hurfsd", searchHotelItem.getHotelId());
        assertEquals("Muong Thanh", searchHotelItem.getName());
        // Add more assertions if needed
    }
}
