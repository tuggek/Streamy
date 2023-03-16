package GUI.customSwing;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SearchBar extends JTextField{
    public SearchBar(String text){
        super(text);
        this.setText("Search..");
        this.setBackground(Color.black);
        this.setForeground(Color.white);
        this.setFont(new Font("Arial", Font.BOLD, 20));
        this.setHorizontalAlignment(CENTER);
        this.setCaretColor(Color.WHITE);
        this.setCaretPosition(8);
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        this.setFocusable(true);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setText("");
            }
        });
    }
}





