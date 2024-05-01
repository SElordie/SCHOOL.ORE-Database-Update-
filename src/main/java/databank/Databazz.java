package databank;

import java.sql.*;

public class Databazz {

    public static boolean TLOGGED, SLOGGED;

    public static void runBazz() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/schoolore", "ipro", "121983");
        Statement connectedMain = con.createStatement();
        System.out.println("Connection made!");

        // DDL for CREATE/DROP/ALTER
        //---------------------------
        // DQL for Data Manipulation: SELECT

        boolean tableTeacher = connectedMain
                .execute("CREATE TABLE IF NOT EXISTS teachers(teacher_name VARCHAR(30), teacher_surname VARCHAR(30), tchid INT UNIQUE)");
        System.out.println("Teacher_Table result: " + tableTeacher); // Should give FALSE

        boolean tableStudent = connectedMain
                .execute("CREATE TABLE IF NOT EXISTS students(student_name VARCHAR(30), student_surname VARCHAR(30), stdid INT UNIQUE)");
        System.out.println("Student_Table result: " + tableStudent); // Should give FALSE

        connectedMain.close();
        con.close();
    }

    public static void dataRegisterTeacher(String name, String surname, String id) throws ClassNotFoundException, SQLException {
        Connection con = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/schoolore", "ipro", "121983");
        Statement regstateT = con.createStatement();

        String query1 = ("INSERT INTO teachers (teacher_name, teacher_surname, tchid) VALUES ('" + name + "', '" + surname + "', " + id + ")");
        int upd1 = regstateT.executeUpdate(query1);

        regstateT.close();
        con.close();
    }

    public static void dataRegisterStudent(String name, String surname, String id) throws ClassNotFoundException, SQLException {
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
