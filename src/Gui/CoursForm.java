/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;


import Entites.Cours;
import Services.ServiceCours;
import Utils.SideMenuBaseForm;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.components.FloatingActionButton;

import com.codename1.components.ToastBar;
import com.codename1.ui.Button;

import static com.codename1.ui.Component.RIGHT;
import static com.codename1.ui.Component.TOP;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import java.util.ArrayList;
import com.codename1.ui.util.Resources;



public class CoursForm extends SideMenuBaseForm {

    public static Resources res;
    SideMenuBaseForm current;
            ServiceCours pr = new ServiceCours();

            ArrayList<Cours> listT = pr.getcours();
            


    public CoursForm(Resources res, Form previous) {
        super(BoxLayout.y());
                System.out.println(listT);

        this.res = res;
        current = this;
        getToolbar().setTitleCentered(false);

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);

        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Container Allprojects = BoxLayout.encloseY(
                new Label("All ", "CenterTitle")
        );
        Allprojects.setUIID("RemainingTasks");
        

        Button all = new Button();
        Button comp = new Button();
        Button curr = new Button();
        Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton),
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(new Label("Mes cours", "CenterTitle"))
                ),
                GridLayout.encloseIn(1, Allprojects)
        );
        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        fab.getAllStyles().setMarginUnit(Byte.MAX_VALUE);
        getToolbar().setTitleComponent(fab.bindFabToContainer(titleCmp, RIGHT, TOP));
        add(new Label("Cours", "TodayTitle"));

        fab.addActionListener((evvt) -> {
            new AddCours(res).show();
        });
Allprojects.setLeadComponent(all);
       
        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
         if(listT.isEmpty()) {
            System.out.println("null");
             Image empty = res.getImage("landing_1.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth() / 3, Display.getInstance().getDisplayHeight() / 4);

                Container ct = new Container(BoxLayout.yCenter());
                ct.add(empty);
                ct.add(new Label("No active Cours !", "TodayTitle"));
                Button add = new Button("Get started");
                add.setUIID("LoginButton");
                add.addActionListener((evt) -> {
                  new AddCours(res).show();
                });
                ct.add(add);
                add(FlowLayout.encloseCenterMiddle(ct));

                ToastBar.getInstance().setPosition(TOP);
                ToastBar.Status status = ToastBar.getInstance().createStatus();
                status.setIcon(res.getImage("scrumify.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth() / 10, Display.getInstance().getDisplayWidth() / 15));
                status.setMessage("No projects yet");
                status.setExpires(3000);  // only show the status for 3 seconds, then have it automatically clear
                status.show();
             
        } else {
          for (int i = 0; i < listT.size(); i++) {

                Cours p = listT.get(i);

                Container c = new Container(BoxLayout.x());

                
                addProjectBox( p);
     

       
        }
         setupSideMenu(res);
    }
         }

    private void addProjectBox(Cours p) {
        Container projectBox = new Container(BorderLayout.absolute(), "CoursItem");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_SCHOOL, "icon", 3);
        FontImage icon1 = FontImage.createMaterial(FontImage.MATERIAL_SUBJECT, "icon", 3);
        FontImage icon2 = FontImage.createMaterial(FontImage.MATERIAL_PERSON, "icon", 3);
        Container title = new Container(BoxLayout.x());
        title.add(icon);
        title.add(new Label(p.getTitre(),"TodayProject"));
        Container subject = new Container(BoxLayout.x());
        subject.add(icon1);
        subject.add(new Label(p.getMatiere(),"TodayEntry"));
        Container teacher = new Container(BoxLayout.x());
        teacher.add(icon2);
        teacher.add(new Label(p.getAnnee(),"TodayEntry"));
     
       
        projectBox.add(BorderLayout.NORTH, BoxLayout.encloseY(title,subject,teacher));
   Button edit = new Button(FontImage.MATERIAL_EDIT, "icon");
        Button archive = new Button(FontImage.MATERIAL_ARCHIVE, "icon");
        Button sprint = new Button(FontImage.MATERIAL_WORK, "icon");
        Button delete = new Button(FontImage.MATERIAL_DELETE_FOREVER, "icon");
        Button view = new Button(FontImage.MATERIAL_ZOOM_IN, "icon");

        view.addActionListener((evt) -> {
            new CoursDetailsForm(res, current, p).show();

        });
        edit.addActionListener((evt) -> {
            System.out.println(p.getId());

            new EditCours(res, this, p).show();

        });

        archive.addActionListener((evt) -> {
            ToastBar.getInstance().setPosition(TOP);
            ToastBar.Status status = ToastBar.getInstance().createStatus();
            status.setIcon(res.getImage("scrumify.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth() / 10, Display.getInstance().getDisplayWidth() / 15));
            status.setMessage("Project archived successfully");
            status.setExpires(3000);  // only show the status for 3 seconds, then have it automatically clear
            status.show();
            new CoursForm(res, current).show();
        });
        delete.addActionListener((evt) -> {
             Dialog dig = new Dialog("Suppression");
            
            if(dig.show("Suppression","Vous voulez supprimer ce cours ?","Annuler","Oui")) {
                dig.dispose();
            }
            else {
                dig.dispose();
                 }
            System.out.println("l'id cours est \n"+p.getId());
                //n3ayto l suuprimer men service Reclamation
                if(ServiceCours.getInstance().deleteCours(p.getId())) {
                    new CoursForm(res,current).show();
                }
           
        });

        sprint.addActionListener((evt) -> {
           new ExerciceFormm(res,current).show();
        });
        Container con = new Container(BoxLayout.xRight());

        // con.add(projectBox);
        con.addAll(view, edit, archive, sprint, delete);
        projectBox.add(BorderLayout.SOUTH, con);
        add(BoxLayout.encloseY(projectBox));

    }

    private Image createCircleLine(int color, int height, boolean first) {
        Image img = Image.createImage(height, height, 0);
        Graphics g = img.getGraphics();
        g.setAntiAliased(true);
        g.setColor(0xcccccc);
        int y = 0;
        if (first) {
            y = height / 6 + 1;
        }
        g.drawLine(height / 2, y, height / 2, height);
        g.drawLine(height / 2 - 1, y, height / 2 - 1, height);
        g.setColor(color);
        g.fillArc(height / 2 - height / 4, height / 6, height / 2, height / 2, 0, 360);
        return img;
    }

}
