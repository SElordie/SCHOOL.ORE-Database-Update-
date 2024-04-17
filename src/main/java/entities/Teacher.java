package entities;

import main.Runner;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public static void removeTeacher() {
        Scanner sc = new Scanner(System.in);
        int tNR, confRemove; boolean removeBool;

        do {
            removeBool = false;
            System.out.println("You are about to permanently REMOVE a teacher!");
            System.out.println("Would you like to proceed?\n1 - CONTINUE\n0 - CANCEL");
            confRemove = sc.nextInt();

            if (confRemove == 1) {
                if (teachers.isEmpty()) {
                    System.err.println("ERROR ::: There aren't any teachers registered!");
                } else {
                    System.out.println("LIST of registered Teachers:");
                    for (int i = 0; i < teachers.size(); i++) {
                        System.out.println("T-NR: " + i + ", " + teachers.get(i));
                    }

                    System.out.print("Enter the T-NR of the teacher you wish to remove: ");
                    tNR = sc.nextInt();

                    if (tNR < 0 || tNR >= teachers.size()) {
                        System.err.println("INVALID T-NR ::: Please try again!");
                        removeBool = true;
                    } else {
                        teachers.remove(tNR);
                        System.out.println("Teacher by the T-NR: " + tNR + " has been removed!");
                    }
                }
            } else if (confRemove == 0) {
                System.err.println("Process CANCELLED!");
                break;
            } else {
                System.err.println("INVALID DATA ::: Please try again!");
                removeBool = true;
            }
        } while (removeBool);
    }

}
