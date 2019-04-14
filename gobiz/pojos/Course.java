package com.example.vaibh.gobiz.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Course implements Parcelable {

    String courseName;
    List<String> courseModules = new ArrayList<String>();
    String courseDescription;

    /**
     * @param name
     * @param modules
     */

    public Course(String name, List<String> modules) {
        this(name, modules, "Description to be added later as we get more data.");
    }

    public Course(String name, List<String> modules, String courseDescription) {
        courseName = name;
        courseModules = modules;
        this.courseDescription = courseDescription;
    }

    /**
     * @return courseModules
     */
    public List<String> getCourseModules() {
        return courseModules;
    }

    /**
     * @return courseName
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * @return courseDescription
     */
    public String getCourseDescription() {
        return courseDescription;
    }

    protected Course(Parcel in) {
        courseName = in.readString();
        if (in.readByte() == 0x01) {
            courseModules = new ArrayList<String>();
            in.readList(courseModules, String.class.getClassLoader());
        } else {
            courseModules = null;
        }
        courseDescription = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(courseName);
        if (courseModules == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(courseModules);
        }
        dest.writeString(courseDescription);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Course> CREATOR = new Parcelable.Creator<Course>() {
        @Override
        public Course createFromParcel(Parcel in) {
            return new Course(in);
        }

        @Override
        public Course[] newArray(int size) {
            return new Course[size];
        }
    };
}