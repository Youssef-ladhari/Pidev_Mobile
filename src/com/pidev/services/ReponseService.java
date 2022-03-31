package com.pidev.services;

import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import com.pidev.entities.*;
import com.pidev.utils.Statics;

import java.io.IOException;
import java.util.*;

public class ReponseService {

    public ArrayList<Reponse> tasks;
    public LinkedHashMap<String, Object> ques = new LinkedHashMap<>();
    public LinkedHashMap<String, Object> user = new LinkedHashMap<>();
    public static ReponseService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ReponseService() {
        req = new ConnectionRequest();
    }

    public static ReponseService getInstance() {
        if (instance == null) {
            instance = new ReponseService();
        }
        return instance;
    }

    public ArrayList<Reponse> parseReponse(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");

            for(Map<String,Object> obj : list){
                float id = Float.parseFloat(obj.get("id").toString());
                String contenu = obj.get("contenu").toString() ;
                String attachment = obj.get("attachment").toString() ;
                String date = obj.get("datePublication").toString() ;


                ////////////////User//////////////
                user = (LinkedHashMap) obj.get("user");
                User u = new User();
                for (Map.Entry<String, Object> entry : user.entrySet()) {
                    if (entry.getKey().contains("id")) {
                        float idd =Float.parseFloat(entry.getValue().toString());
                        u.setId((int)idd);
                    }
                    if (entry.getKey().contains("username")) {
                        u.setUsername(entry.getValue().toString());
                    }
                    if (entry.getKey().contains("email")) {
                        u.setEmail(entry.getValue().toString());
                    } if (entry.getKey().contains("password")) {
                        u.setPassword(entry.getValue().toString());
                    }if (entry.getKey().contains("confirm_password")) {
                        u.setConfirm_password(entry.getValue().toString());
                    }if (entry.getKey().contains("roles")) {
                        u.setRoles(entry.getValue().toString());
                    }
                }
                ///////////////End User///////////////
                //////////////Question////////////////
                ques = (LinkedHashMap) obj.get("question");
                Question q = new Question();
                for (Map.Entry<String, Object> entry : ques.entrySet()) {
                    if (entry.getKey().contains("id")) {
                        float idd =Float.parseFloat(entry.getValue().toString());
                        q.setId((int)idd);
                    }
                    if (entry.getKey().contains("titre")) {
                        q.setTitre(entry.getValue().toString());
                    }
                    if (entry.getKey().contains("contenu")) {
                       q.setContenu(entry.getValue().toString());
                    }
                }
                ///////////////End Question///////////////
                tasks.add(new Reponse((int)id,contenu,attachment,date,q,u) );
                // System.out.println(tasks);
            }


        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        }
        return tasks;
    }




    public Reponse parseIdReponse(String jsonText){
        Reponse qqq= new Reponse();
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");

            for(Map<String,Object> obj : list){
                float id = Float.parseFloat(obj.get("id").toString());
                String contenu = obj.get("contenu").toString() ;
                String attachment = obj.get("attachment").toString() ;
                String date = obj.get("datePublication").toString() ;


                ////////////////User//////////////
                user = (LinkedHashMap) obj.get("user");
                User u = new User();
                for (Map.Entry<String, Object> entry : user.entrySet()) {
                    if (entry.getKey().contains("id")) {
                        float idd =Float.parseFloat(entry.getValue().toString());
                        u.setId((int)idd);
                    }
                    if (entry.getKey().contains("username")) {
                        u.setUsername(entry.getValue().toString());
                    }
                    if (entry.getKey().contains("email")) {
                        u.setEmail(entry.getValue().toString());
                    } if (entry.getKey().contains("password")) {
                        u.setPassword(entry.getValue().toString());
                    }if (entry.getKey().contains("confirm_password")) {
                        u.setConfirm_password(entry.getValue().toString());
                    }if (entry.getKey().contains("roles")) {
                        u.setRoles(entry.getValue().toString());
                    }
                }
                ///////////////End User///////////////
                //////////////Question////////////////
                ques = (LinkedHashMap) obj.get("question");
                Question q = new Question();
                for (Map.Entry<String, Object> entry : ques.entrySet()) {
                    if (entry.getKey().contains("id")) {
                        float idd =Float.parseFloat(entry.getValue().toString());
                        q.setId((int)idd);
                    }
                    if (entry.getKey().contains("titre")) {
                        q.setTitre(entry.getValue().toString());
                    }
                    if (entry.getKey().contains("contenu")) {
                        q.setContenu(entry.getValue().toString());
                    }
                }
                ///////////////End Question///////////////
                qqq=new Reponse((int)id,contenu,attachment,date,q,u);
                // System.out.println(tasks);
            }


        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        }
        return qqq;
    }




    public ArrayList<Reponse> getAllReponse(){
        String url = Statics.BASE_URL+"reponsesjson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseReponse(new String(req.getResponseData()));
                //       System.out.println("Task : " + tasks);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }

    public ArrayList<Reponse> getAllQuestionReponse(int question){
        String url = Statics.BASE_URL+"questionreponsesjson/"+question;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseReponse(new String(req.getResponseData()));
                //       System.out.println("Task : " + tasks);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }



    public Reponse getIdReponse(int id){
        final Reponse[] qq = {new Reponse()};
        String url = Statics.BASE_URL+"idreponsejson/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                qq[0] = parseIdReponse(new String(req.getResponseData()));
                //          System.out.println("Task : " + tasks);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return qq[0];
    }


    public boolean addReponse(String contenu,String attachment,int question,int user) {
        String url = Statics.BASE_URL+"addreponsejson/"+contenu+"/"+attachment+"/"+question+"/"+user;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean editReponse(int id,String contenu,String attachment,int question,int user) {
        String url = Statics.BASE_URL+"updatereponsejson/"+id+"/"+contenu+"/"+attachment+"/"+question+"/"+user;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean DeleteReponse(int id) {
        String url = Statics.BASE_URL+"deletereponsejson/"+id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
}
