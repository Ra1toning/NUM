package com.example.demo;

import Models.CourseSelection;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CourseSelectionController implements Initializable  {

    @FXML
    private Button CloseButton;

    @FXML
    private ComboBox<String> CourseSelect;

    @FXML
    private TextField LectureClassSelect;
    @FXML
    private TextField SeminarClassSelect;

    @FXML
    private ComboBox<String> LectureDaySelect;

    @FXML
    private TextField LectureStart;

    @FXML
    private Button RegButton;

    @FXML
    private ComboBox<String> SeminarDaySelect;

    @FXML
    private TextField SeminarStart;
    @FXML
    private TextField capacity;

    @FXML
    private ComboBox<String> TeacherSelect;

    @FXML
    private Label registrationMessageLabel;
    private StudentModuleController studentModuleController;
    public void setStudentModuleController(StudentModuleController studentModuleController) {
        this.studentModuleController = studentModuleController;

        CourseSelect.setItems(studentModuleController.getcourseNames().stream()
                .map(course -> course.courseNameProperty().get())
                .collect(Collectors.toCollection(FXCollections::observableArrayList)).sorted());

        TeacherSelect.setItems(studentModuleController.getTeacherNames().stream()
                .map(teacher -> teacher.firstNameProperty().get())
                .collect(Collectors.toCollection(FXCollections::observableArrayList)).sorted());
    }

    public void CloseButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) CloseButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    void RegistrationButtonOnAction(ActionEvent event) {
        String lesson = (CourseSelect.getValue() != null) ? CourseSelect.getValue().toString() : "";
        String teacher = (TeacherSelect.getValue() != null) ? TeacherSelect.getValue().toString() : "";
        String lecDay = (LectureDaySelect.getValue() != null) ? LectureDaySelect.getValue().toString() : "";
        String semDay = (SeminarDaySelect.getValue() != null) ? SeminarDaySelect.getValue().toString() : "";
        String lecClass = LectureClassSelect.getText();
        String semClass = SeminarClassSelect.getText();
        String lecTime = LectureStart.getText();
        String semTime = SeminarStart.getText();
        String cap = capacity.getText();

        CourseSelection newSelection = new CourseSelection(lesson, teacher, lecDay, semDay,lecClass, semClass, lecTime, semTime, cap);

        studentModuleController.addSelectionData(newSelection);

        clearFormFields();
        registrationMessageLabel.setText("Амжилттай бүртгэлээ! Мэдээлэл файлд хадгагдлаа.");

    }
    private void clearFormFields() {
        capacity.clear();
        SeminarStart.clear();
        LectureStart.clear();
        CourseSelect.setValue(null);
        TeacherSelect.setValue(null);
        SeminarDaySelect.setValue(null);
        LectureDaySelect.setValue(null);
        LectureClassSelect.clear();
        SeminarClassSelect.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LectureDaySelect.setItems(FXCollections.observableArrayList("Даваа","Мягмар","Лхагва","Пүрэв","Баасан"));
        SeminarDaySelect.setItems(FXCollections.observableArrayList("Даваа","Мягмар","Лхагва","Пүрэв","Баасан"));
    }
}
