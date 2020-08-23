package bryan.roca.mareu.models;

import android.support.annotation.NonNull;

/**
 * Mareu - bryan.roca.mareu.models<br>
 *
 * A Meeting Room used as place for Meeting.<br>
 *
 * 13/06/20
 * Author Bryan Ferreras-Roca
 */
public class MeetingRoom {

    /**
     * The name of the Meeting Room.
     */
    private String name;

    /**
     * The maximum capacity of the Meeting Room.<br>
     * Default set to 5, but may be change if needed.
     */
    private int maxNumberCapacity = 5;


    public static final String BAD_NAME_LENGTH_ERROR = "Name is too short, please change it !";

    /**
     * Default constructor
     * @param pName the name of the Meeting Room.
     */
    public MeetingRoom(String pName) {
        this.setName(pName);
    }

    //
    // GETTERS
    //
    /**
     * Return the current name of the Meeting Room.
     * @return the name
     */
    public String getName() { return this.name; }

    /**
     * Return the maximum capacity authorized.
     * @return the maximum number
     */
    public int getMaxNumberCapacity() { return this.maxNumberCapacity; }

    //
    // SETTERS
    //

    /**
     * Set the name of the current Meeting Room.
     * @param pName the name
     */
    public void setName(String pName) {
        if (pName.length() < 1) {
            this.name = BAD_NAME_LENGTH_ERROR;
        } else {
            this.name = pName;
        }
    }

    /**
     * Set the maximum capacity for this Meeting Room.
     * @param pMaxNumberCapacity Maximum number capacity
     */
    public void setMaxNumberCapacity(int pMaxNumberCapacity) {
        if (pMaxNumberCapacity < 2) {
            this.maxNumberCapacity = 5;
        } else {
            this.maxNumberCapacity = pMaxNumberCapacity;
        }
    }

    @NonNull
    @Override
    public String toString() {
        return getName();
    }
}
