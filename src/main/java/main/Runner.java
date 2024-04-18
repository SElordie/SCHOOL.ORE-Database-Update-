package main;

import java.util.Scanner;

import static entities.Admin.*;
import static entities.Student.*;
import static entities.Teacher.*;
import static service.Admin_Service.menuAdmin;
import static service.Student_Service.menuStudent;
import static service.Teacher_Service.menuTeacher;
import static utilities.Utilz.containsNumber;

public class Runner {

    public static boolean firstRUN_tch = teachers.isEmpty();
    public static boolean firstRUN_std = students.isEmpty();
    public static boolean firstRUN_adm = admins.isEmpty();
    public static String tFirstname, tLastname, tID, sFirstname, sLastname, sID;
    public static String aFirstname, aLastname, aID;

    public static void main(String[] args) {
        startApp();
    }

    public static void startApp() {
        Scanner sc = new Scanner(System.in);
        String identityChoice = null; int logreg;
        boolean startBool = false, idBool;

        do {
            idBool = false;
            if (!startBool) {
                System.out.println("~~~~~ Welcome to the SCHOOL.ORE application! ~~~~~");
                System.out.println("  ----- Please choose your identity! -----");
                System.out.println("T (teacher) ||| S (student) ||| A (admin)");
                identityChoice = sc.next();
            } else if (startBool) {
                System.out.println("T (teacher) ||| S (student) ||| A (admin)");
                identityChoice = sc.next();
            }

            switch (identityChoice) {
                case "T", "t":
                    if (firstRUN_tch) {
                        do {
                            System.out.println("Please enter the necessary data to make a registration!");
                            System.out.println("First name: "); tFirstname = sc.next();
                            System.out.println("Last name: "); tLastname = sc.next();
                            System.out.println("ID [custom, from 11 to 99]: "); tID = sc.next();

                            if (containsNumber(tFirstname) || containsNumber(tLastname)) {
                                System.err.println("ERROR ::: Name can NOT contain any digits!");
                                System.err.flush();
                                System.err.println("Please try again!");
                                System.err.flush();
                            } else {
                                int parsedID = Integer.parseInt(tID);
                                if (parsedID >= 11 && parsedID <= 99) {
                                    idBool = true;
                                    registerTeacher(tFirstname, tLastname, tID);
                                    firstRUN_tch = false; System.out.println("");
                                    menuTeacher(firstName, lastName, T_ID);
                                } else {
                                    System.err.println("INVALID ID ::: Please try again!");
                                    System.err.flush();
                                }
                            }
                        } while (!idBool);
                    } else {
                        do {
                            System.out.println("Would you like to login or make a new registration?");
                            System.out.println("1 - LOGIN.\n2 - REGISTER.");
                            logreg = sc.nextInt();

                            if (logreg == 1) {
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
                            } else if (logreg == 2) {
                                System.out.println("Please enter the necessary data to make a registration!");
                                System.out.println("First name: "); tFirstname = sc.next();
                                System.out.println("Last name: "); tLastname = sc.next();
                                System.out.println("ID [custom, from 11 to 99]: "); tID = sc.next();

                                if (containsNumber(tFirstname) || containsNumber(tLastname)) {
                                    System.err.println("ERROR ::: Name can NOT contain any digits!");
                                    System.err.flush();
                                    System.err.println("Please try again!");
                                    System.err.flush();
                                } else {
                                    int parsedID = Integer.parseInt(tID);
                                    if (parsedID >= 11 && parsedID <= 99) {
                                        idBool = true;
                                        registerTeacher(tFirstname, tLastname, tID);
                                        firstRUN_tch = false; System.out.println("");
                                        menuTeacher(firstName, lastName, T_ID);
                                    } else {
                                        System.err.println("INVALID ID ::: Please try again!");
                                        System.err.flush();
                                    }
                                }
                            } else {
                                System.err.println("INVALID DATA ::: Please try again!");
                                System.err.flush(); tmch = false;
                            }
                        } while (!tmch);
                    }
                    startBool = false;
                    break;

                case "S", "s":
                    if (firstRUN_std) {
                        do {
                            System.out.println("Please enter the necessary data to make a registration!");
                            System.out.println("First name: "); sFirstname = sc.next();
                            System.out.println("Last name: "); sLastname = sc.next();
                            System.out.println("ID [custom, from 100 to 200]: "); sID = sc.next();

                            if (containsNumber(sFirstname) || containsNumber(sLastname)) {
                                System.err.println("ERROR ::: Name can NOT contain any digits!");
                                System.err.flush();
                                System.err.println("Please try again!");
                                System.err.flush();
                            } else {
                                int parsedID = Integer.parseInt(sID);
                                if (parsedID >= 100 && parsedID <= 200) {
                                    idBool = true;
                                    registerStudent(sFirstname, sLastname, sID);
                                    firstRUN_std = false;
                                    System.out.println("");
                                    menuStudent(sFirstName, sLastName, S_ID);
                                } else {
                                    System.err.println("INVALID ID ::: Please try again!");
                                    System.err.flush();
                                }
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

                case "A", "a":
                    if (firstRUN_adm) {
                        do {
                            System.out.println("Please enter the necessary data to make a registration!");
                            System.out.println("First name: "); aFirstname = sc.next();
                            System.out.println("Last name: "); aLastname = sc.next();
                            System.out.println("ID [custom, from 1 to 3]: "); aID = sc.next();

                            if (containsNumber(aFirstname) || containsNumber(aLastname)) {
                                System.err.println("ERROR ::: Name can NOT contain any digits!");
                                System.err.flush();
                                System.err.println("Please try again!");
                                System.err.flush();
                            } else {
                                int parsedID = Integer.parseInt(aID);
                                if (parsedID >= 1 && parsedID <= 3) {
                                    idBool = true;
                                    registerAdmin(aFirstname, aLastname, aID);
                                    firstRUN_adm = false;
                                    System.out.println("");
                                    menuAdmin(aFirstName, aLastName, A_ID);
                                } else {
                                    System.err.println("INVALID ID ::: Please try again!");
                                    System.err.flush();
                                }
                            }
                        } while (!idBool);
                    } else {
                        do {
                            System.out.println("Enter your information!");
                            System.out.println("First name: ");
                            aFirstname = sc.next();
                            System.out.println("Last name: ");
                            aLastname = sc.next();
                            System.out.println("ID: ");
                            aID = sc.next();

                            loginAdmin(aFirstname, aLastname, aID);

                            if (!amch) {
                                System.out.println("");
                            } else {
                                System.out.println("");
                                menuAdmin(aFirstName, aLastName, A_ID);
                            }
                        } while (!amch);
                    }

                default:
                    System.err.println("INVALID DATA ::: Please try again!");
                    System.err.flush();
                    startBool = true;
            }

            while (!startBool) {
                System.out.println("Press 1 to RERUN application\nPress 0 to EXIT");
                int rerun = sc.nextInt();

                if (rerun == 1) {
                    startBool = true;
                } else if (rerun == 0) {
                    System.err.println("EXIT MADE!");
                    System.err.flush();
                    break;
                } else {
                    System.err.println("INVALID DATA ::: please try again!");
                    System.err.flush();
                }
            }
        } while (startBool);
    }

}
