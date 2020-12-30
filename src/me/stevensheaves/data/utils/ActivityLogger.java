package me.stevensheaves.data.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZonedDateTime;

public class ActivityLogger {
    private static final ActivityLogger instance = new ActivityLogger();
    private static final String loginActivityFileName = "login_activity.txt";

    public static ActivityLogger getInstance() {
        return instance;
    }

    public void writeActivityToLog(String nameEntered, Boolean isSuccessful) {
        try(FileWriter fw = new FileWriter(loginActivityFileName,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println(nameEntered + "\t" + ZonedDateTime.now() + "\tSuccessful: " + isSuccessful);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
