import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

enum EnvTexture {
    GRASS, STONE, SHADOW25, SHADOW50, SHADOW75, SHADOW100
}

public class Environment extends StaticBody{

    public Environment(World world, Vec2 position, EnvTexture texture){

        super(world, new BoxShape(0.9f, 0.9f));

        switch (texture){

            case GRASS:
                this.addImage(new BodyImage("assets/env/grass.png", 2));
                break;

            case STONE:
                this.addImage(new BodyImage("assets/env/n.png", 2));
                break;

            case SHADOW25:
                this.addImage(new BodyImage("assets/shadows/25.png", 2));
                break;

            case SHADOW50:
                this.addImage(new BodyImage("assets/shadows/50.png", 2));
                break;

            case SHADOW75:
                this.addImage(new BodyImage("assets/shadows/75.png", 2));
                break;

            case SHADOW100:
                this.addImage(new BodyImage("assets/shadows/100.png", 2));
                break;

        }

        this.setPosition(position);

    }

    public void render(World world){

        for (int x = -26; x <= 26; x++){
            Environment shadow = new Environment(world, new Vec2(x, -12), EnvTexture.GRASS);
        }

        for (int x = -26; x <= 26; x++){
            Environment shadow = new Environment(world, new Vec2(x, -14), EnvTexture.STONE);
        }

    }

}
