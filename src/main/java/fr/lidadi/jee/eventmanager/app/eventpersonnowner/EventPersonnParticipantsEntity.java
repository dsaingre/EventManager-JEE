/*
 * Created on 17 oct. 2016 ( Time 17:17:22 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a composite Primary Key


package fr.lidadi.jee.eventmanager.app.eventpersonnowner;

import fr.lidadi.jee.eventmanager.dao.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.UUID;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;


import javax.persistence.*;
import javax.persistence.Entity;

/**
 * Persistent class for entity stored in table "EVENT_PERSONN_PARTICIPANTS"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity(name="eventpersonnparticipants")
public class EventPersonnParticipantsEntity implements fr.lidadi.jee.eventmanager.dao.Entity {

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( EMBEDDED IN AN EXTERNAL CLASS )
    //----------------------------------------------------------------------
	@EmbeddedId
    private EventPersonnParticipantsEntityKey compositePrimaryKey ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS
    //----------------------------------------------------------------------


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public EventPersonnParticipantsEntity() {
		super();
		this.compositePrimaryKey = new EventPersonnParticipantsEntityKey();
    }

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE COMPOSITE KEY
    //----------------------------------------------------------------------
    public void setEventId( UUID eventId ) {
        this.compositePrimaryKey.setEventId( eventId ) ;
    }
    public UUID getEventId() {
        return this.compositePrimaryKey.getEventId() ;
    }
    public void setPersonnId( UUID personnId ) {
        this.compositePrimaryKey.setPersonnId( personnId ) ;
    }
    public UUID getPersonnId() {
        return this.compositePrimaryKey.getPersonnId() ;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        if ( compositePrimaryKey != null ) {
            sb.append(compositePrimaryKey.toString());
        }
        else {
            sb.append( "(null-key)" );
        }
        sb.append("]:");
        return sb.toString();
    }

    @Override
    public Object getPrimaryKey() {
        return null;
    }

    @Embeddable
    public class EventPersonnParticipantsEntityKey implements Serializable {
        private static final long serialVersionUID = 1L;

        // ----------------------------------------------------------------------
        // ENTITY KEY ATTRIBUTES
        // ----------------------------------------------------------------------
        @Lob
        @Column(name = "EVENT_ID", nullable = false)
        private UUID eventId;

        @Lob
        @Column(name = "PERSONN_ID", nullable = false)
        private UUID personnId;

        // ----------------------------------------------------------------------
        // CONSTRUCTORS
        // ----------------------------------------------------------------------
        public EventPersonnParticipantsEntityKey() {
            super();
        }

        public EventPersonnParticipantsEntityKey(UUID eventId, UUID personnId) {
            super();
            this.eventId = eventId;
            this.personnId = personnId;
        }

        // ----------------------------------------------------------------------
        // GETTERS & SETTERS FOR KEY FIELDS
        // ----------------------------------------------------------------------
        public void setEventId(UUID value) {
            this.eventId = value;
        }

        public UUID getEventId() {
            return this.eventId;
        }

        public void setPersonnId(UUID value) {
            this.personnId = value;
        }

        public UUID getPersonnId() {
            return this.personnId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            EventPersonnParticipantsEntityKey that = (EventPersonnParticipantsEntityKey) o;

            if (getEventId() != null ? !getEventId().equals(that.getEventId()) : that.getEventId() != null)
                return false;
            return getPersonnId() != null ? getPersonnId().equals(that.getPersonnId()) : that.getPersonnId() == null;

        }

        @Override
        public int hashCode() {
            int result = getEventId() != null ? getEventId().hashCode() : 0;
            result = 31 * result + (getPersonnId() != null ? getPersonnId().hashCode() : 0);
            return result;
        }

        // ----------------------------------------------------------------------
        // toString METHOD
        // ----------------------------------------------------------------------
        public String toString() {
            StringBuffer sb = new StringBuffer();
            sb.append(eventId);
            sb.append("|");
            sb.append(personnId);
            return sb.toString();
        }
    }

}
