import city.cs.engine.Body;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.io.*;

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

        this.levelManager.getInterfaceRenderer().changeBackground(3);

    }

    @Override
    public void end() {

        for (KeyListener listener : frame.getKeyListeners()) {
            frame.removeKeyListener(listener);
        }

        world.removeStepListener(this);

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

                try {

                    writeToFile(levelManager.getInterfaceRenderer().getBlobCount(), levelManager.getInterfaceRenderer().getBoxCount());

                } catch (IOException e1) {

                    try (FileWriter writer = new FileWriter("data/highScore.txt")) {

                        levelManager.getInterfaceRenderer().setIsNew(true);
                        levelManager.getInterfaceRenderer().setScore(new int [] {levelManager.getInterfaceRenderer().getBlobCount(), levelManager.getInterfaceRenderer().getBoxCount()});

                        writer.write(levelManager.getInterfaceRenderer().getBlobCount() + "," + levelManager.getInterfaceRenderer().getBoxCount());
                        writer.flush();

                    }catch (IOException e2){

                        System.out.println("Error creating new highscore file: " + e2);

                    }

                }

                levelManager.stopLevel(LevelContext.THREE);
                levelManager.startLevel(LevelContext.END);

            }

        }

    }

    @Override
    public void postStep(StepEvent e){

        //Just a stub.//

    }

    public void writeToFile(int blobs, int boxes) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("data/highScore.txt"));

        String [] score = reader.readLine().split(",");

        levelManager.getInterfaceRenderer().setScore(new int [] {blobs, boxes});

        if (blobs <= Integer.parseInt(score[0])){

            if (blobs == Integer.parseInt(score[0])){

                if (boxes <= Integer.parseInt(score[1])){

                    levelManager.getInterfaceRenderer().setIsNew(true);

                    BufferedWriter writer = new BufferedWriter(new FileWriter("data/highScore.txt"));
                    writer.write(blobs + "," + boxes);

                    writer.close();

                }

            }else{

                levelManager.getInterfaceRenderer().setIsNew(true);

                BufferedWriter writer = new BufferedWriter(new FileWriter("data/highScore.txt"));
                writer.write(blobs + "," + boxes);

                writer.close();

            }

        }

    }

}
