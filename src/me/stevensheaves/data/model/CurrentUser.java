package me.stevensheaves.data.model;

import java.time.LocalDateTime;

public class CurrentUser {
    private static Integer id;
    private static String userName;
    private static boolean loggedIn;
    private static LocalDateTime loginTime;
    private static LocalDateTime refreshTime;

    public static void login(int id, String userName, boolean loggedIn, LocalDateTime loginTime) {
        setId(id);
        setUserName(userName);
        setLoggedIn(loggedIn);
        setLoginTime(loginTime);

    }

    public static int getId() {
        return id;
    }

    public static void setId(Integer id) {
        CurrentUser.id = id;
    }

    public static String getUserName() {
        return CurrentUser.userName;
    }

    public static void setUserName(String userName) {
        CurrentUser.userName = userName;
    }

    public static boolean isLoggedIn() {
        return CurrentUser.loggedIn;
    }

    public static void setLoggedIn(boolean loggedIn) {
        CurrentUser.loggedIn = loggedIn;
    }


    public static LocalDateTime getLoginTime() {
        return CurrentUser.loginTime;
    }

    public static void setLoginTime(LocalDateTime loginTime) {
        CurrentUser.loginTime = loginTime;
    }

    public static LocalDateTime getRefreshTime() {
        return refreshTime;
    }

    public static void setRefreshTime(LocalDateTime refreshTime) {
        CurrentUser.refreshTime = refreshTime;
    }

    public static void logout() {
        CurrentUser.setLoggedIn(false);
        CurrentUser.setId(null);
        CurrentUser.setLoginTime(null);
        CurrentUser.setUserName(null);
        CurrentUser.setRefreshTime(null);
    }


    public static void print() {
        System.out.println("CurrentUser{" +
                "isLoggedIn=" + loggedIn +
                ", loginTime=" + loginTime +
                ", id=" + id +
                ", userName=" + userName +
                '}');
    }
}
