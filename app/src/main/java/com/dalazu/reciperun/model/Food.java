package com.dalazu.reciperun.model;

public class Food {
    private String id;
    private String foodImage;
    private String foodName;
    private String fooddsc;

    private Float rating;
    private String tvKal;
    private String tvMin;
    private String videoUrl;
    String category;

    public Food() {

    }

    public Food(String id, String foodImage, String foodName, String fooddsc, Float rating, String tvKal, String tvMin, String videoUrl, String category) {
        this.id = id;
        this.foodImage = foodImage;
        this.foodName = foodName;
        this.fooddsc = fooddsc;
        this.rating = rating;
        this.tvKal = tvKal;
        this.tvMin = tvMin;
        this.videoUrl = videoUrl;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFooddsc() {
        return fooddsc;
    }

    public void setFooddsc(String fooddsc) {
        this.fooddsc = fooddsc;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getTvKal() {
        return tvKal;
    }

    public void setTvKal(String tvKal) {
        this.tvKal = tvKal;
    }

    public String getTvMin() {
        return tvMin;
    }

    public void setTvMin(String tvMin) {
        this.tvMin = tvMin;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
