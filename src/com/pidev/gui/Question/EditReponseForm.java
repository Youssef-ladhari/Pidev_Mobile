package com.pidev.gui.Question;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ToastBar;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.pidev.SideMenuBaseForm;
import com.pidev.entities.Categorie;
import com.pidev.entities.Tag;
import com.pidev.services.QuestionService;
import com.pidev.services.ReponseService;

public class EditReponseForm extends SideMenuBaseForm {
    TextArea tfContenu = new TextArea();
    Button btnEdit = new Button("Edit");
    Button btnCancel = new Button("Cancel");
    Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    Container C2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
    public EditReponseForm(Resources res,int idquest,String titrequest,String contenuquest,int idrep,String contenu){
        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        Container titleCmp = BoxLayout.encloseX(
                FlowLayout.encloseIn(BorderLayout.centerAbsolute( menuButton)), FlowLayout.encloseIn(
                        BorderLayout.centerAbsolute( new Label("Edit Reponse", "Title"))
                )
        );

        tb.setTitleComponent(titleCmp);
        setupSideMenu(res);
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        Label lbCont = new Label("  Contenu :");
        lbCont.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 51, 102));
        tfContenu.setText(contenu);
        tfContenu.setPreferredH(700);
        tfContenu.setPreferredW(800);
        C1.add( BoxLayout.encloseX(
                FlowLayout.encloseIn(BorderLayout.centerAbsolute(lbCont)), FlowLayout.encloseIn(
                        BorderLayout.centerAbsolute(  tfContenu)
                )));
        Container C0 = new Container(new FlowLayout(Component.CENTER));
        C0.addAll(BorderLayout.centerAbsolute(btnEdit),BorderLayout.centerAbsolute( btnCancel));
        C1.add(C0);
        C1.add(C2);
        add(C1);
        btnEdit.addActionListener(evt -> {
            //Calendar c = Calendar.getInstance();
            //c.setTime(tfDate.getDate());
            if(testSaisie()){
                ReponseService.getInstance().editReponse(idrep,tfContenu.getText(),"image.png",idquest,1);
                Dialog.show("Edit Reponse", "successfully updated", "ok", null);
                showToast("All fields are valid",FontImage.MATERIAL_INFO);
                SingleQuestion gaq= new SingleQuestion(res,idquest,titrequest,contenuquest);
                gaq.show();
            }
            else {
                showToast("Check your field",FontImage.MATERIAL_ERROR);
            }
        });
        btnCancel.addActionListener(evt -> {
            SingleQuestion gaq= new SingleQuestion(res,idquest,titrequest,contenuquest);
            gaq.show();
        });

    }

    private void showToast(String text,char mat) {
        Image errorImage = FontImage.createMaterial(mat, UIManager.getInstance().getComponentStyle("Title"), 4);
        ToastBar.Status status = ToastBar.getInstance().createStatus();
        status.setMessage(text);
        status.setIcon(errorImage);
        status.setExpires(2000);
        status.show();
    }
    public boolean testSaisie() {
        if (
                tfContenu.getText().isEmpty()
        ) {
            showToast("All fields must be completed",FontImage.MATERIAL_ERROR);
            return false;
        }


        return true;
    }

    @Override
    protected void showOtherForm(Resources res) {
        new AddQuestionForm(res).show();
    }
}