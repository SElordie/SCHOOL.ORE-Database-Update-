package service;

import entities.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Teacher_Service {

    public String tName, tSurname, tchID;
    public static List<String> messageInboxT = new ArrayList<>();

    public Teacher_Service() {
        this.tName = Teacher.firstName;
        this.tSurname = Teacher.lastName;
        this.tchID = Teacher.T_ID;
    }

    public static void menuTeacher(String tName, String tSurname, String tchID) {
        Scanner sc = new Scanner(System.in);
        int menuChoice;
        boolean menuBool;

        do {
            menuBool = false;
            System.out.println("Welcome to TEACHER menu!"
                    + "\n----------\n1 - Account information. [|] 2 - Assign homework."
                    + "\n3 - Set marks. [|] 4 - Set attendance."
                    + "\n5 - Notify parents. [|] 6 - View inbox."
                    + "\n0 - EXIT.");
            menuChoice = sc.nextInt();

            switch (menuChoice) {
                case 1:
                    accountInfoT(tName, tSurname, tchID);
                    break;

                case 2:
                    assignHomework();
                    break;

                case 3:
                    setMarks();
                    break;

                case 4:
                    setAttendance();
                    break;

                case 5:
                    notifyParents();
                    break;

                case 6:
                    viewInbox();
                    break;

                case 0:
                    System.err.println("EXIT MADE ::: logged out of teacher account!");
                    break;

                default:
                    System.err.println("INVALID DATA ::: Please try again!");
                    menuBool = true;
            }
        } while (menuBool);
    }

    public static void accountInfoT(String accNameT, String accSurnameT, String accTID) {
        System.out.println("teach_account");
    }

    public static void assignHomework() {
        System.out.println("teach_homework");
    }

    public static void setMarks() {
        System.out.println("teach_marks");
    }

    public static void setAttendance() {
        System.out.println("teach_attendance");
    }

    public static void notifyParents() {
        System.out.println("teach_messageParents");
    }

    public static void viewInbox() {
        System.out.println("Messages from Students:");
        for (String message : messageInboxT) {
            System.out.println(message);
        }
    }

    public static void receiveMessageT(String msgT) {
        messageInboxT.add(msgT);
    }

}
