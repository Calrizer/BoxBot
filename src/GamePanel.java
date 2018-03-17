import sun.font.TrueTypeFont;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel {

    private Image background;

    public GamePanel(GridBagLayout layout, int blobs, int boxesUsed){

        super(layout);

        this.background = new ImageIcon("assets/env/stonelayer.png").getImage();

        JButton resume = new JButton("Resume");
        JButton menu = new JButton("Main Menu");
        JButton exit = new JButton("Exit");

        JLabel escaped = new JLabel("Blobs Escaped: " + blobs);
        JLabel boxes = new JLabel("Boxes Used: " + boxesUsed);

        GridBagConstraints constraints = new GridBagConstraints();

        try {

            Font fontRaw = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/gameplay.ttf"));
            Font fontBase = fontRaw.deriveFont(14f);
            escaped.setFont(fontBase);
            boxes.setFont(fontBase);
            resume.setFont(fontBase);
            menu.setFont(fontBase);
            exit.setFont(fontBase);

        } catch (FontFormatException | IOException ex) {

            ex.printStackTrace();

        }

        escaped.setForeground(Color.white);
        boxes.setForeground(Color.white);

        constraints.gridy = 0;
        constraints.gridx = 2;

        this.add(escaped);
        this.add(boxes);

        constraints.gridy = 0;
        constraints.gridx = 2;

        this.add(resume, constraints);

        constraints.gridy = 0;
        constraints.gridx = 3;

        this.add(menu, constraints);

        constraints.gridy = 0;
        constraints.gridx = 4;

        this.add(exit, constraints);

    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(background, 0, 0, this);

    }

}
