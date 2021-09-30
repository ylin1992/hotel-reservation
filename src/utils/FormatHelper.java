package utils;

public class FormatHelper {

    public static boolean emailMatcher(String email) {
        String emailRegex = "^(.+)@(.+).(.+)$";
        return email.matches(emailRegex);
    }

}
