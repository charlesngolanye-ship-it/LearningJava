package angie;

import java.util.Scanner;

public class Password {
    static Scanner scanner = new Scanner(System.in);

    public static String readInput(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    public static String readNewPassword(String username, String oldPassword) {
        String newPassword;

        while (true) {
            System.out.println("Enter your new password");
            newPassword = scanner.next();

            if (!newPassword.equals(username) && !newPassword.equals(oldPassword) && newPassword.length() >= 8 && 
                    newPassword.matches(".*[A-Z].*") && newPassword.matches(".*[^a-zA-Z0-9].*"))
                break;

            System.out.println("Enter a valid password");
        }
        return newPassword;
    }

    public static void setup() {
        String username = readInput("Please enter your username");
        String oldPassword = readInput("Enter your old password");
        String newPassword = readNewPassword(username, oldPassword);
        System.out.println("Password successfully changed!");
    }

    public static void passwordRules() {
        System.out.println("Your new password should meet the following requirements:");
        System.out.println("* at least 8 characters long");
        System.out.println("* contain an uppercase letter");
        System.out.println("* contain a special character");
        System.out.println("* not contain a username");
        System.out.println("* not same as the old password");
        System.out.println();
    }
}


