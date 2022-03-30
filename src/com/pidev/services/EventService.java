package com.pidev.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.List;
import com.codename1.ui.events.ActionListener;
import com.pidev.entities.Event;
import com.pidev.utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import java.io.IOException;

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
        request.addArgument("name", event.getNom_event());
        request.addArgument("type", event.getType_event());
        request.addArgument("date", String.valueOf(event.getDate_event()));
        request.addArgument("lieu", event.getLieu_event());
        request.addArgument("description", event.getDescription_event());

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
                events = parseEvent(response);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return events;
    }

    public List<Event> parseEvent (String jsonText){
        try {
            events = new ArrayList<>();
            System.out.println(jsonText);
            System.out.println("+++++++++++++");
            JSONParser jsonParser = new JSONParser();
            Map<String, Object> eventListJson= jsonParser.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println(eventListJson);

            List<Map<String,Object>> list = (List<Map<String,Object>>) eventListJson.get("root");
            for(Map<String,Object> obj : list){
                Event event = new Event();
                float id = Float.parseFloat(obj.get("id").toString());
                event.setId((int)id);

                try{
                    event.setNom_event(obj.get("nom_event").toString());
                }catch(NullPointerException e){
                    event.setNom_event("null");
                }

                try{
                    event.setType_event(obj.get("type_event").toString());

                }catch(NullPointerException e){
                    event.setType_event("null");
                }

                try{
                    event.setDate_event(obj.get("date_event").toString());

                }catch(NullPointerException e){
                    event.setDate_event(ToDate(0));
                }

                try{
                    event.setLieu_event(obj.get("lieu_event").toString());

                }catch(NullPointerException e){
                    event.setLieu_event("null");
                }
                try{
                    event.setDescription_event(obj.get("description_event").toString());

                }catch(NullPointerException e){
                    event.setDescription_event("null");
                }

                events.add(event);
            }
        } catch (IOException ex) {
        }
        return events;
    }

}
