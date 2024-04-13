package entities;

import main.Runner;

public class Teacher {

    public String firstName, lastName, T_ID;

    public Teacher() {
        this.firstName = Runner.tFirstname;
        this.lastName = Runner.tLastname;
        this.T_ID = Runner.tID;
    }

    public static void registerTeacher(String firstName, String lastName, String T_ID) {
        System.out.println("Login successful!");
        System.out.println("First name: " + firstName); System.out.println("Last name: " + lastName);
        System.out.println("ID: " + T_ID); System.out.println("");
    }

    public static void loginTeacher(String firstName, String lastName, String T_ID) {

    }

}
