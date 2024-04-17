package main;

import java.util.Scanner;

import static entities.Student.*;
import static entities.Teacher.*;
import static service.Student_Service.menuStudent;
import static service.Teacher_Service.menuTeacher;

public class Runner {

    public static boolean firstRUN = true;
    public static String tFirstname, tLastname, tID, sFirstname, sLastname, sID;

    public static void main(String[] args) {
        startApp();
    }

    public static void startApp() {
        Scanner sc = new Scanner(System.in);
        String identityChoice = null;
        boolean startBool = false, idBool;

        do {
            idBool = false;
            if (!startBool) {
                System.out.println("~~~~~ Welcome to the SCHOOL.ORE application! ~~~~~");
                System.out.println("  ----- Please choose your identity! -----");
                System.out.println("T (teacher) ||| S (student)");
                identityChoice = sc.next();
            } else if (startBool) {
                System.out.println("T (teacher) ||| S (student)");
                identityChoice = sc.next();
            }

            switch (identityChoice) {
                case "T", "t":
                    if (firstRUN) {
                        do {
                            System.out.println("Please enter the necessary data to make a registration!");
                            System.out.println("First name: "); tFirstname = sc.next();
                            System.out.println("Last name: "); tLastname = sc.next();
                            System.out.println("ID [custom, from 11 to 99]: "); tID = sc.next();

                            int parsedID = Integer.parseInt(tID);
                            if (parsedID >= 11 && parsedID <= 99) {
                                idBool = true;
                                registerTeacher(tFirstname, tLastname, tID);
                                firstRUN = false; System.out.println("");
                                menuTeacher(firstName, lastName, T_ID);
                            } else {
                                System.err.println("INVALID ID ::: Please try again!");
                            }
                        } while (!idBool);
                    } else {
                        do {
                            System.out.println("Enter your information!");
                            System.out.println("First name: "); tFirstname = sc.next();
                            System.out.println("Last name: "); tLastname = sc.next();
                            System.out.println("ID: "); tID = sc.next();

                            loginTeacher(tFirstname, tLastname, tID);

                            if (!tmch) {
                                System.out.println("");
                            } else {
                                System.out.println("");
                                menuTeacher(firstName, lastName, T_ID);
                            }
                        } while (!tmch);
                    }
                    startBool = false;
                    break;

                case "S", "s":
                    if (firstRUN) {
                        do {
                            System.out.println("Please enter the necessary data to make a registration!");
                            System.out.println("First name: "); sFirstname = sc.next();
                            System.out.println("Last name: "); sLastname = sc.next();
                            System.out.println("ID [custom, from 100 to 200]: "); sID = sc.next();

                            int parsedID = Integer.parseInt(sID);
                            if (parsedID >= 100 && parsedID <= 200) {
                                idBool = true;
                                registerStudent(sFirstname, sLastname, sID);
                                firstRUN = false; System.out.println("");
                                menuStudent(sFirstName, sLastName, S_ID);
                            } else {
                                System.err.println("INVALID ID ::: Please try again!");
                            }
                        } while (!idBool);
                    } else {
                        do {
                            System.out.println("Enter your information!");
                            System.out.println("First name: ");
                            sFirstname = sc.next();
                            System.out.println("Last name: ");
                            sLastname = sc.next();
                            System.out.println("ID: ");
                            sID = sc.next();

                            loginStudent(sFirstname, sLastname, sID);

                            if (!smch) {
                                System.out.println("");
                            } else {
                                System.out.println("");
                                menuStudent(sFirstName, sLastName, S_ID);
                            }
                        } while (!smch);
                    }
                    startBool = false;
                    break;

                default:
                    System.err.println("INVALID DATA ::: Please try again!");
                    startBool = true;
            }

            while (!startBool) {
                System.out.println("Press 1 to RERUN application\nPress 0 to EXIT");
                int rerun = sc.nextInt();

                if (rerun == 1) {
                    startBool = true;
                } else if (rerun == 0) {
                    System.err.println("EXIT MADE!");
                    break;
                } else {
                    System.err.println("INVALID DATA ::: please try again!");
                }
            }
        } while (startBool);
    }

}
