import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MenuPanel extends JPanel {

    private Image background;

    public MenuPanel(GridBagLayout layout){

        super(layout);

        this.background = new ImageIcon("assets/env/stonelayer.png").getImage();

        JButton exit = new JButton("Exit");

        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);

            }

        });

        GridBagConstraints constraints = new GridBagConstraints();

        try {

            Font fontRaw = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/gameplay.ttf"));
            Font fontBase = fontRaw.deriveFont(14f);
            exit.setFont(fontBase);

        } catch (FontFormatException | IOException ex) {

            ex.printStackTrace();

        }

        constraints.gridy = 0;
        constraints.gridx = 0;

        this.add(exit, constraints);

    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(background, 0, 0, this);

    }

}
