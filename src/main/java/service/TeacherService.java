package service;

import entities.Teacher;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

import static databank.Databazz.*;
import static entities.Teacher.teachers;
import static runner.MainRunner.*;
import static service.Messaging.message;
import static utilities.Utilz.*;

public class TeacherService {

    public static AtomicBoolean tBool = new AtomicBoolean();
    private static List<String> messageInbox = new ArrayList<>();

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

                                if (firstrunT) {
                                    Teacher teacher = new Teacher(firstName, lastName, ID);
                                    teachers.add(teacher);
                                    tch.remove(Integer.valueOf(convertedID));
                                    dataRegisterTeacher(firstName, lastName, ID);

                                    System.out.println("Registration successful! INFO below:");
                                    System.out.println(teacher);
                                } else {
                                    if (tch.contains(convertedID)) {
                                        Teacher teacher = new Teacher(firstName, lastName, ID);
                                        teachers.add(teacher);
                                        tch.remove(Integer.valueOf(convertedID));

                                        System.out.println("Registration successful! INFO below:");
                                        System.out.println(teacher);
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

    public static void loginTeacher() throws SQLException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("TEACHER LOGIN");
        System.out.print("Enter your ID: ");
        String enteredID = sc.next();

        dataLoginTeacher(enteredID);
        for (Teacher teacher : teachers) {
            if (TLOGGED) {
                System.out.println("Login successful!");
                accountTeacher(teacher.getFirstName(), teacher.getLastName(), teacher.getID());
                return;
            }
        }
//        for (Teacher teacher : teachers) {
//            if (enteredID.equals(teacher.getID())) {
//                System.out.println("Login successful!");
//                accountTeacher(teacher.getFirstName(), teacher.getLastName(), teacher.getID());
//                return;
//            }
//        }
        System.out.println("ERROR: Invalid ID. Please try again.");
    }

    private static void accountTeacher(String fName, String lName, String id) {
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
                    displayInfoT(fName, lName, id);
                    break;

                case 2:
                    Homework.assignHomework();
                    break;

                case 3:
                    Marks.setMarks();
                    break;

                case 4:

                    break;

                case 5:
                    viewInbox();
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

    private static void displayInfoT(String name, String surname, String id) {
        System.out.println("TEACHER - Account Information:");
        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("ID: " + id);
    }

    private static void viewInbox() {
        receiveMessage(message);
        System.out.println("Messages from students:");
        for (String message : messageInbox) {
            System.out.println(message);
        }
    }

    public static void receiveMessage(String msg) {
        messageInbox.add(msg);
    }

}
