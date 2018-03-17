
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;

public class Main {

    private static Bot bot;

    public static void main(String[] args) {

        World world = new World();

        MainView view = new MainView(world, 1000, 500);

        final JFrame frame = new JFrame("BoxBot");

        InterfaceRenderer interfaceRenderer = new InterfaceRenderer(frame, view);

//        JSplitPane splitPane = new JSplitPane();
//        GamePanel gamePanel = new GamePanel(new GridBagLayout());
//
//        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
//        splitPane.setDividerLocation(500);
//        splitPane.setTopComponent(view);
//        splitPane.setBottomComponent(gamePanel);
//        splitPane.setEnabled(false);
//
//        frame.setPreferredSize(new Dimension(1000, 566));
//
//        frame.add(splitPane);
//
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLocationByPlatform(true);
//        frame.setResizable(false);
//        frame.pack();
//        frame.setVisible(true);
//        frame.setLayout(new BorderLayout());
//        frame.toFront();
//        frame.requestFocus();

        //JFrame debugView = new DebugViewer(world, 1000, 500);

        view.addMouseListener(new MouseHandler(view));

        world.start();
        world.setGravity(40);

        new WorldRenderer(world);

        new LevelManager(world, frame, interfaceRenderer);

        //bot = new Bot(world, new Vec2(-16, -4));

        //frame.addKeyListener(new KeyboardHandler(view, world, bot));

        //Blob blob = new Blob(world, new Vec2(16, 0));

    }

    public Bot getBot(){
        return bot;
    }

}
