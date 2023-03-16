package GUI.customSwing;

import Models.Controller;

import javax.swing.*;
import java.awt.*;



public class Header extends JPanel {
    public Header(Controller controller){
        this.setBackground(new Color(0,0,0));
        this.setPreferredSize(new Dimension(800,80));
        this.setLayout(new GridLayout(0,8));
    }
}
