package Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CourseSelection {
    private final StringProperty lessons;
    private final StringProperty teacher;
    private final StringProperty lectureDates;
    private final StringProperty seminarDates;
    private final StringProperty lectureClasses;
    private final StringProperty seminarClasses;
    private final StringProperty lectureTimes;
    private final StringProperty seminarTimes;
    private final StringProperty courseCapacity;

    // Constructor
    public CourseSelection(String lessons,String teacher, String lectureDates, String seminarDates,
                           String lectureClasses, String seminarClasses, String lectureTimes,
                           String seminarTimes, String courseCapacity) {
        this.lessons = new SimpleStringProperty(lessons);
        this.teacher = new SimpleStringProperty(teacher);
        this.lectureDates = new SimpleStringProperty(lectureDates);
        this.seminarDates = new SimpleStringProperty(seminarDates);
        this.lectureClasses = new SimpleStringProperty(lectureClasses);
        this.seminarClasses = new SimpleStringProperty(seminarClasses);
        this.lectureTimes = new SimpleStringProperty(lectureTimes);
        this.seminarTimes = new SimpleStringProperty(seminarTimes);
        this.courseCapacity = new SimpleStringProperty(courseCapacity);
    }

    // Getters for StringProperty
    public StringProperty lessonsProperty() {
        return lessons;
    }

    public StringProperty teacherProperty() {
        return teacher;
    }

    public StringProperty lectureDatesProperty() {
        return lectureDates;
    }

    public StringProperty seminarDatesProperty() {
        return seminarDates;
    }

    public StringProperty lectureClassesProperty() {
        return lectureClasses;
    }

    public StringProperty seminarClassesProperty() {
        return seminarClasses;
    }

    public StringProperty lectureTimesProperty() {
        return lectureTimes;
    }

    public StringProperty seminarTimesProperty() {
        return seminarTimes;
    }

    public StringProperty courseCapacityProperty() {
        return courseCapacity;
    }
}
