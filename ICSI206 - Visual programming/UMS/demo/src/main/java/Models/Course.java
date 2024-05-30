package Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Course {
    private final StringProperty courseName;
    private final StringProperty courseIndex;
    private final StringProperty courseLevel;
    private final StringProperty credit;
    private final StringProperty timePerWeekLectures;
    private final StringProperty timePerWeekLabs;
    private final StringProperty termOfCourse;
    private final StringProperty purposeOfCourse;
    private final StringProperty contentOfCourse;
    private final StringProperty knowledgeToBeAcquired;

    // Constructor
    public Course(String courseName, String courseIndex, String courseLevel,
                  String credit, String timePerWeekLectures, String timePerWeekLabs,
                  String termOfCourse, String purposeOfCourse, String contentOfCourse,
                  String knowledgeToBeAcquired) {
        this.courseName = new SimpleStringProperty(courseName);
        this.courseIndex = new SimpleStringProperty(courseIndex);
        this.courseLevel = new SimpleStringProperty(courseLevel);
        this.credit = new SimpleStringProperty(credit);
        this.timePerWeekLectures = new SimpleStringProperty(timePerWeekLectures);
        this.timePerWeekLabs = new SimpleStringProperty(timePerWeekLabs);
        this.termOfCourse = new SimpleStringProperty(termOfCourse);
        this.purposeOfCourse = new SimpleStringProperty(purposeOfCourse);
        this.contentOfCourse = new SimpleStringProperty(contentOfCourse);
        this.knowledgeToBeAcquired = new SimpleStringProperty(knowledgeToBeAcquired);
    }

    // Getters for StringProperty
    public StringProperty courseNameProperty() {
        return courseName;
    }

    public StringProperty courseIndexProperty() {
        return courseIndex;
    }

    public StringProperty courseLevelProperty() {
        return courseLevel;
    }

    public StringProperty creditProperty() {
        return credit;
    }

    public StringProperty timePerWeekLecturesProperty() {
        return timePerWeekLectures;
    }

    public StringProperty timePerWeekLabsProperty() {
        return timePerWeekLabs;
    }

    public StringProperty termOfCourseProperty() {
        return termOfCourse;
    }

    public StringProperty purposeOfCourseProperty() {
        return purposeOfCourse;
    }

    public StringProperty contentOfCourseProperty() {
        return contentOfCourse;
    }

    public StringProperty knowledgeToBeAcquiredProperty() {
        return knowledgeToBeAcquired;
    }
}
