package me.stevensheaves.data.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZonedDateTime;

public class ActivityLogger {
    /**
     * Creates a singleton instance of the <code>ActivityLogger</code> class.
     */
    private static final ActivityLogger instance = new ActivityLogger();

    /**
     * Stores the name of the login_activity.txt file, to help mitigate typos.
     */
    private static final String loginActivityFileName = "login_activity.txt";

    /**
     * Returns the singleton instance of the ActivityLogger class
     * @return Returns the singleton instance of the ActivityLogger class
     */
    public static ActivityLogger getInstance() {
        return instance;
    }

    /**
     * Writes login activity to a the file <code>login_activity.txt</code>.
     * @param nameEntered The name to be written to the file
     * @param isSuccessful Boolean value which represents whether the login attempt was successful
     */
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
