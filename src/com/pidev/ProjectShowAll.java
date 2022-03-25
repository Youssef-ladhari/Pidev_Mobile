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

package com.pidev;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.*;
import com.codename1.ui.layouts.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

import com.pidev.entities.Project;
import com.pidev.services.ProjectService;

import javax.swing.*;
import javax.swing.ButtonGroup;

import java.awt.color.ColorSpace;
import java.util.ArrayList;
import java.util.Base64;


/**
 *
 * @author Shai Almog
 */
public class ProjectShowAll extends SideMenuBaseForm {


    EncodedImage img;

    public ProjectShowAll(Resources res) {
    super(new BorderLayout());

    Container list = new Container(BoxLayout.y());
    list.setScrollableY(true);
        ArrayList<Project> proj = ProjectService.getInstance().getAllTasks();



list.add("aaaaaaaaaaaaa");

        try {

            String base64ImageData = "";
            byte[] b = Base64.getDecoder().decode(proj.get(0).getImage().getBytes("UTF-8"));

             img =  EncodedImage.create(b);
            ImageViewer iv = new ImageViewer(img);

            Container item = new Container(BoxLayout.x());



            item.add(addButton(img, "Morbi per tincidunt tellus sit of amet eros laoreet.", false, 26, 32));
            item.add(addButton(img, "Fusce ornare cursus masspretium tortor integer placera.", true, 15, 21));
            item.add(addButton(img, "Maecenas eu risus blanscelerisque massa non amcorpe.", false, 36, 15));
            item.add(addButton(img, "Pellentesque non lorem diam. Proin at ex sollicia.", false, 11, 9));

list.add(item);
        }catch (Exception e){
            System.out.println(e);
        }


this.add(CENTER,list);




    Label spacer1 = new Label();
    Label spacer2 = new Label();

    ButtonGroup bg = new ButtonGroup();
    int size = Display.getInstance().convertToPixels(1);
    Image unselectedWalkthru = Image.createImage(size, size, 0);












    }
    private Component addButton(Image img, String title, boolean liked, int likeCount, int commentCount) {
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        cnt.setLeadComponent(image);
        TextArea ta = new TextArea(title);
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);

        Label likes = new Label(likeCount + " Likes  ", "NewsBottomLine");
        likes.setTextPosition(RIGHT);
        if(!liked) {
            FontImage.setMaterialIcon(likes, FontImage.MATERIAL_FAVORITE);
        } else {
            Style s = new Style(likes.getUnselectedStyle());
            s.setFgColor(0xff2d55);
            FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
            likes.setIcon(heartImage);
        }
        Label comments = new Label(commentCount + " Comments", "NewsBottomLine");
        FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);


        cnt.add(BorderLayout.CENTER,
                BoxLayout.encloseY(
                        ta,
                        BoxLayout.encloseX(likes, comments)
                ));

        image.addActionListener(e -> ToastBar.showMessage(title, FontImage.MATERIAL_INFO));
        return cnt;
    }

}
