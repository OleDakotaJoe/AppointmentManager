package me.stevensheaves.data.security;

public class VerifyProvidedPassword {

        public static boolean verifyPassword(String providedPassword, String securePassword, String salt) {

            boolean passwordMatch = PasswordUtils.verifyUserPassword(providedPassword, securePassword, salt);
            if(passwordMatch)
            {
                System.out.println("Provided user password " + providedPassword + " is correct.");
            } else {
                System.out.println("Provided password is incorrect");
            }

            return passwordMatch;
        }
}
