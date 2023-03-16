package GUI.customSwing;


import Models.Controller;
import Models.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class View extends JFrame {
    public View(Model model, Controller controller) {
        Header header = new Header(controller);
        this.setTitle("Streamy");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(0,0,0));
        this.setSize(1200,800);
        this.add(model,BorderLayout.CENTER); // Add mainPanel to frame

        Button logo = new Button("");
        logo.giveButtonIcon("icon2");
        logo.setContentAreaFilled(false);
        header.add(logo);

        Button goToFilms = new Button("Films");
        goToFilms.addActionListener(e -> controller.setCurrentState(1));
        header.add(goToFilms);

        Button goToSeries = new Button("Series");
        goToSeries.addActionListener(e -> controller.setCurrentState(2));
        header.add(goToSeries);

        Button goToMyList = new Button("My List");
        goToMyList.addActionListener(e -> {
            controller.updateMyListPanel();
            controller.setCurrentState(4);
        });
        header.add(goToMyList);

        OptionBox categoriesBox = new OptionBox("Category");;
        for(String string : controller.getCategories()){
            categoriesBox.addItem(string);
        }
        categoriesBox.addActionListener(e -> {
            controller.updateFilmsPanel((String) categoriesBox.getSelectedItem());
            controller.updateSeriesPanel((String) categoriesBox.getSelectedItem());
        });
        header.add(categoriesBox);

        OptionBox usersCombo = new OptionBox();
        usersCombo.addActionListener(e -> {
            controller.changeCurrentUser(Objects.requireNonNull(usersCombo.getSelectedItem()).toString());
            controller.updateMyListPanel();
        });
        header.add(usersCombo);

        Button addUser = new Button("Add User");
        addUser.addActionListener(e -> {
            String userName = JOptionPane.showInputDialog("Username?");
            controller.addUser(userName);
            usersCombo.addItem(controller.getUser(userName));
        });
        header.add(addUser);

        SearchBar searchBar = new SearchBar("Search");
        searchBar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                controller.keyPressed(e, searchBar.getText());
            }
        });
        header.add(searchBar);

        JScrollPane scrollPane = new JScrollPane(model);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);

        this.add(scrollPane); // we add a scrollbar to our Frame
        this.add(header, BorderLayout.PAGE_START);
        this.setVisible(true);
    }

}
