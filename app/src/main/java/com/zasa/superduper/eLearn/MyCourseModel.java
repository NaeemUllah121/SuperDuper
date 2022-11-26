package com.zasa.superduper.eLearn;

public class MyCourseModel {

    String courseImg, courseTitle, courseDiscript,courseDuration;

    public MyCourseModel() {
    }

    public  MyCourseModel(String courseImg, String courseTitle, String courseDiscript, String courseDuration) {
        this.courseImg = courseImg;
        this.courseTitle = courseTitle;
        this.courseDiscript = courseDiscript;
        this.courseDuration = courseDuration;
    }

    public String getCourseImg() {
        return courseImg;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public String getCourseDiscript() {
        return courseDiscript;
    }

    public String getCourseDuration() {
        return courseDuration;
    }
}
