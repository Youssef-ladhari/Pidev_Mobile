package com.pidev.gui;

import com.codename1.ui.*;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.pidev.SideMenuBaseForm;

import com.codename1.components.FloatingHint;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

import java.awt.*;

public class AddProject extends Form {


    public AddProject(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);

        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());

        TextField Tname = new TextField("", "Project Name", 20, TextField.ANY);
        TextField Tperiode = new TextField("", "Periode", 20, TextField.ANY);
        TextField Tprice = new TextField("", "Price", 20, TextField.ANY);
        TextArea Tdescription = new TextField("", "Description",20, TextField.ANY);
        Tname.setSingleLineTextArea(false);
        Tperiode.setSingleLineTextArea(false);
        Tprice.setSingleLineTextArea(false);
        Tdescription.setSingleLineTextArea(false);
        Button next = new Button("Next");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Already have an account?");

        Container item = new Container(BoxLayout.y());



Tname.getStyle().setFgColor(Color.BLACK.getRGB());
Tperiode.getStyle().setFgColor(Color.BLACK.getRGB());
Tprice.getStyle().setFgColor(Color.BLACK.getRGB());
Tdescription.getStyle().setFgColor(Color.BLACK.getRGB());



        Container content = BoxLayout.encloseY(

                new FloatingHint(Tname),
                createLineSeparator(),
                new FloatingHint(Tperiode),
                createLineSeparator(),
                new FloatingHint(Tprice),
                createLineSeparator(),
                new FloatingHint(Tdescription),
                createLineSeparator()
        );
        content.setScrollableY(true);
        add(BorderLayout.NORTH,new Label(" "));
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        next.requestFocus();

    }




    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }
}
