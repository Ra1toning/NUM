package com.example.demo;

import Models.Course;
import Models.CourseSelection;
import Models.Student;
import Models.Teacher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentModuleController implements Initializable {
    @FXML
    private TableView<Student> tbStudents;
    @FXML
    private Button CloseButton;
    @FXML
    private TableView<Teacher> tbTeachers;
    @FXML
    private TableView<Course> tbCourses;
    @FXML
    private TableView<CourseSelection> tbCourseSelection;
    @FXML
    private BorderPane studentForm;
    @FXML
    private BorderPane teacherForm;
    @FXML
    private BorderPane courseForm;
    @FXML
    private BorderPane courseSelectionForm;
    @FXML
    private Button TeacherModule;
    @FXML
    private Button StudentModule;
    @FXML
    private Button CourseModule;
    @FXML
    private Circle profile;
    @FXML
    private Button StudModule;
    @FXML
    private Button removeButton;
    @FXML
    private Button addButton;
    @FXML
    private ObservableList<Student> data;
    @FXML
    private TableColumn<Student, String> tcID;
    @FXML
    private TableColumn<Student, String> tcFName;
    @FXML
    private TableColumn<Student, String> tcLName;
    @FXML
    private TableColumn<Student, String> tcGender;
    @FXML
    private TableColumn<Student, String> tcRD;
    @FXML
    private TableColumn<Student, String> tcPhoneNum;
    @FXML
    private TableColumn<Student, String> tcAddress;
    @FXML
    private TableColumn<Student, String> tcProgram;
    @FXML
    private TableColumn<Student, String> tcPass;
    @FXML
    private ObservableList<Teacher> data1;
    @FXML
    private TableColumn<Teacher, String> tcID1;
    @FXML
    private TableColumn<Teacher, String> tcFName1;
    @FXML
    private TableColumn<Teacher, String> tcLName1;
    @FXML
    private TableColumn<Teacher, String> tcGender1;
    @FXML
    private TableColumn<Teacher, String> tcRD1;
    @FXML
    private TableColumn<Teacher, String> tcDegree1;
    @FXML
    private TableColumn<Teacher, String> tcPhoneNum1;
    @FXML
    private TableColumn<Teacher, String> tcAddress1;
    @FXML
    private TableColumn<Teacher, String> tcProgram1;
    @FXML
    private TableColumn<Teacher, String> tcPass1;
    @FXML
    private ObservableList<Course> data3;
    @FXML
    private TableColumn<Course, String> tcIDX;
    @FXML
    private TableColumn<Course, String> tcName;
    @FXML
    private TableColumn<Course, String> tcDegreeCourse;
    @FXML
    private TableColumn<Course, String> tcCredit;
    @FXML
    private TableColumn<Course, String> tcLecture;
    @FXML
    private TableColumn<Course, String> tcSeminar;
    @FXML
    private TableColumn<Course, String> tcSeason;
    @FXML
    private TableColumn<Course, String> tcPurpose;
    @FXML
    private TableColumn<Course, String> tcContent;
    @FXML
    private TableColumn<Course, String> tcKnowledge;
    @FXML
    private ObservableList<CourseSelection> data2;
    @FXML
    private TableColumn<CourseSelection, String> tcLesson;
    @FXML
    private TableColumn<CourseSelection, String> tcInstructor;
    @FXML
    private TableColumn<CourseSelection, String> tcLecDay;
    @FXML
    private TableColumn<CourseSelection, String> tcLecClass;
    @FXML
    private TableColumn<CourseSelection, String> tcLecTime;
    @FXML
    private TableColumn<CourseSelection, String> tcSemDay;
    @FXML
    private TableColumn<CourseSelection, String> tcSemclass;
    @FXML
    private TableColumn<CourseSelection, String> tcSemTime;
    @FXML
    private TableColumn<CourseSelection, String> tcCapacity;
    public void StudentButtonOnAction(ActionEvent event) {
        createStudentAccount();
    }
    public void TeacherButtonOnAction(ActionEvent event) {
        createTeacherAccount();
    }
    public void CourseButtonOnAction(ActionEvent event) {
        createCourseAccount();
    }
    public void CourseSelectionButtonOnAction(ActionEvent event) {
        createSelectionForm();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

           tcAddress.setCellValueFactory(new PropertyValueFactory<>("residenceAddress"));
           tcLName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
           tcFName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
           tcRD.setCellValueFactory(new PropertyValueFactory<>("registerNumber"));
           tcGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
           tcID.setCellValueFactory(new PropertyValueFactory<>("sisiId"));
           tcPass.setCellValueFactory(new PropertyValueFactory<>("password"));
           tcProgram.setCellValueFactory(new PropertyValueFactory<>("schoolProgram"));
           tcPhoneNum.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

           data = FXCollections.observableArrayList(

           );

           tbStudents.setItems(data);

           tcAddress1.setCellValueFactory(new PropertyValueFactory<>("address"));
           tcLName1.setCellValueFactory(new PropertyValueFactory<>("lastName"));
           tcFName1.setCellValueFactory(new PropertyValueFactory<>("firstName"));
           tcDegree1.setCellValueFactory(new PropertyValueFactory<>("selectedDegree"));
           tcRD1.setCellValueFactory(new PropertyValueFactory<>("registerNumber"));
           tcGender1.setCellValueFactory(new PropertyValueFactory<>("gender"));
           tcID1.setCellValueFactory(new PropertyValueFactory<>("instructorId"));
           tcPass1.setCellValueFactory(new PropertyValueFactory<>("password"));
           tcProgram1.setCellValueFactory(new PropertyValueFactory<>("program"));
           tcPhoneNum1.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

           data1 = FXCollections.observableArrayList();

           tbTeachers.setItems(data1);

        tbCourses.setItems(data3);

        tcIDX.setCellValueFactory(new PropertyValueFactory<>("courseIndex"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        tcDegreeCourse.setCellValueFactory(new PropertyValueFactory<>("courseLevel"));
        tcCredit.setCellValueFactory(new PropertyValueFactory<>("credit"));
        tcLecture.setCellValueFactory(new PropertyValueFactory<>("timePerWeekLectures"));
        tcSeminar.setCellValueFactory(new PropertyValueFactory<>("timePerWeekLabs"));
        tcSeason.setCellValueFactory(new PropertyValueFactory<>("termOfCourse"));
        tcPurpose.setCellValueFactory(new PropertyValueFactory<>("purposeOfCourse"));
        tcContent.setCellValueFactory(new PropertyValueFactory<>("contentOfCourse"));
        tcKnowledge.setCellValueFactory(new PropertyValueFactory<>("knowledgeToBeAcquired"));

        data3 = FXCollections.observableArrayList();

        tbCourses.setItems(data3);

        tbCourseSelection.setItems(data2);

        tcLesson.setCellValueFactory(new PropertyValueFactory<>("lessons"));
        tcInstructor.setCellValueFactory(new PropertyValueFactory<>("teacher"));
        tcLecDay.setCellValueFactory(new PropertyValueFactory<>("lectureDates"));
        tcLecClass.setCellValueFactory(new PropertyValueFactory<>("lectureClasses"));
        tcLecTime.setCellValueFactory(new PropertyValueFactory<>("lectureTimes"));
        tcSemDay.setCellValueFactory(new PropertyValueFactory<>("seminarDates"));
        tcSemclass.setCellValueFactory(new PropertyValueFactory<>("seminarClasses"));
        tcSemTime.setCellValueFactory(new PropertyValueFactory<>("seminarTimes"));
        tcCapacity.setCellValueFactory(new PropertyValueFactory<>("courseCapacity"));


        data2 = FXCollections.observableArrayList();

        tbCourseSelection.setItems(data2);

        Image img = new Image("https://static.wikia.nocookie.net/isekai-ojisan/images/5/50/Yousuke_%28Anime%29.png/revision/latest?cb=20220706214655");
        profile.setFill(new ImagePattern(img));
    }
    public void createStudentAccount(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("student.fxml"));
            Parent root = loader.load();

            StudentController studentController = loader.getController();

            studentController.setStudentModuleController(this);

            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root, 600, 652));
            registerStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void createTeacherAccount(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("instructor.fxml"));
            Parent root = loader.load();

            InstructorController instructorController = loader.getController();

            instructorController.setStudentModuleController(this);

            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root, 600, 658));
            registerStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void createCourseAccount(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("course.fxml"));
            Parent root = loader.load();

            CourseController courseController = loader.getController();

            courseController.setStudentModuleController(this);

            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root, 603, 886));
            registerStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void createSelectionForm(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CourseSelection.fxml"));
            Parent root = loader.load();

            CourseSelectionController courseSelectionController = loader.getController();

            courseSelectionController.setStudentModuleController(this);

            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root, 603, 512));
            registerStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addStudentData(Student newStudent) {
        data.add(newStudent);
    }
    public void addTeacherData(Teacher newTeacher) {
        data1.add(newTeacher);
    }
    public void addCourseData(Course newCourse) {
        data3.add(newCourse);
    }
    public void addSelectionData(CourseSelection newSelection) {
        data2.add(newSelection);
    }
    @FXML
    private void removeStudent(ActionEvent event) {
        Student selectedStudent = tbStudents.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            data.remove(selectedStudent);
        }
    }
    @FXML
    private void removeTeacher(ActionEvent event) {
        Teacher selectedTeacher = tbTeachers.getSelectionModel().getSelectedItem();
        if (selectedTeacher != null) {
            data1.remove(selectedTeacher);
        }
    }
    @FXML
    private void removeCourse(ActionEvent event) {
        Course selectedCourse = tbCourses.getSelectionModel().getSelectedItem();
        if (selectedCourse != null) {
            data3.remove(selectedCourse);
        }
    }
    @FXML
    private void removeSelection(ActionEvent event) {
        CourseSelection selection = tbCourseSelection.getSelectionModel().getSelectedItem();
        if (selection != null) {
            data2.remove(selection);
        }
    }
    public void switchModule(ActionEvent event){
        if(event.getSource() == StudentModule) {
            studentForm.setVisible(true);
            teacherForm.setVisible(false);
            courseForm.setVisible(false);
            courseSelectionForm.setVisible(false);
        } else if (event.getSource() == TeacherModule) {
            studentForm.setVisible(false);
            teacherForm.setVisible(true);
            courseForm.setVisible(false);
            courseSelectionForm.setVisible(false);
        } else if (event.getSource() == CourseModule) {
            studentForm.setVisible(false);
            teacherForm.setVisible(false);
            courseForm.setVisible(true);
            courseSelectionForm.setVisible(false);
        } else if (event.getSource() == StudModule) {
            studentForm.setVisible(false);
            teacherForm.setVisible(false);
            courseForm.setVisible(false);
            courseSelectionForm.setVisible(true);
        }
    }
    public ObservableList<Teacher> getTeacherNames() {
        return data1;
    }

    public ObservableList<Course> getcourseNames() {
        return data3;
    }
    public void CloseButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) CloseButton.getScene().getWindow();
        stage.close();
    }
}
