import city.cs.engine.Body;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LevelThree implements Level, StepListener{

    private LevelManager levelManager;
    private World world;
    private JFrame frame;

    private int time = 0;
    private int ufos;

    public LevelThree(LevelManager levelManager, World world, JFrame frame){

        this.levelManager = levelManager;
        this.world = world;
        this.frame = frame;

        Bot bot = new Bot(world, new Vec2(-16, -4));

        world.addStepListener(this);
        frame.addKeyListener(new KeyboardHandler(levelManager.getInterfaceRenderer(), world, bot));

        start();

    }

    @Override
    public void start() {



    }

    @Override
    public void end() {

        for (KeyListener listener : frame.getKeyListeners()) {
            frame.removeKeyListener(listener);
        }

        world.removeStepListener(this);
        System.out.println("finished");

    }

    @Override
    public void preStep(StepEvent e){

        if (ufos <= 40){

            time++;

            if (time % 20 == 0){

                new UFO(this.levelManager.getInterfaceRenderer(), world, new Vec2(25, -4));
                ufos++;

            }

        }else{

            boolean found = false;

            for (Body body: this.world.getDynamicBodies()) {

                if (body.getName() == "Ufo"){
                    found = true;
                }

            }

            if (!found){

                levelManager.stopLevel(LevelContext.THREE);
                levelManager.startLevel(LevelContext.MENU);

                try {
                    writeToFile("High Score - Blobs Lost: " + levelManager.getInterfaceRenderer().getBlobCount() + " Crates Fired: " + levelManager.getInterfaceRenderer().getBoxCount());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }


            }

        }

    }

    @Override
    public void postStep(StepEvent e){

        //Just a stub.//

    }

    public void writeToFile(String data) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter("data/highScore.txt"));
        writer.write(data);

        writer.close();

    }

}
