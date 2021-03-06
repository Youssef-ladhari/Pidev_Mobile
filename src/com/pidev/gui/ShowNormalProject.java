package com.pidev.gui;

import com.codename1.components.*;
import com.codename1.ui.*;
import com.codename1.ui.Button;

import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;

import com.codename1.ui.layouts.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;

import com.pidev.SideMenuBaseForm;
import com.pidev.entities.Category;
import com.pidev.entities.Project;
import com.pidev.entities.User;
import com.pidev.services.ProjectService;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Base64;

public class ShowNormalProject extends SideMenuBaseForm {
    boolean in = false;

    public ShowNormalProject(Resources ress, Project p, User user) {
        super(BoxLayout.y(), user);
        Resources res = ress;

        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);

        Image profilePic = res.getImage("user-picture.jpg");
        try {
            byte[] b = Base64.getDecoder().decode(p.getImage().getBytes("UTF-8"));
            EncodedImage imge = EncodedImage.create(b);
            profilePic = imge;

        } catch (Exception e) {
            System.out.println(e);
        }
        Image mask = res.getImage("round-mask.png");
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(profilePic, "ProfilePicTitle");
        profilePicLabel.setMask(mask.createMask());
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        Container Pricelab = BoxLayout.encloseY();
        if (p.getPrice() == 0.0) {
            Label lp = new Label("Free", "CenterTitle");

            Pricelab.add(
                    new Label("Price", "CenterSubTitle")
            );
            Pricelab.add(lp);

        } else {
            Pricelab.add(
                    new Label("Price", "CenterSubTitle")

            );
            Pricelab.add(new Label(p.getPrice().toString() + " DT", "CenterTitle"));
        }
        Pricelab.setUIID("RemainingTasks");
        Container Periodelab = BoxLayout.encloseY(
                new Label("Duration ", "CenterSubTitle"),
                new Label(p.getPeriode().toString() + " J/H", "CenterTitle")
        );
        Periodelab.setUIID("CompletedTasks");

        Container Ltime = new Container(new BorderLayout());
        Label lt = new Label("Duration" + p.getPeriode());
        Ltime.add(RIGHT, lt);

Button complete = new Button("Set As Complete");
        complete.getStyle().setFgColor(Color.GREEN.getRGB());
        Container Ccomeplete = new Container(new BorderLayout());

        Ccomeplete.add(CENTER,complete);
        Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton),
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                new Label(p.getName(), "Title"),
                                new Label(p.getCategory().getName(), "SubTitle"),
                                new Label(p.getPeriode().toString()+ " J/H", "SubTitle")
                        )
                ).add(BorderLayout.WEST, profilePicLabel),
                GridLayout.encloseIn(2, Pricelab, Periodelab)
        );
        System.out.println("p" + p.getUsers() + "-------000user" + user.getId());
        for (User us : p.getUsers()) {

            if (us.getId() == user.getId()) {

                in = true;
                System.out.println(in);
            }
        }
        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);

        fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
        fab.getAllStyles().setMargin(BOTTOM, Periodelab.getPreferredH() - fab.getPreferredH() / 2);

        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));

        fab.addActionListener((e)->{

            if (!in) {

ProjectService.getInstance().Join(p.getId(),user.getId());

                ArrayList<User> pop = p.getUsers();
                pop.add(user);
                p.setUsers(pop);
                new ShowNormalProject(res,p,user).show();

            }



        });



        if (in) {
            fab.getStyle().setBgColor(Color.RED.getRGB());

            System.out.println("IFFFFFFFFFFFF");
            } else {

                fab.getStyle().setBgColor(Color.GREEN.getRGB());
            System.out.println("elseeeeeeeeeeeeeeeeeeeeee");
            }
            System.out.println("---+" + p.getUsers());


            Form Tab = new Form("Collaborators", new TableLayout(p.getUsers().size(), 3));
            for (User u : p.getUsers()) {
                Container row = new Container(new BorderLayout());

                Label l = new Label(u.getEmail());
                Button b = new Button("Block");

                b.getStyle().setFgColor(Color.RED.getRGB());

                b.addPointerPressedListener((e) -> {

                  ProjectService.getInstance().block(user.getId(), p.getId());

                    ArrayList<Integer> blo = p.getBlocked();
                blo.add(user.getId());
                  p.setBlocked(blo);
                    new ShowNormalProject(res, ProjectService.getInstance().getProjectById(p.getId()), user).show();

                });


                row.add(LEFT,l);
                row.add(RIGHT,b);

                Tab.add(row);

            }

            if ((user.getId()==p.getCreator().getId()) && (p.getState()==0)) {
                complete.addActionListener((e)->{

                   ProjectService.getInstance().setcomplete(p.getId());
                    p.setState(1);
                    new ShowNormalProject(res,p,user).show();
                });
                this.add(Ccomeplete);
                System.out.println((user.getId()==p.getCreator().getId()) && (p.getState()==0));
            }


            this.add(Tab);


            setupSideMenu(res);


    }

    private void addButtonBottom(Image arrowDown, String text, int color, boolean first) {
        MultiButton finishLandingPage = new MultiButton(text);
        finishLandingPage.setEmblem(arrowDown);
        finishLandingPage.setUIID("Container");
        finishLandingPage.setUIIDLine1("TodayEntry");
        finishLandingPage.setIcon(createCircleLine(color, finishLandingPage.getPreferredH(), first));
        finishLandingPage.setIconUIID("Container");
        add(FlowLayout.encloseIn(finishLandingPage));
    }

    private Image createCircleLine(int color, int height, boolean first) {
        Image img = Image.createImage(height, height, 0);
        Graphics g = img.getGraphics();
        g.setAntiAliased(true);
        g.setColor(0xcccccc);
        int y = 0;
        if (first) {
            y = height / 6 + 1;
        }
        g.drawLine(10, y, 10, 10);
        g.drawLine(height / 2 - 1, y, height / 2 - 1, height);
        g.setColor(color);
        g.fillArc(height / 2 - height / 4, height / 6, height / 2, height / 2, 0, 360);
        return img;
    }
}
