package main;

import java.util.Scanner;

public class Runner {

    public static boolean firstRUN = true;

    public static void main(String[] args) {
        startApp();
    }

    public static void startApp() {
        Scanner sc = new Scanner(System.in);
        String identityChoice = null;
        String tFirstname, tLastname, tID, sFirstname, sLastname, sID;
        boolean startBool = false;

        do {
            if (!startBool) {
                startBool = false;
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
                        System.out.println("Please enter the necessary data to make a registration!");
                        System.out.println("First name: "); tFirstname = sc.next();
                        System.out.println("Last name: "); tLastname = sc.next();
                        System.out.println("ID [custom, from 10 to 99]: "); tID = sc.next();
                        loginTeacher(tFirstname, tLastname, tID);
                        firstRUN = false;
                    } else {
                        System.out.println("Enter your information!");
                        System.out.println("First name: "); tFirstname = sc.next();
                        System.out.println("Last name: "); tLastname = sc.next();
                        System.out.println("ID: "); tID = sc.next();
                        loginTeacher(tFirstname, tLastname, tID);
                    }
                    startBool = false;
                    break;

                case "S", "s":
                    if (firstRUN) {
                        System.out.println("Please enter the necessary data to make a registration!");
                        System.out.println("First name: "); sFirstname = sc.next();
                        System.out.println("Last name: "); sLastname = sc.next();
                        System.out.println("ID [custom, from 10 to 99]: "); sID = sc.next();
                        loginStudent(sFirstname, sLastname, sID);
                        firstRUN = false;
                    } else {
                        System.out.println("Enter your information!");
                        System.out.println("First name: "); sFirstname = sc.next();
                        System.out.println("Last name: "); sLastname = sc.next();
                        System.out.println("ID: "); sID = sc.next();
                        loginStudent(sFirstname, sLastname, sID);
                    }
                    startBool = false;
                    break;

                default:
                    System.err.println("INVALID DATA ::: Please try again!");
                    startBool = true;
            }
        } while (startBool);
    }

}
