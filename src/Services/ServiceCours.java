/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Entites.Cours;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import Services.Statics;

/**
 *
 * @author USER
 */
public class ServiceCours {
            public ArrayList<Cours> cours;

    public static ServiceCours instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceCours getInstance() {
        if(instance == null )
            instance = new ServiceCours();
        return instance ;
    }
    
    
    
    public ServiceCours() {
        req = new ConnectionRequest();
        
    }
    
    
    //ajout 
    public boolean ajoutCours(Cours a) {
        
        String url =Statics.BASE_URL+"/ajoutercours"+"?&titre=" + a.getTitre()+"&matiere="+ a.getMatiere()+"&annee="+ a.getAnnee(); // aa sorry n3adi getId lyheya mech ta3 user ta3 reclamation
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
                    if (!str.contains("erreur")) {
               
                 resultOk= true;
                //Dialog.show("Confirmation", "success", "Ok", null);
            } else {
                Dialog.show("Erreur", "date", "Ok", null);
                resultOk=false;
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
       return resultOk;//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
    public ArrayList<Cours> parseCours(String jsonText) {
        try {
            cours = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Cours f = new Cours();

               float id = Float.parseFloat(obj.get("id").toString());
                        f.setId((int) id);
                        f.setMatiere(obj.get("matiere").toString());
                        f.setAnnee(obj.get("annee").toString());
                        f.setTitre(obj.get("titre").toString());
                System.out.print(obj);

                cours.add(f);
            }
        } catch (IOException ex) {

        }
        return cours;
    }
    //affichage
     public ArrayList<Cours>getcours() {
        ArrayList<Cours> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/displayCours";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapCours = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapCours.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Cours a = new Cours();
                      
                        float id = Float.parseFloat(obj.get("id").toString());
                        a.setId((int) id);
                        a.setMatiere(obj.get("matiere").toString());
                        a.setAnnee(obj.get("annee").toString());
                        a.setTitre(obj.get("titre").toString());
                        
                       
                        result.add(a);
                       
                    
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
        
       
    }
      public boolean deleteCours(int id ) {
        String url = Statics.BASE_URL +"/deletecours?id="+id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
       public boolean modifierCours(Cours cours) {
        String url = Statics.BASE_URL +"/updateCours?id="+cours.getId()+"&titre="+cours.getTitre()+"&matiere="+cours.getMatiere()+"&annee="+cours.getAnnee();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOk;
        
    }      
}
