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

public class GetAllFeedbackTest {
    APIService apiService;
    Call<List<CommentItem>> getAllFeedback;
    Response<List<CommentItem>> response;

    @BeforeAll
    public void setup() throws IOException {
        apiService = APIUtils.getTestService();
        getAllFeedback = apiService.getAllFeedback("3LQ2wJJMwKAlfdnaVC9v");
        response = getAllFeedback.execute();
    }
    @Test
    public void testGetAllHotel() throws IOException {
        setup();

        assertEquals(200, response.code());

        List<CommentItem> result = response.body();

        assertEquals(4, result.size());

        CommentItem commentItem = result.get(0);

        assertNotNull(commentItem.getFeedbackItem());

        assertNotNull(commentItem.getHotel_id());

        assertNotNull(commentItem.getUser_id());
    }
}
