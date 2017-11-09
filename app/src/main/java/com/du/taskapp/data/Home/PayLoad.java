
package com.du.taskapp.data.Home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PayLoad {

    @SerializedName("sectionId")
    @Expose
    private Integer sectionId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("titleArabic")
    @Expose
    private String titleArabic;
    @SerializedName("titleColor")
    @Expose
    private String titleColor;
    @SerializedName("shortDesc")
    @Expose
    private String shortDesc;
    @SerializedName("shortDescArabic")
    @Expose
    private String shortDescArabic;
    @SerializedName("shortDescColor")
    @Expose
    private String shortDescColor;
    @SerializedName("imagePath")
    @Expose
    private String imagePath;
    @SerializedName("position")
    @Expose
    private Integer position;
    @SerializedName("isActive")
    @Expose
    private Integer isActive;
    @SerializedName("isVisible")
    @Expose
    private Integer isVisible;
    @SerializedName("isCmsApi")
    @Expose
    private Integer isCmsApi;
    @SerializedName("isTileView")
    @Expose
    private Integer isTileView;
    @SerializedName("isCmsSection")
    @Expose
    private Integer isCmsSection;
    @SerializedName("isDeletable")
    @Expose
    private Integer isDeletable;
    @SerializedName("createdDate")
    @Expose
    private Long createdDate;
    @SerializedName("updatedDate")
    @Expose
    private Long updatedDate;
    @SerializedName("isLink")
    @Expose
    private Integer isLink;
    @SerializedName("externalLink")
    @Expose
    private String externalLink;
    @SerializedName("externalLinkArabic")
    @Expose
    private String externalLinkArabic;

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
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

    public String getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(String titleColor) {
        this.titleColor = titleColor;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getShortDescArabic() {
        return shortDescArabic;
    }

    public void setShortDescArabic(String shortDescArabic) {
        this.shortDescArabic = shortDescArabic;
    }

    public String getShortDescColor() {
        return shortDescColor;
    }

    public void setShortDescColor(String shortDescColor) {
        this.shortDescColor = shortDescColor;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Integer getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(Integer isVisible) {
        this.isVisible = isVisible;
    }

    public Integer getIsCmsApi() {
        return isCmsApi;
    }

    public void setIsCmsApi(Integer isCmsApi) {
        this.isCmsApi = isCmsApi;
    }

    public Integer getIsTileView() {
        return isTileView;
    }

    public void setIsTileView(Integer isTileView) {
        this.isTileView = isTileView;
    }

    public Integer getIsCmsSection() {
        return isCmsSection;
    }

    public void setIsCmsSection(Integer isCmsSection) {
        this.isCmsSection = isCmsSection;
    }

    public Integer getIsDeletable() {
        return isDeletable;
    }

    public void setIsDeletable(Integer isDeletable) {
        this.isDeletable = isDeletable;
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

    public Integer getIsLink() {
        return isLink;
    }

    public void setIsLink(Integer isLink) {
        this.isLink = isLink;
    }

    public String getExternalLink() {
        return externalLink;
    }

    public void setExternalLink(String externalLink) {
        this.externalLink = externalLink;
    }

    public String getExternalLinkArabic() {
        return externalLinkArabic;
    }

    public void setExternalLinkArabic(String externalLinkArabic) {
        this.externalLinkArabic = externalLinkArabic;
    }

}
