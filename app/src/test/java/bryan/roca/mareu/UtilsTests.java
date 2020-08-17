package bryan.roca.mareu;

import org.junit.Test;

import static bryan.roca.mareu.utils.IsEmailValid.isEmailAddressValid;
import static org.junit.Assert.*;

public class UtilsTests {

    /**
     * Check if the regex use for email validation is working
     */
    @Test
    public void isEmailValidationWorks() {
        String goodEmail = "bryan.ferreras@gmail.com";
        String badEmail = "bryan.com";

        assertTrue(isEmailAddressValid(goodEmail));
        assertFalse(isEmailAddressValid(badEmail));
    }
}