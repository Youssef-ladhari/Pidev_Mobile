package com.pidev.gui;

import com.codename1.capture.Capture;
import com.codename1.components.FileEncodedImageAsync;
import com.codename1.ui.*;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.pidev.SideMenuBaseForm;

import com.codename1.components.FloatingHint;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.pidev.entities.Category;
import com.pidev.entities.Project;
import com.pidev.entities.User;
import com.pidev.services.ProjectService;

import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Map;

public class AddProject extends Form {

String ImageEncoded;
    public AddProject(Resources res, User user, ArrayList<Category> cats) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);

        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());

        TextField Tname = new TextField("", "Project Name", 20, TextField.NUMERIC);
        TextField Tperiode = new TextField("", "Periode", 20, TextField.NUMERIC);
        TextField Tprice = new TextField("", "Price", 20, TextField.NUMERIC);
        TextArea Tdescription = new TextField("", "Description",20, TextField.ANY);

        Tdescription.setSingleLineTextArea(false);
        Button next = new Button("Next");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Already have an account?");

        Container item = new Container(BoxLayout.y());
        Button btn = new Button("Select Image");
        Label Limg = new Label();

        btn.addActionListener((e)->{
            String path = Capture.capturePhoto(Display.getInstance().getDisplayWidth(),-1);
            if(path != null){
                try{

                    Image imimgeh = Image.createImage(path);
                    Image imgg=   EncodedImage.createFromImage(imimgeh,true);
                    ImageEncoded =Base64.getEncoder().encodeToString(EncodedImage.createFromImage(imimgeh,true).getImageData()) ;
                    Limg.setIcon(imgg);
                    revalidate();
                 //   System.out.println(FileEncodedImageAsync.create(path).getImageData());
                }catch (Exception ex){
                    System.out.println(ex);
                }

            }



        });


Tname.getStyle().setFgColor(Color.BLACK.getRGB());
Tperiode.getStyle().setFgColor(Color.BLACK.getRGB());
Tprice.getStyle().setFgColor(Color.BLACK.getRGB());
Tdescription.getStyle().setFgColor(Color.BLACK.getRGB());



        ComboBox combo = new ComboBox<> ();

        for(Category c : cats){
        combo.addItem(c.getName());}


        Container content = BoxLayout.encloseY(

                new FloatingHint(Tname),

                new FloatingHint(Tperiode),

                new FloatingHint(Tprice),

                new FloatingHint(Tdescription),
                combo,
                btn,Limg

        );
        content.setScrollableY(true);
        add(BorderLayout.NORTH,new Label(" "));
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(new Label(" "))
        ));
        next.addActionListener((e)->{


            String vn =Tname.getText();
            String vper =Tperiode.getText();
            String vpri =Tprice.getText();
            String vd =Tdescription.getText();

            if (!(vn.length()>0)){

                Dialog.show("Error", "name length must be > 0", "OK", "Cancel");


            }else{
            if (!(vper.length()>0) | !(isNumeric(vper))){

                Dialog.show("Error", "Periode must be > 0", "OK", "Cancel");

            }else{

            if (!(vpri.length()>=0) | !(isNumeric(vpri))){

                Dialog.show("Error", "Price must be >= 0", "OK", "Cancel");

            }else{

            if ((ImageEncoded==null)){

                Dialog.show("Error", "Select an image ", "OK", "Cancel");

            }else{
            if (!(vd.length()>10)){

                Dialog.show("Error", "description length must be >10 ", "OK", "Cancel");

            }else{

                Project p = new Project();
                p.setName(vn);
                p.setPeriode(Integer.parseInt(vper));
                p.setCreator(user);
                p.setImage(ImageEncoded);
                p.setPrice(Float.parseFloat(vpri));
                p.setDescription(vd);

                for (Category ca : cats) {

                    if (combo.getSelectedItem().equals(ca.getName())) {
                        p.setCategory(ca);
                    }
                ProjectService.getInstance().addProject(p);
            }


                }
            }}}}

        });

    }


    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }
}
