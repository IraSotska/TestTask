package com.agileengine.entity;

import java.awt.*;

public class ImageDataEntity {

    private int imageId;
    private String croppedImage;

    public ImageDataEntity() {
    }

    public ImageDataEntity(int imageId, String imageAutor, String croppedImage, String fullImage) {
        this.imageId = imageId;
        this.croppedImage = croppedImage;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getCroppedImage() {
        return croppedImage;
    }

    public void setCroppedImage(String croppedImage) {
        this.croppedImage = croppedImage;
    }

}
