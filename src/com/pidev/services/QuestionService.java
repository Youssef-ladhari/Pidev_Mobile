package com.pidev.services;

import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import com.pidev.entities.Categorie;
import com.pidev.entities.Question;
import com.pidev.entities.Tag;
import com.pidev.entities.User;
import com.pidev.utils.Statics;

import java.io.IOException;
import java.util.*;

public class QuestionService {

    public ArrayList<Question> tasks;
    public ArrayList<Tag> tasksTag;
    public ArrayList<Categorie> tasksCat;
    public LinkedHashMap<String, Object> tag = new LinkedHashMap<>();
    public LinkedHashMap<String, Object> cate = new LinkedHashMap<>();
    public LinkedHashMap<String, Object> user = new LinkedHashMap<>();
    public static QuestionService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private QuestionService() {
        req = new ConnectionRequest();
    }

    public static QuestionService getInstance() {
        if (instance == null) {
            instance = new QuestionService();
        }
        return instance;
    }

    public ArrayList<Question> parseQuestion(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");

            for(Map<String,Object> obj : list){
                float id = Float.parseFloat(obj.get("id").toString());
                String titre = obj.get("titre").toString() ;
                String contenu = obj.get("contenu").toString() ;
                String attachment = obj.get("attachment").toString() ;
                String date = obj.get("date").toString() ;


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
                //////////////Categorie////////////////
                cate = (LinkedHashMap) obj.get("categorie");
                Categorie c = new Categorie();
                for (Map.Entry<String, Object> entry : cate.entrySet()) {
                    if (entry.getKey().contains("id")) {
                        float idd =Float.parseFloat(entry.getValue().toString());
                       c.setId((int)idd);
                    }
                    if (entry.getKey().contains("name")) {
                        c.setName(entry.getValue().toString());
                    }
                }
                ///////////////End Categorie///////////////
                //////////////Tag////////////////
                tag = (LinkedHashMap) obj.get("tag");
                Tag t = new Tag();
                for (Map.Entry<String, Object> entry : tag.entrySet()) {
                    if (entry.getKey().contains("id")) {
                        float idd =Float.parseFloat(entry.getValue().toString());
                        t.setId((int)idd);
                    }
                    if (entry.getKey().contains("label")) {
                        t.setLabel(entry.getValue().toString());
                    }
                }
                ///////////////End Tag///////////////
                tasks.add(new Question((int)id,titre,contenu,attachment,date,t,c,u) );
                // System.out.println(tasks);
            }


        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        }
        return tasks;
    }

    public ArrayList<Tag> parseTag(String jsonText){
        try {
            tasksTag=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                float id = Float.parseFloat(obj.get("id").toString());
                String label = obj.get("label").toString() ;
                tasksTag.add(new Tag((int)id,label) );
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return tasksTag;
    }

    public ArrayList<Categorie> parseCategorie(String jsonText){
        try {
            tasksCat=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                float id = Float.parseFloat(obj.get("id").toString());
                String name = obj.get("name").toString() ;
                tasksCat.add(new Categorie((int)id,name) );
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return tasksCat;
    }


    public Question parseIdQuestion(String jsonText){
        Question qqq= new Question();
        try {

            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");

            for(Map<String,Object> obj : list){
                float id = Float.parseFloat(obj.get("id").toString());
                String titre = obj.get("titre").toString() ;
                String contenu = obj.get("contenu").toString() ;
                String attachment = obj.get("attachment").toString() ;
                String date = obj.get("date").toString() ;


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
                //////////////Categorie////////////////
                cate = (LinkedHashMap) obj.get("categorie");
                Categorie c = new Categorie();
                for (Map.Entry<String, Object> entry : cate.entrySet()) {
                    if (entry.getKey().contains("id")) {
                        float idd =Float.parseFloat(entry.getValue().toString());
                        c.setId((int)idd);
                    }
                    if (entry.getKey().contains("name")) {
                        c.setName(entry.getValue().toString());
                    }
                }
                ///////////////End Categorie///////////////
                //////////////Tag////////////////
                tag = (LinkedHashMap) obj.get("tag");
                Tag t = new Tag();
                for (Map.Entry<String, Object> entry : tag.entrySet()) {
                    if (entry.getKey().contains("id")) {
                        float idd =Float.parseFloat(entry.getValue().toString());
                        t.setId((int)idd);
                    }
                    if (entry.getKey().contains("label")) {
                        t.setLabel(entry.getValue().toString());
                    }
                }
                ///////////////End Tag///////////////
                qqq.setId((int)id);
                qqq.setTitre(titre);
                qqq.setContenu(contenu);
                qqq.setAttachment(attachment);
                qqq.setDate(date);
                qqq.setTag(t);
                qqq.setCategorie(c);
                qqq.setUser(u);
                // System.out.println(tasks);
            }


        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        }
        return qqq;
    }




    public ArrayList<Question> getAllQuestion(){
        String url = Statics.BASE_URL+"questionsjson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseQuestion(new String(req.getResponseData()));
                //       System.out.println("Task : " + tasks);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }


    public ArrayList<Tag> getAllTag(){
        String url = Statics.BASE_URL+"tagsjson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasksTag = parseTag(new String(req.getResponseData()));
                //     System.out.println("Task : " + tasks);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasksTag;
    }
    public ArrayList<Categorie> getAllCategorie(){
        String url = Statics.BASE_URL+"categoriesjson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasksCat = parseCategorie(new String(req.getResponseData()));
                //     System.out.println("Task : " + tasks);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasksCat;
    }


    public Question getIdQuestion(int id){
        final Question[] qq = {new Question()};
        String url = Statics.BASE_URL+"idquestionjson/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                qq[0] = parseIdQuestion(new String(req.getResponseData()));
                //          System.out.println("Task : " + tasks);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return qq[0];
    }


    public boolean addQuestion(String titre,String contenu,String attachment,int tag,int categorie,int user) {
        String url = Statics.BASE_URL+"addquestionjson/"+titre+"/"+contenu+"/"+attachment+"/"+tag+"/"+categorie+"/"+user;
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

    public boolean editQuestion(int id,String contenu,String titre,String attachment,int tag,int categorie,int user) {
        String url = Statics.BASE_URL+"updatequestionjson/"+id+"/"+contenu+"/"+titre+"/"+attachment+"/"+tag+"/"+categorie+"/"+user;
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

    public boolean DeleteQuestion(int id) {
        String url = Statics.BASE_URL+"deletequestionjson/"+id;
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
