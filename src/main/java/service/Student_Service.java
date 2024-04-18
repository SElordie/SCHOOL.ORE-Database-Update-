package service;

import entities.Student;
import entities.Teacher;
import service.Teacher_Service;

import java.util.Scanner;

public class Student_Service {

    public String sName, sSurname, stdID;

    public Student_Service() {
        this.sName = Student.sFirstName;
        this.sSurname = Student.sLastName;
        this.stdID = Student.S_ID;
    }

    public static void menuStudent(String sName, String sSurname, String stdID) {
        Scanner sc = new Scanner(System.in);
        int menuChoice;
        boolean menuBool;

        do {
            menuBool = false;
            System.out.println("Welcome to STUDENT menu!"
                    + "\n----------\n1 - Account information. [|] 2 - View homework."
                    + "\n3 - View marks. [|] 4 - View attendance."
                    + "\n5 - Notify teachers. [|] 0 - EXIT.");
            menuChoice = sc.nextInt();

            switch (menuChoice) {
                case 1:
                    accountInfoS(sName, sSurname, stdID);
                    break;

                case 2:
                    viewHomework();
                    break;

                case 3:
                    viewMarks();
                    break;

                case 4:
                    viewAttendance();
                    break;

                case 5:
                    notifyTeachers();
                    break;

                case 0:
                    System.err.println("EXIT MADE ::: logged out of student account!");
                    break;

                default:
                    System.err.println("INVALID DATA ::: Please try again!");
                    menuBool = true;
            }
        } while (menuBool);
    }

    public static void accountInfoS(String accNameS, String accSurnameS, String accSID) {
        System.out.println("Account Information:");
        System.out.println("Name: " + accNameS);
        System.out.println("Surname: " + accSurnameS);
        System.out.println("Student ID: " + accSID);
    }

    public static void viewHomework() {
        System.out.println("menu_homework");
    }

    public static void viewMarks() {
        System.out.println("menu_marks");
    }

    public static void viewAttendance() {
        System.out.println("menu_attendance");
    }

    public static void notifyTeachers() {
        Scanner sc = new Scanner(System.in);
        int teacherIndex;

        System.out.println("List of Teachers:");
        for (int i = 0; i < Teacher.teachers.size(); i++) {
            System.out.println(i + ": " + Teacher.teachers.get(i));
        }

        System.out.println("Choose a teacher by index to send a message:");
        teacherIndex = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter your message:");
        String message = sc.nextLine();

        if (teacherIndex >= 0 && teacherIndex < Teacher.teachers.size()) {
            System.out.println("Message sent to " + Teacher.teachers.get(teacherIndex));
        } else {
            System.err.println("ERROR ::: Invalid teacher index!");
            System.err.flush();
        }
    }

}
