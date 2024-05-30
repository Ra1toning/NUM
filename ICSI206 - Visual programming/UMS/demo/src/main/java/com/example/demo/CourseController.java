package com.example.demo;

import Models.Course;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CourseController implements Initializable {

    @FXML
    private Button CloseButton;

    @FXML
    private TextField CourseIndex;

    @FXML
    private TextField CourseName;

    @FXML
    private TextField Credit;

    @FXML
    private TextField LabTime;

    @FXML
    private TextField LectureTime;

    @FXML
    private ComboBox<String> Level;

    @FXML
    private Button RegButton;

    @FXML
    private TextArea contentField;

    @FXML
    private DatePicker date;

    @FXML
    private TextArea knowledgeField;

    @FXML
    private TextArea purposeField;

    @FXML
    private Label registrationMessageLabel;

    @FXML
    private ComboBox<String> season;
    private StudentModuleController studentModuleController;

    public void setStudentModuleController(StudentModuleController studentModuleController) {
        this.studentModuleController = studentModuleController;
    }

    @FXML
    public void CloseButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) CloseButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void RegistrationButtonOnAction(ActionEvent event) {

        String courseIndex = CourseIndex.getText();
        String courseName = CourseName.getText();
        String credit = Credit.getText();
        String labTime = LabTime.getText();
        String lectureTime = LectureTime.getText();
        String courseLevel = (Level.getValue() != null) ? Level.getValue().toString() : "";
        String courseSeason = (season.getValue() != null) ? season.getValue().toString() : "";
        String content = contentField.getText();
        String knowledge = knowledgeField.getText();
        String purpose = purposeField.getText();

        Course newCourse = new Course(courseName, courseIndex, courseLevel, credit, lectureTime, labTime, courseSeason, purpose, content, knowledge);

        studentModuleController.addCourseData(newCourse);

        clearFormFields();
        registrationMessageLabel.setText("Амжилттай бүртгэлээ! Мэдээлэл файлд хадгагдлаа.");
    }
    private void clearFormFields() {
        CourseIndex.clear();
        CourseName.clear();
        Credit.clear();
        LabTime.clear();
        LectureTime.clear();
        Level.setValue(null);
        season.setValue(null);
        contentField.clear();
        knowledgeField.clear();
        purposeField.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Level.setItems(FXCollections.observableArrayList("Бакалавр","Магистр","Доктор").sorted());
        season.setItems(FXCollections.observableArrayList("Намар","Хавар","Зун","Өвөл","Улирал харгалзахгүй").sorted());
    }

}
