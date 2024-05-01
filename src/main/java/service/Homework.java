package service;

import entities.Student;

import java.util.*;

import static entities.Student.students;

public class Homework {

    public static Map<String, List<String>> studentHomework = new HashMap<>();
    private static List<String> homeworkDetails = new ArrayList<>();

    public static void assignHomework() {
        Scanner sc = new Scanner(System.in);
        boolean cont = true;

        while (cont) {
            System.out.println("****** HOMEWORKS ******");
            System.out.println("Press 1 to assign homework, 0 to exit.");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("List-of-STUDENTS:");
                    for (Student s : students) {
                        System.out.println("ID: " + s.getID() + " Full-name: " + s.getFirstName() + " " + s.getLastName());
                    }

                    System.out.print("Enter the ID of the student to assign homework: ");
                    String studentID = sc.next();

                    for (Student s : students) {
                        if (s.getID().equals(studentID)) {
                            System.out.println("Student found: " + s.getFirstName() + " " + s.getLastName());
                            System.out.println("---| HOMEWORK DETAILS |---");

                            System.out.print("Enter the subject: ");
                            String subject = sc.next();
                            sc.nextLine();

                            System.out.print("Enter the due date: ");
                            String dueDate = sc.next();
                            sc.nextLine();

                            System.out.print("Enter the task: ");
                            String task = sc.nextLine();

                            homeworkDetails.add(subject);
                            homeworkDetails.add(dueDate);
                            homeworkDetails.add(task);
                            studentHomework.put(studentID, homeworkDetails);

                            System.out.println("Homework assigned successfully!");
                            break;
                        } else {
                            System.out.println("NO STUDENTS FOUND ::: Invalid ID!");
                        }
                    }

                case 0:
                    System.out.println("EXIT MADE!");
                    cont = false;
                    break;

                default:
                    System.out.println("ERROR: Invalid choice. Please try again!");
            }
        }
    }

    public static void viewHomework(String stdID) {
        List<String> hwdetails = studentHomework.get(stdID);
        if (hwdetails != null) {
            System.out.println("Homework assignment for Student ID - " + stdID + ":");
            System.out.println("Subject: " + hwdetails.get(0));
            System.out.println("Due Date: " + hwdetails.get(1));
            System.out.println("Task: " + hwdetails.get(2));
        } else {
            System.out.println("No homework assignment available for Student ID " + stdID + ".");
        }
    }

}
