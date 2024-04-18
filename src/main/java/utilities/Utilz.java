package utilities;

import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilz {

    public static String firstNameEXCEPTION(String firstName) {
        String rgx = "^[a-zA-Z]+$";
        Pattern pattern = Pattern.compile(rgx);
        Matcher matcher = pattern.matcher(firstName);

        if (!matcher.matches()){
            try {
                System.err.println("INVALID DATA");
                throw new InputMismatchException("First name must be letters only!");
            } catch (InputMismatchException e){
                return e.getMessage();
            }
        }

        return firstName;
    }

    public static String lastNameEXCEPTION(String lastName) {
        String rgx = "^[a-zA-Z]+$";
        Pattern pattern = Pattern.compile(rgx);
        Matcher matcher = pattern.matcher(lastName);

        if (!matcher.matches()){
            try {
                System.err.println("INVALID DATA");
                throw new InputMismatchException("Last name must be letters only!");
            } catch (InputMismatchException e){
                return e.getMessage();
            }
        }

        return lastName;
    }

    public static String emailEXCEPTION(String email) {
        String rgx = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(rgx);
        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches()){
            try {
                System.err.println("INVALID DATA");
                throw new InputMismatchException("Email must be in this format: example@gmail.com");
            } catch (InputMismatchException e){
                return e.getMessage();
            }
        }

        return email;
    }

    public static String phoneNumberEXCEPTION(String phoneNumber){
        String rgx = "\\d+";
        Pattern pattern = Pattern.compile(rgx);
        Matcher matcher = pattern.matcher(phoneNumber);


        if (!matcher.matches()){
            try {
                System.err.println("INVALID DATA");
                throw new InputMismatchException("Phone number must be digits only!");
            } catch (InputMismatchException e){
                return e.getMessage();
            }
        }

        return phoneNumber;
    }

    public static String passwordEXCEPTION(String password) {
        String rgx = "^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
        Pattern pattern = Pattern.compile(rgx);
        Matcher matcher = pattern.matcher(password);

        if (!matcher.matches()){
            try {
                System.err.println("INVALID DATA");
                throw new InputMismatchException("Password must have at least one digit," +
                        "one upper/lower case letter and one special character!");
            } catch (InputMismatchException e){
                return e.getMessage();
            }
        }

        return password;
    }

    public static String dateException(String date) {
        String rgx = "^(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[0-2])/\\d{4}$";
        Pattern pattern = Pattern.compile(rgx);
        Matcher matcher = pattern.matcher(date);

        if (!matcher.matches()){
            try {
                System.err.println("INVALID DATA");
                throw new InputMismatchException("Date must be in this format only: DD/MM/YYYY");
            } catch (InputMismatchException e){
                return e.getMessage();
            }
        }

        return date;
    }

    public static boolean containsNumber(String input) {
        return input.matches(".*\\d+.*");
    }

    public static boolean isNumeric(String input) {
        return input.matches("\\d+");
    }

}
