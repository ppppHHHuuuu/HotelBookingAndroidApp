package com.example.mobdev_nhom7.models.responseObj.ratings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RatingItem {
    @SerializedName("count")
    @Expose
    private String count;
    @SerializedName("value")
    @Expose
    private Double value;
    @SerializedName("building")
    @Expose
    private Double building;
    @SerializedName("comfort")
    @Expose
    private Double comfort;

    @SerializedName("cleanliness")
    @Expose
    private Double cleanliness;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getBuilding() {
        return building;
    }

    public void setBuilding(Double building) {
        this.building = building;
    }

    public Double getComfort() {
        return comfort;
    }

    public void setComfort(Double comfort) {
        this.comfort = comfort;
    }

    public Double getCleanliness() {
        return cleanliness;
    }

    public void setCleanliness(Double cleanliness) {
        this.cleanliness = cleanliness;
    }

}
