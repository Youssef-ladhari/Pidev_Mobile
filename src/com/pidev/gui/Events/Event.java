package com.pidev.gui.Events;

import com.codename1.components.MultiButton;
import com.codename1.ui.*;
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
Container ctb = new Container();

        Button menuButton = new Button();
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        Container Pricelab = BoxLayout.encloseY();
        ctb.add(menuButton);
        Button BUTAdd = new Button("Add Event");
        //Button BUTMod = new Button("Modifier Event");
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
