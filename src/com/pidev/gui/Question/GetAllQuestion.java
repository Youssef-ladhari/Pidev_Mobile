package com.pidev.gui.Question;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.SpanLabel;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.pidev.SideMenuBaseForm;
import com.pidev.entities.Question;
import com.pidev.services.QuestionService;
import static com.codename1.ui.plaf.Border.createDashedBorder;

public class GetAllQuestion extends SideMenuBaseForm {

    public GetAllQuestion(Resources res) {
        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        Container titleCmp = BoxLayout.encloseX(
                FlowLayout.encloseIn(BorderLayout.centerAbsolute( menuButton)), FlowLayout.encloseIn(
                        BorderLayout.centerAbsolute( new Label("All Questions", "Title"))
                )
        );

        tb.setTitleComponent(titleCmp);
        setupSideMenu(res);
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        Button btnAddQuestion = new Button(FontImage.MATERIAL_ADD, "Add Question");
        btnAddQuestion.addActionListener(evt -> {
            AddQuestionForm aqf=new AddQuestionForm(res);
            aqf.show();
        });
        add(btnAddQuestion);
        Font lbl = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        for (Question t : QuestionService.getInstance().getAllQuestion()) {
            Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container C3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container C1 = new Container(new FlowLayout(Component.CENTER));
            TableLayout tl = new TableLayout(1, 2);
            Container container = new Container(tl);
            SpanLabel spId = new SpanLabel();
            SpanLabel spTitre = new SpanLabel();
            SpanLabel spContenu = new SpanLabel();
            SpanLabel spDate = new SpanLabel();
            SpanLabel spUser = new SpanLabel();
            SpanLabel spTag = new SpanLabel();
            SpanLabel spCat = new SpanLabel();
            Button btnEdit = new Button("Edit");
            Button btnDelete = new Button("Delete");
            String id = String.valueOf(t.getId());
            spId.setText("Id : " + id);
            spTitre.setText("Titre : " + t.getTitre());
            spContenu.setText("Contenu : " + t.getContenu());
            spUser.setText("User : " + t.getUser().getUsername());
            spTag.setText("Tag : " + t.getTag().getLabel());
            spCat.setText("Categorie : " + t.getCategorie().getName());
            spDate.setText("Date : " + t.getDate());
            Label ligne = new Label("____________________________________________________");
            ligne.getUnselectedStyle().setFgColor(ColorUtil.rgb(153, 0, 76));
            ligne.getUnselectedStyle().setFont(lbl);
            C1.addAll(BorderLayout.centerAbsolute(btnEdit),BorderLayout.centerAbsolute( btnDelete));
            /*C2.setInlineAllStyles("border :2px solid black");
            Stroke borderStroke = new Stroke(2, Stroke.CAP_BUTT, Stroke.JOIN_BEVEL, 1);
            C2.getUnselectedStyle().setBorder(RoundBorder.createGrooveBorder(4, 0x000000));
            createDashedBorder(3, 0xffffff);*/
            /*C2.addAll(spUser,
                    BorderLayout.centerAbsolute(spDate),BorderLayout.centerAbsolute( spTitre), BorderLayout.centerAbsolute(spContenu)
                    ,BorderLayout.centerAbsolute( spTag),BorderLayout.centerAbsolute( spCat),C1,BorderLayout.centerAbsolute(ligne));
            */
            C2.addAll(spUser,
                    spDate, spTitre,spContenu
                    ,spTag,spCat);
            C3.add(C2);
            /// 1 replaceable with Current session id
            if(t.getUser().getId()==1){
            C3.add(C1);
            }
            C3.add(ligne);

            btnDelete.addActionListener(evt -> {
                QuestionService.getInstance().DeleteQuestion(t.getId());
                Dialog.show("Delete Reponse", "successfully removed", "ok", null);
                GetAllQuestion l = new GetAllQuestion(res);
                l.show();
            });

            btnEdit.addActionListener(evt -> {
                EditQuestionForm ea = new EditQuestionForm(res, t.getId(), t.getTitre(), t.getContenu());
                ea.show();
            });

            C3.setPreferredH(1100);
            C3.setPreferredW(500);
            Button onActionContainer = new Button();
            onActionContainer.addActionListener(e -> {
                SingleQuestion sq = new SingleQuestion(res,t.getId(),t.getTitre(),t.getContenu());
                sq.show();
            });
            C2.setLeadComponent(onActionContainer);
            add(C3);

        }
    }


}
