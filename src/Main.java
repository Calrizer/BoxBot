
import city.cs.engine.*;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        World world = new World();

        MainView view = new MainView(world, 1000, 500, 1);

        final JFrame frame = new JFrame("BoxBot");

        InterfaceRenderer interfaceRenderer = new InterfaceRenderer(frame, view);

        System.out.println("Interface: Loaded");

        //JFrame debugView = new DebugViewer(world, 1000, 500);

        view.addMouseListener(new MouseHandler(view));

        world.start();
        world.setGravity(40);

        new WorldRenderer(world);

        System.out.println("World: Rendered");

        LevelManager levelManager = new LevelManager(world, frame, interfaceRenderer);

        interfaceRenderer.addLevelManager(levelManager);

        System.out.println("Game Running!");

    }

}
