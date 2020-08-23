package bryan.roca.mareu.models;

import android.support.annotation.NonNull;

import bryan.roca.mareu.utils.IsEmailValid;

import static bryan.roca.mareu.utils.IsEmailValid.isEmailAddressValid;

/**
 * Mareu - bryan.roca.mareu.models<br>
 *
 * A Collaborator of the company<br>
 *
 * 13/06/20
 * Author Bryan Ferreras-Roca
 */
public class Collaborator {

    /**
     * The email address of the Collaborator
     */
    private String id;

    /**
     * Message error assigned for ID
     */
    public static final String BAD_COLLABORATOR_ID_ERROR = "Bad email please set it !";

    /**
     * Default constructor.
     * @param pId the Collaborator's ID.
     */
    public Collaborator(String pId) {
        this.setId(pId);
    }

    //
    // GETTERS
    //

    /**
     * Return the ID of the Collaborator, represented by his mail address.
     * @return the ID of the Collaborator
     */
    public String getId() {
        return this.id;
    }

    //
    // SETTERS
    //

    /**
     * Set the ID of the Collaborator, represented by his mail address.
     * @param pId the ID of the Collaborator
     */
    public void setId(String pId) {
        if (!isEmailAddressValid(pId)) {
            this.id = BAD_COLLABORATOR_ID_ERROR;
        } else {
            this.id = pId;
        }
    }

    @NonNull
    @Override
    public String toString() {
        return getId();
    }
}
