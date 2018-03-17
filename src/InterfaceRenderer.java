import javax.swing.*;
import java.awt.*;

public class InterfaceRenderer {

    private JFrame frame;
    private JSplitPane splitPane;
    private GamePanel gamePanel;

    private int blobCount;
    private int boxCount;

    public InterfaceRenderer(JFrame frame, MainView view){

        this.frame = frame;
        this.splitPane = new JSplitPane();
        this.gamePanel = new GamePanel(new GridBagLayout(), 0, 0);

        this.splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        this.splitPane.setDividerLocation(500);
        this.splitPane.setTopComponent(view);
        this.splitPane.setBottomComponent(gamePanel);
        this.splitPane.setEnabled(false);

        this.frame.setPreferredSize(new Dimension(1000, 566));

        this.frame.add(splitPane);

        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationByPlatform(true);
        this.frame.setResizable(false);
        this.frame.pack();
        this.frame.setVisible(true);
        this.frame.setLayout(new BorderLayout());
        this.frame.toFront();
        this.frame.requestFocus();

    }

    public void render(int blobs, int boxes){

        this.blobCount = this.blobCount + blobs;
        this.boxCount = this.boxCount + boxes;

        this.splitPane.setBottomComponent(new GamePanel(new GridBagLayout(), this.blobCount,this.boxCount));

    }

}
