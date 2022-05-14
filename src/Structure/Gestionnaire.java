package Structure;

import java.util.ArrayList;
import java.util.Objects;

public class Gestionnaire {
    private final ArrayList<Bavard> listBavard = new ArrayList<>();
    private final ArrayList<CommunityManager> listCM = new ArrayList<>();

    public Gestionnaire() {
    }

    public ArrayList<Bavard> getListBavard() {
        return listBavard;
    }

    public ArrayList<CommunityManager> getListCM() {
        return listCM;
    }

    public CommunityManager getCMbyTopic(String topic){
        for(CommunityManager cm : listCM){
            if (Objects.equals(cm.getTopic(), topic)){
                return cm;
            }
        }
        System.out.println("Aucun topic existant ne porte ce nom");
        return null;
    }
    @Override
    public String toString() {
        return "Structure.Gestionnaire{" +
                "listBavard=" + listBavard +
                ", listCM=" + listCM +
                '}';
    }

    public Bavard searchBavard(String pseudo, String mdp){
        for(Bavard bavard : listBavard){
            if((Objects.equals(bavard.getPseudo(), pseudo)) && (Objects.equals(bavard.getMdp(), mdp))){
                return bavard;
            }
        }
        return null;
    }

    public CommunityManager searchCM(String topic){
        for(CommunityManager cm : listCM){
            if(Objects.equals(cm.getTopic(), topic)){
                return cm;
            }
        }
        return null;
    }


    public Bavard createBavard(String pseudo, String mdp){
        boolean commut = true;
        for(Bavard bavard : listBavard){
            if (Objects.equals(pseudo, bavard.getPseudo())){
                commut = false;
                break;
            }
        }
        if(commut){
            Bavard bavard = new Bavard(pseudo, mdp, this);
            listBavard.add(bavard);
            return bavard;
        }
        else{
            System.out.println("Un utilisateur porte déjà ce pseudo, veuillez ressayer avec un autre pseudo.");
            return null;
        }
    }

    public void deleteBavard(String pseudo, String mdp){
        Bavard bavard = searchBavard(pseudo,mdp);
        listBavard.remove(bavard);
    }

    public CommunityManager createCM(String topic){
        CommunityManager cm = new CommunityManager(topic);
        listCM.add(cm);
        return cm;
    }

    public void deleteCM(String topic){
        CommunityManager cm = searchCM(topic);
        listCM.remove(cm);
    }

    public void addListener(Bavard bavard, CommunityManager cm){
        cm.getListListeners().add(bavard);
        bavard.getCommunityListeners().add(cm);
    }

    public void removeListener(Bavard bavard, CommunityManager cm){
        cm.getListListeners().remove(bavard);
    }

    public void connectionBavard(Bavard bavard){
        bavard.setConnection(true);

    }

    public void deconnectionBavard(Bavard bavard){
        bavard.setConnection(false);
    }


}
