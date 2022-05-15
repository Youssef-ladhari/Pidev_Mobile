package com.pidev.gui.Events;

import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.pidev.entities.Event;
import com.pidev.services.EventService;

public class DeleteEvent extends Form {

    public DeleteEvent(Form previous) {
        setTitle("Delete Event");
        setLayout(BoxLayout.y());
        TextField id = new TextField("", "id");
        id.getStyle().setFgColor(154245);
        Button btnValider = new Button("Valider");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((id.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Event event= new Event(Integer.parseInt(id.getText()));
                        if( EventService.getInstance().deleteEvent(Integer.parseInt(id.getText())))
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
        addAll(id,btnValider);
    }
}
