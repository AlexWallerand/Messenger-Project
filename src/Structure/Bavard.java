package Structure;

import java.util.ArrayList;

public class Bavard implements MessageListener {
    private final String pseudo, mdp; // Pseudo unique requis
    private final ArrayList<MessageListener> communityListeners = new ArrayList<>();
    private Boolean connection = false;
    private Gestionnaire gestionnaire;

    public Bavard(String pseudo, String mdp, Gestionnaire gestionnaire) {
        this.pseudo = pseudo;
        this.mdp = mdp;
        this.gestionnaire = gestionnaire;
    }

    public String getPseudo() {
        return pseudo;
    }
    public String getMdp() {
        return mdp;
    }
    public Boolean getConnection() {
        return connection;
    }

    public void setConnection(Boolean connection) {
        this.connection = connection;
    }

    public ArrayList<MessageListener> getCommunityListeners() {
        return communityListeners;
    }

    @Override
    public String toString() {
        return pseudo;
    }

    public void addCMListeners(CommunityManager cm){
        communityListeners.add(cm);
    }

    public void newMessageEvent(String sujet, String corps, String topicDestination) {
        if(this.getConnection()) {
            CommunityManager cmDestination = gestionnaire.getCMbyTopic(topicDestination);
            MessageEvent message = new MessageEvent(this, sujet, corps);
            assert cmDestination != null;
            cmDestination.messageRecu(message);
        }
    }

    @Override
    public void messageRecu(MessageEvent message) {
        System.out.println(this.pseudo + " reçoit : " + message);
    }

}
