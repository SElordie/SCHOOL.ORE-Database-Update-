package entities;

import main.Runner;

import java.util.ArrayList;
import java.util.List;

public class Student {

    public static boolean smch;
    public String firstName, lastName, S_ID;
    public static List<ArrayList<String>> students = new ArrayList<>();

    public Student() {
        this.firstName = Runner.sFirstname;
        this.lastName = Runner.sLastname;
        this.S_ID = Runner.sID;
    }

    public static void registerStudent(String firstName, String lastName, String S_ID) {
        ArrayList<String> student = new ArrayList<>();
        student.add(firstName);
        student.add(lastName);
        student.add(S_ID);

        students.add(student);
        System.out.println("Registration successful! INFO below:");
        System.out.println(student);
    }

    public static void loginStudent(String firstName, String lastName, String S_ID) {
        smch = false;
        for (ArrayList<String> s : students) {
            if (s.get(0).equals(firstName) && s.get(1).equals(lastName) && s.get(2).equals(S_ID)) {
                smch = true;
            } else {
                smch = false;
            }
        }

        if (smch) {
            System.out.println("Login successful!");
        } else {
            System.err.println("ERROR! Login failed, please try again!");
        }
    }

}
