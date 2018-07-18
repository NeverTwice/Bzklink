package Core.Inspector;

import java.util.regex.Pattern;

public class User {
    public static boolean isValidUser(String login, String email, String password, String type) {
        if (!isValidLogin(login)) {
            System.out.println("error login");

            return false;
        } else if (!isValidEmail(email)) {
            System.out.println("error email");

            return false;
        } else if (!isValidPassword(password)) {
            System.out.println("error password");

            return false;
        } else if (!isValidType(type)) {
            System.out.println("error type");

            return false;
        }

        return true;
    }

    public static boolean isValidLogin(String login) {
        if (login.isEmpty() || login.length() < 2) {
            return false;
        }

        return true;
    }

    // todo : check email format
    public static boolean isValidEmail(String email) {
        if (email.isEmpty()|| email.length() < 7) {
            return false;
        }

        return true;
    }

    public static boolean isValidPassword(String password) {
        if (password.isEmpty() || password.length() < 6) {
            return false;
        }

        return true;
    }

    // todo : check value propositions
    public static boolean isValidType(String type) {
        if (type.isEmpty()) {
            return false;
        }

        return true;
    }
}
