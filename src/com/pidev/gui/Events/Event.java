package com.pidev.gui.Events;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.pidev.SideMenuBaseForm;

public class Event extends SideMenuBaseForm {
    Form current;
    public Event(Resources res) {
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        current=this; //Back
        add(new Label("Welcome to Events "));
        setTitle(" --Events-- ");
        setLayout(BoxLayout.y());

        Button BUTAdd = new Button("Add Event");
       // Button BUTMod = new Button("Modifier Voyage");
        Button BUTSup = new Button("Supprimer Event");
        Button BUTShow = new Button("Show Event");
        Button BUTCam = new Button("Camera ");
        BUTAdd.addActionListener((evt) -> new AddEvent(current).show());
        BUTSup.addActionListener((evt) -> new DeleteEvent(current).show());
        //BUTMod.addActionListener((evt) -> new UpdateEvent(current).show());
        BUTShow.addActionListener((evt) -> new ShowEvent(current, 0).show());
        addAll(BUTAdd,BUTShow,BUTSup);

        }
}
