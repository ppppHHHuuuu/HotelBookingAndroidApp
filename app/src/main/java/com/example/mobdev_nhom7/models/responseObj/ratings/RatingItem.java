package com.example.mobdev_nhom7.models.responseObj.ratings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RatingItem {
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("value")
    @Expose
    private Float value;
    @SerializedName("building")
    @Expose
    private Float building;
    @SerializedName("comfort")
    @Expose
    private Float comfort;

    @SerializedName("cleanliness")
    @Expose
    private Float cleanliness;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Float getBuilding() {
        return building;
    }

    public void setBuilding(Float building) {
        this.building = building;
    }

    public Float getComfort() {
        return comfort;
    }

    public void setComfort(Float comfort) {
        this.comfort = comfort;
    }

    public Float getCleanliness() {
        return cleanliness;
    }

    public void setCleanliness(Float cleanliness) {
        this.cleanliness = cleanliness;
    }
}
