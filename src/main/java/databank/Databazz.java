package databank;

import java.sql.*;

public class Databazz {

    public static boolean TLOGGED, SLOGGED;

    public static void runBazz() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/schoolore", "ipro", "121983");
        Statement stmain = con.createStatement();
        System.out.println("Connection made!");

        // DDL for CREATE/DROP/ALTER
        //---------------------------
        // DQL for Data Manipulation: SELECT

        boolean tableTeacher = stmain
                .execute("CREATE TABLE IF NOT EXISTS teachers (teacher_name VARCHAR(30), teacher_surname VARCHAR(30), tchid INT UNIQUE)");
        System.out.println("Teacher_Table result: " + tableTeacher); // Should give FALSE

        boolean tableStudent = stmain
                .execute("CREATE TABLE IF NOT EXISTS students (student_name VARCHAR(30), student_surname VARCHAR(30), stdid INT UNIQUE)");
        System.out.println("Student_Table result: " + tableStudent); // Should give FALSE

        boolean tableAttendance = stmain
                .execute("CREATE TABLE IF NOT EXISTS attendance (student_id INT, date VARCHAR(30), status VARCHAR(30))");
        System.out.println("Attendance_Table result: " + tableAttendance);

        boolean tableHomework = stmain
                .execute("CREATE TABLE IF NOT EXISTS homeworks (student_id INT, given_by INT, subject VARCHAR(30), due_date VARCHAR(30), task VARCHAR(70))");
        System.out.println("Homework_Table result: " + tableHomework);

        stmain.close();
        con.close();
    }

    public static void dataRegisterTeacher(String name, String surname, int id) throws ClassNotFoundException, SQLException {
        Connection con = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/schoolore", "ipro", "121983");
        Statement regstateT = con.createStatement();

        String query1 = ("INSERT INTO teachers (teacher_name, teacher_surname, tchid) VALUES ('" + name + "', '" + surname + "', " + id + ")");
        int upd1 = regstateT.executeUpdate(query1);

        regstateT.close();
        con.close();
    }

    public static void dataRegisterStudent(String name, String surname, int id) throws ClassNotFoundException, SQLException {
        Connection con = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/schoolore", "ipro", "121983");
        Statement regstateS = con.createStatement();

        String query1 = ("INSERT INTO students (student_name, student_surname, stdid) VALUES ('" + name + "', '" + surname + "', " + id + ")");
        int upd1 = regstateS.executeUpdate(query1);

        regstateS.close();
        con.close();
    }

    public static void dataLoginTeacher(String id) throws ClassNotFoundException, SQLException {
        Connection con = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/schoolore", "ipro", "121983");
        Statement logstateT = con.createStatement();

        String query = ("SELECT tchid FROM teachers WHERE tchid = " + id);
        ResultSet rq = logstateT.executeQuery(query);

        if (rq.next()) {
            TLOGGED = true;
        } else {
            TLOGGED = false;
        }

        logstateT.close();
        con.close();
    }

    public static void dataLoginStudent(String id) throws ClassNotFoundException, SQLException {
        Connection con = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/schoolore", "ipro", "121983");
        Statement logstateS = con.createStatement();

        String query = ("SELECT stdid FROM students WHERE stdid = " + id);
        ResultSet rq = logstateS.executeQuery(query);

        if (rq.next()) {
            SLOGGED = true;
        } else {
            SLOGGED = false;
        }

        logstateS.close();
        con.close();
    }

}
