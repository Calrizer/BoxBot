
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

        //JFrame debugView = new DebugViewer(world, 1000, 500);

        view.addMouseListener(new MouseHandler(view));

        world.start();
        world.setGravity(40);

        new WorldRenderer(world);

        LevelManager levelManager = new LevelManager(world, frame, interfaceRenderer);

        interfaceRenderer.addLevelManager(levelManager);

    }

    public Bot getBot(){
        return bot;
    }

}
