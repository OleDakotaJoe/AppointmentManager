package me.stevensheaves.data.model;

public class User {
    int id;
    String userName;

    /**
     * Constructor for the <code>User</code> class
     * @param id
     * @param userName
     */
    public User(int id, String userName) {
        this.id = id;
        this.userName = userName;
    }
    /**
     * Getter for the <code>id</code> field.
     * @return Returns the <code>int</code> value of the <code>CurrentUser</code>'S <code>id</code>
     */
    public int getId() {
        return id;
    }
    /**
     * Getter for the <code>userName</code> field.
     * @return Returns the <code>String</code> value of the <code>CurrentUser</code>'S <code>userName</code>
     */
    public String getUserName() {
        return userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                '}';
    }
}
