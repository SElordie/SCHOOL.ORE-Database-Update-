package service;

import java.sql.*;
import java.util.Scanner;

public class Attendance {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/schoolore";
    private static final String USER = "ipro";
    private static final String PASSWORD = "121983";

    public static void dataSetAttendance() throws SQLException {
        Connection con = DriverManager
                .getConnection(DB_URL, USER, PASSWORD);
        Statement setattcon = con.createStatement();

        Scanner sc = new Scanner(System.in);
        boolean att; String stat, date; int confatt, sd;

        do {
            att = false;

            System.out.println("---| CONFIRM |---");
            System.out.println("Press 1 to continue, 0 to exit");
            confatt = sc.nextInt();

            switch (confatt) {
                case 1:
                    System.out.println("****** ATTENDANCE ******");
                    System.out.print("Please enter the student ID: "); sd = sc.nextInt();

                    String query = ("SELECT stdid FROM students WHERE stdid = " + sd);
                    ResultSet rq = setattcon.executeQuery(query);

                    if (rq.next()) {
                        System.out.print("Please enter the date: "); date = sc.next();
                        System.out.print("Please enter the status of attendance: "); stat = sc.next();

                        String ins = ("INSERT INTO attendance (student_id, date, status) VALUES (" + sd + ", '" + date + "', '" + stat + "')");
                        int updins = setattcon.executeUpdate(ins);

                        System.out.println("Attendance set successfully!");
                    } else {
                        System.out.println("ERROR ::: No student with this ID!");
                        att = true;
                    }
                    break;

                case 0:
                    System.out.println("EXIT MADE!");
                    break;

                default:
                    System.out.println("ERROR ::: INVALID DATA - Please try again!");
                    att = true;
            }
        } while (att);

        setattcon.close();
        con.close();
    }

    public static void dataViewAttendance(int id) throws SQLException {
        Connection con = DriverManager
                .getConnection(DB_URL, USER, PASSWORD);
        Statement viewattcon = con.createStatement();

        String query = ("SELECT date, status FROM attendance WHERE student_id = " + id);
        ResultSet rq = viewattcon.executeQuery(query);

        System.out.println("****** ATTENDANCE ******");
        while (rq.next()) {
            String date = rq.getString("date");
            String status = rq.getString("status");
            System.out.println("Date: " + date + ", Status: " + status);
        }
    }

}
