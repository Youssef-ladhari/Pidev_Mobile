package com.pidev.gui.Events;

import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.pidev.entities.Event;
import com.pidev.services.EventService;


public class AddEvent extends Form{

    public AddEvent(Form previous) {
        setTitle("Add Event");
        setLayout(BoxLayout.y());
        TextField nom_event = new TextField("", "nom_event");
        nom_event.getStyle().setFgColor(154245);
        TextField type_event = new TextField("", "type_event");
        type_event.getStyle().setFgColor(154245);
        TextField date_event = new TextField("", "date_event");
        date_event.getStyle().setFgColor(154245);
        TextField lieu_event = new TextField("", "lieu_event");
        lieu_event.getStyle().setFgColor(154245);
        TextField description_event = new TextField("", "description_event");
        description_event.getStyle().setFgColor(154245);
        Button btnValider = new Button("Valider");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.out.println((nom_event.getText().length()==0)||(type_event.getText().length()==0)||(date_event.getText().length()==0)||(lieu_event.getText().length()==0)||(description_event.getText().length()==0));
                if ((nom_event.getText().length()==0)||(type_event.getText().length()==0)||(date_event.getText().length()==0)||(lieu_event.getText().length()==0)||(description_event.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        //nom_event.getText(),type_event.getText(),date_event.getText(),lieu_event.getText(),description_event.getText()
                        Event events = new Event();
                        events.setNom_event(nom_event.getText());
                        events.setType_event(type_event.getText());
                        events.setDate_event(date_event.getText());
                        events.setLieu_event(lieu_event.getText());
                        events.setDescription_event(description_event.getText());
                        events.setStatus_event("Pending");
                        if( EventService.getInstance().addEvent(events))
                        {
                            Dialog.show("Success","Connection accepted",new Command("OK"));
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
        addAll(nom_event,type_event,date_event,lieu_event,description_event,btnValider);
    }
}
