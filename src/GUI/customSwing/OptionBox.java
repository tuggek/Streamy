package GUI.customSwing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class OptionBox extends JComboBox{

    public OptionBox(String title) {
        this.setFont(new Font("Arial", Font.BOLD, 25));
        this.setBorder(BorderFactory.createEmptyBorder());
        ((JLabel)this.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < this.getComponentCount(); i++) {
            if (this.getComponent(i) instanceof JComponent) {
                ((JComponent) this.getComponent(i)).setBorder(new EmptyBorder(0, 0,0,0));
            }
            if (this.getComponent(i) instanceof AbstractButton) {
                ((AbstractButton) this.getComponent(i)).setBorderPainted(false);
            }
        }
        this.setBackground(Color.black);
        this.setForeground(Color.white);
        this.setFocusable(false);
        this.setBorder(BorderFactory.createEtchedBorder());
        this.setPreferredSize( new Dimension(70,20));
        this.addItem(title);
    }

    public OptionBox() {
        this.setFont(new Font("Arial", Font.BOLD, 25));
        for (int i = 0; i < this.getComponentCount(); i++) {
            if (this.getComponent(i) instanceof JComponent) {
                ((JComponent) this.getComponent(i)).setBorder(new EmptyBorder(0, 0,0,0));
            }
            if (this.getComponent(i) instanceof AbstractButton) {
                ((AbstractButton) this.getComponent(i)).setBorderPainted(false);
            }
        }
        ((JLabel)this.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        this.setBackground(Color.black);
        this.setForeground(Color.white);
        this.setFocusable(false);
        this.setBorder(BorderFactory.createEtchedBorder());
        this.setPreferredSize( new Dimension(70,20));
    }
}
