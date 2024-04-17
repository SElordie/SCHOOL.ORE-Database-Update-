package entities;

import main.Runner;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin {

    public static boolean amch;
    public static String aFirstName, aLastName, A_ID;
    public static List<ArrayList<String>> admins = new ArrayList<>();

    public Admin() {
        this.aFirstName = Runner.aFirstname;
        this.aLastName = Runner.aLastname;
        this.A_ID = Runner.aID;
    }

    public static void registerAdmin(String aFirstName, String aLastName, String A_ID) {
        ArrayList<String> admin = new ArrayList<>();
        admin.add(aFirstName);
        admin.add(aLastName);
        admin.add(A_ID);

        admins.add(admin);
        System.out.println("Registration successful! INFO below:");
        System.out.println(admin);
    }

    public static void loginAdmin(String aFirstName, String aLastName, String A_ID) {
        amch = false;
        for (ArrayList<String> a : admins) {
            if (a.get(0).equals(aFirstName) && a.get(1).equals(aLastName) && a.get(2).equals(A_ID)) {
                amch = true;
            } else {
                amch = false;
            }
        }

        if (amch) {
            System.out.println("Login successful!");
        } else {
            System.err.println("ERROR! Login failed, please try again!");
        }
    }

    public static void removeAdmin() {
        Scanner sc = new Scanner(System.in);
        int aNR, confRemove; boolean removeBool;

        do {
            removeBool = false;
            System.out.println("You are about to permanently REMOVE an admin!");
            System.out.println("Would you like to proceed?\n1 - CONTINUE\n0 - CANCEL");
            confRemove = sc.nextInt();

            if (confRemove == 1) {
                if (admins.isEmpty()) {
                    System.err.println("ERROR ::: There aren't any admins registered!");
                } else {
                    System.out.println("LIST of registered Admins:");
                    for (int i = 0; i < admins.size(); i++) {
                        System.out.println("A-NR: " + i + ", " + admins.get(i));
                    }

                    System.out.print("Enter the A-NR of the student you wish to remove: ");
                    aNR = sc.nextInt();

                    if (aNR < 0 || aNR >= admins.size()) {
                        System.err.println("INVALID A-NR ::: Please try again!");
                        removeBool = true;
                    } else {
                        admins.remove(aNR);
                        System.out.println("Admin by the A-NR: " + aNR + " has been removed!");
                    }
                }
            } else if (confRemove == 0) {
                System.err.println("Process CANCELLED!");
                break;
            } else {
                System.err.println("INVALID DATA ::: Please try again!");
                removeBool = true;
            }
        } while (removeBool);
    }

}
