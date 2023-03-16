package GUI.customSwing;

import Models.*;
import javax.swing.*;
import java.awt.*;



public class Button extends JButton {
    public Button(Controller controller, Media media) {
        this.setBackground(new Color (78, 25, 92));
        this.setBorder(null);
        this.setText(media.getTitle());
        this.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.setHorizontalTextPosition(SwingConstants.CENTER);
        this.setPreferredSize(new Dimension(150,300));
        this.setMaximumSize(this.getPreferredSize());
        this.setMinimumSize(this.getPreferredSize());
        this.setForeground(Color.white);

        this.addActionListener(e -> {
            controller.updateMediaPrePanel(media);
            controller.setCurrentState(3);
        });

        ImageIcon icon = new ImageIcon("images\\" + media.getTitle() + ".jpg");
        Image image = icon.getImage(); // transform it
        Image newImg = image.getScaledInstance(150, 275, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        icon = new ImageIcon(newImg);  // transform it back
        this.setIcon(icon);
    }

    public Button(String title){
        this.setText(title);
        this.setBackground(Color.BLACK);
        this.setForeground(Color.WHITE);
        this.setFont(new Font("Arial", Font.BOLD, 25));
        this.setPreferredSize( new Dimension(70,20));
        this.setContentAreaFilled(true);
        this.setFocusable(false);
        this.setBorder(BorderFactory.createEtchedBorder());
    }

    public void giveButtonIcon(String path) {
        ImageIcon icon = new ImageIcon("images/"+ path + ".png");
        Image image = icon.getImage(); // transform it
        Image newimg = image.getScaledInstance(200, 60, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        icon = new ImageIcon(newimg);  // transform it back
        this.setIcon(icon);
    }
}
