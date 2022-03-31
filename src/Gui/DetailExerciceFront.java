/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entites.Exercice;
import Utils.SideMenuBaseForm;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author USER
 */
public class DetailExerciceFront extends SideMenuBaseForm {
        SideMenuBaseForm current;
   public DetailExerciceFront(Resources res, Form previous, Exercice p) {
        super(BoxLayout.y());
        current = this;
        getToolbar().setTitleCentered(false);
         // Container te = FlowLayout.encloseCenter(new Label("Team : " + tt.getName(), "SideMenuTitle"));
        Button returnButton = new Button("");
        returnButton.setUIID("Title");
        FontImage.setMaterialIcon(returnButton, FontImage.MATERIAL_ARROW_BACK);
        returnButton.addActionListener(e -> new ExerciceFront(res, current).showBack());

        Container titleCmp = BoxLayout.encloseY(
                BorderLayout.west(returnButton),
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                new Label(p.getNiveau(),"Title")
                                
                        )
                )
        );
       

        getToolbar().setTitleComponent(titleCmp);

        add(new Label("Exercice Details ", "TodayTitle"));

        add(new Label("Enonce : " + p.getEnonce(), "Label"));
        add(new Label("Question : " + p.getQuestion(), "Label"));
        add(new Label("Correction : " + p.getCorrection(), "Label"));
        add(new Label("Numero : " + p.getNumero(), "Label"));


       /* add(new Label("More ", "TodayTitle"));
        Button exercice = new Button("Exercice");

        exercice.setUIID("ActionButton");

        exercice.addActionListener((evt) -> {
            System.out.println(p.getId());

            new ExerciceFormm(res,current).show();

        });
        add(BoxLayout.encloseYBottom(exercice));*/


    }
    
}
