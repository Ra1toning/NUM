package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {

    @FXML
    private Button StudentModule;
    @FXML
    private Button InstructorModule;
    @FXML
    private Button CourseModule;
    @FXML
    private Button StudModule;
    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private ImageView brandingImageView;
    @FXML
    private ImageView LogoView;
    @FXML
    private TextField SISiField;
    @FXML
    private PasswordField PassField;

    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    public void LoginButtonOnAction(ActionEvent event) {

        if(!SISiField.getText().isBlank() && !PassField.getText().isBlank()) {
            ValidateLogin();
        } else {
            loginMessageLabel.setText("Нэвтрэх нэр, нууц үгээ шалгана уу.");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile = new File("src/images/reg.jpg");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);

        File LogoFile = new File("src/images/num-logo.png");
        Image LogoImage = new Image(LogoFile.toURI().toString());
        LogoView.setImage(LogoImage);
    }
    public void ValidateLogin() {
        String username = SISiField.getText();
        String password = PassField.getText();

        if (!username.equals("admin") && !password.equals("admin")) {
            loginMessageLabel.setText("Дахин шалгана уу.");
        } else {
            UniversityManagementSystem();
            SISiField.setText("");
            PassField.setText("");
        }
    }
    public void UniversityManagementSystem() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("UManagementSystem.fxml"));
            Stage CourseStage = new Stage();
            CourseStage.initStyle(StageStyle.UNDECORATED);
            CourseStage.setScene(new Scene(root, 1280, 720));
            CourseStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
