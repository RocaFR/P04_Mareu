package bryan.roca.mareu.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Check if e-mail address is valid
 */
public class IsEmailValid {
    private static String regex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    public static Boolean isEmailAddressValid(String pEmailAdrress) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(pEmailAdrress);

        return matcher.matches();
    }
}
