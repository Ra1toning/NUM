package com.example.demo;

import Models.Teacher;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class InstructorController implements Initializable {
    @FXML
    private TextField AddressField;
    @FXML
    private Button CloseButton;
    @FXML
    private TextField FirstName;
    @FXML
    private TextField regNumber;
    @FXML
    private TextField phone;
    @FXML
    private TextField LastName;
    @FXML
    private Button RegButton;
    @FXML
    private TextField SISiField;
    @FXML
    private DatePicker date;
    @FXML
    private ComboBox<String> degree;
    @FXML
    private ComboBox<String> gender;
    @FXML
    private PasswordField passwordData;
    @FXML
    private TextField program;
    @FXML
    private Label registrationMessageLabel;
    private StudentModuleController studentModuleController;

    public void setStudentModuleController(StudentModuleController studentModuleController) {
        this.studentModuleController = studentModuleController;
    }
    public void CloseButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) CloseButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    void RegistrationButtonOnAction(ActionEvent event) {

        String registerNumber = regNumber.getText();
        String lastName = LastName.getText();
        String firstName = FirstName.getText();
        String genderValue = gender.getValue();
        String sisiId = SISiField.getText();
        String phoneNumber = phone.getText();
        String residenceAddress = AddressField.getText();
        String schoolProgram = program.getText();
        String password = passwordData.getText();
        String selectedDegree = degree.getValue();

        Teacher newTeacher = new Teacher(sisiId, lastName, firstName, genderValue, phoneNumber, selectedDegree, residenceAddress, schoolProgram, password, registerNumber);

        studentModuleController.addTeacherData(newTeacher);

        clearFormFields();
        registrationMessageLabel.setText("Амжилттай бүртгэлээ! Мэдээлэл файлд хадгагдлаа.");
    }
    private void clearFormFields() {
        regNumber.clear();
        LastName.clear();
        FirstName.clear();
        gender.setValue(null);
        degree.setValue(null);
        SISiField.clear();
        phone.clear();
        AddressField.clear();
        program.clear();
        passwordData.clear();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        degree.setItems(FXCollections.observableArrayList("Бакалавр","Магистр","Доктор"));
        gender.setItems(FXCollections.observableArrayList("Эр","Эм"));
    }
}