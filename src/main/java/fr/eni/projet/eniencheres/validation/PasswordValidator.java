package fr.eni.projet.eniencheres.validation;


import java.util.regex.Pattern;

public class PasswordValidator {
    private static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$";
    private static final Pattern pattern = Pattern.compile(PASSWORD_REGEX);

    public static boolean isValid(String password) {
        return password != null && pattern.matcher(password).matches();
    }
}
