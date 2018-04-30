
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Callum Drain
 */

public class HighScorePanel extends JPanel {

    private Image background;
    private boolean isNew;
    private int [] score;

    /**
     * HighScorePanel is used to render the highscore after finishing the game.
     * @param layout Add layout to define the user interface layout.
     * @param score Add the user's current score.
     * @param isNew Add if the user has set a new highscore.
     */

    public HighScorePanel(GridBagLayout layout, int [] score, boolean isNew){

        super(layout);

        this.background = new ImageIcon("assets/env/stonelayer.png").getImage();
        this.isNew = isNew;
        this.score = score;

        JButton exit = new JButton("Exit");

        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);

            }

        });

        JLabel highscore;

        try {

            String [] datas = readFile();

            if (this.isNew){

                highscore = new JLabel("New Highscore! - Blobs Escaped: " + datas[0] + " Boxes Used: " + datas[1]);

            }else{

                highscore = new JLabel("Your Score - Blobs Escaped: " + this.score[0] + " Boxes Used: " + this.score[1] + "  |  " + "Highscore - Blobs Escaped: " + datas[0] + " Boxes Used: " + datas[1]);

            }


        }catch (IOException e){

            highscore = new JLabel("Highscore - You Haven't Played Yet!");

        }

        highscore.setForeground(Color.white);

        GridBagConstraints constraints = new GridBagConstraints();

        try {

            Font fontRaw = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/gameplay.ttf"));
            Font fontBase = fontRaw.deriveFont(14f);
            exit.setFont(fontBase);
            highscore.setFont(fontBase);

        } catch (FontFormatException | IOException ex) {

            ex.printStackTrace();

        }

        constraints.gridy = 0;
        constraints.gridx = 0;

        this.add(highscore, constraints);

        constraints.gridy = 0;
        constraints.gridx = 1;

        this.add(exit, constraints);

    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(background, 0, 0, this);

    }

    public String[] readFile() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("data/highScore.txt"));
        String data = reader.readLine();

        String [] datas = data.split(",");

        reader.close();

        return datas;

    }

}
