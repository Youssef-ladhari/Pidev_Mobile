package com.pidev.gui.Events;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

public class Event extends Form {
    Form current;
    public Event(Form previous) {
        current=this; //Back
        add(new Label("Welcom to Travel_Me"));
        setTitle(" --Voyage-- ");
        setLayout(BoxLayout.y());

        Button BUTAdd = new Button("Add Voyage");
        Button BUTMod = new Button("Modifier Voyage");
        Button BUTSup = new Button("Supprimer Voyage");
        Button BUTShow = new Button("Show Voyage");
        Button BUTCam = new Button("Camera ");
        BUTAdd.addActionListener((evt) -> new AddEvent(current).show());
        BUTSup.addActionListener((evt) -> new DeleteEvent(current).show());
        //BUTMod.addActionListener((evt) -> new UpdateEvent(current).show());
        BUTShow.addActionListener((evt) -> new ShowEvent(current).show());
        addAll(BUTAdd,BUTShow,BUTSup,BUTMod);

        }
}
