package bryan.roca.mareu.models;

/**
 * Mareu - bryan.roca.mareu.models<br>
 *
 * A Collaborator of the company<br>
 *
 * 13/06/20
 * Author Bryan Ferreras-Roca
 */
public class Collaborator {

    private String id;

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
        if (pId.length() < 5) {
            this.id = "collaborator@company.com";
        } else {
            this.id = pId;
        }
    }
}
