/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entites.Exercice;
import Services.ServiceExercice;
import Utils.SideMenuBaseForm;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.RIGHT;
import static com.codename1.ui.Component.TOP;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;


public class ExerciceFront extends SideMenuBaseForm {

    public static Resources res;
    
    ServiceExercice pr = new ServiceExercice() ;
    ArrayList<Exercice> listT = pr.getexercice();
    SideMenuBaseForm current;

    public ExerciceFront(Resources res, Form previous) {
        super(BoxLayout.y());
        this.res = res;
        current = this;
        getToolbar().setTitleCentered(false);

      /*  Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);

        menuButton.addActionListener(e -> getToolbar().openSideMenu());
*/
        Container Allprojects = BoxLayout.encloseY(
                new Label("All ", "CenterTitle")
        );
        Allprojects.setUIID("RemainingTasks");
        

        Button all = new Button();
        Button comp = new Button();
        Button curr = new Button();
    /*    Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton),
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(new Label("Exercices", "CenterTitle"))
                ),
                GridLayout.encloseIn(1,Allprojects)
        );*/
        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        fab.getAllStyles().setMarginUnit(Byte.MAX_VALUE);
      //  fab.getAllStyles().setMargin(BOTTOM, completedProjects.getPreferredH() - fab.getPreferredH() / 2);
      //  getToolbar().setTitleComponent(fab.bindFabToContainer(titleCmp, RIGHT, TOP));
        add(new Label("Exercice", "TodayTitle"));

        fab.addActionListener((evvt) -> {
                       new AddExercice(res).show();

        });

        curr.addActionListener((evt) -> {

           // new CurrProjectsForm(res, current).show();;

        });
        comp.addActionListener((evt) -> {
         //   new CompProjectsForm(res, current).show();;

        });
         Allprojects.setLeadComponent(all);
        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
         if(listT.isEmpty()) {
            System.out.println("null");
             Image empty = res.getImage("landing_1.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth() / 3, Display.getInstance().getDisplayHeight() / 4);

                Container ct = new Container(BoxLayout.yCenter());
                ct.add(empty);
                ct.add(new Label("No active Exercice !", "TodayTitle"));
                Button add = new Button("Get started");
                add.setUIID("LoginButton");
                add.addActionListener((evt) -> {
                  new AddExercice(res).show();
                });
                ct.add(add);
                add(FlowLayout.encloseCenterMiddle(ct));
                ToastBar.getInstance().setPosition(TOP);
                ToastBar.Status status = ToastBar.getInstance().createStatus();
                status.setIcon(res.getImage("scrumify.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth() / 10, Display.getInstance().getDisplayWidth() / 15));
                status.setMessage("No Exercice yet");
                status.setExpires(3000);  // only show the status for 3 seconds, then have it automatically clear
                status.show();
             
        } else {
          for (int i = 0; i < listT.size(); i++) {

                Exercice p = listT.get(i);

                Container c = new Container(BoxLayout.x());

                
                addProjectBox( p);
     

       
        }
    }
    }

    private void addProjectBox(Exercice p) {
        Container projectBox = new Container(BorderLayout.absolute(), "ProjectItem");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_GRADE, "icon", 3);
        Container niveau = new Container(BoxLayout.x());
        niveau.add(icon);
        niveau.add(new Label(p.getNiveau(),"TodayProject"));
        //
         FontImage icon1 = FontImage.createMaterial(FontImage.MATERIAL_BOOK, "icon", 3);
        Container enonce = new Container(BoxLayout.x());
        enonce.add(icon1);
        enonce.add(new Label(p.getNiveau(),"TodayEntry"));
        //
        FontImage icon2 = FontImage.createMaterial(FontImage.MATERIAL_QUESTION_ANSWER, "icon", 3);
        Container question = new Container(BoxLayout.x());
        question.add(icon2);
        question.add(new Label(p.getNiveau(),"TodayEntry"));
        //
        FontImage icon3 = FontImage.createMaterial(FontImage.MATERIAL_CODE, "icon", 3);
        Container numero = new Container(BoxLayout.x());
        numero.add(icon3);
        numero.add(new Label(p.getNiveau(),"TodayEntry"));
        
        
        projectBox.add(BorderLayout.NORTH, BoxLayout.encloseY(niveau,enonce,question,numero));


       
        Button view = new Button(FontImage.MATERIAL_ZOOM_IN, "icon");

        view.addActionListener((evt) -> {
           // new DetailExerciceFront(res, current, p).show();

        });
       
        Container con = new Container(BoxLayout.xRight());

        con.addAll(view);
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
