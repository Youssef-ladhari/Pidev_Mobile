package com.pidev.services;

import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import com.pidev.entities.Event;
import com.pidev.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Float.parseFloat;

public class EventService {

    public ArrayList<Event> events;
    private boolean responseOk = false;

    public static EventService instance = null;

    private ConnectionRequest request;

    public static EventService getInstance(){
        if(instance == null){
            instance = new EventService();
        }
        return instance;
    }

    public EventService(){
        request = new ConnectionRequest();
    }

    public boolean addEvent(Event event){

        try{
            String jsonEvent= "{" +
                    "\"nom_event\": \""+event.getNom_event()+"\"," +
                    "\"type_event\": \""+event.getType_event()+"\"," +
                    "\"date_event\": \""+event.getDate_event()+"\"," +
                    "\"lieu_event\": \""+event.getLieu_event()+"\"," +
                    "\"description_event\": \""+event.getDescription_event()+"\"," +
                    "\"status_event\": \""+event.getStatus_event()+"\"" +
                    "}";
        String url =  "http://127.0.0.1:8000/event/add";
        request.setUrl(url);
        request.setPost(true);
        request.setRequestBody(jsonEvent);
//        request.addArgument("nom_event", event.getNom_event());
//        request.addArgument("type_event", event.getType_event());
//        request.addArgument("date_event", event.getDate_event());
//        request.addArgument("lieu_event", event.getLieu_event());
//        request.addArgument("description_event", event.getDescription_event());
//        request.addArgument("status_event",event.getStatus_event());
        System.out.println(request.getRequestBody());

        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseOk = request.getResponseCode() == 200; //Code Status Http 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return responseOk;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Event> affichageEvent(){
        String url = "http://127.0.0.1:8000/event/liste";
        request.setUrl(url);
        request.setPost(false);

        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                String response = new String(request.getResponseData());
                events = parseTask(response);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return events;
    }

    public ArrayList<Event> parseTask(String jsonText){
        try {
            events =new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson =
                    j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            System.out.println("aaaaaaaaaaaaa"+jsonText);
            for(Map<String,Object> obj : list){
                Event e = new Event();
                float id = parseFloat(obj.get("id").toString());
                e.setId((int)id);
                e.setNom_event(obj.get("nom_event").toString());
                e.setType_event(obj.get("type_event").toString());
                e.setDate_event(obj.get("date_event").toString());
                e.setLieu_event(obj.get("lieu_event").toString());
                e.setDescription_event(obj.get("description_event").toString());
                events.add(e);
            }


        } catch (IOException ex) {

        }
        return events;
    }

    public boolean UpdateEvent(int id,String nom_event,String type_event, String date_event, String lieu_event, String description_event)
    {
        String url = Statics.BASE_URL+"/event/UpdateJson/"+id+"/"+nom_event+"/"+type_event+"/"+date_event+"/"+lieu_event+"/"+description_event;
        //  String url = Statics.BASE_URL + "create";
        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseOk = request.getResponseCode() == 200; //Code HTTP 200 OK
                request.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return responseOk;
    }




    public boolean deleteEvent(int id) {

        String url = "http://127.0.0.1:8000/event/delete"+id+"";
        request.setUrl(url);
        request.setPost(true);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseOk = request.getResponseCode() == 200;
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return responseOk;
    }



//    public ArrayList<Event> affichageEvent()
//    {
//        ArrayList<Event> result = new ArrayList<>();
//        String  url = Statics.BASE_URL +"event/liste";
//        request.setUrl(url);
//        request.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                JSONParser jsonp;
//                jsonp = new JSONParser();
//                try {
//                    //renvoi une map avec clé = root et valeur le reste
//                    Map<String, Object> mapEvent = jsonp.parseJSON(new CharArrayReader(new String(request.getResponseData()).toCharArray()));
//
//                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapEvent.get("root");
//
//                    System.out.println(listOfMaps.toString());
//                    for (Map<String, Object> obj : listOfMaps) {
//                        Event e = new Event();
//                        int id = (int) parseFloat(obj.get("id").toString());
//                        String nom_event = obj.get("nom_event").toString();
//                        String type_event = obj.get("type_event").toString();
//                        String date_event= obj.get("date_event").toString();
//                        String lieu_event = obj.get("lieu_event").toString();
//                        String description_event = obj.get("description_event").toString();
//
//
//                        e.setId((int) id);
//                        e.setNom_event(nom_event);
//                        e.setType_event(type_event);
//                        e.setLieu_event(lieu_event);
//                        e.setDescription_event(description_event);
//
//
////                        String DateConverter=obj.get("date").toString().substring(obj.get("Date").toString().indexOf("timestamp")+10 , obj.get("Date").toString().lastIndexOf("}"));
//                        //             Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
//                        //             SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//                        //            String dateString = formatter.format(currentTime);
//                        //            v.setDate(dateString);
//                        result.add(e);
//
//                    }
//                }
//
//                catch(Exception e ){
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        NetworkManager.getInstance().addToQueueAndWait(request);
//
//        return result;
//    }


    public ArrayList<Event> order_By_NomJSON()
    {
        ArrayList<Event> result = new ArrayList<>();
        String  url = Statics.BASE_URL +"/voyage/order_By_NomJSON";
        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> mapVoyage = jsonp.parseJSON(new CharArrayReader(new String(request.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapVoyage.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                        Event v = new Event();
                        int id = (int) Float.parseFloat(obj.get("id").toString());
                        String nom_event = obj.get("nom_event").toString();
                        String type_event = obj.get("type_event").toString();
                        String date_event = obj.get("date_event").toString();
                        String lieu_event = obj.get("lieu_event").toString();
                        String description_event = obj.get("description_event").toString();



                        v.setId((int) id);
                        v.setNom_event(nom_event);
                        v.setType_event(type_event);
                        v.setDate_event(date_event);
                        v.setLieu_event(lieu_event);
                        v.setDescription_event(description_event);


//                        String DateConverter=obj.get("date").toString().substring(obj.get("Date").toString().indexOf("timestamp")+10 , obj.get("Date").toString().lastIndexOf("}"));
                        //             Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
                        //             SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        //            String dateString = formatter.format(currentTime);
                        //            v.setDate(dateString);
                        result.add(v);

                    }
                }

                catch(Exception e ){
                    e.printStackTrace();
                }
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(request);

        return result;
    }
}
