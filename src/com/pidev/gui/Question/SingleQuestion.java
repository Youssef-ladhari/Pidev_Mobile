package com.pidev.gui.Question;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.pidev.SideMenuBaseForm;
import com.pidev.entities.Question;
import com.pidev.entities.Reponse;
import com.pidev.services.QuestionService;
import com.pidev.services.ReponseService;

public class SingleQuestion extends SideMenuBaseForm {
    Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    TextArea tfContenu = new TextArea();
    Button btnAdd = new Button("Add Reponse");
    public SingleQuestion(Resources res,int id,String titre,String contenu) {
        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        Container titleCmp = BoxLayout.encloseX(
                FlowLayout.encloseIn(BorderLayout.centerAbsolute( menuButton)), FlowLayout.encloseIn(
                        BorderLayout.centerAbsolute( new Label("Single Question", "Title"))
                )
        );

        tb.setTitleComponent(titleCmp);
        setupSideMenu(res);
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        SpanLabel spTitre = new SpanLabel();
        SpanLabel spContenu = new SpanLabel();
        spTitre.setText("Titre   :"+titre);
        spContenu.setText("Contenu   :"+contenu);
        C2.addAll(spTitre,spContenu);
        C2.setPreferredH(1100);
        C2.setPreferredW(500);
        add(C2);
        Font lbl = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        for (Reponse t : ReponseService.getInstance().getAllQuestionReponse(id)) {
            Container CR2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container CR3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container CR1 = new Container(new FlowLayout(Component.CENTER));
            TableLayout tl = new TableLayout(1, 2);
            Container container = new Container(tl);
            SpanLabel spRepId = new SpanLabel();
            SpanLabel spRepContenu = new SpanLabel();
            SpanLabel spRepAtt = new SpanLabel();
            SpanLabel spRepDate = new SpanLabel();
            SpanLabel spRepUser = new SpanLabel();
            Button btnEdit = new Button("Edit");
            Button btnDelete = new Button("Delete");
            String repid = String.valueOf(t.getId());
            spRepId.setText("Id : " + repid);
            spRepUser.setText("User : " + t.getUser().getUsername());
            spRepContenu.setText("Contenu : " + t.getContenu());
            spRepAtt.setText("Att : " + t.getAttachment());
            spRepDate.setText("DatePub : " + t.getDatePublication());
            Label ligne = new Label("____________________________________________________");
            ligne.getUnselectedStyle().setFgColor(ColorUtil.rgb(153, 0, 76));
            ligne.getUnselectedStyle().setFont(lbl);
            CR1.addAll(BorderLayout.centerAbsolute(btnEdit),BorderLayout.centerAbsolute( btnDelete));
            /*C2.setInlineAllStyles("border :2px solid black");
            Stroke borderStroke = new Stroke(2, Stroke.CAP_BUTT, Stroke.JOIN_BEVEL, 1);
            C2.getUnselectedStyle().setBorder(RoundBorder.createGrooveBorder(4, 0x000000));
            createDashedBorder(3, 0xffffff);*/
            /*C2.addAll(spUser,
                    BorderLayout.centerAbsolute(spDate),BorderLayout.centerAbsolute( spTitre), BorderLayout.centerAbsolute(spContenu)
                    ,BorderLayout.centerAbsolute( spTag),BorderLayout.centerAbsolute( spCat),C1,BorderLayout.centerAbsolute(ligne));
            */
            CR2.addAll(spRepUser,
                    spRepDate,spRepContenu
                    );
            CR3.add(CR2);
            /// 1 replaceable with Current session id
            if(t.getUser().getId()==1){
                CR3.add(CR1);
            }
            CR3.add(ligne);

            btnDelete.addActionListener(evt -> {
                ReponseService.getInstance().DeleteReponse(t.getId());
                Dialog.show("Delete Reponse", "successfully removed", "ok", null);
                SingleQuestion gaq= new SingleQuestion(res,id,titre,contenu);
                gaq.show();
            });

            btnEdit.addActionListener(evt -> {
                EditReponseForm ea = new EditReponseForm(res, id,titre,contenu, t.getId(), t.getContenu());
                ea.show();
            });

            CR3.setPreferredH(800);
            CR3.setPreferredW(500);
            add(CR3);
        }

        C1.addAll(tfContenu,btnAdd);
        add(C1);
        btnAdd.addActionListener(evt -> {
            if(testSaisie()){
                ReponseService.getInstance().addReponse(tfContenu.getText(),"png.png",id,1);
                Dialog.show("Add Reponse", "successfully added", "ok", null);
                showToast("All fields are valid",FontImage.MATERIAL_INFO);
                SingleQuestion gaq= new SingleQuestion(res,id,titre,contenu);
                gaq.show();
            }

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
}
