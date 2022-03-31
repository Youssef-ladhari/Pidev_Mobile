/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;


import Entites.Cours;
import Services.ServiceCours;
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
import Utils.SideMenuBaseForm;



public class EditCours extends SideMenuBaseForm {

    SideMenuBaseForm current;

    public EditCours(Resources res, Form previous, Cours p) {
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

        add(new Label("Edit Course", "TodayTitle"));
        TextField tfName = new TextField(p.getTitre(), "Course Name");
        TextField tfDescription = new TextField(p.getMatiere(), "Course Subject");
        TextField tfteachField = new TextField(p.getAnnee(), "Teacher Subject");


        

        Button btnValider = new Button("Submit");
        btnValider.setUIID("LoginButton");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length() == 0) || (tfDescription.getText().length() == 0) || (tfteachField.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                          p.setTitre(tfName.getText());
                          p.setMatiere(tfDescription.getText());
                          System.out.println(tfDescription.getText());
                          p.setAnnee(tfteachField.getText());
                        if(ServiceCours.getInstance().modifierCours(p)){
                              ToastBar.getInstance().setPosition(TOP);
                            ToastBar.Status status = ToastBar.getInstance().createStatus();
                            status.setIcon(res.getImage("scrumify.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));                    
                            status.setMessage("Course edited successfully");
                            status.setExpires(4000);  // only show the status for 3 seconds, then have it automatically clear
                            status.show();
                        new CoursDetailsForm(res, current, p).show();}

                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "type", new Command("OK"));
                    }

                }

            }
        });

        add(FlowLayout.encloseCenterMiddle(BoxLayout.encloseY(tfName,tfDescription,tfteachField,btnValider)));

    }

   
}
