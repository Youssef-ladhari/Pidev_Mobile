/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entites.Cours;
import Entites.Exercice;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import Services.Statics;

/**
 *
 * @author USER
 */
public class ServiceExercice {
       public static ServiceExercice instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceExercice getInstance() {
        if(instance == null )
            instance = new ServiceExercice();
        return instance ;
    }
    
    
    
    public ServiceExercice() {
        req = new ConnectionRequest();
        
    }
    
    
    //ajout 
    public boolean ajoutEx(Exercice a) {
        
        String url =Statics.BASE_URL+"/ajouterexercice"+"?&numero=" + a.getNumero()+"&niveau="+ a.getNiveau()+"&questions="+ a.getQuestion()+"&correction="+ a.getCorrection()+"&enonce="+a.getEnonce(); 
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
    //affichage
     public ArrayList<Exercice>getexercice() {
        ArrayList<Exercice> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/displayExercice";
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
                        Exercice a = new Exercice();
                      
                        float id = Float.parseFloat(obj.get("id").toString());
                        float niveau = Float.parseFloat(obj.get("numero").toString());
                        a.setId((int) id);
                        a.setNumero((int) niveau);
                        a.setEnonce(obj.get("enonce").toString());
                        a.setQuestion(obj.get("questions").toString());
                        a.setCorrection(obj.get("correction").toString());
                        a.setNiveau(obj.get("niveau").toString());

                        
                       
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
        String url = Statics.BASE_URL +"/deleteexercice?id="+id;
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
       public boolean modifierCours(Exercice a) {
        String url = Statics.BASE_URL +"/updateExercice?id="+a.getId()+"&niveau="+ a.getNiveau()+"&questions="+ a.getQuestion()+"&correction="+ a.getCorrection()+"&enonce="+a.getEnonce(); 
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
