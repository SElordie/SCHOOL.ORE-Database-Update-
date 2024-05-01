package entities;

public class Teacher {

    private String firstName, lastName, ID;

    public Teacher(String firstName, String lastName, String ID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "TEACHER [" +
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
