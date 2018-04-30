
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Pickup extends StaticBody implements CollisionListener{

    private World world;
    private SoundClip pop;

    public Pickup(World world, Vec2 position) {

        super(world, new BoxShape(0.5f,0.5f));

        this.world = world;
        this.addImage(new BodyImage("assets/env/cristal.gif", 1));
        this.setPosition(position);
        this.addCollisionListener(this);
        this.setName("Pickup");

    }

    @Override
    public void collide(CollisionEvent e){

        if (e.getOtherBody().getName() == "Bot"){

            try {

                this.pop = new SoundClip("assets/sounds/pop.wav");
                pop.play();

            }catch (UnsupportedAudioFileException |IOException |LineUnavailableException ex){

                System.out.println("Unable to play pop sound: " + ex);

            }

            for (int x = -20; x < 20; x++){

                this.destroy();
                new FlyingCrate(this.world, new Vec2(x, 10));

            }

        }

    }


}