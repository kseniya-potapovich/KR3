package model;

import java.util.Comparator;
import java.util.Objects;

public class Student implements Comparable<Student> {
    private int id;
    private String firstName;
    private String lastName;
    private double avg;
    private String fileName;

    public Student(int id, String firstName, String lastName, double avg, String fileName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avg = avg;
        this.fileName = fileName;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getAvg() {
        return avg;
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public String toString() {
        return "id: " + id + "\n" +
                "firstName: " + firstName + "\n" +
                "lastName: " + lastName + "\n" +
                "avg: " + avg + "\n" +
                "fileName: " + fileName + "\n";
    }

    @Override
    public int compareTo(Student other) {
        return Objects.compare(this, other, Comparator.comparing(Student::getLastName).thenComparing(Student::getFirstName).thenComparing(Comparator.comparing(Student::getAvg).reversed()));
    }
}
