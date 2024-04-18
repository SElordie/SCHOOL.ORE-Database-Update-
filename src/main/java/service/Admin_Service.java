package service;

import entities.Admin;

import java.util.Scanner;

public class Admin_Service {

    public String aName, aSurname, admID;

    public Admin_Service() {
        this.aName = Admin.aFirstName;
        this.aSurname = Admin.aLastName;
        this.admID = Admin.A_ID;
    }

    public static void menuAdmin(String aName, String aSurname, String admID) {
        Scanner sc = new Scanner(System.in);
        int menuChoice;
        boolean menuBool;

        do {
            menuBool = false;
            System.out.println("Welcome to ADMIN menu!"
                    + "\n----------\n1 - Account information. [|] 2 - View teachers."
                    + "\n3 - View students. [|] 4 - Remove a teacher."
                    + "\n5 - Remove a student. [|] 6 - Message teachers."
                    + "\n0 - EXIT.");
            menuChoice = sc.nextInt();

            switch (menuChoice) {
                case 1:
                    accountInfoA(aName, aSurname, admID);
                    break;

                case 2:
                    viewTeachers();
                    break;

                case 3:
                    viewStudents();
                    break;

                case 4:
                    removeTeacherBYadmin();
                    break;

                case 5:
                    removeStudentBYadmin();
                    break;

                case 6:
                    messageTeachers();
                    break;

                case 0:
                    System.err.println("EXIT MADE ::: logged out of admin account!");
                    break;

                default:
                    System.err.println("INVALID DATA ::: Please try again!");
                    menuBool = true;
            }
        } while (menuBool);
    }

    private static void accountInfoA(String aName, String aSurname, String admID) {
    }

    private static void viewTeachers() {
    }

    private static void viewStudents() {
    }

    private static void removeTeacherBYadmin() {

    }

    private static void removeStudentBYadmin() {

    }

    private static void messageTeachers() {

    }

}
