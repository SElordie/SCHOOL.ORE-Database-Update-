package entities;

public class Student {

    private String firstName, lastName, ID;

    public Student(String firstName, String lastName, String ID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "STUDENT [" +
                "name: " + firstName + "," +
                " surname: " + lastName + "," +
                " ID: " + ID +
                ']';
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

}
