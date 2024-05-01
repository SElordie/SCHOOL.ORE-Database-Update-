package service;

import entities.Teacher;

import java.sql.*;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

import static databank.Databazz.*;
import static service.Attendance.dataSetAttendance;
import static service.Homework.dataGiveHomework;
import static service.Marks.dataGiveMarks;
import static service.Messaging.dataViewInbox;
import static utilities.Utilz.*;

public class TeacherService {

    public static Teacher teacher;
    public static AtomicBoolean tBool = new AtomicBoolean();

    public static void teacherSection(AtomicBoolean closeapp) throws SQLException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            tBool.set(false);

            System.out.println("------ TEACHER SECTION ------");
            System.out.println("1 - Register\n2 - Login\n3 - Return to MAIN MENU\n0 - EXIT APP");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    registerTeacher();
                    break;

                case 2:
                    loginTeacher();
                    break;

                case 3:
                    System.out.println("BACK TO MAIN MENU");
                    break;

                case 0:
                    System.out.println("APP CLOSED!");
                    closeapp.set(true);
                    break;

                default:
                    invalidChoiceError(tBool);
            }
        } while (tBool.get());
    }

    public static void registerTeacher() throws SQLException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        boolean reg; int cn;

        do {
            reg = false;

            System.out.println("---| CONFIRM |---");
            System.out.println("Press 1 to continue, 0 to exit");
            cn = sc.nextInt();

            switch (cn) {
                case 1:
                    System.out.println("****** REGISTRATION ******");
                    System.out.println("Please fill in the necessary data:");
                    System.out.print("[] First name: ");
                    String firstName = sc.next();

                    String check = firstNameEXCEPTION(firstName);
                    if (check.equals(firstName)) {
                        System.out.print("[] Last name: ");
                        String lastName = sc.next();

                        check = lastNameEXCEPTION(lastName);
                        if (check.equals(lastName)) {
                            System.out.print("[] ID {from 11 to 99}: ");
                            String ID = sc.next();

                            int convertedID = Integer.parseInt(ID);
                            if (convertedID >= 11 && convertedID <= 99) {
                                dataRegisterTeacher(firstName, lastName, convertedID);

                                System.out.println("Registration successful! INFO below:");
                                dataReglookTeacher(firstName, lastName, convertedID);
                            } else {
                                System.out.println("ERROR ::: INVALID DATA - Please try again!");
                                reg = true;
                            }
                        } else {
                            System.out.println("ERROR ::: INVALID DATA - Please try again!");
                            reg = true;
                        }
                    } else {
                        System.out.println("ERROR ::: INVALID DATA - Please try again!");
                        reg = true;
                    }
                    break;

                case 0:
                    System.out.println("EXIT MADE!");
                    break;

                default:
                    System.out.println("ERROR ::: INVALID DATA - Please try again!");
                    reg = true;
            }
        } while (reg);
    }

    public static void loginTeacher() throws SQLException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("TEACHER LOGIN");
        System.out.print("Enter your ID: ");
        String enteredID = sc.next();

        dataLoginTeacher(enteredID);
        if (TLOGGED) {
            teacher = new Teacher("", "", enteredID);
            int IDint = Integer.parseInt(enteredID);

            System.out.println("Login successful!");
            accountTeacher(IDint);
        } else {
            System.out.println("ERROR: Invalid ID. Please try again.");
        }
    }

    private static void accountTeacher(int tID) throws SQLException {
        Scanner sc = new Scanner(System.in);
        boolean menuExit = false;

        do {
            System.out.println("_____| TEACHER MENU |_____");
            System.out.println("1 - Account information");
            System.out.println("2 - Assign homework");
            System.out.println("3 - Set marks");
            System.out.println("4 - Set attendance");
            System.out.println("5 - View inbox");
            System.out.println("0 - EXIT");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    dataInfoTeacher(tID);
                    break;

                case 2:
                    dataGiveHomework(tID);
                    break;

                case 3:
                    dataGiveMarks(tID);
                    break;

                case 4:
                    dataSetAttendance();
                    break;

                case 5:
                    dataViewInbox(tID);
                    break;

                case 0:
                    System.out.println("EXIT MADE!");
                    menuExit = true;
                    break;

                default:
                    System.out.println("ERROR: Invalid choice! Please try again.");
            }
        } while (!menuExit);
    }

    private static void dataInfoTeacher(int id) throws SQLException {
        Connection con = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/schoolore", "ipro", "121983");
        Statement datasat = con.createStatement();

        String query = ("SELECT * FROM teachers WHERE tchid = " + id);
        ResultSet rq = datasat.executeQuery(query);

        System.out.println("TEACHER - Account Information:");
        while (rq.next()) {
            String teacherName = rq.getString("teacher_name");
            String teacherSurname = rq.getString("teacher_surname");
            String teacherID = rq.getString("tchid");

            System.out.println("Name: " + teacherName);
            System.out.println("Surname: " + teacherSurname);
            System.out.println("ID: " + teacherID);
        }

        datasat.close();
        con.close();
    }

    private static void dataReglookTeacher(String name, String surname, int id) {
        System.out.println("[name: " + name + " | surname: " + surname + " | ID: " + id + "]");
    }

}
