package Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Teacher {
    private final StringProperty instructorId;
    private final StringProperty firstName;
    private final StringProperty phoneNumber;
    private final StringProperty registerNumber;
    private final StringProperty lastName;
    private final StringProperty gender;
    private final StringProperty selectedDegree;
    private final StringProperty address;
    private final StringProperty program;
    private final StringProperty password;

    // Constructor
    public Teacher(String instructorId, String firstName, String lastName, String gender,
                   String selectedDegree,String phoneNumber, String address, String program, String password, String registerNumber) {
        this.instructorId = new SimpleStringProperty(instructorId);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.gender = new SimpleStringProperty(gender);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.selectedDegree = new SimpleStringProperty(selectedDegree);
        this.address = new SimpleStringProperty(address);
        this.program = new SimpleStringProperty(program);
        this.password = new SimpleStringProperty(password);
        this.registerNumber = new SimpleStringProperty(registerNumber);
    }

    // Getters for StringProperty
    public StringProperty instructorIdProperty() {
        return instructorId;
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public StringProperty genderProperty() {
        return gender;
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public StringProperty selectedDegreeProperty() {
        return selectedDegree;
    }

    public StringProperty addressProperty() {
        return address;
    }
    public StringProperty registerNumberProperty() {
        return registerNumber;
    }

    public StringProperty programProperty() {
        return program;
    }

    public StringProperty passwordProperty() {
        return password;
    }
}
