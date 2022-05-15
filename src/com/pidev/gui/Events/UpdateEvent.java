package com.pidev.gui.Events;

import com.codename1.components.InfiniteProgress;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.pidev.entities.Event;
import com.pidev.services.EventService;

public class UpdateEvent extends Form {


    public UpdateEvent(Form previous, Event v) {
        setTitle("Add Event");
        setLayout(BoxLayout.y());
        TextField nom_event = new TextField(v.getNom_event(), "Nom_Event");
        nom_event.getStyle().setFgColor(154245);
        TextField type_event = new TextField(v.getType_event(), "Type");
        type_event.getStyle().setFgColor(154245);
        TextField date_event = new TextField(v.getDate_event(), "Date_event");
        date_event.getStyle().setFgColor(154245);
        TextField lieu_event = new TextField(v.getLieu_event(),"Lieu_Event");
        lieu_event.getStyle().setFgColor(154245);
        TextField description_event = new TextField(v.getDescription_event(), "Description");
        description_event.getStyle().setFgColor(154245);
        Button btnValider = new Button("Valider");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((nom_event.getText().length()==0)||(type_event.getText().length()==0)||(lieu_event.getText().length()==0)||(description_event.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {  InfiniteProgress ip = new InfiniteProgress();
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    try {
                        v.setId(v.getId());
                        v.setNom_event(nom_event.getText());
                        v.setType_event(type_event.getText());
                        v.setDate_event(date_event.getText());
                        v.setLieu_event(lieu_event.getText());
                        v.setDescription_event(description_event.getText());
                        if(EventService.getInstance().UpdateEvent(v.getId(),nom_event.getText(),type_event.getText(), date_event.getText(),lieu_event.getText(),description_event.getText()))
                        {
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                            previous.showBack();
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }            }
        });
        getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            previous.showBack();
        });
        addAll(nom_event,type_event,lieu_event,description_event,btnValider);
    }
}
