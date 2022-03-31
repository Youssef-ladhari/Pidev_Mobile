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
import Utils.SideMenuBaseForm;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.TOP;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
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
public class UpdateExercice  extends SideMenuBaseForm {

    SideMenuBaseForm current;

    public UpdateExercice(Resources res, Form previous, Exercice p) {
        current = this;
        setTitle("Scrumify");
        setLayout(BoxLayout.y());
        getToolbar().setTitleCentered(false);
           Button returnButton = new Button("");
        returnButton.setUIID("Title");
        FontImage.setMaterialIcon(returnButton, FontImage.MATERIAL_ARROW_BACK);
        //returnButton.addActionListener(e -> new CoursForm(res, current).showBack());
        
        Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(returnButton)
        );
        getToolbar().setTitleComponent(titleCmp);

        setupSideMenu(res);

        add(new Label("Edit Exercice", "TodayTitle"));
        TextField tfNiveau = new TextField(p.getNiveau(), "Exercice Level");
        TextField tfQuestion = new TextField(p.getQuestion(), "Exercice Question");
        TextField tfReponse = new TextField(p.getCorrection(), "Exercice Answer");
        TextField tfEnonce = new TextField(p.getEnonce(), "Exercice Enonce");



        

        Button btnValider = new Button("Submit");
        btnValider.setUIID("LoginButton");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNiveau.getText().length() == 0) || (tfQuestion.getText().length() == 0) || (tfReponse.getText().length() == 0)|| (tfEnonce.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                          p.setNiveau(tfNiveau.getText());
                          p.setQuestion(tfQuestion.getText());
                          p.setCorrection(tfReponse.getText());
                          p.setEnonce(tfEnonce.getText());

                        if(ServiceExercice.getInstance().modifierCours(p)){
                              ToastBar.getInstance().setPosition(TOP);
                            ToastBar.Status status = ToastBar.getInstance().createStatus();
                            status.setIcon(res.getImage("scrumify.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));                    
                            status.setMessage("Project edited successfully");
                            status.setExpires(4000);  // only show the status for 3 seconds, then have it automatically clear
                            status.show();
                        new DetailExercice(res, current, p).show()
                       ;}

                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "type", new Command("OK"));
                    }

                }

            }
        });

        add(FlowLayout.encloseCenterMiddle(BoxLayout.encloseY(tfNiveau,tfQuestion,tfReponse,tfEnonce,btnValider)));

    }

}
