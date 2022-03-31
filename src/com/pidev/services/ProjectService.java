package com.pidev.services;


import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pidev.entities.Category;
import com.pidev.entities.Project;
import com.pidev.entities.User;
import com.pidev.utils.Statics;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ProjectService {

    String test;
    Project project = new Project();
    public ArrayList<Project> projects = new ArrayList<>();

    public ArrayList<Project> dummy () {

Project p = new Project();
        p.setId(2);

        p.setName("dummy");
        p.setPeriode(12);



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




    public  ArrayList<Project> getAllProjects(){
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL;

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
    public  Project getProjectById(int id){

        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL+"myshow/"+id;
        System.out.println("url"+url);
        req.setUrl(url);
        req.setPost(false);
        req.addArgument("Sender", "mobile");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                project =parseOneProject( new String(req.getResponseData()));

                req.removeResponseListener(this);
            }
        });


        NetworkManager.getInstance().addToQueueAndWait(req);
        return project;
    }
    public ArrayList<Project> parseProjects(String jsonText){
        ArrayList<Project>   projs=new ArrayList<>();
        ArrayList<User> userss = new ArrayList<>();
        try {


              JSONParser j = new JSONParser();
            Map<String,Object> projectsListJson =j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)projectsListJson.get("root");

            for(Map<String,Object> obj : list){
                Project p = new Project();

                int i = 0 ;

                p.setId( (int) Double.parseDouble(obj.get("id").toString()));


                p.setName(  obj.get("name").toString());
                p.setPeriode((int) Double.parseDouble(obj.get("periode").toString()));
                p.setPrice(Float.parseFloat(obj.get("price").toString()));
                p.setImage(obj.get("image").toString());
              //  System.out.println("----"+obj.get("creator").toString());
//                List<User> ce = mapper.convertValue(
//                        obj.get("creator"),
//                        new TypeReference<List<User>>() { }
//                );
//                System.out.println(ce.toString());
               LinkedHashMap<String,Object> lc = (LinkedHashMap <String,Object>)obj.get("creator");
                User creator = new User();
                creator.setUsername(lc.get("username").toString());

                creator.setEmail(lc.get("email").toString());

                creator.setId((int) Double.parseDouble(lc.get("id").toString()));
                p.setCreator(creator);

           //    System.out.println( lc.get("id"));


                LinkedHashMap<String,Object> lcat = (LinkedHashMap <String,Object>)obj.get("category");
                Category category = new Category();
                category.setName(lcat.get("name").toString());
                category.setId((int) Double.parseDouble(lcat.get("id").toString()));
                p.setCategory(category);
                List<Map<String,Object>> listUsers = (List<Map<String,Object>>)obj.get("users");

                for(Map<String,Object> lu : listUsers){
                User u = new User();
                u.setId((int) Double.parseDouble(lu.get("id").toString()));
                u.setUsername(lu.get("username").toString());
                    u.setEmail(lu.get("email").toString());
                userss.add(u);
                }

                p.setUsers(userss);

                projs.add(p);

            }


        } catch (IOException ex) {

        }
        return projs;
    }

    public Project parseOneProject(String jsonText){

        ArrayList<User> userss = new ArrayList<>();
        try {


            JSONParser j = new JSONParser();
            Map<String,Object> obj =j.parseJSON(new CharArrayReader(jsonText.toCharArray()));


                Project p = new Project();

                int i = 0 ;

                p.setId( (int) Double.parseDouble(obj.get("id").toString()));


                p.setName(  obj.get("name").toString());
                p.setPeriode((int) Double.parseDouble(obj.get("periode").toString()));
                p.setPrice(Float.parseFloat(obj.get("price").toString()));
                p.setImage(obj.get("image").toString());
                //  System.out.println("----"+obj.get("creator").toString());
//                List<User> ce = mapper.convertValue(
//                        obj.get("creator"),
//                        new TypeReference<List<User>>() { }
//                );
//                System.out.println(ce.toString());
                LinkedHashMap<String,Object> lc = (LinkedHashMap <String,Object>)obj.get("creator");
                User creator = new User();
                creator.setUsername(lc.get("username").toString());

                creator.setEmail(lc.get("email").toString());

                creator.setId((int) Double.parseDouble(lc.get("id").toString()));
                p.setCreator(creator);

                //    System.out.println( lc.get("id"));


                LinkedHashMap<String,Object> lcat = (LinkedHashMap <String,Object>)obj.get("category");
                Category category = new Category();
                category.setName(lcat.get("name").toString());
                category.setId((int) Double.parseDouble(lcat.get("id").toString()));
                p.setCategory(category);
                List<Map<String,Object>> listUsers = (List<Map<String,Object>>)obj.get("users");

                for(Map<String,Object> lu : listUsers){
                    User u = new User();
                    u.setId((int) Double.parseDouble(lu.get("id").toString()));
                    u.setUsername(lu.get("username").toString());
                    u.setEmail(lu.get("email").toString());
                    userss.add(u);
                }

                p.setUsers(userss);

                return p;




        } catch (IOException ex) {

        }
        return null ;
    }
}
