package com.example.mobdev_nhom7.models.responseObj.comment;

import com.example.mobdev_nhom7.models.responseObj.ratings.RatingItem;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FeedbackItem {
    public RatingItem getRatingItemList() {
        return ratingItemList;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String comment;
     public void setRatingItemList(RatingItem ratingItemList) {
        this.ratingItemList = ratingItemList;
    }

    @SerializedName("ratings")
    RatingItem ratingItemList;
}
