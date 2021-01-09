package me.stevensheaves.data.model;

import java.time.LocalDateTime;

/**
 * Class which holds the data of the <code>User</code> which is logged in.
 */
public class CurrentUser {
    private static Integer id;
    private static String userName;
    private static boolean loggedIn;
    private static LocalDateTime loginTime;
    private static LocalDateTime refreshTime;

    /**
     * Method used for logging in a user
     * @param id <code>int</code> value to be set as the <code>id</code>
     * @param userName <code>String</code> value to be set as the <code>userName</code>
     * @param loggedIn <code>boolean</code> value to be set as the <code>loggedIn</code>
     * @param loginTime <code>LocalDateTime</code> value to be set as the <code>loginTime</code>
     */
    public static void login(int id, String userName, boolean loggedIn, LocalDateTime loginTime) {
        setId(id);
        setUserName(userName);
        setLoggedIn(loggedIn);
        setLoginTime(loginTime);

    }
    /**
     * Getter for the <code>id</code> field.
     * @return Returns the <code>int</code> value of the <code>CurrentUser</code>'S <code>id</code>
     */
    public static int getId() {
        return id;
    }

    /**
     * Setter for the <code>id</code> field.
     * @param id Sets the <code>int</code> value of the <code>CurrentUser</code>'S <code>id</code>
     */
    public static void setId(Integer id) {
        CurrentUser.id = id;
    }
    /**
     * Getter for the <code>userName</code> field.
     * @return Returns the <code>String</code> value of the <code>CurrentUser</code>'S <code>userName</code>
     */
    public static String getUserName() {
        return CurrentUser.userName;
    }
    /**
     * Setter for the <code>userName</code> field.
     * @param userName Sets the <code>String</code> value of the <code>CurrentUser</code>'S <code>userName</code>
     */
    public static void setUserName(String userName) {
        CurrentUser.userName = userName;
    }
    /**
     * Getter for the <code>loggedIn</code> field.
     * @return Returns the <code>boolean</code> value of the <code>CurrentUser</code>'S <code>loggedIn</code>
     */
    public static boolean isLoggedIn() {
        return CurrentUser.loggedIn;
    }
    /**
     * Setter for the <code>loggedIn</code> field.
     * @param loggedIn Sets the <code>boolean</code> value of the <code>CurrentUser</code>'S <code>loggedIn</code>
     */
    public static void setLoggedIn(boolean loggedIn) {
        CurrentUser.loggedIn = loggedIn;
    }

    /**
     * Setter for the <code>loginTime</code> field.
     * @param loginTime Sets the <code>LocalDateTime</code> value of the <code>CurrentUser</code>'S <code>loginTime</code>
     */
    public static void setLoginTime(LocalDateTime loginTime) {
        CurrentUser.loginTime = loginTime;
    }


    /**
     * Method for 'logging out' a the <code>CurrentUser</code>.
     * Sets all fields to null/falsy values.
     */
    public static void logout() {
        CurrentUser.setLoggedIn(false);
        CurrentUser.setId(null);
        CurrentUser.setLoginTime(null);
        CurrentUser.setUserName(null);
    }

}
