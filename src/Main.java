import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import javax.swing.JFrame;
import java.awt.*;
import java.util.Timer;

public class Main {

    public static void main(String[] args) {

        World world = new World();

        UserView view = new UserView(world, 1000, 500);

        final JFrame frame = new JFrame("BoxBot");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.add(view);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);

        // Uncomment this to make a debugging view
        //JFrame debugView = new DebugViewer(world, 1000, 500);

        view.addMouseListener(new MouseHandler(view));

        world.start();

        world.setGravity(40);

        for (int x = -26; x <= 26; x++){
            Environment grass = new Environment(world, new Vec2(x, -10), EnvTexture.GRASS);
        }

        for (int x = -26; x <= 26; x++){
            Environment stone = new Environment(world, new Vec2(x, -12), EnvTexture.STONE);
        }

        Crate crate = new Crate(world, new Vec2(0, 0));

        Bot bot = new Bot(world, new Vec2(-16, 0));

        frame.addKeyListener(new KeyboardHandler(view, world, bot));

        Menu mainMenu = new Menu();

        mainMenu.render();

    }
}
