package com.lucky.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator.
 */
public class CourseSet {
    private Set<Course> courses = new HashSet<Course>();

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public  CourseSet(){
        //Set集合的绑定必须先添加两个变量，否则无法完成绑定
        courses.add(new Course());
        courses.add(new Course());
    }
}
