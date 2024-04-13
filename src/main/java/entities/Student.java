package entities;

import main.Runner;

public class Student {

    public String firstName, lastName, S_ID;

    public Student() {
        this.firstName = Runner.sFirstname;
        this.lastName = Runner.sLastname;
        this.S_ID = Runner.sID;
    }

    public static void registerStudent(String firstName, String lastName, String S_ID) {
        System.out.println("Login successful!");
        System.out.println("First name: " + firstName); System.out.println("Last name: " + lastName);
        System.out.println("ID: " + S_ID); System.out.println("");
    }

    public static void loginStudent(String firstName, String lastName, String S_ID) {

    }

}
