import java.util.ArrayList;
import java.util.Objects;

public class Gestionnaire {
    private static final ArrayList<Bavard> listBavard = new ArrayList<>();
    private static final ArrayList<CommunityManager> listCM = new ArrayList<>();

    public Gestionnaire() {
    }

    public static ArrayList<Bavard> getListBavard() {
        return listBavard;
    }

    public static ArrayList<CommunityManager> getListCM() {
        return listCM;
    }

    public static CommunityManager getCMbyTopic(String topic){
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
        return "Gestionnaire{" +
                "listBavard=" + listBavard +
                ", listCM=" + listCM +
                '}';
    }

    public Bavard searchBavard(String pseudo){
        for(Bavard bavard : listBavard){
            if(Objects.equals(bavard.getPseudo(), pseudo)){
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
        Bavard bavard = new Bavard(pseudo, mdp);
        listBavard.add(bavard);
        return bavard;
    }

    public void deleteBavard(String pseudo){
        Bavard bavard = searchBavard(pseudo);
        listBavard.remove(bavard);
    }

    public CommunityManager createCM(String topic, String pseudoCM){
        CommunityManager cm = new CommunityManager(topic,pseudoCM);
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

    public void connectionBavard(String pseudo, String mdp){
        Bavard bavard = searchBavard(pseudo);
        if(Objects.equals(bavard.getMdp(), mdp)){
            bavard.setConnection(true);
        }
    }

    public void deconnectionBavard(Bavard bavard){
        bavard.setConnection(false);
    }


}
