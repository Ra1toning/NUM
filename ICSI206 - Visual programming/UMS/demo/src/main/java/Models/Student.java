package Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student {
    private final StringProperty sisiId;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty gender;
    private final StringProperty registerNumber;
    private final StringProperty phoneNumber;
    private final StringProperty residenceAddress;
    private final StringProperty schoolProgram;
    private final StringProperty password;

    // Constructor
    public Student(String sisiId, String firstName, String lastName, String gender,
                   String registerNumber, String phoneNumber, String residenceAddress,
                   String schoolProgram, String password) {
        this.sisiId = new SimpleStringProperty(sisiId);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.gender = new SimpleStringProperty(gender);
        this.registerNumber = new SimpleStringProperty(registerNumber);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.residenceAddress = new SimpleStringProperty(residenceAddress);
        this.schoolProgram = new SimpleStringProperty(schoolProgram);
        this.password = new SimpleStringProperty(password);
    }

    // Getters for StringProperty
    public StringProperty sisiIdProperty() {
        return sisiId;
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

    public StringProperty registerNumberProperty() {
        return registerNumber;
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public StringProperty residenceAddressProperty() {
        return residenceAddress;
    }

    public StringProperty schoolProgramProperty() {
        return schoolProgram;
    }

    public StringProperty passwordProperty() {
        return password;
    }
}
