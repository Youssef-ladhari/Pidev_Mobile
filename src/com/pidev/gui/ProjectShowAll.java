/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.pidev.gui;

import com.codename1.components.*;
import com.codename1.ui.*;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

import com.pidev.SideMenuBaseForm;
import com.pidev.entities.Category;
import com.pidev.entities.Project;
import com.pidev.entities.User;
import com.pidev.gui.ShowNormalProject;
import com.pidev.services.ProjectService;

import com.codename1.ui.Graphics;

import javax.swing.ButtonGroup;


import java.awt.*;
import java.awt.Font;
import java.awt.Stroke;
import java.awt.color.ColorSpace;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;




/**
 * @author Shai Almog
 */
public class ProjectShowAll extends SideMenuBaseForm {

User userr;
    EncodedImage imge;
    Resources res ;
    public ProjectShowAll(Resources ress, User user) {
        super(new BorderLayout(),user);
userr=user;
        res=ress;
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
this.setScrollable(true);

        Container T1 = new Container(new BorderLayout());

        T1.add(  LEFT,FlowLayout.encloseIn(menuButton));
        Label title = new Label("Projects");
        title.setUIID("Title");
        T1.add(CENTER,title);

        Button add = new Button("Create Project");
        add.addActionListener((e)-> {
            ArrayList<Category> cts=  ProjectService.getInstance().getCategory();
            new AddProject(res, user,cts).show();
        });

    T1.add(RIGHT,add);


        tb.setTitleComponent(T1);
        setupSideMenu(res);
        /////


        Container item = new Container(BoxLayout.y());
        item.setScrollableY(true);

        Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
        ArrayList<Project> proj = ProjectService.getInstance().getAllProjects();
        Dimension d = new Dimension(Display.getInstance().getDisplayHeight(), Display.getInstance().getDisplayWidth());
        item.setSize(d);



        try {


            for (Project p : proj) {



               // System.out.println(p);
                item.add(addButton( p));
                Container line = new Container(new FlowLayout(CENTER));
                Dimension dim = new Dimension(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight()/120);



                line.add(createLineSeparator(Color.LIGHT_GRAY.getRGB()));
               line.setPreferredSize(dim);
                item.add(  line);

            }
        } catch (Exception e) {
            System.out.println(e);
        }


        this.add(CENTER, item);


        Label spacer1 = new Label();
        Label spacer2 = new Label();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);


    }

    private Component addButton(Project p ) {

try {
    byte[] b = Base64.getDecoder().decode(p.getImage().getBytes("UTF-8"));
    imge = EncodedImage.create(b);
}catch (Exception e){
    System.out.println(e);
}
        Image img = imge;
        String title = p.getName();
        Float price = p .getPrice();
        int members = 0;
        int height = Display.getInstance().convertToPixels(20f);
        int width = Display.getInstance().convertToPixels(20f);
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        cnt.setLeadComponent(image);
        TextArea ta = new TextArea(title);
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);
        ta.setAlignment(Component.CENTER);
       /* for (User us : p.getUsers()) {

            if (us.getId() == userr.getId()) {
   ta.getStyle().setFgColor(Color.GREEN.getRGB());

            }
            else{
                ta.getStyle().setFgColor(Color.BLACK.getRGB());
            }
        }*/
        Label Lprice = new Label();
        Lprice.setUIID("NewsBottomLine");
        FontImage.setMaterialIcon(Lprice, FontImage.MATERIAL_ATTACH_MONEY);
if (p.getPrice()==0.0){
      Lprice.setText("Free");
        Lprice.setTextPosition(RIGHT);



        }else {
    Lprice.setText(p.getPrice().toString());
}
        Label Lnumber = new Label( p.getPeriode()+" J/H", "NewsBottomLine");
        FontImage.setMaterialIcon(Lnumber, FontImage.MATERIAL_CHAT);

        Label Lcategory = new Label("Category : " +p.getCategory().getName(),"NewsBottomLine");
      //  FontImage.setMaterialIcon(Lprice, FontImage.MATERIAL_CHAT);

        Button ReadMore = new Button("Read More");

        Label Lcreater = new Label("Creator : " + p.getCreator().getUsername(), "NewsBottomLine");


        Container L1 = new Container(new BorderLayout());

        L1.add(LEFT, ta);
        L1.add(RIGHT, ReadMore);
        Container L2 = new Container(new BorderLayout());

        L2.add(LEFT,Lcategory);
        Container L3 = new Container(new BorderLayout());
        L3.add(LEFT,Lcreater);
        Container L4 = new Container(new BorderLayout());
        L4.add(LEFT,Lprice);
        L4.add(RIGHT,Lnumber);

        cnt.add(BorderLayout.CENTER, BoxLayout.encloseY(
                L1,
                L2,
                L3,
                L4
        ));

      //  cnt.add(s);
//       Container line = new Container();
////        line.setSize(Display.getInstance().convertToPixels(1f),Display.getInstance().convertToPixels(100f));
////        line.setBackground(Color.GRAY);
//        //cnt.add(line);
//        line.setSize(new Dimension(Display.getInstance().convertToPixels(1f),Display.getInstance().convertToPixels(100f)));
        image.addActionListener((e)->{new ShowNormalProject(res,p,userr).show();
              System.out.println(p.getName()+"IMMMAGE"+p.getUsers());});

        ReadMore.addActionListener((e)->{});

        return cnt;
    }
    public Component createLineSeparator(int color) {
        Label separator = new Label("                                                                                                                                 ", "WhiteSeparator");
        Dimension d = new Dimension(Display.getInstance().getDisplayHeight()/100, Display.getInstance().getDisplayWidth());

        separator.setSize(d);
        separator.getUnselectedStyle().setBgColor(color);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        return separator;
    }
}
