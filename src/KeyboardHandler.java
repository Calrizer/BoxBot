import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.FileWriter;
import java.io.IOException;
import java.security.Key;

public class KeyboardHandler extends KeyAdapter implements StepListener {

    private WorldView view;
    private World world;
    private Bot bot;

    private boolean isHeadingUp;
    private boolean isHeadingLeft;
    private boolean isHeadingRight;

    public KeyboardHandler(WorldView view, World world, Bot bot) {
        this.view = view;
        this.world = world;
        this.bot = bot;

        this.world.addStepListener(this);

    }

    @Override
    public void keyPressed(KeyEvent e) {

        char key = e.getKeyChar();

        switch (key){

            case 'w':
                isHeadingUp = true;
                break;
            case 'a':
                isHeadingLeft = true;
                break;
            case 's':
                System.out.println("s pressed.");
                bot.removeAllImages();
                bot.addImage(new BodyImage("assets/bot/4.png", 2));
                break;
            case 'd':
                isHeadingRight = true;
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e){

        char key = e.getKeyChar();

        switch (key){

            case 'w':
                isHeadingUp = false;
                break;
            case 'a':
                isHeadingLeft = false;
                break;
            case 's':

                break;
            case 'd':
                isHeadingRight = false;
                break;
        }

    }

    @Override
    public void preStep(StepEvent e){

        //System.out.println("test");

        if (isHeadingUp){
            bot.applyImpulse(new Vec2(0,25));
            bot.removeAllImages();
            bot.addImage(new BodyImage("assets/bot/2.png", 2));
        }else if (isHeadingUp == false){
            bot.removeAllImages();
            bot.addImage(new BodyImage("assets/bot/4.png", 2));
        }

        if (isHeadingLeft){
            bot.applyImpulse(new Vec2(-15,0));
            bot.removeAllImages();
            bot.addImage(new BodyImage("assets/bot/2.png", 2));
        }

        if (isHeadingRight) {
            bot.applyImpulse(new Vec2(15, 0));
            bot.removeAllImages();
            bot.addImage(new BodyImage("assets/bot/2.png", 2));
        }

    }

    @Override
    public void postStep(StepEvent e){



    }

    public void writeToFile(String data) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter("data/moveContext.txt"));
        writer.write(data);

        writer.close();

    }

}
