package com.pidev.gui.Events;

import com.codename1.components.MultiButton;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.pidev.entities.Event;
import com.pidev.services.EventService;

import java.util.ArrayList;


public class ShowEvent extends Form{
    Form current;
    int n=0;
    private Resources theme;

    public ShowEvent(Form previous) {
        current =this;
        setTitle("events list");
        setLayout(BoxLayout.y());

        Button BUTActualiser = new Button("Actualiser");
        BUTActualiser.addActionListener((evt) -> new ShowEvent(current).show());
//        Button BUTTrieNom = new Button("Trie selon Nom ");
//        BUTTrieNom.addActionListener((evt) -> new ShowVoyage(current,1).show());
//        Button BUTTriePrix = new Button("Trie selon Prix ");
//        BUTTriePrix.addActionListener((evt) -> new ShowVoyage(current,2).show());
//        Button BUTTrieDest = new Button("Trie selon Dest ");
//        BUTTrieDest.addActionListener((evt) -> new ShowVoyage(current,3).show());
//        addAll(BUTTrieNom,BUTTriePrix,BUTTrieDest,BUTActualiser);
        add(BUTActualiser);

            ArrayList<Event> event = EventService.getInstance().affichageEvent();
            Container list = new Container(BoxLayout.y());
            list.setScrollableY(true);
            for (Event event1 : event) {

                //EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(50, 50, 0xffff0000), true);
                //Image i = URLImage.createToStorage(placeholder,voyage.getImage(),Statics.BASE_URL+"/uploads/"+voyage.getImage());
                MultiButton sp = new MultiButton(event1.getNom_event());
                sp.setTextLine1("type_event : "+event1.getType_event()+" Lieu : "+event1.getLieu_event());
                sp.setTextLine2("Description : "+event1.getDescription_event());
               // sp.setTextLine2("Date: "+event1.getDate_event());
                list.add(sp);
                sp.addActionListener((evt) -> {
                    System.out.println("Participer");
//                    if (Dialog.show("Confirmation", "Que voulez vous faire ?", "Supprimer", "Modifier")) {
//
//                        if( ServiceVoyage.getInstance().deletedVoyage(voyage.getId())){
//                            {
//                                Dialog.show("Success","L'agence "+voyage.getNom_Voyage()+" a été supprimé avec succées",new Command("OK"));
//                                previous.showBack();
//                            }
//                        }
//                    }
//                    else{
//
//                        new ModifierVoyage(current,voyage).show();
//                    }
                    //  new AddResrvationVoyage(current,voyage).show();
                });
            }

            //SpanLabel sp = new SpanLabel();
            //sp.setText(ServiceVoyage.getInstance().affichageVoyage().toString());
            this.add(list);

//        if(n==1)
//        {
//            ArrayList<Entity.Voyage> voyages = ServiceVoyage.getInstance().order_By_NomJSON();
//            Container list = new Container(BoxLayout.y());
//            list.setScrollableY(true);
//            for (Entity.Voyage voyage : voyages) {
//
//                EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(50, 50, 0xffff0000), true);
//                Image i = URLImage.createToStorage(placeholder,voyage.getImage(),Statics.BASE_URL+"/uploads/"+voyage.getImage());
//                MultiButton sp = new MultiButton(voyage.getNom_Voyage());
//                sp.setIcon(i.fill(200, 200));
//                sp.setTextLine1("Destination : "+voyage.getDestination()+" Prix : "+voyage.getPrix_Voyage());
//                sp.setTextLine2("Durèe : "+voyage.getDuree_Voyage());
//                list.add(sp);
//                sp.addActionListener((evt) -> {
//                    System.out.println("reserver");
//                    new AddResrvationVoyage(current,voyage).show();
//                });
//            }
//
//            //SpanLabel sp = new SpanLabel();
//            //sp.setText(ServiceVoyage.getInstance().affichageVoyage().toString());
//            this.add(list);
//        }
//
//        if(n==2)
//        {
//            ArrayList<Entity.Voyage> voyages = ServiceVoyage.getInstance().order_By_PrixJSON();
//            Container list = new Container(BoxLayout.y());
//            list.setScrollableY(true);
//            for (Entity.Voyage voyage : voyages) {
//
//                EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(50, 50, 0xffff0000), true);
//                Image i = URLImage.createToStorage(placeholder,voyage.getImage(),Statics.BASE_URL+"/uploads/"+voyage.getImage());
//                MultiButton sp = new MultiButton(voyage.getNom_Voyage());
//                sp.setIcon(i.fill(200, 200));
//                sp.setTextLine1("Destination : "+voyage.getDestination()+" Prix : "+voyage.getPrix_Voyage());
//                sp.setTextLine2("Durèe : "+voyage.getDuree_Voyage());
//                list.add(sp);
//                sp.addActionListener((evt) -> {
//                    System.out.println("reserver");
//                    new AddResrvationVoyage(current,voyage).show();
//                });
//            }
//
//            //SpanLabel sp = new SpanLabel();
//            //sp.setText(ServiceVoyage.getInstance().affichageVoyage().toString());
//            this.add(list);
//        }
//
//        if(n==3)
//        {
//            ArrayList<Entity.Voyage> voyages = ServiceVoyage.getInstance().order_By_DestJSON();
//            Container list = new Container(BoxLayout.y());
//            list.setScrollableY(true);
//            for (Entity.Voyage voyage : voyages) {
//
//                EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(50, 50, 0xffff0000), true);
//                Image i = URLImage.createToStorage(placeholder,voyage.getImage(),Statics.BASE_URL+"/uploads/"+voyage.getImage());
//                MultiButton sp = new MultiButton(voyage.getNom_Voyage());
//                sp.setIcon(i.fill(200, 200));
//                sp.setTextLine1("Destination : "+voyage.getDestination()+" Prix : "+voyage.getPrix_Voyage());
//                sp.setTextLine2("Durèe : "+voyage.getDuree_Voyage());
//                list.add(sp);
//                sp.addActionListener((evt) -> {
//                    System.out.println("reserver");
//                    new AddResrvationVoyage(current,voyage).show();
//                });
//            }
//
//            //SpanLabel sp = new SpanLabel();
//            //sp.setText(ServiceVoyage.getInstance().affichageVoyage().toString());
//            this.add(list);
//        }
//        getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {
//            previous.showBack();
//        });
    }
}
