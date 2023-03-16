package GUI.customSwing;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    public Panel(){
        //this.setBackground(new Color(80, 3, 120));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 25));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth();
        int h = getHeight();

        GradientPaint gp = new GradientPaint(0, 0, new Color(57, 3, 80), 0, getHeight(), new Color(0, 0, 0));
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
}
}
