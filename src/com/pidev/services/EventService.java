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

    public List<Event> events;
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

        String url =  Statics.BASE_URL + "create";
        request.setUrl(url);
        request.setPost(false);
        request.addArgument("nom_event", event.getNom_event());
        request.addArgument("type_event", event.getType_event());
        request.addArgument("date_event", String.valueOf(event.getDate_event()));
        request.addArgument("lieu_event", event.getLieu_event());
        request.addArgument("description_event", event.getDescription_event());

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

    public List<Event> getAllEvents(){
        String url = Statics.BASE_URL + "get";
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

    public List<Event> parseTask(String jsonText){
        try {
            events =new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson =
                    j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Event e = new Event(nom_event.getText(), type_event.getText(), lieu_event.getText(), description_event.getText());
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

    public boolean UpdateEvent(Event events)
    {
        String url = Statics.BASE_URL+"/voyage/UpdateVoyageJSON/"+events.getId()+"&Nom_event="+events.getNom_event()+"&type_event="+events.getType_event()+"&date_event="+events.getDate_event()+"&lieu_event="+events.getLieu_event()+"?Destination="+events.getDescription_event();
        //  String url = Statics.BASE_URL + "create";
        request.setUrl(url);
        request.addResponseListener((e) -> {
            responseOk = request.getResponseCode() == 200; //Code HTTP 200 OK
            String str = new String(request.getResponseData());
            System.out.println("data"+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return responseOk;
    }

    public boolean deleteEvent(int id) {

        String url = Statics.BASE_URL + "/voyage/DeleteVoyageJSON/" + id + "";
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

    public ArrayList<Event> affichageEvent()
    {
        ArrayList<Event> result = new ArrayList<>();
        String  url = Statics.BASE_URL +"/voyage/AllVoyageJSON";
        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> mapEvent = jsonp.parseJSON(new CharArrayReader(new String(request.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapEvent.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                        Event e = new Event(nom_event.getText(), type_event.getText(), lieu_event.getText(), description_event.getText());
                        int id = (int) parseFloat(obj.get("id").toString());
                        String nom_event = obj.get("nom_event").toString();
                        String type_event = obj.get("type_event").toString();
                        //Date date_event=(Date) request.parseDate();
                        String lieu_event = obj.get("lieu_event").toString();
                        String description_event = obj.get("description_event").toString();


                        e.setId((int) id);
                        e.setNom_event(nom_event);
                        e.setType_event(type_event);
                        e.setLieu_event(lieu_event);
                        e.setDescription_event(description_event);

//                        String DateConverter=obj.get("date").toString().substring(obj.get("Date").toString().indexOf("timestamp")+10 , obj.get("Date").toString().lastIndexOf("}"));
                        //             Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
                        //             SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        //            String dateString = formatter.format(currentTime);
                        //            v.setDate(dateString);
                        result.add(e);

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
