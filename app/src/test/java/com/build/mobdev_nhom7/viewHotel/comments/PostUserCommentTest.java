package com.build.mobdev_nhom7.viewHotel.comments;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import com.example.mobdev_nhom7.models.requestObj.feedback.FeedbackRequest;
import com.example.mobdev_nhom7.models.responseObj.DefaultResponseObj;
import com.example.mobdev_nhom7.models.responseObj.ratings.RatingItem;
import com.example.mobdev_nhom7.remote.APIService;
import com.example.mobdev_nhom7.remote.APIUtils;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class PostUserCommentTest {
    APIService apiService;
    Call<DefaultResponseObj> postUserComment;
    Response<DefaultResponseObj> response;
    FeedbackRequest feedbackRequest;

    private static RatingItem createSampleRatingItem() {
        RatingItem ratingItem = new RatingItem();

        // Set sample values for the rating item
        ratingItem.setCount(10);
        ratingItem.setValue(4.5f);
        ratingItem.setBuilding(4.0f);
        ratingItem.setComfort(4.5f);
        ratingItem.setCleanliness(4.8f);

        return ratingItem;
    }

    @BeforeAll
    public void setup() throws IOException {
        feedbackRequest = new FeedbackRequest();
        feedbackRequest.setReservationId("123456"); // Replace with an actual reservation ID
        feedbackRequest.setComment("This is a sample comment.");
        RatingItem ratingItem = createSampleRatingItem();
        feedbackRequest.setRatings(ratingItem);
        apiService = APIUtils.getTestService();
        postUserComment = apiService.postUserCommentHotel(feedbackRequest);
        response = postUserComment.execute();
        // Set sample values for the feedback request

    }
    @Test
    public void testAddUserComment() throws IOException {
        setup();
        assertEquals(200, response.code());

        DefaultResponseObj result = response.body();
        assertEquals(true, response.isSuccessful());

        assertNotEquals("", response.body());

    }
}
