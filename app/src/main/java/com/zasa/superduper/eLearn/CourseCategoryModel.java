package com.zasa.superduper.eLearn;

public class CourseCategoryModel {

    String image,name;

    public CourseCategoryModel() {
    }

    public CourseCategoryModel(String image, String name) {
        this.image = image;
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
}
