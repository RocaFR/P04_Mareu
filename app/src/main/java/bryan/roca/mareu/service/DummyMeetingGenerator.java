package bryan.roca.mareu.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import bryan.roca.mareu.models.Meeting;
import bryan.roca.mareu.models.MeetingRoom;

/**
 * Mareu - bryan.roca.mareu.service
 * 13/06/20
 * Author Bryan Ferreras-Roca
 */
public class DummyMeetingGenerator {

    public static List<Meeting> DUMMY_MEETINGS = new ArrayList<>();
    public static List<MeetingRoom> DUMMY_MEETINGROOMS = Arrays.asList(
            new MeetingRoom("Dune du Pilat"),
            new MeetingRoom("L'Ile aux oiseaux"),
            new MeetingRoom("Banc d'Arguin"),
            new MeetingRoom("Banc du Toulinguet"),
            new MeetingRoom("Le Warf"),
            new MeetingRoom("Peireire"),
            new MeetingRoom("Phare du Cap Ferret"),
            new MeetingRoom("Cabanes tchanquées"),
            new MeetingRoom("Pinasse"),
            new MeetingRoom("You le phoque")
    );
    
    public static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }
    public static List<MeetingRoom> generateMeetingRooms() {
        return new ArrayList<>(DUMMY_MEETINGROOMS);
    }
}