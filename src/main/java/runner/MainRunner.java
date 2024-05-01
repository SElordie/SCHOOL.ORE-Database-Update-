package runner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

import static databank.Databazz.runBazz;
import static service.StudentService.studentSection;
import static service.TeacherService.teacherSection;
import static utilities.Utilz.invalidChoiceError;

public class MainRunner {

    public static ArrayList<Integer> tch = new ArrayList<>();
    public static ArrayList<Integer> ss = new ArrayList<>();
    public static AtomicBoolean mainBool = new AtomicBoolean();
    public static AtomicBoolean appClosed = new AtomicBoolean(false);
    public static boolean firstrunT = true;
    public static boolean firstrunS = true;
    public static boolean firstrun = true;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        runBazz();
        System.out.println("****************************************************************");
        runApp();
    }

    public static void runApp() throws SQLException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        String identity;

        do {
            mainBool.set(false);

            if (firstrunT && firstrunS && firstrun) {
                System.out.println("========= Welcome to the SCHOOL.ORE application =========");
                System.out.println("       ------ Please choose your identity ------");
                System.out.println("              T (teacher) ||| S (student)");
                identity = sc.next();
            } else {
                System.out.println("              T (teacher) ||| S (student)");
                identity = sc.next();
            }

            switch (identity) {
                case "T", "t":
                    teacherSection(appClosed);
                    firstrunT = false;
                    break;

                case "S", "s":
                    studentSection(appClosed);
                    firstrunS = false;
                    break;

                default:
                    invalidChoiceError(mainBool);
                    firstrun = false;
            }

            if (!appClosed.get()) {
                if (!mainBool.get()) {
                    mainBool.set(true);
                }
            } else {
                break;
            }
        } while (mainBool.get());
    }

}
