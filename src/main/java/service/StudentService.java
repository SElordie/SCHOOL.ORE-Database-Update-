package service;

import entities.Student;
import entities.Teacher;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

import static databank.Databazz.*;
import static entities.Student.students;
import static entities.Teacher.teachers;
import static runner.MainRunner.*;
import static utilities.Utilz.*;

public class StudentService {

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

                                if (firstrunS) {
                                    Student student = new Student(firstName, lastName, ID);
                                    students.add(student);
                                    ss.remove(Integer.valueOf(convertedID));
                                    dataRegisterStudent(firstName, lastName, ID);

                                    System.out.println("Registration successful! INFO below:");
                                    System.out.println(student);
                                } else {
                                    if (ss.contains(convertedID)) {
                                        Student student = new Student(firstName, lastName, ID);
                                        students.add(student);
                                        ss.remove(Integer.valueOf(convertedID));
                                        dataRegisterStudent(firstName, lastName, ID);

                                        System.out.println("Registration successful! INFO below:");
                                        System.out.println(student);
                                    } else {
                                        System.out.println("This ID is not available!");
                                        reg = true;
                                    }
                                }
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
        for (Student student : students) {
            if (SLOGGED) {
                System.out.println("Login successful!");
                accountStudent(student.getFirstName(), student.getLastName(), student.getID());
                return;
            }
        }
//        for (Student student : students) {
//            if (enteredID.equals(student.getID())) {
//                System.out.println("Login successful!");
//                accountStudent(student.getFirstName(), student.getLastName(), student.getID());
//                return;
//            }
//        }
        System.out.println("ERROR: Invalid ID. Please try again.");
    }

    private static void accountStudent(String fName, String lName, String id) {
        Scanner sc = new Scanner(System.in);
        boolean menuExit = false;

        do {
            System.out.println("_____| STUDENT MENU |_____");
            System.out.println("1 - Account information");
            System.out.println("2 - View homework");
            System.out.println("3 - View marks");
            System.out.println("4 - Set attendance");
            System.out.println("5 - Notify teachers");
            System.out.println("0 - EXIT");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    displayInfoS(fName, lName, id);
                    break;

                case 2:
                    Homework.viewHomework(id);
                    break;

                case 3:
                    Marks.viewMarks();
                    break;

                case 4:

                    break;

                case 5:
                    Messaging.notifyTeachers();
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

    private static void displayInfoS(String name, String surname, String id) {
        System.out.println("STUDENT - Account Information:");
        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("ID: " + id);
    }

}
