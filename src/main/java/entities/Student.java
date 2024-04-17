package entities;

import main.Runner;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public static void removeStudent() {
        Scanner sc = new Scanner(System.in);
        int sNR, confRemove; boolean removeBool;

        do {
            removeBool = false;
            System.out.println("You are about to permanently REMOVE a student!");
            System.out.println("Would you like to proceed?\n1 - CONTINUE\n0 - CANCEL");
            confRemove = sc.nextInt();

            if (confRemove == 1) {
                if (students.isEmpty()) {
                    System.err.println("ERROR ::: There aren't any students registered!");
                } else {
                    System.out.println("LIST of registered Students:");
                    for (int i = 0; i < students.size(); i++) {
                        System.out.println("S-NR: " + i + ", " + students.get(i));
                    }

                    System.out.print("Enter the S-NR of the student you wish to remove: ");
                    sNR = sc.nextInt();

                    if (sNR < 0 || sNR >= students.size()) {
                        System.err.println("INVALID S-NR ::: Please try again!");
                        removeBool = true;
                    } else {
                        students.remove(sNR);
                        System.out.println("Student by the S-NR: " + sNR + " has been removed!");
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
