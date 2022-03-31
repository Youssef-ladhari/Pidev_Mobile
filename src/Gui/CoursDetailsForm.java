/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;


import Entites.Cours;
import Utils.SideMenuBaseForm;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.Button;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import java.util.Map;

/**
 *
 * @author AmiraDoghri
 */
public class CoursDetailsForm extends SideMenuBaseForm {

    SideMenuBaseForm current;

    public CoursDetailsForm(Resources res, Form previous, Cours p) {
        super(BoxLayout.y());
        current = this;
        getToolbar().setTitleCentered(false);
         // Container te = FlowLayout.encloseCenter(new Label("Team : " + tt.getName(), "SideMenuTitle"));
        Button returnButton = new Button("");
        returnButton.setUIID("Title");
        FontImage.setMaterialIcon(returnButton, FontImage.MATERIAL_ARROW_BACK);
        returnButton.addActionListener(e -> new CoursForm(res, current).showBack());

        Container titleCmp = BoxLayout.encloseY(
                BorderLayout.west(returnButton),
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                new Label(p.getTitre(), "Title")
                                
                        )
                )
        );
       

        getToolbar().setTitleComponent(titleCmp);

        add(new Label("Course Details ", "TodayTitle"));

        add(new Label("Title : " + p.getTitre(), "Label"));
        add(new Label("Subject : " + p.getMatiere(), "Label"));
        add(new Label("Teacher : " + p.getAnnee(), "Label"));

        add(new Label("More ", "TodayTitle"));
        Button exercice = new Button("Exercice");

        exercice.setUIID("ActionButton");

        exercice.addActionListener((evt) -> {
            System.out.println(p.getId());

            new ExerciceFormm(res,current).show();

        });
        add(BoxLayout.encloseYBottom(exercice));

        setupSideMenu(res);

    }

}
