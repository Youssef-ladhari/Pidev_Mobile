package com.pidev.services;

import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pidev.entities.Project;
import com.pidev.utils.Statics;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProjectService {

    String test;
    public ArrayList<Project> projects = new ArrayList<>();

    public ArrayList<Project> dummy () {

Project p = new Project();
        p.setId(2);
        p.setCategory_id(1);
        p.setName("dummy");
        p.setPeriode(12);

        p.setCreator_id(1);

        projects.add(p);
        return  projects;
    }


    public static ProjectService instance=null;
    public boolean resultOK;
    private URLConnection con ;
    private ConnectionRequest req;

    private ProjectService() {
        req = new ConnectionRequest();
    }

    public static ProjectService getInstance() {
        if (instance == null) {
            instance = new ProjectService();
        }
        return instance;
    }




    public  ArrayList<Project> getAllTasks(){
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL+"project";

        req.setUrl(url);
        req.setPost(false);
        req.addArgument("Sender", "mobile");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                 projects =parseProjects( new String(req.getResponseData()));

                req.removeResponseListener(this);
            }
        });


        NetworkManager.getInstance().addToQueueAndWait(req);
        return projects;
    }

    public ArrayList<Project> parseProjects(String jsonText){
        ArrayList<Project>   projs=new ArrayList<>();
        try {


              JSONParser j = new JSONParser();
            Map<String,Object> projectsListJson =j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)projectsListJson.get("root");
            for(Map<String,Object> obj : list){
                Project p = new Project();
                int i = 0 ;
                System.out.println("iii" +i++ +obj.get("name").toString());
                p.setId( (int) Double.parseDouble(obj.get("id").toString()));
                p.setName(obj.get("name").toString());
                p.setImage(obj.get("image").toString());
                projs.add(p);
            }


        } catch (IOException ex) {

        }
        return projs;
    }
}
