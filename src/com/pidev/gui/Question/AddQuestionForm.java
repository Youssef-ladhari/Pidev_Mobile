package com.pidev.gui.Question;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ToastBar;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.pidev.SideMenuBaseForm;
import com.pidev.StatsForm;
import com.pidev.entities.Categorie;
import com.pidev.entities.Tag;
import com.pidev.services.QuestionService;
import java.util.Calendar;

public class AddQuestionForm  extends SideMenuBaseForm {
    TextField tfTitre = new TextField();
    ComboBox cbTag = new ComboBox();
    ComboBox cbCat = new ComboBox();
    TextArea tfContenu = new TextArea();
    Button btnAdd = new Button("Add");
    Button btnCancel = new Button("Cancel");
    Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    Container C2 = new Container(new BoxLayout(BoxLayout.X_AXIS));

    public AddQuestionForm(Resources res) {
        super(BoxLayout.y());
        tfTitre.getUnselectedStyle().setFgColor(0x000000);
        Toolbar tb = getToolbar();
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        Container titleCmp = BoxLayout.encloseX(
                FlowLayout.encloseIn(BorderLayout.centerAbsolute( menuButton)), FlowLayout.encloseIn(
                        BorderLayout.centerAbsolute( new Label("Add Question", "Title"))
                )
        );

        tb.setTitleComponent(titleCmp);
        setupSideMenu(res);
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        Label lbTitre = new Label("  Titre :");
        lbTitre.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 51, 102));
        Label lbTag = new Label(" Tag :");
        lbTag.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 51, 102));
        Label lbCat = new Label("  Categorie :");
        lbCat.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 51, 102));
        Label lbCont = new Label("  Contenu :");
        lbCont.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 51, 102));
        for (Tag t : QuestionService.getInstance().getAllTag()) {
            cbTag.addItem(t.getId() + "-" + t.getLabel());
        }
        for (Categorie t : QuestionService.getInstance().getAllCategorie()) {
            cbCat.addItem(t.getId() + "-" + t.getName());
        }
        cbTag.getAllStyles().setFgColor(ColorUtil.rgb(153, 0, 76));
        cbTag.getAllStyles().setBgColor(ColorUtil.rgb(153, 0, 76));
        //cbTag.setPreferredW(350);
        cbCat.getAllStyles().setFgColor(ColorUtil.rgb(153, 0, 76));
        cbCat.getAllStyles().setBgColor(ColorUtil.rgb(153, 0, 76));
        //cbCat.setPreferredW(350);
        //C2.setPreferredH(100);
        tfContenu.setPreferredH(700);
        tfContenu.setPreferredW(800);
        C1.add( BoxLayout.encloseX(
                FlowLayout.encloseIn(BorderLayout.centerAbsolute(lbTitre)), FlowLayout.encloseIn(
                        BorderLayout.centerAbsolute(  tfTitre)
                )));
        C1.add( BoxLayout.encloseX(
                FlowLayout.encloseIn(BorderLayout.centerAbsolute(lbTag)), FlowLayout.encloseIn(
                        BorderLayout.centerAbsolute(  cbTag)
                )));
        C1.add( BoxLayout.encloseX(
                FlowLayout.encloseIn(BorderLayout.centerAbsolute(lbCat)), FlowLayout.encloseIn(
                        BorderLayout.centerAbsolute(  cbCat)
                )));
        C1.add( BoxLayout.encloseX(
                FlowLayout.encloseIn(BorderLayout.centerAbsolute(lbCont)), FlowLayout.encloseIn(
                        BorderLayout.centerAbsolute(  tfContenu)
                )));
        Container C0 = new Container(new FlowLayout(Component.CENTER));
        C0.addAll(BorderLayout.centerAbsolute(btnAdd),BorderLayout.centerAbsolute( btnCancel));
        C1.add(C0);
        C1.add(C2);
        add(C1);
        btnAdd.addActionListener(evt -> {
            //Calendar c = Calendar.getInstance();
            //c.setTime(tfDate.getDate());
            if(testSaisie()){
                String idtag = cbTag.getSelectedItem().toString().substring(0, cbTag.getSelectedItem().toString().indexOf('-'));
                String idcat = cbCat.getSelectedItem().toString().substring(0, cbCat.getSelectedItem().toString().indexOf('-'));
                QuestionService.getInstance().addQuestion(tfTitre.getText(),tfContenu.getText(),"image.png",Integer.parseInt(idtag),Integer.parseInt(idcat),1);
                Dialog.show("Add Question", "successfully added", "ok", null);
                showToast("All fields are valid",FontImage.MATERIAL_INFO);
                GetAllQuestion gaq= new GetAllQuestion(res);
                gaq.show();
            }
            else {
                showToast("Check your field",FontImage.MATERIAL_ERROR);
            }
        });
        btnCancel.addActionListener(evt -> {
            GetAllQuestion gaq= new GetAllQuestion(res);
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
                   tfTitre.getText().isEmpty()
                || tfContenu.getText().isEmpty()
                || cbTag.getSelectedItem().toString() == null
                || cbCat.getSelectedItem().toString() == null
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
