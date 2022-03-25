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
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

import com.pidev.entities.Project;
import com.pidev.services.ProjectService;

import com.codename1.ui.Image;
import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.util.ArrayList;
import java.util.Base64;


/**
 *
 * @author Shai Almog
 */
public class ProjectShowAll extends SideMenuBaseForm {



    private void addButton(Image img, String title, boolean liked, int likeCount, int commentCount) {
        Container cont = new Container(BoxLayout.y());
    }

    public ProjectShowAll(Resources res) {
    super(new BorderLayout());

    Container list = new Container(BoxLayout.y());
    list.setScrollableY(true);
        ArrayList<Project> proj = ProjectService.getInstance().getAllTasks();


        Container item = new Container(BoxLayout.x());
list.add("aaaaaaaaaaaaa");

        try {

            String base64ImageData = "";
            byte[] b = Base64.getDecoder().decode(base64ImageData.getBytes("UTF-8"));
            System.out.println(b);
            Image img =  Image.createImage(b,500,500);
        }catch (Exception e){
            System.out.println(e);
        }


this.add(CENTER,list);


}




}
