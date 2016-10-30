package fr.lidadi.jee.eventmanager.app.participant;

/**
 * Created by damien on 30/10/2016.
 */
public class ParticipantService {

    private ParticipantDao participantDao = new ParticipantDao();

    public void add(Participant p){
        participantDao.add(p);
    }

}
