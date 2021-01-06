package me.stevensheaves.data.model;

/**
 * This class is used in holding all data for a Contact.
 * There are multiple constructors in the class to account for the multiple use cases where not all data may be available or required.
 */
public class Contact {
    private int id;
    private String name;
    private String email;

    public Contact(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Contact(String name, String email) {
        this.name = name;
        this.email = email;
    }

    /**
     * Getter for the <code>id</code> field.
     * @return Returns the int value of the <code>id</code> field.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for the <code>id</code> field.
     * @param id The int value to be set as the <code>id</code>.
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Getter for the <code>name</code> field.
     * @return Returns the String value of the <code>name</code> field.
     */
    public String getName() {
        return name;
    }
    /**
     * Setter for the <code>name</code> field.
     * @param name The int value to be set as the <code>name</code>.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Getter for the <code>email</code> field.
     * @return Returns the String value of the <code>email</code> field.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Overrides the toString() method for more readable printing.
     * @return returns the <code>name</code> of the Contact.
     */
    @Override
    public String toString() {
        return name;
    }
}
