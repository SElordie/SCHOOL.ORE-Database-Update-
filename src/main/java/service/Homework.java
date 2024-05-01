package service;

import java.sql.*;
import java.util.Scanner;

public class Homework {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/schoolore";
    private static final String USER = "ipro";
    private static final String PASSWORD = "121983";

    public static void dataGiveHomework(int id) throws SQLException {
        Connection con = DriverManager
                .getConnection(DB_URL, USER, PASSWORD);
        Statement sethmwcon = con.createStatement();

        Scanner sc = new Scanner(System.in);
        boolean hmw; String subject, task, duedate; int confhmw, sd;

        do {
            hmw = false;

            System.out.println("---| CONFIRM |---");
            System.out.println("Press 1 to continue, 0 to exit");
            confhmw = sc.nextInt();

            switch (confhmw) {
                case 1:
                    System.out.println("****** HOMEWORKS ******");
                    System.out.print("Please enter the student ID: "); sd = sc.nextInt();
                    sc.nextLine();

                    String query = ("SELECT stdid FROM students WHERE stdid = " + sd);
                    ResultSet rq = sethmwcon.executeQuery(query);

                    if (rq.next()) {
                        System.out.print("Please enter the SUBJECT: "); subject = sc.next();
                        System.out.print("Please enter the DUE DATE: "); duedate = sc.next();
                        sc.nextLine();
                        System.out.print("Please enter the TASK: "); task = sc.nextLine();

                        String ins = ("INSERT INTO homeworks (student_id, given_by, subject, due_date, task) VALUES (" + sd + ", " + id + ", '" + subject + "', '" + duedate + "', '" + task + "')");
                        int updins = sethmwcon.executeUpdate(ins);
                        System.out.println("Homework given successfully!");
                    } else {
                        System.out.println("ERROR ::: No student with this ID!");
                        hmw = true;
                    }
                    break;

                case 0:
                    System.out.println("EXIT MADE!");
                    break;

                default:
                    System.out.println("ERROR ::: INVALID DATA - Please try again!");
                    hmw = true;
            }
        } while (hmw);
    }

    public static void dataViewHomework(int id) throws SQLException {
        Connection con = DriverManager
                .getConnection(DB_URL, USER, PASSWORD);
        Statement viewhmwcon = con.createStatement();

        String check = ("SELECT given_by FROM homeworks WHERE student_id = " + id);
        ResultSet givenbycheck = viewhmwcon.executeQuery(check);
        int given = 0;

        if (givenbycheck.next()) {
            given = givenbycheck.getInt("given_by");
        }

        String tchname = "";
        String tchsurname = "";
        String backcheck = ("SELECT tchid FROM teachers WHERE tchid = " + given);
        ResultSet givenBC = viewhmwcon.executeQuery(backcheck);

        if (givenBC.next()) {
            String gettch = ("SELECT teacher_name, teacher_surname FROM teachers WHERE tchid = " + given);
            ResultSet GETTCH = viewhmwcon.executeQuery(gettch);

            while (GETTCH.next()) {
                tchname = GETTCH.getString("teacher_name");
                tchsurname = GETTCH.getString("teacher_surname");
            }

            String gotten = ("SELECT subject, due_date, task FROM homeworks WHERE student_id = " + id);
            ResultSet gottencheck = viewhmwcon.executeQuery(gotten);

            System.out.println("****** HOMEWORK ******");
            while (gottencheck.next()) {
                String subject = gottencheck.getString("subject");
                String duedate = gottencheck.getString("due_date");
                String task = gottencheck.getString("task");

                System.out.println(subject + "\t\t" + "Given by: " + tchname + " " + tchsurname + "\t\t" + duedate + "\t\t" + task);
            }
        } else {
            System.out.println("Homeworks: NONE");
        }
    }

}
