package com.example.mobdev_nhom7.models.responseObj.cityDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Alert {
    @SerializedName("content")
    @Expose
    private String content;

    @SerializedName("id")
    @Expose
    private String id;


    // Getter cho thuộc tính 'content'
    public String getContent() {
        return content;
    }

    // Setter cho thuộc tính 'content'
    public void setContent(String content) {
        this.content = content;
    }

    // Getter cho thuộc tính 'id'
    public String getId() {
        return id;
    }

    // Setter cho thuộc tính 'id'
    public void setId(String id) {
        this.id = id;
    }
}
