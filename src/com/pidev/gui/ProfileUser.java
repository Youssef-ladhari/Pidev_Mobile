

package com.pidev.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.ui.*;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.pidev.SideMenuBaseForm;
import com.pidev.StatsForm;
import com.pidev.entities.User;
import com.pidev.serviceImplementation.UserServiceImplementation;

import javax.swing.*;

public class ProfileUser extends SideMenuBaseForm {
    private User user;
    public ProfileUser(Resources res, User user) {
        super(BoxLayout.y(),user);
        this.user=user;
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Image profilePic = res.getImage("user-picture.jpg");
        Image mask = res.getImage("round-mask.png");
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(profilePic, "ProfilePicTitle");
        profilePicLabel.setMask(mask.createMask());
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Container remainingTasks = BoxLayout.encloseY(
                new Label("12", "CenterTitle"),
                new Label("remaining tasks", "CenterSubTitle")
        );
        remainingTasks.setUIID("RemainingTasks");
        Container completedTasks = BoxLayout.encloseY(
                new Label("32", "CenterTitle"),
                new Label("completed tasks", "CenterSubTitle")
        );
        completedTasks.setUIID("CompletedTasks");

        Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton),
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                new Label(user.getUsername(), "Title"),
                                new Label(user.getEmail(), "SubTitle")
                        )
                ).add(BorderLayout.WEST, profilePicLabel),
                GridLayout.encloseIn(2, remainingTasks, completedTasks)
        );

        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
        fab.getAllStyles().setMargin(BOTTOM, completedTasks.getPreferredH() - fab.getPreferredH() / 2);
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));

        add(new Label("Profile", "TodayTitle"));

        Container contUsername= (FlowLayout.encloseIn(
                new TextField("","Username"),
                new TextField("","Email"),
                new TextField("","Password",20,TextArea.PASSWORD),
                new TextField("","Confirm password",20,TextArea.PASSWORD)

        ));
        TextField username=new TextField(""+user.getUsername());
        username.getAllStyles().setFgColor(000000);
        TextField email=new TextField(""+ user.getEmail());
        email.getAllStyles().setFgColor(000000);
        TextField tel=new TextField(""+ user.getTelephone());
        tel.getAllStyles().setFgColor(000000);
        TextField addr=new TextField(""+ user.getAddress());
        addr.getAllStyles().setFgColor(000000);
        Button modify=new Button("Modify");
//        Container c=BoxLayout.encloseY(
//                username,
//                email,
//                tel,
//                addr,
//                modify
//        );
//        Container c1=BoxLayout.encloseY(
//               new Label("Username"),
//               new Label("Email"),
//               new Label("Telephone"),
//               new Label("Address")
//        );
//        Container call=GridLayout.encloseIn(5,c1,c);


        //add(GridLayout.encloseIn(4,c1,c));
        add(BorderLayout.west(new Label("Username","Label")).add(BorderLayout.CENTER,username));
        add(BorderLayout.west(new Label("Email")).add(BorderLayout.CENTER,email));
        add(BorderLayout.west(new Label("Telephone")).add(BorderLayout.CENTER,tel));
        add(BorderLayout.west(new Label("Address")).add(BorderLayout.CENTER,addr));
        add(BorderLayout.west(new Label("")).add(BorderLayout.CENTER,modify));
        //add(call);
        modify.addActionListener(evt ->{
            User userToModify=new User();
            userToModify.setUsername(username.getText());
            userToModify.setEmail(email.getText());
            userToModify.setTelephone(tel.getText());
            userToModify.setAddress(addr.getText());
            UserServiceImplementation userService=new UserServiceImplementation();
            User userModified=userService.modifyProfile(userToModify);
            if (userModified!=null){
                new ProfileUser(res,userModified).show();
            }


        });
        setupSideMenu(res);
    }


    private Image createCircleLine(int color, int height, boolean first) {
        Image img = Image.createImage(height, height, 0);
        Graphics g = img.getGraphics();
        g.setAntiAliased(true);
        g.setColor(0xcccccc);
        int y = 0;
        if(first) {
            y = height / 6 + 1;
        }
        g.drawLine(height / 2, y, height / 2, height);
        g.drawLine(height / 2 - 1, y, height / 2 - 1, height);
        g.setColor(color);
        g.fillArc(height / 2 - height / 4, height / 6, height / 2, height / 2, 0, 360);
        return img;
    }

    @Override
    protected void showOtherForm(Resources res) {
        new StatsForm(res,this.user).show();
    }
}
