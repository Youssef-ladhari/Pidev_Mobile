package com.pidev.gui;

import com.codename1.ui.*;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.pidev.LoginForm;
import com.pidev.entities.User;
import com.pidev.services.AuthentificationService;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class SignUpForm extends Form {
    public SignUpForm(Resources res){

        super(BoxLayout.y());
        AuthentificationService authentificationService=new AuthentificationService();
        Container container= new Container(BoxLayout.y());


        TextField tfu = new TextField("","Username");
        TextField tfe =       new TextField("","Email");
        TextField tfp =       new TextField("","Password",20,TextArea.PASSWORD);
        TextField tfcp =       new TextField("","Confirm password",20,TextArea.PASSWORD);

        tfu.getStyle().setFgColor(Color.BLACK.getRGB());
        tfe.getStyle().setFgColor(Color.BLACK.getRGB());
        tfp.getStyle().setFgColor(Color.BLACK.getRGB());
        tfcp.getStyle().setFgColor(Color.BLACK.getRGB());

        Container contUsername= (FlowLayout.encloseIn(
              tfu,tfe,tfp,tfcp

        ));
        Container footer=new Container(BoxLayout.x());

        Button signUp=new Button("Sign up");
        //contUsername.add(signUp);
        Button back=new Button("Back");
        //contUsername.add(back);
        back.addActionListener(evt -> {
            try {
                new LoginForm(res).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        footer.add(signUp);
        footer.add(back);
        contUsername.add(footer);
        signUp.addActionListener(evt -> {
            User user=new User();
            try {
                List<Component> list=contUsername.getChildrenAsList(false);
                for(Component comp : list){
                    if (comp instanceof TextField) {
                        System.out.println(((TextField) comp).getHint());
                        switch (((TextField) comp).getHint()){
                            case "Username": user.setUsername(((TextField) comp).getText());break;
                            case "Email": user.setEmail(((TextField) comp).getText());break;
                            case "Password": user.setPassword(((TextField) comp).getText());break;
                            case "Confirm password": user.setConfirm_password(((TextField) comp).getText());break;
                            default:
                                throw new IllegalStateException("Unexpected value: " + ((TextField) comp).getHint());
                        }
                    }
                }
                if (authentificationService.signUp(user)){
                    new LoginForm(res).show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        add(contUsername);
    }
}
