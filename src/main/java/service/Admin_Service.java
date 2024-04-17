package service;

import entities.Admin;
import entities.Student;

public class Admin_Service {

    public String aName, aSurname, admID;

    public Admin_Service() {
        this.aName = Admin.aFirstName;
        this.aSurname = Admin.aLastName;
        this.admID = Admin.A_ID;
    }

    public static void menuAdmin(String aName, String aSurname, String admID) {

    }

}
