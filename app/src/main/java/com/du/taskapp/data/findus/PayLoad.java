
package com.du.taskapp.data.findus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PayLoad {

    @SerializedName("locationId")
    @Expose
    private Integer locationId;
    @SerializedName("locationSection")
    @Expose
    private Integer locationSection;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("titleArabic")
    @Expose
    private String titleArabic;
    @SerializedName("hourOperation")
    @Expose
    private String hourOperation;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("addressArabic")
    @Expose
    private String addressArabic;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("isActive")
    @Expose
    private Integer isActive;
    @SerializedName("createdDate")
    @Expose
    private Long createdDate;
    @SerializedName("updatedDate")
    @Expose
    private Long updatedDate;
    private Double distance;

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Integer getLocationSection() {
        return locationSection;
    }

    public void setLocationSection(Integer locationSection) {
        this.locationSection = locationSection;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleArabic() {
        return titleArabic;
    }

    public void setTitleArabic(String titleArabic) {
        this.titleArabic = titleArabic;
    }

    public String getHourOperation() {
        return hourOperation;
    }

    public void setHourOperation(String hourOperation) {
        this.hourOperation = hourOperation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressArabic() {
        return addressArabic;
    }

    public void setAddressArabic(String addressArabic) {
        this.addressArabic = addressArabic;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Long updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Double getDistance() {
        return distance;
    }

    public int getDistanceInt() {
        return distance.intValue();
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

}
