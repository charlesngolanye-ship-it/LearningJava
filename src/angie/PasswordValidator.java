package angie;

import java.util.Scanner;


public class PasswordValidator {

    private String username;
    private String currentPassword;

    private boolean valid;
    private String errorMessage;

    private static Scanner scanner = new Scanner(System.in);

    public PasswordValidator(String username, String currentPassword) {
        this.username = username;
        this.currentPassword = currentPassword;
    }

    public boolean isValid() {
        return valid;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void closeScanner() {
        scanner.close();
    }

    public static void main(String[] args) {
        var validator = login();// username and password values in new PasswordValidator used to validate the new password
        validator.printPasswordRules();// login() returns a PasswordValidator object...we need a variable to hold it. Variable validator is used to access the methods changePassword, printPasswordRules etc
        // printPasswordRules is an instance method of the PasswordValidator class, validator is an instance of that class, created by login(). So we can call any non-static method of the object using validator.


        do {
            var proposedPassword = validator.getProposedPassword();
            validator.changePassword(proposedPassword);//variable proposedPassword holds the password typed by the user. it gets passed as newPassword to changePassword()

            if (!validator.isValid()) {
                System.out.println(validator.getErrorMessage());
            }
        } while (!validator.isValid());// Repeats the loop as long as the password is not valid
        System.out.println("The proposed password is valid");
        validator.closeScanner();
    }

    public static PasswordValidator login() {
        System.out.println("Enter your username");
        String username = scanner.nextLine();

        System.out.println("Enter your password");
        String password = scanner.nextLine();

        return new PasswordValidator(username, password); // means ask user to type username and password then create a new PasswordValidator object with those values
    }

    public void printPasswordRules() {
        System.out.println("Your new password must meet the following requirements:");
        System.out.println("* at least 8 characters long");
        System.out.println("* contain an uppercase letter");
        System.out.println("* contain a special character");
        System.out.println("* not contain the username");
        System.out.println("* not the same as the old password");
        System.out.println();
    }

    public String getProposedPassword() {
        System.out.println("Enter your new password:");
        return scanner.nextLine();
    }

    public void changePassword(String newPassword) {

        valid = true;
        errorMessage = "";

        if (newPassword.length() < 8) {
            valid = false;
            errorMessage += "\n Your password must be at least 8 characters.";//  += means errorMessage + "something"..adds the new string to the end of the current string. Allows to build a long message with multiple errors
            // \n means new line..add this error on a new line in the error message
        }

        //Alternatively, can loop through string and use Character.isUpperCase on each char
        if (newPassword.equals(newPassword.toLowerCase())) {
            valid = false;
            errorMessage += "\n Your password must include an uppercase letter.";
        }

        if (newPassword.matches("[a-zA-Z0-9 ]*")) {
            valid = false;
            errorMessage += "\n Your password must include a special character (e.g. %,$[:).";
        }

        if (newPassword.toUpperCase().contains(username.toUpperCase())) {
            valid = false                                      ;
            errorMessage += "\n Your password cannot contain your username";
        }

        if (newPassword.equals(currentPassword)) {
            valid= false;
            errorMessage += "\n Your password must be different from your current password";
        }

    }
}
