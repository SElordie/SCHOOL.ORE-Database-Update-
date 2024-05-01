package service;

import entities.Student;

import java.sql.*;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

import static databank.Databazz.*;
import static service.Attendance.dataViewAttendance;
import static service.Homework.dataViewHomework;
import static service.Marks.dataViewMarks;
import static service.Messaging.dataNotifyTeachers;
import static utilities.Utilz.*;

public class StudentService {

    public static Student student;
    public static AtomicBoolean sBool = new AtomicBoolean();

    public static void studentSection(AtomicBoolean closeapp) throws SQLException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            sBool.set(false);

            System.out.println("------ STUDENT SECTION ------");
            System.out.println("1 - Register\n2 - Login\n3 - Return to MAIN MENU\n0 - EXIT APP");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    registerStudent();
                    break;

                case 2:
                    loginStudent();
                    break;

                case 3:
                    System.out.println("BACK TO MAIN MENU");
                    break;

                case 0:
                    System.out.println("APP CLOSED!");
                    closeapp.set(true);
                    break;

                default:
                    invalidChoiceError(sBool);
            }
        } while (sBool.get());
    }

    public static void registerStudent() throws SQLException, ClassNotFoundException {
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
                            System.out.print("[] ID {from 100 to 500}: ");
                            String ID = sc.next();

                            int convertedID = Integer.parseInt(ID);
                            if (convertedID >= 100 && convertedID <= 500) {
                                dataRegisterStudent(firstName, lastName, convertedID);
                                System.out.println("Registration successful! INFO below:");

                                dataReglookStudent(firstName, lastName, convertedID);
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

    public static void loginStudent() throws SQLException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("STUDENT LOGIN");
        System.out.print("Enter your ID: ");
        String enteredID = sc.next();

        dataLoginStudent(enteredID);
        if (SLOGGED) {
            student = new Student("", "", enteredID);
            int IDint = Integer.parseInt(enteredID);

            System.out.println("Login successful!");
            accountStudent(IDint);
        } else {
            System.out.println("ERROR: Invalid ID. Please try again.");
        }
    }

    private static void accountStudent(int sID) throws SQLException {
        Scanner sc = new Scanner(System.in);
        boolean menuExit = false;

        do {
            System.out.println("_____| STUDENT MENU |_____");
            System.out.println("1 - Account information");
            System.out.println("2 - View homework");
            System.out.println("3 - View marks");
            System.out.println("4 - View attendance");
            System.out.println("5 - Notify teachers");
            System.out.println("0 - EXIT");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    dataInfoStudent(sID);
                    break;

                case 2:
                    dataViewHomework(sID);
                    break;

                case 3:
                    dataViewMarks(sID);
                    break;

                case 4:
                    dataViewAttendance(sID);
                    break;

                case 5:
                    dataNotifyTeachers(sID);
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

    public static void dataInfoStudent(int id) throws SQLException {
        Connection con = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/schoolore", "ipro", "121983");
        Statement datasat = con.createStatement();

        String query = ("SELECT * FROM students WHERE stdid = " + id);
        ResultSet rq = datasat.executeQuery(query);

        System.out.println("STUDENT - Account Information:");
        while (rq.next()) {
            String studentName = rq.getString("student_name");
            String studentSurname = rq.getString("student_surname");
            String studentID = rq.getString("stdid");

            System.out.println("Name: " + studentName);
            System.out.println("Surname: " + studentSurname);
            System.out.println("ID: " + studentID);
        }

        datasat.close();
        con.close();
    }

    public static void dataReglookStudent(String name, String surname, int id) {
        System.out.println("[name: " + name + " | surname: " + surname + " | ID: " + id + "]");
    }

}
