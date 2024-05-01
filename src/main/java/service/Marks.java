package service;

import entities.Student;

import java.util.*;

import static entities.Student.students;

public class Marks {

    private static ArrayList<String> marks = new ArrayList<>();
    private static ArrayList<String> subjects = new ArrayList<>();
    private static ArrayList<String> numbers = new ArrayList<>();

    public static void setMarks() {
        Scanner sc = new Scanner(System.in);
        boolean cont = true;

        while (cont) {
            System.out.println("****** MARKS ******");
            System.out.println("Press 1 to continue, 0 to exit.");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("List-of-STUDENTS:");
                    for (Student s : students) {
                        System.out.println("ID: " + s.getID() + ", Full-name: " + s.getFirstName() + " " + s.getLastName());
                    }

                    System.out.print("Enter the ID of the student to set marks: ");
                    String studentID = sc.next();

                    for (Student s : students) {
                        if (s.getID().equals(studentID)) {
                            System.out.println("Student found: " + s.getFirstName() + " " + s.getLastName());
                            System.out.print("Enter the subject: ");
                            String subject = sc.next();

                            System.out.print("Enter the mark: ");
                            double mark = sc.nextDouble();

                            String m = Double.toString(mark);
                            marks.add(m);
                            subjects.add(subject);
                            numbers.add(studentID);

                            System.out.println("Marks set successfully!");
                            break;
                        } else {
                            System.out.println("ERROR: No student by this ID!");
                            break;
                        }
                    }
                    break;

                case 0:
                    System.out.println("EXIT MADE!");
                    cont = false;
                    break;

                default:
                    System.out.println("ERROR: Invalid choice! Please try again.");
            }
        }
    }

    public static void viewMarks() {
        if ((marks != null && !marks.isEmpty()) && (subjects != null && !subjects.isEmpty()) && (numbers != null && !numbers.isEmpty())) {
            for (int i = 0; i <= numbers.size(); i++) {
                System.out.println("Your Marks: ");
                System.out.println("Subject\t\tMark");

                for (String sbj : subjects) {
                    for (String mrk : marks) {
                        System.out.println(sbj + "\t\t" + mrk);
                    }
                }
            }
        } else {
            System.out.println("No marks available");
        }
    }

}
