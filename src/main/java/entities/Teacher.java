package entities;

import main.Runner;

import java.util.ArrayList;
import java.util.List;

public class Teacher {

    public static boolean tmch;
    public static String firstName, lastName, T_ID;
    public static List<ArrayList<String>> teachers = new ArrayList<>();

    public Teacher() {
        this.firstName = Runner.tFirstname;
        this.lastName = Runner.tLastname;
        this.T_ID = Runner.tID;
    }

    public static void registerTeacher(String firstName, String lastName, String T_ID) {
        ArrayList<String> teacher = new ArrayList<>();
        teacher.add(firstName);
        teacher.add(lastName);
        teacher.add(T_ID);

        teachers.add(teacher);
        System.out.println("Registration successful! INFO below:");
        System.out.println(teacher);
    }

    public static void loginTeacher(String firstName, String lastName, String T_ID) {
        tmch = false;
        for (ArrayList<String> t : teachers) {
            if (t.get(0).equals(firstName) && t.get(1).equals(lastName) && t.get(2).equals(T_ID)) {
                tmch = true;
            } else {
                tmch = false;
            }
        }

        if (tmch) {
            System.out.println("Login successful!");
        } else {
            System.err.println("ERROR! Login failed, please try again!");
        }
    }

}
