package medicationtracking;

/**
 * The Person class serves as the base class for both Patient and Doctor classes.
 * It contains common attributes that are shared by both types of entities in the system.
 * 
 * @author Your Name
 * @version 1.0
 */
public class Person {
    private int id;
    private String name;
    private int age;
    private String phoneNumber;

    /**
     * Default constructor for Person class.
     */
    public Person() {
        // Default constructor
    }

    /**
     * Parameterized constructor for Person class.
     * 
     * @param id the unique identifier for the person
     * @param name the name of the person
     * @param age the age of the person
     * @param phoneNumber the contact information of the person
     */
    public Person(int id, String name, int age, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    // Getters and Setters

    /**
     * Gets the unique identifier of the person.
     * 
     * @return the person's ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the person.
     * 
     * @param id the person's ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the person.
     * 
     * @return the person's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the person.
     * 
     * @param name the person's name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the age of the person.
     * 
     * @return the person's age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the person.
     * 
     * @param age the person's age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Gets the phone number of the person.
     * 
     * @return the person's phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the person.
     * 
     * @param phoneNumber the person's phone number to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns a string representation of the Person object.
     * 
     * @return a string containing the person's details
     */
    @Override
    public String toString() {
        return "ID: " + id + 
               ", Name: " + name + 
               ", Age: " + age + 
               ", Phone: " + phoneNumber;
    }

    /**
     * Displays the person's information to the console.
     */
    public void displayInfo() {
        System.out.println(this.toString());
    }
}