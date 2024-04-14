package entities;

import main.Runner;

import java.util.ArrayList;
import java.util.List;

public class Student {

    public static boolean smch;
    public static String sFirstName, sLastName, S_ID;
    public static List<ArrayList<String>> students = new ArrayList<>();

    public Student() {
        this.sFirstName = Runner.sFirstname;
        this.sLastName = Runner.sLastname;
        this.S_ID = Runner.sID;
    }

    public static void registerStudent(String sFirstName, String sLastName, String S_ID) {
        ArrayList<String> student = new ArrayList<>();
        student.add(sFirstName);
        student.add(sLastName);
        student.add(S_ID);

        students.add(student);
        System.out.println("Registration successful! INFO below:");
        System.out.println(student);
    }

    public static void loginStudent(String sFirstName, String sLastName, String S_ID) {
        smch = false;
        for (ArrayList<String> s : students) {
            if (s.get(0).equals(sFirstName) && s.get(1).equals(sLastName) && s.get(2).equals(S_ID)) {
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
