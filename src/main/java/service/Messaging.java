package service;

import entities.Teacher;

import java.util.Scanner;

public class Messaging {

    public static String message;

    public static void notifyTeachers() {
        Scanner sc = new Scanner(System.in);
        int teacherIndex; boolean note; int conf;

        do {
            note = false;

            System.out.println("---| MESSAGING |---");
            System.out.println("Press 1 to continue, 0 to exit");
            conf = sc.nextInt();

            switch (conf) {
                case 1:
                    System.out.println("List-of-TEACHERS:");
                    for (int i = 0; i < Teacher.getTeachers().size(); i++) {
                        System.out.println(i + ": " + Teacher.getTeachers().get(i));
                    }

                    System.out.println("Choose a teacher by index to send a message:");
                    teacherIndex = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Enter your message:");
                    message = sc.nextLine();

                    if (teacherIndex >= 0 && teacherIndex < Teacher.getTeachers().size()) {
                        System.out.println("Message sent to " + Teacher.getTeachers().get(teacherIndex));
                    } else {
                        System.out.println("ERROR ::: INVALID DATA - Please try again!");
                        note = true;
                    }
                    break;

                case 0:
                    System.err.println("EXIT MADE!");
                    break;

                default:
                    System.out.println("ERROR ::: INVALID DATA - Please try again!");
                    note = true;
            }
        } while (note);
    }

}
