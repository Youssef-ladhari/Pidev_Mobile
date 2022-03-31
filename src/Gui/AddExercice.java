/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entites.Cours;
import Entites.Exercice;
import Services.ServiceCours;
import Services.ServiceExercice;
import Services.ServiceMail;
import Utils.SideMenuBaseForm;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.TOP;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author USER
 */
public class AddExercice extends SideMenuBaseForm {
   SideMenuBaseForm current;
    public static int id;

    public AddExercice(Resources res) {
        current = this;
        setTitle("DevBox");
        setLayout(BoxLayout.y());
        getToolbar().setTitleCentered(false);

        Button returnButton = new Button("");
        returnButton.setUIID("Title");
        FontImage.setMaterialIcon(returnButton, FontImage.MATERIAL_ARROW_BACK);
        returnButton.addActionListener(e -> new CoursForm(res, current).showBack());

        Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(returnButton)
        );
        getToolbar().setTitleComponent(titleCmp);

        setupSideMenu(res);

        add(new Label("Add a Exercice", "TodayTitle"));
        TextField tfNum = new TextField("Exercices Number", "Course Title");
        TextField tfNiveau = new TextField("Exercices Level", "Course Subject");
        TextField tfQuestion = new TextField("Exercices Question", "Course Teacher");
        TextField tfReponse = new TextField("Exercices Answer", "Course Teacher");
        TextField tfEnonce = new TextField("Exercices Enonce", "Course Teacher");


        


        Button btnValider = new Button("Add Exercice");
        btnValider.setUIID("LoginButton");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNum.getText().length() == 0) || (tfNiveau.getText().length() == 0) || (tfQuestion.getText().length() == 0)|| (tfReponse.getText().length() == 0)|| (tfEnonce.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {

                      //  Cours t = new Cours(tfName.getText(), tfDescription.getText(), tfTeach.getText());
                       Exercice t = new Exercice(Integer.parseInt(tfNum.getText()), tfNiveau.getText(), tfQuestion.getText(), tfReponse.getText(), tfEnonce.getText());
                      if (ServiceExercice.getInstance().ajoutEx(t)) {
                           // Services.ServiceMail ss = new ServiceMail();
                          //  ss.sendmailfunc(tfName.getText(), "New Course", "News!!");
                            ToastBar.getInstance().setPosition(TOP);

                           ToastBar.Status status = ToastBar.getInstance().createStatus();
                            status.setIcon(res.getImage("scrumify.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth() / 10, Display.getInstance().getDisplayWidth() / 15));

                            status.setMessage("Exercice added successfully");
                            status.setExpires(3000);  // only show the status for 3 seconds, then have it automatically clear
                            status.show();
                            new ExerciceFormm(res, current).show();
                        }

                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "type", new Command("OK"));
                    }

                }

            }
        });
        add(FlowLayout.encloseCenterMiddle(BoxLayout.encloseY(tfNum, tfNiveau, tfQuestion,tfReponse,tfEnonce, btnValider)));

    }
 
}
