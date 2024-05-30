package com.example.demo;

import Models.Student;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentController implements Initializable {
    @FXML
    private Button CloseButton;
    @FXML
    private Button RegButton;
    @FXML
    private Label registrationMessageLabel;
    @FXML
    private PasswordField passwordData;
    @FXML
    private TextField Program;
    @FXML
    private ComboBox<String> gender;
    @FXML
    private TextField SISiField;
    @FXML
    private TextField AddressField;
    @FXML
    private TextField regNumber;
    @FXML
    private TextField phone;
    @FXML
    private TextField FirstName;
    @FXML
    private TextField LastName;
    @FXML
    private DatePicker date;
    private StudentModuleController studentModuleController;

    public void setStudentModuleController(StudentModuleController studentModuleController) {
        this.studentModuleController = studentModuleController;
    }

    public void CloseButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) CloseButton.getScene().getWindow();
        stage.close();
    }

    public void RegistrationButtonOnAction(ActionEvent event) {
        String registerNumber = regNumber.getText();
        String lastName = LastName.getText();
        String firstName = FirstName.getText();
        String genderValue = gender.getValue();
        String sisiId = SISiField.getText();
        String phoneNumber = phone.getText();
        String residenceAddress = AddressField.getText();
        String schoolProgram = Program.getText();
        String password = passwordData.getText();

        Student newStudent = new Student(registerNumber, lastName, firstName, genderValue, sisiId, phoneNumber, residenceAddress, schoolProgram, password);

        studentModuleController.addStudentData(newStudent);

        clearFormFields();
        registrationMessageLabel.setText("Амжилттай бүртгэлээ! Мэдээлэл файлд хадгагдлаа.");
    }

    private void clearFormFields() {
        regNumber.clear();
        LastName.clear();
        FirstName.clear();
        gender.setValue(null);
        SISiField.clear();
        phone.clear();
        AddressField.clear();
        Program.clear();
        passwordData.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gender.setItems(FXCollections.observableArrayList("Эр", "Эм"));
    }
}
